package com.iod_l04_beta.project.dto;
import java.util.List;

public class SortResponse<T> {

    private long executionTimeMs;
    private List<T> sortedData;

    public SortResponse(long executionTimeMs, List<T> sortedData) {
        this.executionTimeMs = executionTimeMs;
        this.sortedData = sortedData;
    }

    public long getExecutionTimeMs() {
        return executionTimeMs;
    }

    public List<T> getSortedData() {
        return sortedData;
    }
}