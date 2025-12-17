package com.iod_l04_beta.project.dto;
import java.util.List;

/**
 * Wynik benchmarku pojedynczego algorytmu sortowania.
 */

public class BenchmarkResult<T> {

    /**Wynik benchmarku pojedynczego algorytmu sortowania.*/
    private String algorithm;

    /** Czas wykonania algorytmu w milisekundach */
    private long executionTimeMs;

    private List<T> sortedData;

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
