package com.iod_l04_beta.project.service;

import com.iod_l04_beta.project.algorithm.SortAlgorithm;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


/**
 * Serwis odpowiedzialny za delegowanie sortowania
 * do odpowiedniego algorytmu.
 */
@Service
public class SortService {
    private final SortAlgorithmFactory factory;

    public SortService(SortAlgorithmFactory factory) {
        this.factory = factory;
    }


    /**
     * Sortuje dane przy użyciu wybranego algorytmu.
     *
     * @param data dane wejściowe
     * @param algorithmName nazwa algorytmu
     * @return posortowana lista
     */
    public <T extends Comparable<T>> List<T> sort(
            List<T> data,
            String algorithmName
    ) {
        List<T> copy = new ArrayList<>(data);
        SortAlgorithm<T> algorithm = factory.getAlgorithm(algorithmName);
        algorithm.sort(copy);
        return copy;
    }
}
