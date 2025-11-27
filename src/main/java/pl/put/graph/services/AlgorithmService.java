package pl.put.graph.services;

import pl.put.graph.api.dto.GraphDataDto;

import java.util.List;

public interface AlgorithmService {

    int runExactC(List<Integer> degrees);

    int runGreedyC(List<Integer> degrees);

    int runExactPython(List<Integer> degrees);

    GraphDataDto generateGraphC(int n, double p);
}