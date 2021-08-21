package com.tushar.junit.controllers;

import com.tushar.junit.services.WelcomeService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.mockito.Mockito.when;

@WebMvcTest(WelcomeRestController.class)
class WelcomeRestControllerTest {

    @MockBean
    private WelcomeService welcomeService;

    @Autowired
    private MockMvc mockMvc;

    @Test
    void welcomeMessageTest() throws Exception {
        when(welcomeService.getWelcomeMessage()).thenReturn("Good Morning...!");
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/welcome");
        ResultActions result = mockMvc.perform(requestBuilder);
        MvcResult mvcResult = result.andReturn();
        MockHttpServletResponse response = mvcResult.getResponse();
        int status = response.getStatus();
        Assertions.assertEquals(200, status);
    }
}