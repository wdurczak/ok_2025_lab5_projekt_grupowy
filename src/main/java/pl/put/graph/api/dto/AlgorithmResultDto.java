package pl.put.graph.api.dto;

import lombok.Getter;

@Getter
public class AlgorithmResultDto {
    private final int value;

    public AlgorithmResultDto(int value) {
        this.value = value;
    }

}