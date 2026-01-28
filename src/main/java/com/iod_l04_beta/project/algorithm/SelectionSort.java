package com.iod_l04_beta.project.algorithm;

import org.springframework.stereotype.Component;
import java.util.List;


/**
 * Implementacja algorytmu Selection Sort.
 *
 *Złożoność czasowa: O(n²)
 *
 * @param <T> typ danych porównywalnych
 */

@Component
public class SelectionSort<T extends Comparable<T>> implements SortAlgorithm<T> {


    /**
     * @return nazwa algorytmu używana w API ("selection")
     */
    @Override
    public String getName() {
        return "selection";
    }


    /**
     * Sortuje dane rosnąco przy użyciu algorytmu Selection Sort.
     *
     * @param data lista danych do posortowania
     * @param maxIterations ilość iteracji algorytmu
     */
    @Override
    public void sort(List<T> data, Integer maxIterations) {
        int n = data.size();

        for (int i = 0; i < n - 1; i++) {

            if (maxIterations != null && i >= maxIterations) {
                return;
            }

            int minIndex = i;
            for (int j = i + 1; j < n; j++) {
                if (data.get(j).compareTo(data.get(minIndex)) < 0) {
                    minIndex = j;
                }
            }
            swap(data, i, minIndex);
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
