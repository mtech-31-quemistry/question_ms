package com.quemistry.question_ms.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.quemistry.question_ms.entity.MCQ;
import com.quemistry.question_ms.mapper.MCQMapper;
import com.quemistry.question_ms.model.MCQDto;
import com.quemistry.question_ms.model.RetrieveMCQRequest;
import com.quemistry.question_ms.model.RetrieveMCQResponse;
import com.quemistry.question_ms.repository.MCQCustomRepository;
import com.quemistry.question_ms.repository.MCQPageRepository;
import com.quemistry.question_ms.repository.MCQRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

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
    public void setUp() {
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
    public void testRetrieveMCQs() {
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

    // add test for retrieveMCQs function
    @Test
    public void testRetrieveMCQsByRequest() {
        MCQ mcq1 = new MCQ();
        MCQ mcq2 = new MCQ();
        List<MCQ> mockMCQList = Arrays.asList(mcq1, mcq2);

        when(mcqRepository.findByTopicsIn(any())).thenReturn(mockMCQList);

        RetrieveMCQResponse response = mcqService.retrieveMCQs(new RetrieveMCQRequest());

        verify(mcqRepository, times(1)).findByTopicsIn(any());

        assertEquals(2, response.getMcqs().size()); // Assuming two MCQs were returned
    }
}
