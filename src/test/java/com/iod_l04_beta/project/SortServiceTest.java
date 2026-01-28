package com.iod_l04_beta.project;

import com.iod_l04_beta.project.algorithm.*;
import com.iod_l04_beta.project.service.SortAlgorithmFactory;
import com.iod_l04_beta.project.service.SortService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SortServiceTest {

    private SortService sortService;

    @BeforeEach
    void setUp() {
        // Konfiguracja fabryki i serwisu przed każdym testem
        List<SortAlgorithm<?>> algorithms = Arrays.asList(
                new BubbleSort<>(),
                new QuickSort<>(),
                new MergeSort<>(),
                new InsertionSort<>(),
                new SelectionSort<>(),
                new HeapSort<>()
        );

        SortAlgorithmFactory factory = new SortAlgorithmFactory(algorithms);
        sortService = new SortService(factory);
    }

    @Test
    @DisplayName("Should sort numbers ASCENDING by default (ASC)")
    void shouldSortNumbersAsc() {
        // given
        List<Integer> input = Arrays.asList(5, 1, 9, 3);

        // when
        // NULL jako 4 argument to brak limitu iteracji
        List<Integer> result = sortService.sort(input, "bubble", "ASC", null);

        // then
        assertEquals(Arrays.asList(1, 3, 5, 9), result);
    }

    @Test
    @DisplayName("Should sort numbers DESCENDING when direction is DESC")
    void shouldSortNumbersDesc() {

        List<Integer> input = Arrays.asList(5, 1, 9, 3);

        List<Integer> result = sortService.sort(input, "quick", "DESC", null);

        assertEquals(Arrays.asList(9, 5, 3, 1), result);
    }

    @Test
    @DisplayName("Should sort text DESCENDING when direction is DESC")
    void shouldSortTextDesc() {

        List<String> input = Arrays.asList("orange", "banana", "apple");

        List<String> result = sortService.sort(input, "merge", "DESC", null);

        assertEquals(Arrays.asList("orange", "banana", "apple"), result);
    }

    @Test
    @DisplayName("Should handle empty list correctly")
    void shouldHandleEmptyList() {

        List<Integer> input = Arrays.asList();

        List<Integer> result = sortService.sort(input, "heap", "DESC", null);

        assertTrue(result.isEmpty());
    }

    @Test
    @DisplayName("Should default to ASC when direction is null or invalid")
    void shouldDefaultToAsc() {

        List<Integer> input = Arrays.asList(2, 1);

        List<Integer> resultNull = sortService.sort(input, "insertion", null, null);
        List<Integer> resultInvalid = sortService.sort(input, "insertion", "TYPO", null);

        assertEquals(Arrays.asList(1, 2), resultNull);
        assertEquals(Arrays.asList(1, 2), resultInvalid);
    }

    @Test
    @DisplayName("Should stop sorting after specified iterations (Bubble Sort)")
    void shouldStopAfterIterations() {
        // given
        // W Bubble Sort po 1 iteracji największy element (9) musi być na końcu.
        // Reszta może nie być posortowana.
        List<Integer> input = Arrays.asList(5, 9, 1, 3);

        // when
        // Ustawiamy limit iteracji na 1
        List<Integer> result = sortService.sort(input, "bubble", "ASC", 1);

        // then
        // Sprawdzamy, czy ostatni element to 9 (największy)
        assertEquals(9, result.get(result.size() - 1));
        // Sprawdzamy, czy lista nie jest w pełni posortowana (1 jest przed 3 i 5)
        assertNotEquals(Arrays.asList(1, 3, 5, 9), result);
    }

    @Test
    @DisplayName("Should check if all algorithms handle DESC logic correctly")
    void shouldVerifyAllAlgorithmsDesc() {
        List<String> allAlgos = Arrays.asList("bubble", "heap", "insertion", "merge", "quick", "selection");
        List<Integer> input = Arrays.asList(10, 30, 20);
        List<Integer> expected = Arrays.asList(30, 20, 10);

        for (String algoName : allAlgos) {
            List<Integer> result = sortService.sort(input, algoName, "DESC", null);
            assertEquals(expected, result, "Algorithm failed for DESC: " + algoName);
        }
    }
}