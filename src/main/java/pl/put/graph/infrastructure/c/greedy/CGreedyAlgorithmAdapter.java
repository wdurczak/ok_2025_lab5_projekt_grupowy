package pl.put.graph.infrastructure.c.greedy;

import com.sun.jna.Library;
import com.sun.jna.Native;
import org.springframework.stereotype.Component;
import pl.put.graph.AlgorithmResult;
import pl.put.graph.DegreesSequence;
import pl.put.graph.domain.GreedyAlgorithmPort;

import java.util.List;


@Component
public class CGreedyAlgorithmAdapter implements GreedyAlgorithmPort {

    public interface GreedyLib extends Library {
        int greedy(int n, int[] degrees);
    }

    private static final GreedyLib LIB =
            Native.load("greedy", GreedyLib.class);

    @Override
    public AlgorithmResult runGreedy(DegreesSequence sequence) {
        List<Integer> list = sequence.getDegrees();
        int n = list.size();
        int[] arr = list.stream().mapToInt(Integer::intValue).toArray();

        int value = LIB.greedy(n, arr);
        return new AlgorithmResult(value);
    }
}