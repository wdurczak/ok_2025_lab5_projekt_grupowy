package pl.put.graph.api.dto;

import lombok.Data;

import java.util.List;

@Data
public class GenerateResponseDto {
    private java.util.List<Integer> degrees;

    public GenerateResponseDto(List<Integer> degrees) {
        this.degrees = degrees;
    }

}

