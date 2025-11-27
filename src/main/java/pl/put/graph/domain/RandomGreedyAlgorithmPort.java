package pl.put.graph.domain;

import pl.put.graph.AlgorithmResult;
import pl.put.graph.DegreesSequence;

public interface RandomGreedyAlgorithmPort {
    AlgorithmResult runRandomGreedy(DegreesSequence sequence);
}