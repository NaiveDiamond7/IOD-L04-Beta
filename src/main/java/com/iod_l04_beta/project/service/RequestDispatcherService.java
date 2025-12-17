package com.iod_l04_beta.project.service;

import com.iod_l04_beta.project.dto.BenchmarkRequest;
import com.iod_l04_beta.project.dto.SortRequest;
import com.iod_l04_beta.project.dto.SortResponse;
import org.springframework.stereotype.Service;

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
