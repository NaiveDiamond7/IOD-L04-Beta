package com.iod_l04_beta.project.dto;
import java.util.List;

public class BenchmarkResult<T> {
    private final String algorithm;
    private final long executionTimeMs;
    private final List<T> sortedData;

    public BenchmarkResult(String algorithm, long executionTimeMs, List<T> sortedData) {
        this.algorithm = algorithm;
        this.executionTimeMs = executionTimeMs;
        this.sortedData = sortedData;
    }

    public String getAlgorithm() {
        return algorithm;
    }

    public long getExecutionTimeMs() {
        return executionTimeMs;
    }

    public List<T> getSortedData() {
        return sortedData;
    }
}
