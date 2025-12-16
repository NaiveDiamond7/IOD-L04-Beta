package com.iod_l04_beta.project.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.iod_l04_beta.project.dto.BenchmarkRequest;
import com.iod_l04_beta.project.service.JsonFileService;
import com.iod_l04_beta.project.service.RequestDispatcherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/benchmark")
public class BenchmarkController {

    private final RequestDispatcherService dispatcher;
    private final JsonFileService jsonFileService;

    @Autowired
    private ObjectMapper objectMapper;

    public BenchmarkController(
            RequestDispatcherService dispatcher,
            JsonFileService jsonFileService
    ) {
        this.dispatcher = dispatcher;
        this.jsonFileService = jsonFileService;
    }

    @PostMapping
    public Object benchmark(@RequestBody BenchmarkRequest request) {
        return dispatcher.benchmark(request);
    }

    // GET /api/benchmark/file?name=data.json
    @GetMapping("/file")
    public String benchmarkFromFile(@RequestParam String name) {

        BenchmarkRequest request = jsonFileService.readBenchmarkRequest(name);
        Object result = dispatcher.benchmark(request);

        return formatAsLines(result);
    }

    private String formatAsLines(Object result) {
        try {
            if (result instanceof List<?> list) {
                StringBuilder sb = new StringBuilder();
                for (Object item : list) {
                    sb.append(
                            objectMapper
                                    .writerWithDefaultPrettyPrinter()
                                    .writeValueAsString(item)
                    );
                    sb.append("\n\n");
                }
                return sb.toString();
            }
            return objectMapper
                    .writerWithDefaultPrettyPrinter()
                    .writeValueAsString(result);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}