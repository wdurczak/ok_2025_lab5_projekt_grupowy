package pl.put.graph.infrastructure.prolog;

import org.jpl7.Query;

import java.util.List;
import java.util.stream.Collectors;

public class PrologAdapter {

    public PrologAdapter() {

        String prologPath = "prolog/graph.pl";

        Query q = new Query("consult('" + prologPath + "')");
        if (!q.hasSolution()) {
            throw new RuntimeException("Cannot load Prolog file " + prologPath);
        }
    }

    public boolean isGraphicalSequence(List<Integer> degrees) {
        String prologList = degrees.stream()
                .map(Object::toString)
                .collect(Collectors.joining(", ", "[", "]"));

        Query q = new Query("graphical_sequence(" + prologList + ")");
        return q.hasSolution();
    }
}