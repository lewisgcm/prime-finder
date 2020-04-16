package com.rbs.interview;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@AutoConfigureMockMvc
public class InterviewApplicationIntegrationTests {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void test_non_existent_path_is_not_found() throws Exception {
        this.mockMvc
            .perform(MockMvcRequestBuilders.get("/asdsd"))
            .andExpect(MockMvcResultMatchers.status().is4xxClientError());
    }

    @Test
    public void test_non_integer_initial_is_not_found() throws Exception {
        this.mockMvc
            .perform(MockMvcRequestBuilders.get("/primes/asas"))
            .andExpect(MockMvcResultMatchers.status().is4xxClientError());
    }

    @Test
    public void test_negative_integer_initial_is_not_found() throws Exception {
        this.mockMvc
            .perform(MockMvcRequestBuilders.get("/primes/-10"))
            .andExpect(MockMvcResultMatchers.status().is4xxClientError());
    }

    @Test
    public void test_json_mime_type_is_honored() throws Exception {
        this.mockMvc
            .perform(MockMvcRequestBuilders.get("/primes/2"))
            .andExpect(MockMvcResultMatchers.status().is2xxSuccessful())
            .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(MockMvcResultMatchers.content().json("{\"Initial\":2,\"Primes\":[2]}"));
    }

    @Test
    public void test_xml_mime_type_is_honored() throws Exception {
        this.mockMvc
            .perform(MockMvcRequestBuilders.get("/primes/2").accept(MediaType.APPLICATION_XML))
            .andExpect(MockMvcResultMatchers.status().is2xxSuccessful())
            .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_XML))
            .andExpect(MockMvcResultMatchers.content().xml("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>" +
                "<Primes Initial=\"2\">" +
                    "<Prime>2</Prime>" +
                "</Primes>"
            ));
    }
}
