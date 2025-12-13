package com.iod_l04_beta.project.dto;
import java.util.List;

public class SortRequest {
    private String type;        // "number" | "text"
    private String algorithm;   // "bubble"
    private List<Integer> numberData;
    private List<String> textData;

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
}
