package com.quemistry.question_ms.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.quemistry.question_ms.model.MCQDto;
import com.quemistry.question_ms.service.MCQService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.HashMap;
import java.util.Map;

class QuestionControllerTest {


    private MockMvc mockMvc;

    @Mock
    private MCQService mcqService;

    @InjectMocks
    private QuestionController questionController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(questionController).build();
    }

    @Test
    void testHealth() throws Exception {
        Map<String, String> expectedResponse = new HashMap<>();
        expectedResponse.put("service", "auth");
        expectedResponse.put("status", "UP");

        ObjectMapper objectMapper = new ObjectMapper();
        String expectedResponseBody = objectMapper.writeValueAsString(expectedResponse);

        mockMvc.perform(get("/v1/questions/health"))
                .andExpect(status().isOk())
                .andExpect(content().json(expectedResponseBody));
    }
    @Test
    void testSaveQuestion() throws Exception {
        MCQDto mcqDto = new MCQDto();
        // Set properties of mcqDto as needed

        when(mcqService.saveQuestion(mcqDto)).thenReturn(mcqDto);

        ObjectMapper objectMapper = new ObjectMapper();
        String mcqDtoJson = objectMapper.writeValueAsString(mcqDto);

        mockMvc.perform(post("/v1/questions")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mcqDtoJson)
                        .header("Some-Header", "value"))
                .andExpect(status().isOk())
                .andExpect(content().json(mcqDtoJson));
    }
}
