package pl.put.graph.infrastructure.c.greedy;

import com.sun.jna.Library;
import com.sun.jna.Native;

public interface GreedyLibrary extends Library {
    GreedyLibrary INSTANCE = Native.load("greedy", GreedyLibrary.class);

    int runGreedy(int n, int[] degrees);
}