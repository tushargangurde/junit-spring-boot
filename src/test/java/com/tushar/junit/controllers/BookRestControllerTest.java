package com.tushar.junit.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tushar.junit.controllers.models.Book;
import com.tushar.junit.exception.BookNotFoundException;
import com.tushar.junit.services.BookService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.mockito.Mockito.when;

@WebMvcTest(BookRestController.class)
class BookRestControllerTest {

    @MockBean
    private BookService bookService;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void addBookTestForTrue() throws Exception {
        when(bookService.saveBook(ArgumentMatchers.any())).thenReturn(true);
        Book book = new Book(101, "Spring", 450.00);
        String bookJson = objectMapper.writeValueAsString(book);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/api/addBook")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(bookJson);
        ResultActions result = mockMvc.perform(requestBuilder);
        MvcResult mvcResult = result.andReturn();
        MockHttpServletResponse response = mvcResult.getResponse();
        int status = response.getStatus();
        Assertions.assertEquals(200, status);
    }

    @Test
    void addBookTestForFalse() throws Exception {
        when(bookService.saveBook(ArgumentMatchers.any())).thenReturn(false);
        Book book = new Book(101, "Spring", 450.00);
        String bookJson = objectMapper.writeValueAsString(book);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/api/addBook")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(bookJson);
        ResultActions result = mockMvc.perform(requestBuilder);
        MvcResult mvcResult = result.andReturn();
        MockHttpServletResponse response = mvcResult.getResponse();
        int status = response.getStatus();
        Assertions.assertEquals(400, status);
    }

    @Test
    void getBookForFound() throws Exception {
        when(bookService.getBook(ArgumentMatchers.anyInt())).thenReturn(ArgumentMatchers.any());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/getBook/1");
        ResultActions result = mockMvc.perform(requestBuilder);
        MvcResult mvcResult = result.andReturn();
        MockHttpServletResponse response = mvcResult.getResponse();
        int status = response.getStatus();
        Assertions.assertEquals(302, status);
    }

    @Test
    void getBookForNotFound() throws Exception {
        when(bookService.getBook(ArgumentMatchers.anyInt())).thenThrow(BookNotFoundException.class);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/getBook/1");
        ResultActions result = mockMvc.perform(requestBuilder);
        MvcResult mvcResult = result.andReturn();
        MockHttpServletResponse response = mvcResult.getResponse();
        int status = response.getStatus();
        Assertions.assertEquals(404, status);
    }

    @Test
    void updateBookForUpdate() throws Exception {
        Book book = new Book(1, "Spring", 450.00);
        when(bookService.updateBook(ArgumentMatchers.anyInt(), ArgumentMatchers.any())).thenReturn(book);
        String bookJson = objectMapper.writeValueAsString(book);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.put("/api/updateBook/1")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(bookJson);
        ResultActions result = mockMvc.perform(requestBuilder);
        MvcResult mvcResult = result.andReturn();
        MockHttpServletResponse response = mvcResult.getResponse();
        int status = response.getStatus();
        Assertions.assertEquals(200, status);
    }

    @Test
    void updateBookForNotUpdate() throws Exception {
        when(bookService.updateBook(ArgumentMatchers.anyInt(), ArgumentMatchers.any())).thenThrow(BookNotFoundException.class);
        Book book = new Book(1, "Spring", 450.00);
        String bookJson = objectMapper.writeValueAsString(book);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.put("/api/updateBook/1")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(bookJson);
        ResultActions result = mockMvc.perform(requestBuilder);
        MvcResult mvcResult = result.andReturn();
        MockHttpServletResponse response = mvcResult.getResponse();
        int status = response.getStatus();
        Assertions.assertEquals(404, status);
    }

    @Test
    void deleteBookForDelete() throws Exception {
        when(bookService.deleteBook(ArgumentMatchers.anyInt())).thenReturn(ArgumentMatchers.any());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/api/deleteBook/1");
        ResultActions result = mockMvc.perform(requestBuilder);
        MvcResult mvcResult = result.andReturn();
        MockHttpServletResponse response = mvcResult.getResponse();
        int status = response.getStatus();
        Assertions.assertEquals(200, status);
    }

    @Test
    void deleteBookForNotFound() throws Exception {
        when(bookService.deleteBook(ArgumentMatchers.anyInt())).thenThrow(BookNotFoundException.class);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/api/deleteBook/1");
        ResultActions result = mockMvc.perform(requestBuilder);
        MvcResult mvcResult = result.andReturn();
        MockHttpServletResponse response = mvcResult.getResponse();
        int status = response.getStatus();
        Assertions.assertEquals(404, status);
    }
}