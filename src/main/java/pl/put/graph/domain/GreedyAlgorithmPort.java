package pl.put.graph.domain;


import pl.put.graph.AlgorithmResult;
import pl.put.graph.DegreesSequence;

public interface GreedyAlgorithmPort {
    AlgorithmResult runGreedy(DegreesSequence seq);
}