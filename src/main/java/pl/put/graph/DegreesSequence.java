package pl.put.graph;

import java.util.List;

public class DegreesSequence {
    private final List<Integer> degrees;
    public DegreesSequence(List<Integer> degrees) { this.degrees = degrees; }
    public List<Integer> getDegrees() { return degrees; }
}