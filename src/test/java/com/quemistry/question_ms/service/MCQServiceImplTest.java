package com.quemistry.question_ms.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.quemistry.question_ms.entity.MCQ;
import com.quemistry.question_ms.mapper.MCQMapper;
import com.quemistry.question_ms.model.MCQDto;
import com.quemistry.question_ms.model.RetrieveMCQRequest;
import com.quemistry.question_ms.model.RetrieveMCQResponse;
import com.quemistry.question_ms.repository.MCQPageRepository;
import com.quemistry.question_ms.repository.MCQRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class MCQServiceImplTest {

    @Mock
    private MCQRepository mcqRepository;

    @Mock
    private MCQPageRepository mcqPageRepository;

    @InjectMocks
    private MCQServiceImpl mcqService;

    private final MCQMapper mcqMapper = MCQMapper.INSTANCE;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mcqService = new MCQServiceImpl(mcqRepository, mcqPageRepository);
    }

    @Test
    void testSaveQuestion() {
        MCQDto mcqDto = new MCQDto();
        // Set properties of mcqDto as needed
        mcqDto.setId(1);
        mcqDto.setStem("Sample Question?");
        MCQDto.OptionDto option = MCQDto.OptionDto.builder()
                .text("Option 1")
                .isAnswer(true)
                .build();
        mcqDto.setOptions(List.of(option));
        MCQ mcq = mcqMapper.mcqDtoToMcq(mcqDto);

        when(mcqRepository.save(any(MCQ.class))).thenReturn(mcq);

        MCQDto result = mcqService.saveQuestion(mcqDto);

        assertEquals(mcqDto.getId(), result.getId());
        assertEquals(mcqDto.getStem(), result.getStem());
        assertEquals(mcqDto.getOptions().size(), result.getOptions().size());
    }


    @Test
    void testRetrieveMCQs() {
        // Mock data
        MCQ mcq1 = new MCQ();
        MCQ mcq2 = new MCQ();
        List<MCQ> mockMCQList = Arrays.asList(mcq1, mcq2);

        // Mock repository method call
        when(mcqRepository.findAll()).thenReturn(mockMCQList);

        // Call the service method
        RetrieveMCQResponse response = mcqService.retrieveMCQs();

        // Verify repository method was called once
        verify(mcqRepository, times(1)).findAll();

        // Assertions
        assertEquals(2, response.getMcqs().size()); // Assuming two MCQs were returned
    }


    @Test
    void testRetrieveMCQByTopic() {
        // Mock request object
        RetrieveMCQRequest request = new RetrieveMCQRequest();
        request.setTopics(List.of(1L, 2L)); // Example topic IDs

        // Mock repositories and mappers
        List<MCQ> mockMCQs = new ArrayList<>(); // Example mock MCQs
        when(mcqRepository.findByTopicOrSkill(anyList(),anyList())).thenReturn(mockMCQs);

        // Call the service method
        RetrieveMCQResponse response = mcqService.retrieveMCQs(request);

        // Assert the response
        assertNotNull(response);
        assertTrue(response.getMcqs().isEmpty()); // Verify that MCQ list is empty as per mock behavior

        // Additional assertions can be added based on your actual business logic
    }
}
