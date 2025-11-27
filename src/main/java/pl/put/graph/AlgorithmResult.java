package pl.put.graph;

import lombok.Data;

@Data
public class AlgorithmResult {
    private final int value;

    public AlgorithmResult(int value) {
        this.value = value;
    }
}
