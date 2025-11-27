package pl.put.graph.api.dto;

import lombok.Data;

import java.util.List;

@Data
public class DegreesRequestDto {
    private List<Integer> degrees;

}