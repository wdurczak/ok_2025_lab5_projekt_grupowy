package pl.put.graph.infrastructure.c.exact;

import com.sun.jna.Library;
import com.sun.jna.Native;
import pl.put.graph.AlgorithmResult;
import pl.put.graph.DegreesSequence;
import pl.put.graph.domain.ExactAlgorithmPort;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CExactAlgorithmAdapter implements ExactAlgorithmPort {

    public interface ExactLib extends Library {
        int exact(int n, int[] degrees);
    }

    private static final ExactLib LIB =
            Native.load("exact", ExactLib.class);

    @Override
    public AlgorithmResult runExact(DegreesSequence sequence) {
        List<Integer> list = sequence.getDegrees();
        int n = list.size();
        int[] arr = list.stream().mapToInt(Integer::intValue).toArray();

        int value = LIB.exact(n, arr);
        return new AlgorithmResult(value);
    }
}