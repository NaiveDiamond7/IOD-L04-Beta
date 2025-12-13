package com.iod_l04_beta.project.controller;

import com.iod_l04_beta.project.dto.BenchmarkRequest;
import com.iod_l04_beta.project.service.BenchmarkService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/benchmark")
public class BenchmarkController {
    private final BenchmarkService benchmarkService;

    public BenchmarkController(BenchmarkService benchmarkService) {
        this.benchmarkService = benchmarkService;
    }

    @PostMapping
    public Object benchmark(@RequestBody BenchmarkRequest request) {

        if ("number".equalsIgnoreCase(request.getType())) {
            return benchmarkService.benchmark(request.getNumberData());
        }

        if ("text".equalsIgnoreCase(request.getType())) {
            return benchmarkService.benchmark(request.getTextData());
        }

        throw new IllegalArgumentException("Unsupported data type");
    }
}
