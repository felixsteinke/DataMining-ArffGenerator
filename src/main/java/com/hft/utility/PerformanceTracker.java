package com.hft.utility;

import java.time.Duration;
import java.time.Instant;

public class PerformanceTracker {
    private Instant start;

    public void start() {
        this.start = Instant.now();
    }

    public void stop() {
        Instant end = Instant.now();
        long minutes = Duration.between(start, end).toMinutes();
        long seconds = Duration.between(start, end).toSecondsPart();
        long millis = Duration.between(start, end).toMillisPart();
        System.out.println("Es sind seit Start: " + minutes + "min " + seconds + "s " + millis + "millis vergangen.");
    }
}
