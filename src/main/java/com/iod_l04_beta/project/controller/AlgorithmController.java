package com.iod_l04_beta.project.controller;
import java.util.Set;

import com.iod_l04_beta.project.service.SortAlgorithmFactory;
import org.springframework.web.bind.annotation.*;

/**
 * Kontroler REST udostępniający informacje
 * o dostępnych algorytmach sortowania.
 *
 */
@RestController
@RequestMapping("/api/algorithms")
public class AlgorithmController {
    private final SortAlgorithmFactory factory;

    public AlgorithmController(SortAlgorithmFactory factory) {
        this.factory = factory;
    }

    @GetMapping
    public Set<String> getAlgorithms() {
        return factory.getAvailableAlgorithmNames();
    }
}
