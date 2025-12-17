package com.iod_l04_beta.project.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.iod_l04_beta.project.dto.BenchmarkRequest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.io.InputStream;

@Service
public class JsonFileService {

    private final ObjectMapper objectMapper = new ObjectMapper();

    public BenchmarkRequest readBenchmarkRequest(String filename) {
        try (InputStream is = new ClassPathResource(filename).getInputStream()) {
            return objectMapper.readValue(is, BenchmarkRequest.class);
        } catch (Exception e) {
            throw new RuntimeException("Cannot read JSON file", e);
        }
    }
}