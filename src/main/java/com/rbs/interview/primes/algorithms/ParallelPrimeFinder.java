package com.rbs.interview.primes.algorithms;

import org.springframework.stereotype.Service;

import java.util.stream.IntStream;

@Service
public final class ParallelPrimeFinder implements PrimeFinder {

    /**
     * Find primes in parallel.
     * {@inheritDoc}
     */
    @Override
    public int[] findPrimes(int initial) {
        return IntStream.range(2, initial + 1)
            .parallel()
            .filter(this::isPrime)
            .toArray();
    }
}
