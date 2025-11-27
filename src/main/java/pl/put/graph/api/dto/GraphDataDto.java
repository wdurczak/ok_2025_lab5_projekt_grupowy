package pl.put.graph.api.dto;

import lombok.Getter;

import java.util.List;

@Getter
public class GraphDataDto {
    private int n;
    private List<int[]> edges;

    public GraphDataDto(int n, List<int[]> edges) {
        this.n = n;
        this.edges = edges;
    }

}