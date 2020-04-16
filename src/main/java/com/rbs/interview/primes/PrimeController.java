package com.rbs.interview.primes;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public final class PrimeController {

    private final PrimeService primeService;
    private final PrimeAlgorithm defaultAlgorithm;

    public PrimeController(
        PrimeService primeService,

        @Value("${app.default-algorithm:BASIC}")
        PrimeAlgorithm defaultAlgorithm
    ) {
        this.primeService = primeService;
        this.defaultAlgorithm = defaultAlgorithm;
    }

    @GetMapping(value = "primes/{initial:\\d+}")
    public PrimeResponse getPrime(
        @PathVariable Integer initial,
        @RequestParam Optional<PrimeAlgorithm> algorithm
    ) {
        int[] primes = primeService.findPrimes(
            initial,
            algorithm.orElse(defaultAlgorithm)
        );

        return new PrimeResponse(initial, primes);
    }
}
