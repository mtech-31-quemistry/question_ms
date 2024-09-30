package com.quemistry.question_ms.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.quemistry.question_ms.entity.MCQ;
import com.quemistry.question_ms.entity.Skill;
import com.quemistry.question_ms.entity.Topic;
import com.quemistry.question_ms.enums.QuestionStatus;
import com.quemistry.question_ms.mapper.MCQMapper;
import com.quemistry.question_ms.model.CreateMcqRequest;
import com.quemistry.question_ms.model.MCQDto;
import com.quemistry.question_ms.model.QuestionOption;
import com.quemistry.question_ms.model.RetrieveMCQByIdsRequest;
import com.quemistry.question_ms.model.RetrieveMCQRequest;
import com.quemistry.question_ms.model.RetrieveMCQResponse;
import com.quemistry.question_ms.model.SaveMcqRequest;
import com.quemistry.question_ms.repository.MCQRepository;
import com.quemistry.question_ms.repository.SkillRepository;
import com.quemistry.question_ms.repository.TopicRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

class MCQServiceImplTest {

    @Mock
    private MCQRepository mcqRepository;
    @Mock
    private TopicRepository topicRepository;
    @Mock
    private SkillRepository skillRepository;
    @Mock
    private MCQMapper mcqMapper;
    @InjectMocks
    private MCQServiceImpl mcqService;
    private List<MCQ> mcqs;
    private MCQ mcq;
    private MCQDto mcqDto;
    private SaveMcqRequest saveMcqRequest;
    private Page<MCQ> mcqPage;
    private List<MCQDto> mcqDTOs;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mcqService = new MCQServiceImpl(mcqRepository, topicRepository, skillRepository);

        saveMcqRequest = new SaveMcqRequest();
        saveMcqRequest.setId(1L);
        saveMcqRequest.setStatus(QuestionStatus.PUBLISHED);
        saveMcqRequest.setStem("Updated question stem");
        saveMcqRequest.setOptions(Collections.emptyList());
        saveMcqRequest.setTopics(Collections.emptyList());
        saveMcqRequest.setSkills(Collections.emptyList());

        mcq = new MCQ();
        mcq.setId(1L);
        mcq.setStatus(QuestionStatus.DRAFT);

        mcqDto = new MCQDto();
        mcqDto.setId(1L);
        mcqDto.setStatus(QuestionStatus.PUBLISHED);
    }

    @Test
    void testCreateQuestion() {
        CreateMcqRequest createMcqRequest = new CreateMcqRequest();
        // Set properties of mcqDto as needed
        createMcqRequest.setStem("Sample Question?");
        QuestionOption option = QuestionOption.builder()
                .text("Option 1")
                .isAnswer(true)
                .build();
        createMcqRequest.setOptions(List.of(option));

        MCQ mcq = MCQMapper.INSTANCE.createMcqRequestToMcq(createMcqRequest);
        when(mcqRepository.save(any(MCQ.class))).thenReturn(mcq);
        when(mcqMapper.createMcqRequestToMcq(any(CreateMcqRequest.class))).thenReturn(mcq);
        MCQDto result = mcqService.createQuestion(createMcqRequest);

        assertEquals(createMcqRequest.getStem(), result.getStem());
        assertEquals(createMcqRequest.getOptions().size(), result.getOptions().size());
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
    void testSaveQuestion_MCQNotFound() {
        when(mcqRepository.findById(saveMcqRequest.getId())).thenReturn(Optional.empty());

        ResponseStatusException exception = assertThrows(ResponseStatusException.class, () -> {
            mcqService.saveQuestion(saveMcqRequest);
        });

        assertEquals(HttpStatus.NOT_FOUND, exception.getStatusCode());
        assertTrue(exception.getReason().contains("mcq with id 1 not found"));
    }

    @Test
    void testSaveQuestion_UpdateDraftMCQ() {
        when(mcqRepository.findById(saveMcqRequest.getId())).thenReturn(Optional.of(mcq));
        when(topicRepository.findAllById(any())).thenReturn(Collections.singletonList(new Topic()));
        when(skillRepository.findAllById(any())).thenReturn(Collections.singletonList(new Skill()));
        when(mcqRepository.save(any(MCQ.class))).thenReturn(mcq);
        when(mcqMapper.mcqToMcqDto(any(MCQ.class))).thenReturn(mcqDto);

        MCQDto result = mcqService.saveQuestion(saveMcqRequest);

        assertNotNull(result);
        assertEquals(QuestionStatus.PUBLISHED, result.getStatus());
        verify(mcqRepository, times(1)).save(any(MCQ.class));
    }

    @Test
    void testSaveQuestion_PublishMCQ() {
        mcq.setStatus(QuestionStatus.DRAFT);

        when(mcqRepository.findById(saveMcqRequest.getId())).thenReturn(Optional.of(mcq));
        when(mcqRepository.save(any(MCQ.class))).thenReturn(mcq);
        when(mcqMapper.mcqToMcqDto(any(MCQ.class))).thenReturn(mcqDto);

        MCQDto result = mcqService.saveQuestion(saveMcqRequest);

        assertNotNull(result);
        assertEquals(QuestionStatus.PUBLISHED, result.getStatus());
        verify(mcqRepository, times(1)).save(any(MCQ.class));
    }

    @Test
    void testSaveQuestion_ArchiveMCQ() {
        saveMcqRequest.setStatus(QuestionStatus.ARCHIVED);

        when(mcqRepository.findById(saveMcqRequest.getId())).thenReturn(Optional.of(mcq));
        when(mcqRepository.save(any(MCQ.class))).thenReturn(mcq);
        when(mcqMapper.mcqToMcqDto(any(MCQ.class))).thenReturn(mcqDto);

        MCQDto result = mcqService.saveQuestion(saveMcqRequest);

        assertNotNull(result);
        assertEquals(QuestionStatus.ARCHIVED, result.getStatus());
        verify(mcqRepository, times(1)).save(any(MCQ.class));
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

        when(mcqRepository.findByTopicOrSkill(anyList(), anyList(), anyList(), any(Pageable.class))).thenReturn(mcqPage);
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
    void testRetrieveMCQsWithPaginationAndTopicSkillStatus() {
        // Arrange
        RetrieveMCQRequest request = new RetrieveMCQRequest();
        request.setTopics(Arrays.asList(1L));
        request.setSkills(Arrays.asList(2L));
        request.setStatuses(Arrays.asList(QuestionStatus.PUBLISHED));
        request.setPageNumber(0);
        request.setPageSize(2);

        List<MCQ> mcqs = Arrays.asList(new MCQ(), new MCQ());
        Page<MCQ> mcqPage = new PageImpl<>(mcqs, PageRequest.of(0, 2), 2);
        List<MCQDto> mcqDtos = Arrays.asList(new MCQDto(), new MCQDto());

        when(mcqRepository.findByTopicSkillStatus(anyList(), anyList(), anyList(), anyList(), any(Pageable.class))).thenReturn(mcqPage);
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


    @Test
    void testRetrieveByIds() {
        RetrieveMCQByIdsRequest request = new RetrieveMCQByIdsRequest();
        request.setIds(Arrays.asList(1L, 2L, 3L));
        request.setPageNumber(0);
        request.setPageSize(10);

        List<MCQ> mcqs;
        Page<MCQ> mcqPage;
        List<MCQDto> mcqDTOs;
        //
        mcqs = Arrays.asList(new MCQ(), new MCQ(), new MCQ());
        mcqPage = new PageImpl<>(mcqs, PageRequest.of(0, 10), mcqs.size());
        mcqDTOs = Arrays.asList(new MCQDto(), new MCQDto(), new MCQDto());

        when(mcqRepository.findByIds(request.getIds(), PageRequest.of(request.getPageNumber(), request.getPageSize()))).thenReturn(mcqPage);
        when(mcqMapper.mcqsToMcqDtos(mcqs)).thenReturn(mcqDTOs);

        RetrieveMCQResponse response =  mcqService.retrieveByIds(request);

        assertEquals(mcqDTOs, response.getMcqs());
        assertEquals(10, response.getPageSize());
        assertEquals(0, response.getPageNumber());
        assertEquals(1, response.getTotalPages());
        assertEquals(3, response.getTotalRecords());
    }

}
