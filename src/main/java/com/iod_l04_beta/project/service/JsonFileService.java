package com.iod_l04_beta.project.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.iod_l04_beta.project.dto.BenchmarkRequest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.io.InputStream;

/**
 * Serwis odpowiedzialny za wczytywanie danych wejściowych
 * z plików JSON znajdujących się w katalogu zasobów aplikacji
 * (classpath, np. {@code src/main/resources}).
 *
 * <p>Serwis ten umożliwia odczyt struktury JSON
 * i mapowanie jej na obiekt {@link BenchmarkRequest}
 * przy użyciu biblioteki Jackson.
 *
 * <p>Plik JSON musi mieć strukturę zgodną z {@link BenchmarkRequest}.
 */

@Service
public class JsonFileService {

    /**
     * Obiekt mapujący JSON na obiekty Java.
     */
    private final ObjectMapper objectMapper = new ObjectMapper();


    /**
     * Wczytuje plik JSON z classpath i mapuje go na {@link BenchmarkRequest}.
     *
     * @param filename nazwa pliku JSON (np. {@code benchmark.json})
     * @return obiekt {@link BenchmarkRequest} zawierający dane wejściowe
     * @throws RuntimeException gdy nie można odczytać pliku lub zmapować JSON
     */
    public BenchmarkRequest readBenchmarkRequest(String filename) {
        try (InputStream is = new ClassPathResource(filename).getInputStream()) {
            return objectMapper.readValue(is, BenchmarkRequest.class);
        } catch (Exception e) {
            throw new RuntimeException("Cannot read JSON file", e);
        }
    }
}