package com.rbs.interview.primes.algorithms;

import org.springframework.stereotype.Service;

import java.util.concurrent.ConcurrentSkipListSet;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;

@Service
public final class CachedPrimeFinder implements PrimeFinder {

    private static final AtomicInteger maxValue = new AtomicInteger(0);
    private static final ConcurrentSkipListSet<Integer> cache = new ConcurrentSkipListSet<>();

    @Override
    public int[] findPrimes(int initial) {

        final int max = maxValue.get();
        if (initial <= max) {
            return cache.headSet(initial)
                .stream()
                .mapToInt(Integer::intValue)
                .toArray();
        }

        IntStream.range(max, initial + 1)
            .parallel()
            .filter(this::isPrime)
            .peek(cache::add)
            .forEach((i) -> maxValue.updateAndGet(value -> Math.max(value, i)));

        maxValue.updateAndGet(value -> Math.max(value, initial));

        return cache.headSet(initial)
            .stream()
            .mapToInt(Integer::intValue)
            .toArray();
    }
}
