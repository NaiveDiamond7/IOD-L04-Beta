package com.iod_l04_beta.project.algorithm;
import java.util.List;

import org.springframework.stereotype.Component;

/**
 * Implementacja algorytmu sortowania bąbelkowego (Bubble Sort).
 *
 * Algorytm o złożoności czasowej O(n²),
 * przeznaczony głównie do celów edukacyjnych.
 */

@Component
public class BubbleSort<T extends Comparable<T>> implements SortAlgorithm<T> {

    @Override
    public String getName() {
        return "bubble";
    }

    /**
     * Sortuje listę metodą bąbelkową.
     * Ala ma kota
     */

    @Override
    public void sort(List<T> data) {
        int n = data.size();
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (data.get(j).compareTo(data.get(j + 1)) > 0) {
                    T temp = data.get(j);
                    data.set(j, data.get(j + 1));
                    data.set(j + 1, temp);
                }
            }
        }
    }
}