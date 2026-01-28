package com.iod_l04_beta.project.service;

import com.iod_l04_beta.project.dto.BenchmarkRequest;
import com.iod_l04_beta.project.dto.SortRequest;
import com.iod_l04_beta.project.dto.SortResponse;
import org.springframework.stereotype.Service;
import java.util.List;


/**
 * Serwis pośredniczący pomiędzy kontrolerami REST
 * a logiką biznesową aplikacji.
 *
 * <p>Odpowiada za:
 * <ul>
 *     <li>rozpoznanie typu danych wejściowych</li>
 *     <li>przekierowanie żądania do odpowiedniego serwisu</li>
 * </ul>
 *
 * <p>Obsługiwane typy danych:
 * <ul>
 *     <li>{@code number} – dane liczbowe</li>
 *     <li>{@code text} – dane tekstowe</li>
 * </ul>
 */

@Service
public class RequestDispatcherService {
    private final SortService sortService;
    private final BenchmarkService benchmarkService;

    public RequestDispatcherService(
            SortService sortService,
            BenchmarkService benchmarkService
    ) {
        this.sortService = sortService;
        this.benchmarkService = benchmarkService;
    }


    /**
     * Obsługuje żądanie sortowania danych.
     *
     * <p>Na podstawie typu danych wywoływana jest
     * odpowiednia metoda serwisu sortującego.
     *
     * @param request obiekt zawierający dane do sortowania i jego kierunek
     * @return {@link SortResponse} zawierający wynik sortowania
     * @throws IllegalArgumentException gdy podano nieobsługiwany typ danych
     */
    public Object sort(SortRequest request) {

        if ("number".equalsIgnoreCase(request.getType())) {
            List<Integer> data = request.getNumberData();

            // tutaj wyznaczamy algorytm - auto albo nie
            String chosenAlgorithm = resolveAlgorithm(request.getAlgorithm(), data.size());

            var result = sortService.sort(
                    data,
                    chosenAlgorithm,
                    request.getDirection(),
                    request.getIterations()
            );
            return new SortResponse<>(
                    chosenAlgorithm, // zwracamy nazwe wybranego algorytmu
                    0,
                    result
            );
        }

        if ("text".equalsIgnoreCase(request.getType())) {
            List<String> data = request.getTextData();

            String chosenAlgorithm = resolveAlgorithm(request.getAlgorithm(), data.size());

            var result = sortService.sort(
                    data,
                    chosenAlgorithm,
                    request.getDirection(),
                    request.getIterations()
            );
            return new SortResponse<>(
                    chosenAlgorithm,
                    0,
                    result
            );
        }
        throw new IllegalArgumentException("Unsupported data type: " + request.getType());
    }

    private String resolveAlgorithm(String requestedAlgo, int dataSize) {
        if (!"auto".equalsIgnoreCase(requestedAlgo)) {
            return requestedAlgo;
        }

        // Heurystyka: Dla małych danych Insertion Sort jest szybszy.
        // Dla dużych Quick Sort jest optymalny.
        if (dataSize < 64) {
            return "insertion";
        } else {
            return "quick";
        }
    }

    /**
     * Obsługuje żądanie benchmarku algorytmów sortowania.
     *
     * <p>Benchmark wykonywany jest dla wszystkich
     * dostępnych algorytmów sortowania.
     *
     * @param request dane wejściowe do benchmarku
     * @return lista wyników benchmarku
     * @throws IllegalArgumentException gdy podano nieobsługiwany typ danych
     */
    public Object benchmark(BenchmarkRequest request) {

        if ("number".equalsIgnoreCase(request.getType())) {
            return benchmarkService.benchmark(request.getNumberData(), request.getDirection());
        }

        if ("text".equalsIgnoreCase(request.getType())) {
            return benchmarkService.benchmark(request.getTextData(), request.getDirection());
        }
        throw new IllegalArgumentException("Unsupported data type: " + request.getType());
    }
}
