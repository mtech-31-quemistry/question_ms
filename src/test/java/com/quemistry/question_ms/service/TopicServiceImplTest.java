package com.quemistry.question_ms.service;

import com.quemistry.question_ms.entity.Skill;
import com.quemistry.question_ms.entity.Topic;
import com.quemistry.question_ms.mapper.TopicMapper;
import com.quemistry.question_ms.model.RetrieveTopicsResponse;
import com.quemistry.question_ms.model.SaveTopicsRequest;
import com.quemistry.question_ms.model.SkillDto;
import com.quemistry.question_ms.model.TopicDto;
import com.quemistry.question_ms.repository.SkillRepository;
import com.quemistry.question_ms.repository.TopicRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class TopicServiceImplTest {

    @Mock
    private TopicRepository topicRepository;

    @Mock
    private SkillRepository skillRepository;

    @InjectMocks
    private TopicServiceImpl topicService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetTopics() {
        // Arrange
        List<Topic> mockTopics = new ArrayList<>();
        RetrieveTopicsResponse mockResponse = RetrieveTopicsResponse.builder()
                .topics(new ArrayList<>())
                .build();

        when(topicRepository.findAll()).thenReturn(mockTopics);

        // Act
        RetrieveTopicsResponse response = topicService.getTopics();

        // Assert
        assertEquals(mockResponse, response);
        verify(topicRepository, times(1)).findAll();
    }

    @Test
    void testSaveTopics() {
        // Arrange
        Topic topic = new Topic();
        topic.setSkills(Collections.singletonList(new Skill()));
        List<Topic> topics = Collections.singletonList(topic);
        SaveTopicsRequest saveTopicsRequest = new SaveTopicsRequest();
        TopicDto topicDto = TopicDto.builder()
            .skills(Collections.singletonList(new SkillDto()))
            .build();
        saveTopicsRequest.setTopics(Collections.singletonList(topicDto));

        when(topicRepository.saveAll(any())).thenReturn(topics);
        when(topicRepository.findAll()).thenReturn(Collections.singletonList(topic));
        // Act
        RetrieveTopicsResponse response = topicService.saveTopics(saveTopicsRequest);

        // Assert
        assertEquals(saveTopicsRequest.getTopics(), response.getTopics());
        verify(topicRepository, times(1)).saveAll(topics);
        verify(skillRepository, times(1)).saveAndFlush(any()); // Verify saveAndFlush was called for skills
    }
}
