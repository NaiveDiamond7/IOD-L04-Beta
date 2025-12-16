package com.iod_l04_beta.project.controller;

import com.iod_l04_beta.project.dto.SortRequest;
import com.iod_l04_beta.project.dto.SortResponse;
import com.iod_l04_beta.project.service.RequestDispatcherService;
import com.iod_l04_beta.project.service.SortService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/sort")
public class SortController {

    private final RequestDispatcherService dispatcher;

    public SortController(RequestDispatcherService dispatcher) {
        this.dispatcher = dispatcher;
    }

    @PostMapping
    public Object sort(@RequestBody SortRequest request) {

        long start = System.nanoTime();
        Object response = dispatcher.sort(request);
        long end = System.nanoTime();

        if (response instanceof SortResponse<?> sr) {
            return new SortResponse<>(
                    (end - start) / 1_000_000,
                    sr.getSortedData()
            );
        }

        return response;
    }
}