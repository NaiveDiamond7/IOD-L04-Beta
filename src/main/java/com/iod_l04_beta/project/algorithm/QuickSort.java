package com.iod_l04_beta.project.algorithm;
import java.util.List;

import org.springframework.stereotype.Component;

/**
 * Implementacja algorytmu Quick Sort.
 *
 * Średnia złożoność czasowa: O(n log n),
 * w najgorszym przypadku: O(n²).
 */

@Component
public class QuickSort<T extends Comparable<T>> implements SortAlgorithm<T> {

    @Override
    public String getName() {
        return "quick";
    }


    /**
     * Sortuje listę przy użyciu algorytmu Quick Sort.
     */

    @Override
    public void sort(List<T> data) {
        quickSort(data, 0, data.size() - 1);
    }


    /**
     * Rekurencyjna implementacja Quick Sort.
     */
    private void quickSort(List<T> data, int low, int high) {
        if (low < high) {
            int pivotIndex = partition(data, low, high);
            quickSort(data, low, pivotIndex - 1);
            quickSort(data, pivotIndex + 1, high);
        }
    }


    /**
     * Dzieli listę względem elementu pivot.
     */
    private int partition(List<T> data, int low, int high) {
        T pivot = data.get(high);
        int i = low - 1;

        for (int j = low; j < high; j++) {
            if (data.get(j).compareTo(pivot) <= 0) {
                i++;
                swap(data, i, j);
            }
        }
        swap(data, i + 1, high);
        return i + 1;
    }


    /**
     * Zamienia miejscami dwa elementy listy.
     */
    private void swap(List<T> data, int i, int j) {
        T temp = data.get(i);
        data.set(i, data.get(j));
        data.set(j, temp);
    }
}
