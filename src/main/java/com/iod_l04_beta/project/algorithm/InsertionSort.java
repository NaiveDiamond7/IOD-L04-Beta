package com.iod_l04_beta.project.algorithm;

import org.springframework.stereotype.Component;
import java.util.List;


/**
 * Implementacja algorytmu Insertion Sort.
 *
 *  <p>Złożoność czasowa:
 *  <ul>
 *      <li>najlepszy przypadek: O(n) – dane już posortowane</li>
 *      <li>średni przypadek: O(n²)</li>
 *  </ul>
 *
 * @param <T> typ danych porównywalnych
 */
@Component
public class InsertionSort<T extends Comparable<T>> implements SortAlgorithm<T> {


    /**
     * @return nazwa algorytmu używana w API ("insertion")
     */
    @Override
    public String getName() {
        return "insertion";
    }


    /**
     * Sortuje dane rosnąco przy użyciu algorytmu Insertion Sort.
     *
     * @param data lista danych do posortowania
     */
    @Override
    public void sort(List<T> data) {
        for (int i = 1; i < data.size(); i++) {
            T key = data.get(i);
            int j = i - 1;

            while (j >= 0 && data.get(j).compareTo(key) > 0) {
                data.set(j + 1, data.get(j));
                j--;
            }
            data.set(j + 1, key);
        }
    }
}
