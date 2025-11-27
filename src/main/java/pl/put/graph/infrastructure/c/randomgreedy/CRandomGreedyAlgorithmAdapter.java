package pl.put.graph.infrastructure.c.randomgreedy;

import com.sun.jna.Library;
import com.sun.jna.Native;
import org.springframework.stereotype.Component;
import pl.put.graph.AlgorithmResult;
import pl.put.graph.DegreesSequence;
import pl.put.graph.domain.RandomGreedyAlgorithmPort;

import java.util.List;

@Component
public class CRandomGreedyAlgorithmAdapter implements RandomGreedyAlgorithmPort {

    public interface RandomGreedyLib extends Library {
        int random_greedy(int n, int[] degrees);
    }

    private static final RandomGreedyLib LIB =
            Native.load("greedy", RandomGreedyLib.class);

    @Override
    public AlgorithmResult runRandomGreedy(DegreesSequence sequence) {
        List<Integer> list = sequence.getDegrees();
        int n = list.size();
        int[] arr = list.stream().mapToInt(Integer::intValue).toArray();

        int value = LIB.random_greedy(n, arr);
        return new AlgorithmResult(value);
    }
}