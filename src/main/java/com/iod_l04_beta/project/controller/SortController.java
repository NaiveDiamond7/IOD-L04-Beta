package com.iod_l04_beta.project.controller;

import com.iod_l04_beta.project.dto.SortRequest;
import com.iod_l04_beta.project.dto.SortResponse;
import com.iod_l04_beta.project.service.RequestDispatcherService;
import com.iod_l04_beta.project.service.SortService;
import org.springframework.web.bind.annotation.*;


/**
 * Kontroler REST odpowiedzialny za sortowanie danych
 * przy użyciu wybranego algorytmu.
 */
@RestController
@RequestMapping("/api/sort")
public class SortController {

    private final RequestDispatcherService dispatcher;

    public SortController(RequestDispatcherService dispatcher) {
        this.dispatcher = dispatcher;
    }



    /**
     * Sortuje dane przekazane w żądaniu.
     *
     * @param request obiekt zawierający dane i nazwę algorytmu
     * @return posortowane dane wraz z czasem wykonania
     */
    @PostMapping
    public Object sort(@RequestBody SortRequest request) {

        long start = System.nanoTime();
        Object response = dispatcher.sort(request);
        long end = System.nanoTime();

        if (response instanceof SortResponse<?> sr) {
            return new SortResponse<>(
                    sr.getAlgorithm(),
                    (end - start) / 1_000_000,
                    sr.getSortedData()
            );
        }

        return response;
    }
}