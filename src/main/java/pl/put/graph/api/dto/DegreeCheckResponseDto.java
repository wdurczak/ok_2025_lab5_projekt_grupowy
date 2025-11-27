package pl.put.graph.api.dto;

import lombok.Getter;

import java.util.List;

@Getter
public class DegreeCheckResponseDto {
    private final List<Integer> degrees;
    private final boolean graphical;

    public DegreeCheckResponseDto(List<Integer> degrees, boolean graphical) {
        this.degrees = degrees;
        this.graphical = graphical;
    }

}
