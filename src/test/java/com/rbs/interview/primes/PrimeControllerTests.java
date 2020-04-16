package com.rbs.interview.primes;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@SpringBootTest
public class PrimeControllerTests {

    private PrimeService mockPrimeService;
    private MockMvc mockMvc;

    @BeforeEach
    public void setup() {
        this.mockPrimeService = Mockito.mock(PrimeService.class);

        this.mockMvc = MockMvcBuilders.standaloneSetup(
            new PrimeController(this.mockPrimeService, PrimeAlgorithm.BASIC)
        ).build();
    }

    @Test
    public void test_that_default_argument_algorithm_is_used() throws Exception {
        this.mockMvc
            .perform(MockMvcRequestBuilders.get("/primes/2"))
            .andExpect(MockMvcResultMatchers.status().is2xxSuccessful());

        Mockito
            .verify(this.mockPrimeService, Mockito.times(1))
            .findPrimes(2, PrimeAlgorithm.BASIC);
    }

    @Test
    public void test_that_basic_algorithm_can_be_used() throws Exception {
        this.mockMvc
            .perform(MockMvcRequestBuilders.get("/primes/2?algorithm=BASIC"))
            .andExpect(MockMvcResultMatchers.status().is2xxSuccessful());

        Mockito
            .verify(this.mockPrimeService, Mockito.times(1))
            .findPrimes(2, PrimeAlgorithm.BASIC);
    }

    @Test
    public void test_that_parallel_algorithm_can_be_used() throws Exception {
        this.mockMvc
            .perform(MockMvcRequestBuilders.get("/primes/2?algorithm=PARALLEL"))
            .andExpect(MockMvcResultMatchers.status().is2xxSuccessful());

        Mockito
            .verify(this.mockPrimeService, Mockito.times(1))
            .findPrimes(2, PrimeAlgorithm.PARALLEL);
    }

    @Test
    public void test_that_cached_algorithm_can_be_used() throws Exception {
        this.mockMvc
            .perform(MockMvcRequestBuilders.get("/primes/2?algorithm=CACHED"))
            .andExpect(MockMvcResultMatchers.status().is2xxSuccessful());

        Mockito
            .verify(this.mockPrimeService, Mockito.times(1))
            .findPrimes(2, PrimeAlgorithm.CACHED);
    }

    @Test
    public void test_that_non_existent_algorithm_is_rejected() throws Exception {
        this.mockMvc
            .perform(MockMvcRequestBuilders.get("/primes/2?algorithm=BLARGY"))
            .andExpect(MockMvcResultMatchers.status().is4xxClientError());

        Mockito
            .verify(this.mockPrimeService, Mockito.times(0))
            .findPrimes(Mockito.anyInt(), Mockito.any());
    }
}
