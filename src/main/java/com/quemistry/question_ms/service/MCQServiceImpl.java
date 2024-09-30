package com.quemistry.question_ms.service;

import com.quemistry.question_ms.entity.MCQ;
import com.quemistry.question_ms.entity.Skill;
import com.quemistry.question_ms.entity.Topic;
import com.quemistry.question_ms.enums.QuestionStatus;
import com.quemistry.question_ms.mapper.MCQMapper;
import com.quemistry.question_ms.model.CreateMcqRequest;
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
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Optional;

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
    public MCQDto createQuestion(CreateMcqRequest createMcqRequest) {
        MCQ mcq = mcqMapper.createMcqRequestToMcq(createMcqRequest);
        List<Topic> topics = topicRepository.findAllById(createMcqRequest.getTopics());
        mcq.setTopics(topics);
        List<Skill> skills = skillRepository.findAllById(createMcqRequest.getSkills());
        mcq.setSkills(skills);
        return mcqMapper.mcqToMcqDto(mcqRepository.save(mcq));
    }

    @Override
    public MCQDto saveQuestion(SaveMcqRequest saveMcqRequest) {
        Optional<MCQ> mcqOptional = mcqRepository.findById(saveMcqRequest.getId());
        if(mcqOptional.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "mcq with id "+ saveMcqRequest.getId() + " not found");
        }
        MCQ mcq = mcqOptional.get();
        if (mcq.getStatus().equals(QuestionStatus.DRAFT)){
            if (saveMcqRequest.getStem() != null){
                mcq.setStem(saveMcqRequest.getStem());
            }
            if (saveMcqRequest.getOptions() != null){
                mcq.setOptions(saveMcqRequest.getOptions());
            }
            if (saveMcqRequest.getTopics() != null){
                List<Topic> topics = topicRepository.findAllById(saveMcqRequest.getTopics());
                mcq.setTopics(topics);
            }
            if (saveMcqRequest.getSkills() != null){
                List<Skill> skills = skillRepository.findAllById(saveMcqRequest.getSkills());
                mcq.setSkills(skills);
            }

        }
        mcq.setUpdatedBy(saveMcqRequest.getUpdatedBy());
        if (saveMcqRequest.getStatus().equals(QuestionStatus.PUBLISHED)){
            mcq.setPublishedOn(new Date());
            mcq.setPublishedBy(saveMcqRequest.getUpdatedBy());
        }
        if (saveMcqRequest.getStatus().equals(QuestionStatus.ARCHIVED)){
            mcq.setArchivedOn(new Date());
            mcq.setArchivedBy(saveMcqRequest.getUpdatedBy());
        }
        mcq.setStatus(saveMcqRequest.getStatus());
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

        Pageable pageable = PageRequest.of(retrieveMCQRequest.getPageNumber(), retrieveMCQRequest.getPageSize(), Sort.by(Sort.Direction.DESC, "id"));
        Page<MCQ> mcqPage;

        // no filter
        log.info("no topic or skill in request, to return all");
        if ((retrieveMCQRequest.getTopics() == null || retrieveMCQRequest.getTopics().isEmpty())
                && (retrieveMCQRequest.getSkills() == null || retrieveMCQRequest.getSkills().isEmpty())){
            mcqPage  = mcqRepository.findAll(pageable);
        } else {
            log.info("filter by topic, skill");
            if (retrieveMCQRequest.getTopics() == null || retrieveMCQRequest.getTopics().isEmpty()){
                retrieveMCQRequest.setTopics(Collections.emptyList());
            }
            if (retrieveMCQRequest.getSkills() == null || retrieveMCQRequest.getSkills().isEmpty()){
                retrieveMCQRequest.setSkills(Collections.emptyList());
            }
            // got filter by topic, skill


            if (retrieveMCQRequest.getStatuses().isEmpty()){
                mcqPage = mcqRepository.findByTopicOrSkill(
                        retrieveMCQRequest.getTopics(),
                        retrieveMCQRequest.getSkills(),
                        retrieveMCQRequest.getExcludeIds(),
                        pageable);
            } else {
                mcqPage = mcqRepository.findByTopicSkillStatus(
                        retrieveMCQRequest.getTopics(),
                        retrieveMCQRequest.getSkills(),
                        retrieveMCQRequest.getStatuses(),
                        retrieveMCQRequest.getExcludeIds(),
                        pageable);
            }

        }

        retrieveMCQResponse.setMcqs(mcqMapper.mcqsToMcqDtos(mcqPage.getContent()));
        retrieveMCQResponse.setPageSize(retrieveMCQRequest.getPageSize());
        retrieveMCQResponse.setPageNumber(retrieveMCQRequest.getPageNumber());
        retrieveMCQResponse.setTotalPages(mcqPage.getTotalPages());
        retrieveMCQResponse.setTotalRecords(mcqPage.getTotalElements());
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
