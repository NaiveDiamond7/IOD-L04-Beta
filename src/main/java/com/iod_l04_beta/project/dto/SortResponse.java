package com.iod_l04_beta.project.dto;
import java.util.List;


/**
 * Odpowiedź zawierająca wynik sortowania.
 *
 * @param <T> typ danych
 */
public class SortResponse<T> {

    /** Algorytm sortowania wybrany przy automatycznym wymuszeniu */
    private final String algorithm;

    /** Czas wykonania sortowania w milisekundach */
    private final long executionTimeMs;

    /** Posortowane dane */
    private final List<T> sortedData;

    public SortResponse(String algorithm, long executionTimeMs, List<T> sortedData) {
        this.algorithm = algorithm;
        this.executionTimeMs = executionTimeMs;
        this.sortedData = sortedData;
    }
    // gettery

    public String getAlgorithm() { return algorithm; }

    public long getExecutionTimeMs() {
        return executionTimeMs;
    }

    public List<T> getSortedData() {
        return sortedData;
    }
}