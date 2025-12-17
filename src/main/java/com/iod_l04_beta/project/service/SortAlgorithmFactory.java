package com.iod_l04_beta.project.service;

import com.iod_l04_beta.project.algorithm.SortAlgorithm;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;


/**
 * Fabryka algorytmów sortowania.
 *
 * <p>Odpowiada za:
 * <ul>
 *     <li>dynamiczne wykrywanie algorytmów oznaczonych {@code @Component}</li>
 *     <li>udostępnianie algorytmu na podstawie jego nazwy</li>
 * </ul>
 */
@Component
public class SortAlgorithmFactory {
    private final Map<String, SortAlgorithm<?>> algorithms = new HashMap<>();

    public SortAlgorithmFactory(List<SortAlgorithm<?>> algorithmList) {
        for (SortAlgorithm<?> algorithm : algorithmList) {
            algorithms.put(algorithm.getName(), algorithm);
        }
    }


    /**
     * Zwraca algorytm sortowania o podanej nazwie.
     *
     * @param name nazwa algorytmu (np. "heap", "insertion")
     * @param <T>  typ danych
     * @return implementacja algorytmu sortowania
     */
    @SuppressWarnings("unchecked")
    public <T extends Comparable<T>> SortAlgorithm<T> getAlgorithm(String name) {
        SortAlgorithm<T> algorithm = (SortAlgorithm<T>) algorithms.get(name);
        if (algorithm == null) {
            throw new IllegalArgumentException("Unknown algorithm: " + name);
        }
        return algorithm;
    }


    /**
     * @return lista dostępnych algorytmów sortowania
     */
    public Set<String> getAvailableAlgorithmNames() {
        return algorithms.keySet();
    }

    public Map<String, SortAlgorithm<?>> getAllAlgorithms() {
        return algorithms;
    }
}
