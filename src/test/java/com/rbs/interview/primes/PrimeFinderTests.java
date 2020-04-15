package com.rbs.interview.primes;

import com.rbs.interview.primes.algorithms.BasicPrimeFinder;
import com.rbs.interview.primes.algorithms.CachedPrimeFinder;
import com.rbs.interview.primes.algorithms.ParallelPrimeFinder;
import com.rbs.interview.primes.algorithms.PrimeFinder;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.stream.Stream;

public class PrimeFinderTests {

    @ParameterizedTest
    @MethodSource("createFinders")
    public void test_negative_number_returns_empty_list_of_primes(PrimeFinder finder) {
        int[] result = finder.findPrimes(-10);

        assertThat(result).isEmpty();
    }

    @ParameterizedTest
    @MethodSource("createFinders")
    public void test_zero_returns_empty_list_of_primes(PrimeFinder finder) {
        int[] result = finder.findPrimes(0);

        assertThat(result).isEmpty();
    }

    @ParameterizedTest
    @MethodSource("createFinders")
    public void test_returns_correct_list_of_primes(PrimeFinder finder) {
        int[] result = finder.findPrimes(10);

        assertThat(result).containsExactly(2, 3, 5, 7);
    }

    @ParameterizedTest
    @MethodSource("createFinders")
    public void test_is_prime_is_correct_on_negative(PrimeFinder finder) {
        boolean result = finder.isPrime(-1);

        assertThat(result).isFalse();
    }

    @ParameterizedTest
    @MethodSource("createFinders")
    public void test_is_prime_is_correct_on_number_one(PrimeFinder finder) {
        boolean result = finder.isPrime(1);

        assertThat(result).isFalse();
    }

    @ParameterizedTest
    @MethodSource("createFinders")
    public void test_is_prime_is_correct_on_prime_number(PrimeFinder finder) {
        boolean result = finder.isPrime(2);

        assertThat(result).isTrue();
    }

    private static Stream<PrimeFinder> createFinders() {
        return Stream.of(
            new BasicPrimeFinder(),
            new ParallelPrimeFinder(),
            new CachedPrimeFinder()
        );
    }
}
