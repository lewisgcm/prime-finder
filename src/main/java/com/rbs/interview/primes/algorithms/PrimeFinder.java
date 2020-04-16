package com.rbs.interview.primes.algorithms;

/**
 * Prime finder is used to find primes and check if a particular number is a prime.
 */
public interface PrimeFinder {

    /**
     * Find primes in the range 0...initial
     * @param initial   the upper bound of the prime search
     * @return          an array of integers which are primes found within the search constraints
     */
    int[] findPrimes(int initial);

    /**
     * Checks whether a number is a prime
     * @param number    the number to check for primality
     * @return          true or false depending on whether the number is prime or not
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
