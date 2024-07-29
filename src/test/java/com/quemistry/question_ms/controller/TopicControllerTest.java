package com.quemistry.question_ms.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.quemistry.question_ms.model.RetrieveTopicsResponse;
import com.quemistry.question_ms.model.SaveTopicsRequest;
import com.quemistry.question_ms.service.TopicService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
class TopicControllerTest {


    private MockMvc mockMvc;

    @Mock
    private TopicService topicService;

    @InjectMocks
    private TopicController topicController;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(topicController).build();
    }

    @Test
    void testGetTopics() {
        // Arrange
        RetrieveTopicsResponse mockResponse = new RetrieveTopicsResponse();
        when(topicService.getTopics()).thenReturn(mockResponse);

        // Act
        ResponseEntity<RetrieveTopicsResponse> response = topicController.getTopics(new HttpHeaders());

        // Assert
        assertEquals(200, response.getStatusCodeValue());
        assertEquals(mockResponse, response.getBody());
    }

    @Test
    void testSaveTopics() {
        // Arrange
        SaveTopicsRequest request = new SaveTopicsRequest();
        RetrieveTopicsResponse mockResponse = new RetrieveTopicsResponse();
        when(topicService.saveTopics(request)).thenReturn(mockResponse);

        // Act
        ResponseEntity<RetrieveTopicsResponse> response = topicController.saveTopics(new HttpHeaders(), request);

        // Assert
        assertEquals(200, response.getStatusCodeValue());
        assertEquals(mockResponse, response.getBody());
    }

}
