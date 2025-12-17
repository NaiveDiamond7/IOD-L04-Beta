package com.iod_l04_beta.project.service;

import com.iod_l04_beta.project.dto.BenchmarkRequest;
import com.iod_l04_beta.project.dto.SortRequest;
import com.iod_l04_beta.project.dto.SortResponse;
import org.springframework.stereotype.Service;


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
     * @param request obiekt zawierający dane do sortowania
     * @return {@link SortResponse} zawierający wynik sortowania
     * @throws IllegalArgumentException gdy podano nieobsługiwany typ danych
     */
    public Object sort(SortRequest request) {

        if ("number".equalsIgnoreCase(request.getType())) {
            var result = sortService.sort(
                    request.getNumberData(),
                    request.getAlgorithm()
            );
            return new SortResponse<>(
                    0,
                    result
            );
        }

        if ("text".equalsIgnoreCase(request.getType())) {
            var result = sortService.sort(
                    request.getTextData(),
                    request.getAlgorithm()
            );
            return new SortResponse<>(
                    0,
                    result
            );
        }
        throw new IllegalArgumentException("Unsupported data type: " + request.getType());
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
            return benchmarkService.benchmark(request.getNumberData());
        }

        if ("text".equalsIgnoreCase(request.getType())) {
            return benchmarkService.benchmark(request.getTextData());
        }
        throw new IllegalArgumentException("Unsupported data type: " + request.getType());
    }
}
