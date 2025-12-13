package com.iod_l04_beta.project.service;

import com.iod_l04_beta.project.algorithm.SortAlgorithm;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Component
public class SortAlgorithmFactory {
    private final Map<String, SortAlgorithm<?>> algorithms = new HashMap<>();

    public SortAlgorithmFactory(List<SortAlgorithm<?>> algorithmList) {
        for (SortAlgorithm<?> algorithm : algorithmList) {
            algorithms.put(algorithm.getName(), algorithm);
        }
    }

    @SuppressWarnings("unchecked")
    public <T extends Comparable<T>> SortAlgorithm<T> getAlgorithm(String name) {
        SortAlgorithm<T> algorithm = (SortAlgorithm<T>) algorithms.get(name);
        if (algorithm == null) {
            throw new IllegalArgumentException("Unknown algorithm: " + name);
        }
        return algorithm;
    }

    public Set<String> getAvailableAlgorithmNames() {
        return algorithms.keySet();
    }

    public Map<String, SortAlgorithm<?>> getAllAlgorithms() {
        return algorithms;
    }
}
