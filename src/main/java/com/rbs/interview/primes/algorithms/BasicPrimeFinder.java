package com.rbs.interview.primes.algorithms;

import org.springframework.stereotype.Service;

import java.util.stream.IntStream;

@Service
public final class BasicPrimeFinder implements PrimeFinder {

    /**
     * Find primes using a basic looping algorithm, primes are checked sequentially.
     * {@inheritDoc}
     */
    @Override
    public int[] findPrimes(int initial) {
        return IntStream.range(2, initial + 1)
            .filter(this::isPrime)
            .toArray();
    }
}
