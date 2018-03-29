package com.codein.springbootjunit;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.hamcrest.Matchers.hasSize;
/**
 * Created by lxh on 2018/3/29.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class HelloControllerTest {

    MockMvc mockMvc;

    @Autowired
    WebApplicationContext wax;

    @Before
    public void setUp() throws Exception {

//        mockMvc = MockMvcBuilders.standaloneSetup(helloController).build();

        mockMvc = MockMvcBuilders.webAppContextSetup(wax).build();

    }

    @Test
    public void hello() throws Exception {

        mockMvc.perform(get("/hello"))
                .andExpect(status().isOk())
                .andExpect(content().string("hello"));

        mockMvc.perform(get("/hello").param("name", "Michale"))
                .andExpect(status().isOk())
                .andExpect(content().string("hello, Michale"));

        mockMvc.perform(get("/hello").param("name", "Anne"))
                .andExpect(status().isOk())
                .andExpect(content().string("hello, Anne"));

    }

    @Test
    public void helloJson() throws Exception {

        mockMvc.perform(get("/hello/json").contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(jsonPath("$.name").value("Michale"))
                .andExpect(jsonPath("$.age").value(25))
                .andExpect(jsonPath("$.*", hasSize(2)));

    }

    @Test
    public void helloJsonList() throws Exception {

        mockMvc.perform(get("/hello/list/json").contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.*", hasSize(2)))
                .andExpect(jsonPath("$.total").isNumber())
                .andExpect(jsonPath("$.list").isArray())
                .andExpect(jsonPath("$.list[0].*", hasSize(2)));

    }

}