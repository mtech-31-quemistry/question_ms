package com.quemistry.question_ms.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import com.quemistry.question_ms.entity.MCQ;
import com.quemistry.question_ms.mapper.MCQMapper;
import com.quemistry.question_ms.model.MCQDto;
import com.quemistry.question_ms.repository.MCQRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.util.List;

class MCQServiceImplTest {

    @Mock
    private MCQRepository mcqRepository;

    @InjectMocks
    private MCQServiceImpl questionService;

    private final MCQMapper mcqMapper = MCQMapper.INSTANCE;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        questionService = new MCQServiceImpl(mcqRepository);
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

        MCQDto result = questionService.saveQuestion(mcqDto);

        assertEquals(mcqDto.getId(), result.getId());
        assertEquals(mcqDto.getStem(), result.getStem());
        assertEquals(mcqDto.getOptions().size(), result.getOptions().size());
    }
}
