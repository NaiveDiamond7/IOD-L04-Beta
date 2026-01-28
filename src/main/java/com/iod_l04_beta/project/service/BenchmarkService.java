package com.iod_l04_beta.project.service;

import com.iod_l04_beta.project.algorithm.SortAlgorithm;
import com.iod_l04_beta.project.dto.BenchmarkResult;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;


/**
 * Serwis odpowiedzialny za benchmark algorytmów sortowania.
 */
@Service
public class BenchmarkService {
    private final SortAlgorithmFactory factory;

    public BenchmarkService(SortAlgorithmFactory factory) {
        this.factory = factory;
    }


    /**
     * Wykonuje benchmark dla wszystkich algorytmów sortowania.
     *
     * @param data dane wejściowe
     * @param <T>  typ danych
     * @return lista wyników benchmarku
     */
    public <T extends Comparable<T>> List<BenchmarkResult<T>> benchmark(List<T> data, String direction) {

        List<BenchmarkResult<T>> results = new ArrayList<>();
        Map<String, SortAlgorithm<?>> algorithms = factory.getAllAlgorithms();
        boolean isDesc = "DESC".equalsIgnoreCase(direction);

        for (Map.Entry<String, SortAlgorithm<?>> entry : algorithms.entrySet()) {

            @SuppressWarnings("unchecked")
            SortAlgorithm<T> algorithm = (SortAlgorithm<T>) entry.getValue();

            List<T> copy = new ArrayList<>(data);

            long start = System.nanoTime();
            algorithm.sort(copy);

            if (isDesc) {
                Collections.reverse(copy);
            }

            long end = System.nanoTime();

            results.add(
                    new BenchmarkResult<>(
                            entry.getKey(),
                            (end - start) / 1_000_000,
                            copy
                    )
            );
        }

        return results;
    }
}
