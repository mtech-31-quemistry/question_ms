package com.quemistry.question_ms.service;

import com.quemistry.question_ms.entity.MCQ;
import com.quemistry.question_ms.entity.Skill;
import com.quemistry.question_ms.mapper.MCQMapper;
import com.quemistry.question_ms.model.MCQDto;
import com.quemistry.question_ms.model.RetrieveMCQByIdsRequest;
import com.quemistry.question_ms.model.RetrieveMCQRequest;
import com.quemistry.question_ms.model.RetrieveMCQResponse;
import com.quemistry.question_ms.model.SaveMcqRequest;
import com.quemistry.question_ms.repository.MCQRepository;
import com.quemistry.question_ms.repository.SkillRepository;
import com.quemistry.question_ms.repository.TopicRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Slf4j
@Service
public class MCQServiceImpl implements MCQService {

    private final MCQRepository mcqRepository;
    private final TopicRepository topicRepository;
    private final SkillRepository skillRepository;

    private static final MCQMapper mcqMapper = MCQMapper.INSTANCE;

    public MCQServiceImpl(MCQRepository mcqRepository, TopicRepository topicRepository, SkillRepository skillRepository) {
        this.mcqRepository = mcqRepository;
        this.topicRepository = topicRepository;
        this.skillRepository = skillRepository;
    }

    @Override
    public MCQDto saveQuestion(SaveMcqRequest saveMcqRequest) {
        MCQ mcq = mcqMapper.mcqDtoToMcq(saveMcqRequest);
//        List<Topic> topics = topicRepository.findAllById(saveMcqRequest.getTopics());
//        mcq.setTopics(topics);
        List<Skill> skills = skillRepository.findAllById(saveMcqRequest.getSkills());
        mcq.setSkills(skills);
        return mcqMapper.mcqToMcqDto(mcqRepository.save(mcq));
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
        RetrieveMCQResponse retrieveMCQResponse = new RetrieveMCQResponse();

        List<MCQ> mcqs;
        // paged
        if (retrieveMCQRequest.getPageNumber() != null && retrieveMCQRequest.getPageSize()!= null){
            log.info("paged");
            Pageable pageable = PageRequest.of(retrieveMCQRequest.getPageNumber(), retrieveMCQRequest.getPageSize(), Sort.by("dsc", "id"));
            Page<MCQ> mcqPage;

            // no filter
            log.info("no topic or skill in request, to return all");
            if (retrieveMCQRequest.getTopics() == null && retrieveMCQRequest.getSkills() == null){
                mcqPage  = mcqRepository.findAll(pageable);

            } else {
                log.info("filter by topic, skill");
                if (retrieveMCQRequest.getTopics() == null){
                    retrieveMCQRequest.setTopics(Collections.emptyList());
                }
                if (retrieveMCQRequest.getSkills() == null){
                    retrieveMCQRequest.setSkills(Collections.emptyList());
                }
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
                if (retrieveMCQRequest.getTopics() == null){
                    retrieveMCQRequest.setTopics(Collections.emptyList());
                }
                if (retrieveMCQRequest.getSkills() == null){
                    retrieveMCQRequest.setSkills(Collections.emptyList());
                }
                mcqs = mcqRepository.findByTopicOrSkill(retrieveMCQRequest.getTopics(), retrieveMCQRequest.getSkills());
            }
            retrieveMCQResponse.setMcqs(mcqMapper.mcqsToMcqDtos(mcqs));
        }
        return retrieveMCQResponse;
    }

    @Override
    public RetrieveMCQResponse retrieveByIds(RetrieveMCQByIdsRequest retrieveMCQByIdsRequest) {
        Pageable pageable = PageRequest.of(retrieveMCQByIdsRequest.getPageNumber(), retrieveMCQByIdsRequest.getPageSize());
        Page<MCQ> mcqPage  = mcqRepository.findByIds(retrieveMCQByIdsRequest.getIds(), pageable);

        RetrieveMCQResponse retrieveMCQResponse = new RetrieveMCQResponse();
        retrieveMCQResponse.setMcqs(mcqMapper.mcqsToMcqDtos(mcqPage.getContent()));
        retrieveMCQResponse.setPageSize(retrieveMCQByIdsRequest.getPageSize());
        retrieveMCQResponse.setPageNumber(retrieveMCQByIdsRequest.getPageNumber());
        retrieveMCQResponse.setTotalPages(mcqPage.getTotalPages());
        retrieveMCQResponse.setTotalRecords(mcqPage.getTotalElements());
        return retrieveMCQResponse;
    }
}
