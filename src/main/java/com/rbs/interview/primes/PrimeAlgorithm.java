package com.rbs.interview.primes;

import com.rbs.interview.primes.algorithms.BasicPrimeFinder;
import com.rbs.interview.primes.algorithms.CachedPrimeFinder;
import com.rbs.interview.primes.algorithms.ParallelPrimeFinder;
import com.rbs.interview.primes.algorithms.PrimeFinder;

public enum PrimeAlgorithm {
    BASIC(BasicPrimeFinder.class),
    PARALLEL(ParallelPrimeFinder.class),
    CACHED(CachedPrimeFinder.class);

    private final Class<? extends PrimeFinder> algorithm;

    PrimeAlgorithm(Class<? extends PrimeFinder> algorithm) {
        this.algorithm = algorithm;
    }

    public Class<? extends PrimeFinder> getAlgorithm() {
        return algorithm;
    }
}
