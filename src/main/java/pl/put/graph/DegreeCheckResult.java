package pl.put.graph;

import lombok.Data;
import lombok.Getter;

import java.util.List;

@Data
public class DegreeCheckResult {
    private final List<Integer> degrees;
    private final boolean graphical;
    public DegreeCheckResult(List<Integer> degrees, boolean graphical) {
        this.degrees = degrees;
        this.graphical = graphical;
    }
}