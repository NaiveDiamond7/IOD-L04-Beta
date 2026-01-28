package com.iod_l04_beta.project.algorithm;

import org.springframework.stereotype.Component;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Implementacja algorytmu Merge Sort.
 *
 * Stabilny algorytm sortowania o złożoności O(n log n).
 */

@Component
public class MergeSort<T extends Comparable<T>> implements SortAlgorithm<T> {

    @Override
    public String getName() {
        return "merge";
    }


    /**
     * Sortuje listę przy użyciu algorytmu Merge Sort.
     */
    @Override
    public void sort(List<T> data, Integer maxIterations) {
        if (data.size() <= 1) return;
        List<T> sorted = mergeSort(data, new AtomicInteger(0), maxIterations);
        data.clear();
        data.addAll(sorted);
    }


    /**
     * Rekurencyjnie dzieli listę na mniejsze części.
     */
    private List<T> mergeSort(List<T> data, AtomicInteger counter, Integer maxIterations) {

        if (maxIterations != null && counter.get() >= maxIterations) {
            return data;
        }

        if (data.size() <= 1) {
            return data;
        }

        counter.incrementAndGet();

        int mid = data.size() / 2;
        List<T> left = mergeSort(new ArrayList<>(data.subList(0, mid)), counter, maxIterations);
        List<T> right = mergeSort(new ArrayList<>(data.subList(mid, data.size())), counter, maxIterations);

        return merge(left, right);
    }


    /**
     * Scala dwie posortowane listy w jedną.
     */
    private List<T> merge(List<T> left, List<T> right) {
        List<T> result = new ArrayList<>();
        int i = 0, j = 0;

        while (i < left.size() && j < right.size()) {
            if (left.get(i).compareTo(right.get(j)) <= 0) {
                result.add(left.get(i++));
            } else {
                result.add(right.get(j++));
            }
        }

        while (i < left.size()) {
            result.add(left.get(i++));
        }

        while (j < right.size()) {
            result.add(right.get(j++));
        }

        return result;
    }
}
