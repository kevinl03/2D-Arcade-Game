package Helpers;

import java.time.Duration;
import java.time.Instant;

public class Stopwatch {
    private Instant start;
    private Instant stop;

    public void startTime(){
        start = Instant.now();
    }

    public Duration getElapsedTime(){
        return Duration.between(start, Instant.now());
    }

    public void stopTime(){
        stop = Instant.now();
    }

    public Duration getFinalTime(){
        return Duration.between(start, stop);
    }

}
