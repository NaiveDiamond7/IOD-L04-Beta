package com.iod_l04_beta.project.dto;
import java.util.List;

/**
 * Obiekt reprezentujący żądanie sortowania.
 */
public class SortRequest {
    /** Typ danych: "number" lub "text" */
    private String type;

    /** Nazwa algorytmu sortowania */
    private String algorithm;

    /** Porządek sortowania: rosnący lub malejący. Domyślnie rosnący. */
    private String direction;

    /** Ilość iteracji: wprowadzana przez użytkownika. */
    private Integer iterations;

    /** Dane liczbowe do sortowania */
    private List<Integer> numberData;

    /** Dane tekstowe do sortowania */
    private List<String> textData;

    // gettery
    public String getType() {
        return type;
    }

    public String getAlgorithm() {
        return algorithm;
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
