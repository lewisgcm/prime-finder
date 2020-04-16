package com.rbs.interview.primes.algorithms;

/**
 * Prime finder is used to find primes and check if a particular number is a prime.
 */
public interface PrimeFinder {

    /**
     * Find primes in the range 0...initial
     * @param initial The upper bound of the prime search
     * @return An array of integers which are primes found within the search constraints
     */
    int[] findPrimes(int initial);

    /**
     * Checks whether a number is a prime
     * @param number The number to check for primality
     * @return True/False depending on whether the number is prime or not
     */
    default boolean isPrime(int number) {
        if (number <= 1) {
            return false;
        }

        for (int i = 2; i <= Math.sqrt(number); i++) {
            if (number % i == 0) {
                return false;
            }
        }

        return true;
    }
}
