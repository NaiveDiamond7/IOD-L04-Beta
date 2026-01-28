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

    /** Porządek sortowania: rosnący lub malejący. Domyślnie rosnący. */
    private String direction;

    /** Ilość iteracji: wprowadzana przez użytkownika. */
    private Integer iterations;

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

    public String getDirection() {
        return direction != null ? direction : "ASC";
    }

    public Integer getIterations() {
        return iterations;
    }
}
