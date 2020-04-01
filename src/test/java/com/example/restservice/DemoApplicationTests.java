package com.example.restservice;

import static org.hamcrest.CoreMatchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.example.restservice.controller.CounterController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(CounterController.class)
class DemoApplicationTests {

    private static final String COUNTER_NAME_ABCD = "/counter?name=abcd";
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void getWithoutNameShouldReturnErrorStatus() throws Exception {
        this.mockMvc.perform(get("/counter")).andDo(print()).andExpect(status().is(400));
    }

    @Test
    public void getWithUnknownNameShouldReturnNotFound() throws Exception {
        this.mockMvc.perform(get(COUNTER_NAME_ABCD)).andDo(print()).andExpect(status().isNotFound());
    }

    @Test
    public void getWithKnownNameAndIncrementedShouldReturnOne() throws Exception {
        this.mockMvc.perform(put(COUNTER_NAME_ABCD));
        this.mockMvc.perform(post(COUNTER_NAME_ABCD));
        this.mockMvc.perform(get(COUNTER_NAME_ABCD)).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString("1")));
        this.mockMvc.perform(delete(COUNTER_NAME_ABCD));
    }

}
