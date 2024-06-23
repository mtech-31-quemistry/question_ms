package com.quemistry.question_ms.service;

import com.quemistry.question_ms.mapper.MCQMapper;
import com.quemistry.question_ms.model.MCQDto;
import com.quemistry.question_ms.repository.MCQRepository;
import org.springframework.stereotype.Service;

@Service
public class MCQServiceImpl implements MCQService {

    private final MCQRepository mcqRepository;

    private final MCQMapper mcqMapper = MCQMapper.INSTANCE;

    public MCQServiceImpl(MCQRepository mcqRepository) {
        this.mcqRepository = mcqRepository;
    }

    @Override
    public MCQDto saveQuestion(MCQDto mcqDto) {
        return mcqMapper.mcqToMcqDto(mcqRepository.save(mcqMapper.mcqDtoToMcq(mcqDto)));
    }
}
