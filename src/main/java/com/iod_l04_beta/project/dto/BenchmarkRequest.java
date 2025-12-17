package com.iod_l04_beta.project.dto;
import java.util.List;

/**
 * Obiekt żądania benchmarku algorytmów sortowania.
 *
 * <p>Umożliwia wykonanie testów wydajności
 * dla wszystkich dostępnych algorytmów sortowania.
 */

public class BenchmarkRequest {

    /** Typ danych: "number" lub "text" */
    private String type;

    line that brakes things!

    /** Dane liczbowe do testów benchmarku */
    private List<Integer> numberData;

    /** Dane tekstowe do testów benchmarku */
    private List<String> textData;

    public String getType() {
        return type;
    }

    public List<Integer> getNumberData() {
        return numberData;
    }

    public List<String> getTextData() {
        return textData;
    }
}
