package com.iod_l04_beta.project.algorithm;

import org.springframework.stereotype.Component;
import java.util.List;

/**
 * Implementacja algorytmu Heap Sort.
 *
 * Złożoność czasowa: O(n log n)
 *
 * @param <T> typ danych porównywalnych
 */
@Component
public class HeapSort<T extends Comparable<T>> implements SortAlgorithm<T> {


    /**
     * @return nazwa algorytmu używana w API ("heap")
     */
    @Override
    public String getName() {
        return "heap";
    }


    /**
     * Sortuje dane rosnąco przy użyciu algorytmu Heap Sort.
     *
     * @param data lista danych do posortowania
     */
    @Override
    public void sort(List<T> data, Integer maxIterations) {
        int n = data.size();

        // Budowanie kopca
        for (int i = n / 2 - 1; i >= 0; i--) {
            heapify(data, n, i);
        }

        int currentIteration = 0;

        // Wyciąganie elementów z kopca
        for (int i = n - 1; i > 0; i--) {

            if (maxIterations != null && currentIteration >= maxIterations) {
                return;
            }
            currentIteration++;

            swap(data, 0, i);
            heapify(data, i, 0);
        }
    }

    /**
     * Przywraca własność kopca maksymalnego.
     *
     * @param data     lista danych
     * @param heapSize aktualny rozmiar kopca
     * @param root     indeks korzenia
     */
    private void heapify(List<T> data, int heapSize, int root) {
        int largest = root;
        int left = 2 * root + 1;
        int right = 2 * root + 2;

        if (left < heapSize && data.get(left).compareTo(data.get(largest)) > 0) {
            largest = left;
        }

        if (right < heapSize && data.get(right).compareTo(data.get(largest)) > 0) {
            largest = right;
        }

        if (largest != root) {
            swap(data, root, largest);
            heapify(data, heapSize, largest);
        }
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
