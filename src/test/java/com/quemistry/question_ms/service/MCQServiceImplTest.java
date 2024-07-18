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
import com.quemistry.question_ms.model.QuestionOption;
import com.quemistry.question_ms.model.RetrieveMCQRequest;
import com.quemistry.question_ms.model.RetrieveMCQResponse;
import com.quemistry.question_ms.repository.MCQRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class MCQServiceImplTest {

    @Mock
    private MCQRepository mcqRepository;

    @InjectMocks
    private MCQServiceImpl mcqService;

    @Mock
    private MCQMapper mcqMapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mcqService = new MCQServiceImpl(mcqRepository);
    }

    @Test
    void testSaveQuestion() {
        MCQDto mcqDto = new MCQDto();
        // Set properties of mcqDto as needed
        mcqDto.setId(1L);
        mcqDto.setStem("Sample Question?");
        QuestionOption option = QuestionOption.builder()
                .text("Option 1")
                .isAnswer(true)
                .build();
        mcqDto.setOptions(List.of(option));

        MCQ mcq = MCQMapper.INSTANCE.mcqDtoToMcq(mcqDto);
        when(mcqRepository.save(any(MCQ.class))).thenReturn(mcq);
        when(mcqMapper.mcqDtoToMcq(any(MCQDto.class))).thenReturn(mcq);
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

    @Test
    void testRetrieveMCQsWithoutPaginationWithoutFilter() {
        // Arrange
        RetrieveMCQRequest request = new RetrieveMCQRequest();

        List<MCQ> mcqs = Arrays.asList(new MCQ(), new MCQ());
        List<MCQDto> mcqDtos = Arrays.asList(new MCQDto(), new MCQDto());

        when(mcqRepository.findAll()).thenReturn(mcqs);
        when(mcqMapper.mcqsToMcqDtos(anyList())).thenReturn(mcqDtos);

        // Act
        RetrieveMCQResponse response = mcqService.retrieveMCQs(request);

        // Assert
        assertEquals(2, response.getMcqs().size());
        verify(mcqRepository, times(1)).findAll();
    }

    @Test
    void testRetrieveMCQsWithoutPaginationWithFilter() {
        // Arrange
        RetrieveMCQRequest request = new RetrieveMCQRequest();
        request.setTopics(Arrays.asList(1L));
        request.setSkills(Arrays.asList(2L));

        List<MCQ> mcqs = Arrays.asList(new MCQ(), new MCQ());
        List<MCQDto> mcqDtos = Arrays.asList(new MCQDto(), new MCQDto());

        when(mcqRepository.findByTopicOrSkill(anyList(), anyList())).thenReturn(mcqs);
        when(mcqMapper.mcqsToMcqDtos(anyList())).thenReturn(mcqDtos);

        // Act
        RetrieveMCQResponse response = mcqService.retrieveMCQs(request);

        // Assert
        assertEquals(2, response.getMcqs().size());
        verify(mcqRepository, times(1)).findByTopicOrSkill(anyList(), anyList());
    }

    @Test
    void testRetrieveMCQsWithPaginationAndFilter() {
        // Arrange
        RetrieveMCQRequest request = new RetrieveMCQRequest();
        request.setTopics(Arrays.asList(1L));
        request.setSkills(Arrays.asList(2L));
        request.setPageNumber(0);
        request.setPageSize(2);

        List<MCQ> mcqs = Arrays.asList(new MCQ(), new MCQ());
        Page<MCQ> mcqPage = new PageImpl<>(mcqs, PageRequest.of(0, 2), 2);
        List<MCQDto> mcqDtos = Arrays.asList(new MCQDto(), new MCQDto());

        when(mcqRepository.findByTopicOrSkill(anyList(), anyList(), any(Pageable.class))).thenReturn(mcqPage);
        when(mcqMapper.mcqsToMcqDtos(anyList())).thenReturn(mcqDtos);

        // Act
        RetrieveMCQResponse response = mcqService.retrieveMCQs(request);

        // Assert
        assertEquals(2, response.getMcqs().size());
        assertEquals(0, response.getPageNumber());
        assertEquals(2, response.getPageSize());
        assertEquals(1, response.getTotalPages());
        assertEquals(2, response.getTotalRecords());
    }

    @Test
    void testRetrieveMCQsWithPaginationWithoutFilter() {
        // Arrange
        RetrieveMCQRequest request = new RetrieveMCQRequest();
        request.setPageNumber(0);
        request.setPageSize(2);

        List<MCQ> mcqs = Arrays.asList(new MCQ(), new MCQ());
        Page<MCQ> mcqPage = new PageImpl<>(mcqs, PageRequest.of(0, 2), 2);
        List<MCQDto> mcqDtos = Arrays.asList(new MCQDto(), new MCQDto());

        when(mcqRepository.findAll(any(Pageable.class))).thenReturn(mcqPage);
        when(mcqMapper.mcqsToMcqDtos(anyList())).thenReturn(mcqDtos);

        // Act
        RetrieveMCQResponse response = mcqService.retrieveMCQs(request);

        // Assert
        assertEquals(2, response.getMcqs().size());
        assertEquals(0, response.getPageNumber());
        assertEquals(2, response.getPageSize());
        assertEquals(1, response.getTotalPages());
        assertEquals(2, response.getTotalRecords());
    }

}
