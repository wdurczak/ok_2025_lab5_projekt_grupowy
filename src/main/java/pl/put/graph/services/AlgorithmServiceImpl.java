package pl.put.graph.services;

import pl.put.graph.api.dto.GraphDataDto;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

@Service
public class AlgorithmServiceImpl implements AlgorithmService {

    private static final String EXACT_C_BIN = "./exact";
    private static final String GREEDY_C_BIN = "./greedy";
    private static final String GENERATOR_C_BIN = "./generator";
    private static final String EXACT_PY_SCRIPT = "./exact.py";


    @Override
    public int runExactC(List<Integer> degrees) {
        return runIntAlgorithmWithBinary(EXACT_C_BIN, degrees);
    }

    @Override
    public int runGreedyC(List<Integer> degrees) {
        return runIntAlgorithmWithBinary(GREEDY_C_BIN, degrees);
    }

    @Override
    public int runExactPython(List<Integer> degrees) {
        return runIntAlgorithmWithPython(EXACT_PY_SCRIPT, degrees);
    }



    @Override
    public GraphDataDto generateGraphC(int n, double p) {
        try {
            ProcessBuilder pb = new ProcessBuilder(GENERATOR_C_BIN);
            Process process = pb.start();

            try (BufferedWriter writer = new BufferedWriter(
                    new OutputStreamWriter(process.getOutputStream()))) {
                writer.write(n + " " + p + "\n");
                writer.flush();
            }

            String json = readAll(process.getInputStream());
            int exit = process.waitFor();
            if (exit != 0) {
                throw new IllegalStateException("generator exit " + exit);
            }

            return parseGraphFromJson(json);
        } catch (Exception e) {
            throw new RuntimeException("Error running C generator", e);
        }
    }


    private int runIntAlgorithmWithBinary(String binPath, List<Integer> degrees) {
        try {
            Process process = getProcess(binPath, degrees);

            BufferedReader br = new BufferedReader(
                    new InputStreamReader(process.getInputStream()));
            String line = br.readLine();
            int exit = process.waitFor();
            if (exit != 0) {
                throw new IllegalStateException(binPath + " exit " + exit);
            }
            return Integer.parseInt(line.trim());
        } catch (Exception e) {
            throw new RuntimeException("Error running " + binPath, e);
        }
    }

    @NotNull
    private static Process getProcess(String binPath, List<Integer> degrees) throws IOException {
        ProcessBuilder pb = new ProcessBuilder(binPath);
        Process process = pb.start();

        try (BufferedWriter writer = new BufferedWriter(
                new OutputStreamWriter(process.getOutputStream()))) {
            writer.write(degrees.size() + "\n");
            for (int i = 0; i < degrees.size(); i++) {
                writer.write(degrees.get(i).toString());
                if (i < degrees.size() - 1) writer.write(" ");
            }
            writer.write("\n");
            writer.flush();
        }
        return process;
    }

    private int runIntAlgorithmWithPython(String scriptPath, List<Integer> degrees) {
        try {
            ProcessBuilder pb = new ProcessBuilder("python3", scriptPath);
            Process process = pb.start();

            try (BufferedWriter writer = new BufferedWriter(
                    new OutputStreamWriter(process.getOutputStream()))) {
                writer.write(degrees.size() + "\n");
                for (int i = 0; i < degrees.size(); i++) {
                    writer.write(degrees.get(i).toString());
                    if (i < degrees.size() - 1) writer.write(" ");
                }
                writer.write("\n");
                writer.flush();
            }

            BufferedReader br = new BufferedReader(
                    new InputStreamReader(process.getInputStream()));
            String line = br.readLine();
            int exit = process.waitFor();
            if (exit != 0) {
                throw new IllegalStateException("python exact exit " + exit);
            }
            return Integer.parseInt(line.trim());
        } catch (Exception e) {
            throw new RuntimeException("Error running Python script", e);
        }
    }

    private String readAll(InputStream is) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(is));
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = br.readLine()) != null) {
            sb.append(line);
        }
        return sb.toString();
    }

    private GraphDataDto parseGraphFromJson(String json) {
        String nStr = json.replaceAll(".*\"n\":(\\d+).*", "$1");
        int n = Integer.parseInt(nStr);

        String edgesPart = json.replaceAll(".*\"edges\":\\[(.*)]}.*", "$1");
        List<int[]> edges = new ArrayList<>();
        if (!edgesPart.trim().isEmpty()) {
            String[] pairs = edgesPart.split("\\],\\[");
            for (String pair : pairs) {
                String cleaned = pair.replace("[", "").replace("]", "");
                String[] nums = cleaned.split(",");
                int u = Integer.parseInt(nums[0]);
                int v = Integer.parseInt(nums[1]);
                edges.add(new int[]{u, v});
            }
        }
        return new GraphDataDto(n, edges);
    }
}