package com.rbs.interview.primes.algorithms;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.concurrent.ConcurrentSkipListSet;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;

@Service
public final class CachedPrimeFinder implements PrimeFinder {

    private static final AtomicInteger maxValue = new AtomicInteger(0);
    private static final ConcurrentSkipListSet<Integer> cache = new ConcurrentSkipListSet<>();
    private static final Logger logger = LoggerFactory.getLogger(CachedPrimeFinder.class);

    /**
     * Find primes using a caching parallel algorithm.
     * This algorithm will cache a set of known primes and the upper bound of those primes, any searches within the
     * upper bound will simply return a copy of this set. Any search outwith those bounds will increase the upperbound
     * and add new primes to the set.
     * New primes are found using a parallel search.
     * {@inheritDoc}
     */
    @Override
    public int[] findPrimes(int initial) {

        final int max = maxValue.get();
        if (initial <= max) {
            return cache.headSet(initial)
                .stream()
                .mapToInt(Integer::intValue)
                .toArray();
        }

        // Search for new primes, adding them to our cache set and updating our known max value.
        // We conditionally set the max value if the new value would be greater than the old to handle
        // race conditions overwriting the value.
        IntStream.range(max, initial + 1)
            .parallel()
            .filter(this::isPrime)
            .peek(cache::add)
            .forEach((i) -> maxValue.updateAndGet(value -> Math.max(value, i)));

        // Set the max value based on our initial search term if greater than known current max,
        // again to handle currency issues with multiple threads searching for primes.
        if (maxValue.updateAndGet(value -> Math.max(value, initial)) == initial) {
            logger.info("Upper bound of cached primes increased from {} to {}", max, initial);
        } else {
            logger.debug("Could not set upper bound of cached primes, another thread has already increased it.");
        }

        return cache.headSet(initial)
            .stream()
            .mapToInt(Integer::intValue)
            .toArray();
    }
}
