package pl.put.graph.infrastructure.prolog;

import pl.put.graph.DegreeCheckResult;
import pl.put.graph.DegreesSequence;
import pl.put.graph.domain.DegreeSequenceCheckerPort;
import org.springframework.stereotype.Component;

@Component
public class PrologDegreeSequenceCheckerAdapter implements DegreeSequenceCheckerPort {

    private final PrologAdapter prolog = new PrologAdapter();

    @Override
    public DegreeCheckResult check(DegreesSequence seq) {
        boolean graphical = prolog.isGraphicalSequence(seq.getDegrees());
        return new DegreeCheckResult(seq.getDegrees(), graphical);
    }
}