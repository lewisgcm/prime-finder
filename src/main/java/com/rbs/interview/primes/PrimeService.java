package com.rbs.interview.primes;

/**
 * The prime service interface is used to find primes using the supplied algorithm.
 */
public interface PrimeService {

    /**
     * @param initial The upper bound of the prime search, i return all primes from 0...initial
     * @param algorithm The algorithm to use when searching for primes
     * @return The array of primes found from 0...initial
     */
    int[] findPrimes(int initial, PrimeAlgorithm algorithm);
}
