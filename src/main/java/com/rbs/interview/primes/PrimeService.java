package com.rbs.interview.primes;

import com.rbs.interview.primes.algorithms.PrimeFinder;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

@Service
public class PrimeService {
    private final ApplicationContext context;

    public PrimeService(ApplicationContext context) {
        this.context = context;
    }

    public int[] findPrimes(int initial, PrimeAlgorithm algorithm) {
        PrimeFinder finder = this.context.getBean(algorithm.getAlgorithm());
        return finder.findPrimes(initial);
    }
}
