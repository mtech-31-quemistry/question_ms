package com.quemistry.question_ms.service;

import com.quemistry.question_ms.entity.MCQ;
import com.quemistry.question_ms.mapper.MCQMapper;
import com.quemistry.question_ms.mapper.TopicMapper;
import com.quemistry.question_ms.model.MCQDto;
import com.quemistry.question_ms.model.RetrieveMCQRequest;
import com.quemistry.question_ms.model.RetrieveMCQResponse;
import com.quemistry.question_ms.repository.MCQCustomRepository;
import com.quemistry.question_ms.repository.MCQPageRepository;
import com.quemistry.question_ms.repository.MCQRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Slf4j
@Service
public class MCQServiceImpl implements MCQService {

    private final MCQRepository mcqRepository;
    private final MCQPageRepository mcqPageRepository;


    private final MCQMapper mcqMapper = MCQMapper.INSTANCE;
    private final TopicMapper topicMapper = TopicMapper.INSTANCE;

    public MCQServiceImpl(MCQRepository mcqRepository, MCQPageRepository mcqPageRepository ) {
        this.mcqRepository = mcqRepository;
        this.mcqPageRepository = mcqPageRepository;
    }

    @Override
    public MCQDto saveQuestion(MCQDto mcqDto) {
        return mcqMapper.mcqToMcqDto(mcqRepository.save(mcqMapper.mcqDtoToMcq(mcqDto)));
    }

    @Override
    public RetrieveMCQResponse retrieveMCQs() {
        List<MCQ> mcqs = new ArrayList<>(mcqRepository.findAll());
        return RetrieveMCQResponse.builder()
                .mcqs(mcqMapper.mcqsToMcqDtos(mcqs))
                .build();
    }

    @Override
    public RetrieveMCQResponse retrieveMCQs(RetrieveMCQRequest retrieveMCQRequest) {
        RetrieveMCQResponse retrieveMCQResponse = new RetrieveMCQResponse();

        List< com.quemistry.question_ms.entity.Topic> topics = topicMapper.topicDtosToTopics(retrieveMCQRequest.getTopics());
        if (retrieveMCQRequest.getPageNumber() != null && retrieveMCQRequest.getPageSize()!= null){
            Pageable pageable = PageRequest.of(retrieveMCQRequest.getPageNumber(), retrieveMCQRequest.getPageSize());
            Page<MCQ> mcqPage = mcqPageRepository.findByTopicsIn(topics, pageable);
            retrieveMCQResponse.setMcqs(mcqMapper.mcqsToMcqDtos(mcqPage.getContent()));
            retrieveMCQResponse.setPageSize(retrieveMCQRequest.getPageSize());
            retrieveMCQResponse.setPageNumber(retrieveMCQRequest.getPageNumber());

        } else {
            retrieveMCQResponse.setMcqs(mcqMapper.mcqsToMcqDtos(mcqRepository.findByTopicsIn(topics)));
        }
        return retrieveMCQResponse;
    }
}
