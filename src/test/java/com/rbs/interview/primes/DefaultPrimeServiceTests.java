package com.rbs.interview.primes;

import com.rbs.interview.primes.algorithms.PrimeFinder;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.context.ApplicationContext;

public class DefaultPrimeServiceTests {

    @Test
    public void test_that_prime_finder_is_resolved_and_called() {
        ApplicationContext mockApplicationContext = Mockito.mock(ApplicationContext.class);
        PrimeFinder mockPrimeFinder = Mockito.mock(PrimeFinder.class);

        Mockito
            .when(mockApplicationContext.getBean(Mockito.<Class<PrimeFinder>>any()))
            .thenReturn(mockPrimeFinder);

        Mockito
            .when(mockPrimeFinder.findPrimes(5))
            .thenReturn(new int[] {1});

        final int initial = 5;
        DefaultPrimeService service = new DefaultPrimeService(mockApplicationContext);
        service.findPrimes(initial, PrimeAlgorithm.BASIC);

        Mockito
            .verify(mockApplicationContext, Mockito.times(1))
            .getBean(Mockito.<Class<PrimeFinder>>any());

        Mockito
            .verify(mockPrimeFinder, Mockito.times(1))
            .findPrimes(initial);
    }
}
