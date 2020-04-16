package com.rbs.interview.primes;

import com.rbs.interview.primes.algorithms.PrimeFinder;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

@Service("PrimeService")
public final class DefaultPrimeService implements PrimeService {
    private final ApplicationContext context;

    public DefaultPrimeService(ApplicationContext context) {
        this.context = context;
    }

    /**
     * Finds primes using the supplied algorithm by resolving the prime finding algorithm class from the spring context.
     * {@inheritDoc}
     */
    @Override
    public int[] findPrimes(int initial, PrimeAlgorithm algorithm) {
        PrimeFinder finder = this.context.getBean(algorithm.getAlgorithm());
        return finder.findPrimes(initial);
    }
}
