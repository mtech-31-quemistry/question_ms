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
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Slf4j
@Service
public class MCQServiceImpl implements MCQService {

    private final MCQRepository mcqRepository;
    private final MCQPageRepository mcqPageRepository;

    private final MCQCustomRepository mcqCustomRepository;

    private final MCQMapper mcqMapper = MCQMapper.INSTANCE;
    private final TopicMapper topicMapper = TopicMapper.INSTANCE;

    public MCQServiceImpl(MCQRepository mcqRepository, MCQPageRepository mcqPageRepository, MCQCustomRepository mcqCustomRepository) {
        this.mcqRepository = mcqRepository;
        this.mcqPageRepository = mcqPageRepository;
        this.mcqCustomRepository = mcqCustomRepository;
    }

    @Override
    public MCQDto saveQuestion(MCQDto mcqDto) {
        return mcqMapper.mcqToMcqDto(mcqRepository.save(mcqMapper.mcqDtoToMcq(mcqDto)));
    }

    @Override
    public RetrieveMCQResponse retrieveMCQs() {
        List<MCQ> mcqs = StreamSupport.stream(mcqRepository.findAll().spliterator(), false)
                .collect(Collectors.toList());;
        return RetrieveMCQResponse.builder()
                .mcqs(mcqMapper.mcqsToMcqDtos(mcqs))
                .build();
    }

    @Override
    public RetrieveMCQResponse retrieveMCQs(RetrieveMCQRequest retrieveMCQRequest) {
        List<MCQ> results;
        RetrieveMCQResponse retrieveMCQResponse = new RetrieveMCQResponse();

//        List<Topic> topics = topicMapper.topicDtosToTopics(retrieveMCQRequest.getTopics());
//        log.info("topics=== {}", topics);
        List<MCQ> mcqs  = mcqRepository.findByTopicIds(retrieveMCQRequest.getTopics());
        retrieveMCQResponse.setMcqs(mcqMapper.mcqsToMcqDtos(mcqs));
//        if (retrieveMCQRequest.getPageNumber() != null && retrieveMCQRequest.getPageSize()!= null){
//            Pageable pageable = PageRequest.of(retrieveMCQRequest.getPageNumber(), retrieveMCQRequest.getPageSize());
//            Page<MCQ> mcqPage = mcqPageRepository.findByTopicsIn(topics, pageable);
//            retrieveMCQResponse.setMcqs(mcqMapper.mcqsToMcqDtos(mcqPage.getContent()));
//            retrieveMCQResponse.setPageSize(retrieveMCQRequest.getPageSize());
//            retrieveMCQResponse.setPageNumber(retrieveMCQRequest.getPageNumber());
//
//        } else {
//            List<MCQ> mcqs  = mcqRepository.findByTopicsIn(topics);
//            retrieveMCQResponse.setMcqs(mcqMapper.mcqsToMcqDtos(mcqs));
//        }
        return retrieveMCQResponse;
    }
}
