package com.iod_l04_beta.project.algorithm;
import java.util.List;


/**
 * Wspólny interfejs dla wszystkich algorytmów sortowania.
 *
 * @param <T> typ elementów do sortowania, musi implementować Comparable
 */

public interface SortAlgorithm<T extends Comparable<T>> {

    /**
     * Zwraca nazwę algorytmu wykorzystywaną np. w API.
     *
     * @return nazwa algorytmu (np. "quick", "bubble")
     */
    String getName();


    /**
     * Sortuje przekazaną listę danych rosnąco.
     *
     * @param data lista danych do posortowania
     */
    default void sort(List<T> data) {
        sort(data, null);
    };

    /**
     * Sortuje listę z opcjonalnym limitem iteracji/kroków.
     * @param data dane do posortowania
     * @param maxIterations maksymalna liczba kroków (null = brak limitu)
     */
    void sort(List<T> data, Integer maxIterations);
}