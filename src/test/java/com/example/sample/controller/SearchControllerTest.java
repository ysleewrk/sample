package com.example.sample.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@AutoConfigureMockMvc
public class SearchControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testRestSearchSuccessCase() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/search")
                        .param("term", "테스트")
                //        .param("param2", "value2")
                )
                .andExpect(MockMvcResultMatchers.status().isOk());
//                .andExpect(MockMvcResultMatchers.content().string("Expected response body"));
    }

    @Test
    public void testRestSearchSizeErrorCase() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/search")
                                .param("term", "테스트")
                                .param("size", "100")
                )
                .andExpect(MockMvcResultMatchers.status().is4xxClientError());
//                .andExpect(MockMvcResultMatchers.content().string("Expected response body"));
    }
}