package com.iod_l04_beta.project.controller;

import com.iod_l04_beta.project.dto.SortRequest;
import com.iod_l04_beta.project.dto.SortResponse;
import com.iod_l04_beta.project.service.SortService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/sort")
public class SortController {
    private final SortService sortService;

    public SortController(SortService sortService) {
        this.sortService = sortService;
    }

    @PostMapping
    public Object sort(@RequestBody SortRequest request) {

        long start = System.nanoTime();

        if ("number".equalsIgnoreCase(request.getType())) {
            var result = sortService.sort(
                    request.getNumberData(),
                    request.getAlgorithm()
            );
            long end = System.nanoTime();
            return new SortResponse<>((end - start) / 1_000_000, result);
        }

        if ("text".equalsIgnoreCase(request.getType())) {
            var result = sortService.sort(
                    request.getTextData(),
                    request.getAlgorithm()
            );
            long end = System.nanoTime();
            return new SortResponse<>((end - start) / 1_000_000, result);
        }

        throw new IllegalArgumentException("Unsupported data type");
    }
}