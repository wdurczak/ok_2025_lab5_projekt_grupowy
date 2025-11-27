package pl.put.graph.infrastructure.c.exact;

import com.sun.jna.Library;
import com.sun.jna.Native;

public interface ExactLibrary extends Library {
    ExactLibrary INSTANCE = Native.load("exact", ExactLibrary.class);

    int runExact(int n, int[] degrees);
}