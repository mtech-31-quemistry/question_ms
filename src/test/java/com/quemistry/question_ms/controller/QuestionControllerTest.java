package com.quemistry.question_ms.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.quemistry.question_ms.model.MCQDto;
import com.quemistry.question_ms.model.RetrieveMCQByIdsRequest;
import com.quemistry.question_ms.model.RetrieveMCQRequest;
import com.quemistry.question_ms.model.RetrieveMCQResponse;
import com.quemistry.question_ms.model.SaveMcqRequest;
import com.quemistry.question_ms.service.MCQService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class QuestionControllerTest {


    private MockMvc mockMvc;

    @Mock
    private MCQService mcqService;

    @InjectMocks
    private QuestionController questionController;

    private final ObjectMapper objectMapper = new ObjectMapper();

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
        SaveMcqRequest saveMcqRequest = SaveMcqRequest.builder().build();
        // Set properties of mcqDto as needed

        when(mcqService.saveQuestion(saveMcqRequest)).thenReturn(mcqDto);

        ObjectMapper objectMapper = new ObjectMapper();
        String saveMcqRequestJson = objectMapper.writeValueAsString(saveMcqRequest);
        String mcqDtoJson = objectMapper.writeValueAsString(mcqDto);

        mockMvc.perform(post("/v1/questions")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(saveMcqRequestJson )
                        .header("Some-Header", "value"))
                .andExpect(status().isOk())
                .andExpect(content().json(mcqDtoJson));
    }

    @Test
    void testGetQuestions() throws Exception {
        // Mock service response
        RetrieveMCQResponse mockResponse = new RetrieveMCQResponse();
        mockResponse.setMcqs(Collections.singletonList(new MCQDto())); // Mocking with empty MCQDto for simplicity

        when(mcqService.retrieveMCQs()).thenReturn(mockResponse);

        // Perform GET request and verify
        mockMvc.perform(MockMvcRequestBuilders.get("/v1/questions")
                        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.mcqs").isArray())
                .andExpect(MockMvcResultMatchers.jsonPath("$.mcqs.length()").value(1)); // Adjust based on expected MCQ count
    }

    @Test
    void testRetrieveQuestion() throws Exception {
        // Mock request object
        RetrieveMCQRequest request = new RetrieveMCQRequest();
        // Assuming RetrieveMCQRequest has some fields to set for testing

        // Mock service response
        RetrieveMCQResponse mockResponse = new RetrieveMCQResponse();
        when(mcqService.retrieveMCQs(any(RetrieveMCQRequest.class))).thenReturn(mockResponse);

        // Convert request object to JSON
        String requestBody = objectMapper.writeValueAsString(request);

        // Perform POST request and verify response
        mockMvc.perform(post("/v1/questions/retrieve")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(status().isOk());
    }

    @Test
    void testRetrieveQuestionById() throws Exception {
        RetrieveMCQResponse response = new RetrieveMCQResponse();
        RetrieveMCQByIdsRequest request = new RetrieveMCQByIdsRequest();
        request.setIds(Arrays.asList(1L, 2L, 3L));
        request.setPageNumber(0);
        request.setPageSize(10);
        when(mcqService.retrieveByIds(any(RetrieveMCQByIdsRequest.class))).thenReturn(response);

        mockMvc.perform(post("/v1/questions/retrieve-by-ids")
                        .contentType(MediaType.APPLICATION_JSON)
                        .headers(new HttpHeaders())
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk());

        verify(mcqService, times(1)).retrieveByIds(any(RetrieveMCQByIdsRequest.class));
}

}
