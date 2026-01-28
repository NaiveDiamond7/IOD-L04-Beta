package com.iod_l04_beta.project;

import com.iod_l04_beta.project.dto.BenchmarkRequest;
import com.iod_l04_beta.project.dto.SortRequest;
import com.iod_l04_beta.project.dto.SortResponse;
import com.iod_l04_beta.project.service.BenchmarkService;
import com.iod_l04_beta.project.service.RequestDispatcherService;
import com.iod_l04_beta.project.service.SortService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class RequestDispatcherServiceTest {

    private RequestDispatcherService dispatcher;
    private SortService sortService;
    private BenchmarkService benchmarkService;

    @BeforeEach
    void setUp() {
        // Tworzymy mocki serwisów zależnych
        sortService = Mockito.mock(SortService.class);
        benchmarkService = Mockito.mock(BenchmarkService.class);

        // Wstrzykujemy mocki do testowanej klasy
        dispatcher = new RequestDispatcherService(sortService, benchmarkService);
    }

    @Test
    @DisplayName("powinien zostac wybrany algorytm INSERTION kiedy 'auto' jako algorytm i maly zestaw danych (< 64)")
    void shouldSelectInsertionForSmallData() { // mock1
        // given
        SortRequest request = Mockito.mock(SortRequest.class);
        List<Integer> smallList = Arrays.asList(1, 5, 2);

        when(request.getType()).thenReturn("number");
        when(request.getAlgorithm()).thenReturn("auto");
        when(request.getNumberData()).thenReturn(smallList);
        when(sortService.<Integer>sort(any(), any(), any(), any())).thenReturn(smallList);

        // when
        dispatcher.sort(request);

        // then
        verify(sortService).sort(
                eq(smallList),
                eq("insertion"), // "auto" ma sie zmienic na "insertion"
                any(),
                any()
        );
    }

    @Test
    @DisplayName("powinien zostac wybrany algorytm QUICK kiedy 'auto' jako algorytm i duzy zestaw danych (< 64)")
    void shouldSelectQuickForLargeData() { // mock2
        // given
        SortRequest request = Mockito.mock(SortRequest.class);

        // Generujemy listę 100 elementów
        List<Integer> largeList = new ArrayList<>(Collections.nCopies(100, 1));

        when(request.getType()).thenReturn("number");
        when(request.getAlgorithm()).thenReturn("auto");
        when(request.getNumberData()).thenReturn(largeList);
        when(sortService.<Integer>sort(any(), any(), any(), any())).thenReturn(largeList);

        // when
        dispatcher.sort(request);

        // then
        verify(sortService).sort(
                eq(largeList),
                eq("quick"), // Oczekujemy, że "auto" zmieniło się na "quick"
                any(),
                any()
        );
    }

    @Test
    @DisplayName("algorytm nie jest wybierany 'auto' gdy user podal go z reki")
    void shouldPassExplicitAlgorithmName() { // mock3
        // given
        SortRequest request = Mockito.mock(SortRequest.class);
        List<Integer> list = Arrays.asList(1, 2, 3);

        when(request.getType()).thenReturn("number");
        when(request.getAlgorithm()).thenReturn("heap"); // chcemy konkretnie "heap"
        when(request.getNumberData()).thenReturn(list);
        when(sortService.<Integer>sort(any(), any(), any(), any())).thenReturn(list);

        // when
        dispatcher.sort(request);

        // then
        verify(sortService).sort(
                eq(list),
                eq("heap"), // Nazwa nie powinna się zmienić
                any(),
                any()
        );
    }

    @Test
    @DisplayName("powinien zwrocic SortResponse zawierajacy nazwe wybranego algorytmu")
    void shouldReturnCorrectResponseWrapper() {
        // given
        SortRequest request = Mockito.mock(SortRequest.class);
        List<Integer> list = Arrays.asList(1, 2);

        when(request.getType()).thenReturn("number");
        when(request.getAlgorithm()).thenReturn("auto");
        when(request.getNumberData()).thenReturn(list);
        when(sortService.<Integer>sort(any(), any(), any(), any())).thenReturn(list);

        // when
        Object result = dispatcher.sort(request);

        // then
        assertTrue(result instanceof SortResponse);
        SortResponse<?> response = (SortResponse<?>) result;
        assertEquals("insertion", response.getAlgorithm());
    }

    @Test
    @DisplayName("powienien zwrocic exception przy podanym blednym typie danych")
    void shouldThrowExceptionForInvalidType() {
        // given
        SortRequest request = Mockito.mock(SortRequest.class);
        when(request.getType()).thenReturn("image"); // Nieobsługiwany typ

        // when n then
        assertThrows(IllegalArgumentException.class, () -> dispatcher.sort(request));
    }

    @Test
    @DisplayName("powinien wydac benchmark request dla wybranego typu number")
    void shouldDispatchBenchmarkForNumbers() {
        // given
        BenchmarkRequest request = Mockito.mock(BenchmarkRequest.class);
        List<Integer> data = Arrays.asList(1, 2, 3);

        when(request.getType()).thenReturn("number");
        when(request.getNumberData()).thenReturn(data);
        when(request.getDirection()).thenReturn("ASC");

        // when
        dispatcher.benchmark(request);

        // then
        verify(benchmarkService).benchmark(eq(data), eq("ASC")); // mock4
    }

    @Test
    @DisplayName("Spowinien wydac benchmark request dla wybranego typu text")
    void shouldDispatchBenchmarkForText() {
        // given
        BenchmarkRequest request = Mockito.mock(BenchmarkRequest.class);
        List<String> data = Arrays.asList("a", "b");

        when(request.getType()).thenReturn("text");
        when(request.getTextData()).thenReturn(data);
        when(request.getDirection()).thenReturn("DESC");

        // when
        dispatcher.benchmark(request);

        // then
        verify(benchmarkService).benchmark(eq(data), eq("DESC")); // mock5
    }
}