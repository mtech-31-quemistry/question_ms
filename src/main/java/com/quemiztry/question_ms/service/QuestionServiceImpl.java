package com.quemiztry.question_ms.service;

import com.quemiztry.question_ms.mapper.QuestionMapper;
import com.quemiztry.question_ms.model.QuestionDto;
import com.quemiztry.question_ms.repository.QuestionRepository;
import org.springframework.stereotype.Service;

@Service
public class QuestionServiceImpl implements QuestionService{

    private final QuestionRepository questionRepository;

    private final QuestionMapper questionMapper = QuestionMapper.INSTANCE;

    public QuestionServiceImpl(QuestionRepository questionRepository) {
        this.questionRepository = questionRepository;
    }

    @Override
    public QuestionDto saveQuestion(QuestionDto questionDto) {
        return questionMapper.questionToQuestionDto(questionRepository.save(questionMapper.questionDtoToQuestion(questionDto)));
    }
}
