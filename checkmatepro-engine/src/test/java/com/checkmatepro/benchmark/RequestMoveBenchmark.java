package com.checkmatepro.benchmark;

import com.checkmatepro.game.GameFactory;
import com.checkmatepro.game.IGameInterface;
import com.checkmatepro.model.BoardPosition;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Fork;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Timeout;
import org.openjdk.jmh.annotations.Warmup;

import java.util.concurrent.TimeUnit;

public class RequestMoveBenchmark
{
    @Benchmark
    @BenchmarkMode(Mode.AverageTime)
    @OutputTimeUnit(TimeUnit.MILLISECONDS)
    @Fork(value = 1, warmups = 1)
    @Timeout(time = 1, timeUnit = TimeUnit.MINUTES)
    @Warmup(iterations = 2)
    public void benchmark_ComplexBoard()
    {
        IGameInterface game = new GameFactory().newClassicCustomGame("R,3,4,W/P,3,6,B/R,2,4,W/P,2,6,W/P,4,2,W/P,7,0,B/Q,3,2,W/P,1,2,B");

        game.requestMove(new BoardPosition(3, 2), new BoardPosition(6, 5));
    }

    @Benchmark
    @BenchmarkMode(Mode.AverageTime)
    @OutputTimeUnit(TimeUnit.MILLISECONDS)
    @Fork(value = 1, warmups = 1)
    @Timeout(time = 1, timeUnit = TimeUnit.MINUTES)
    @Warmup(iterations = 2)
    public void benchmark_EmptyBoard()
    {
        IGameInterface game = new GameFactory().newClassicCustomGame("Q,3,2,W");

        game.requestMove(new BoardPosition(3, 2), new BoardPosition(6, 5));
    }
}
