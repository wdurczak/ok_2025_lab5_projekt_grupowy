package pl.put.graph.services;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GenerateDegreesService {

    private final Random random = new Random();

    public List<Integer> generate(int n, double p) {
        int[] deg = new int[n];

        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if (random.nextDouble() < p) {
                    deg[i]++;
                    deg[j]++;
                }
            }
        }

        List<Integer> result = new ArrayList<>(n);
        for (int d : deg) {
            result.add(d);
        }
        return result;
    }
}