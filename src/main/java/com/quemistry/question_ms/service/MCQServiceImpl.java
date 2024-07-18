package com.quemistry.question_ms.service;

import com.quemistry.question_ms.entity.MCQ;
import com.quemistry.question_ms.mapper.MCQMapper;
import com.quemistry.question_ms.model.MCQDto;
import com.quemistry.question_ms.model.RetrieveMCQRequest;
import com.quemistry.question_ms.model.RetrieveMCQResponse;
import com.quemistry.question_ms.repository.MCQRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class MCQServiceImpl implements MCQService {

    private final MCQRepository mcqRepository;

    private static final MCQMapper mcqMapper = MCQMapper.INSTANCE;

    public MCQServiceImpl(MCQRepository mcqRepository) {
        this.mcqRepository = mcqRepository;
    }

    @Override
    public MCQDto saveQuestion(MCQDto mcqDto) {
        return mcqMapper.mcqToMcqDto(mcqRepository.save(mcqMapper.mcqDtoToMcq(mcqDto)));
    }

    @Override
    public RetrieveMCQResponse retrieveMCQs() {
        List<MCQ> mcqs = mcqRepository.findAll();
        return RetrieveMCQResponse.builder()
                .mcqs(mcqMapper.mcqsToMcqDtos(mcqs))
                .build();
    }

    @Override
    public RetrieveMCQResponse retrieveMCQs(RetrieveMCQRequest retrieveMCQRequest) {
        List<MCQ> results;
        RetrieveMCQResponse retrieveMCQResponse = new RetrieveMCQResponse();

        List<MCQ> mcqs;
        // paged
        if (retrieveMCQRequest.getPageNumber() != null && retrieveMCQRequest.getPageSize()!= null){
            log.info("paged");
            Pageable pageable = PageRequest.of(retrieveMCQRequest.getPageNumber(), retrieveMCQRequest.getPageSize());
            Page<MCQ> mcqPage;

            // no filter
            log.info("no topic or skill in request, to return all");
            if (retrieveMCQRequest.getTopics() == null && retrieveMCQRequest.getSkills() == null){
                mcqPage  = mcqRepository.findAll(pageable);

            } else {
                log.info("filter by topic, skill");
                // got filter by topic, skill
                mcqPage = mcqRepository.findByTopicOrSkill(retrieveMCQRequest.getTopics(), retrieveMCQRequest.getSkills(), pageable);
            }

            retrieveMCQResponse.setMcqs(mcqMapper.mcqsToMcqDtos(mcqPage.getContent()));
            retrieveMCQResponse.setPageSize(retrieveMCQRequest.getPageSize());
            retrieveMCQResponse.setPageNumber(retrieveMCQRequest.getPageNumber());
            retrieveMCQResponse.setTotalPages(mcqPage.getTotalPages());
            retrieveMCQResponse.setTotalRecords(mcqPage.getTotalElements());


        } else { // not paged
            log.info("not paged");
            // no filter
            if (retrieveMCQRequest.getTopics() == null && retrieveMCQRequest.getSkills() == null) {
                log.info("no topic or skill in request, to return all");
                return retrieveMCQs();
            } else { // filter by topic, skill
                log.info("filter by topic, skill");
                mcqs = mcqRepository.findByTopicOrSkill(retrieveMCQRequest.getTopics(), retrieveMCQRequest.getSkills());
            }
            retrieveMCQResponse.setMcqs(mcqMapper.mcqsToMcqDtos(mcqs));
        }
        return retrieveMCQResponse;
    }
}
