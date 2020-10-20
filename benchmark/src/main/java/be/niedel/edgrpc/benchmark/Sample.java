package be.niedel.edgrpc.benchmark;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Fork;
import org.openjdk.jmh.annotations.Mode;

import java.util.stream.Stream;

public class Sample {

    @Benchmark
    @Fork(value = 1, warmups = 2)
    @BenchmarkMode(Mode.AverageTime)
    public void doWork() {
        for(int i = 0; i < 2; i++) {
            System.out.println("Nee: " + i);
        }
    }

}
