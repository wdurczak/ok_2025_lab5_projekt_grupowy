package pl.put.graph.domain;

import pl.put.graph.DegreeCheckResult;
import pl.put.graph.DegreesSequence;

public interface DegreeSequenceCheckerPort {
    DegreeCheckResult check(DegreesSequence sequence);
}