package com.quemistry.question_ms.service;

import com.quemistry.question_ms.entity.Topic;
import com.quemistry.question_ms.mapper.TopicMapper;
import com.quemistry.question_ms.model.RetrieveTopicsResponse;
import com.quemistry.question_ms.model.SaveTopicsRequest;
import com.quemistry.question_ms.repository.SkillRepository;
import com.quemistry.question_ms.repository.TopicRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;


@Slf4j
@Service
public class TopicServiceImpl implements TopicService {

    private final TopicRepository topicRepository;
    private final SkillRepository skillRepository;

    public TopicServiceImpl(TopicRepository topicRepository, SkillRepository skillRepository) {
        this.topicRepository = topicRepository;
        this.skillRepository = skillRepository;
    }

    @Override
    public RetrieveTopicsResponse getTopics() {
        return RetrieveTopicsResponse.builder()
                .topics(TopicMapper.INSTANCE.topicsToTopicDtos(topicRepository.findAll()))
                .build();
    }

    @Override
    public RetrieveTopicsResponse saveTopics(SaveTopicsRequest saveTopicsRequest) {
        List<Topic> topics = TopicMapper.INSTANCE.topicDtosToTopics(saveTopicsRequest.getTopics());
        topics.forEach(t -> {
            t.getSkills().forEach(s -> {
                if(s.getId() == null){
                    skillRepository.saveAndFlush(s);
                }
            });
        });
        topicRepository.saveAll(topics);
        TopicMapper.INSTANCE.topicsToTopicDtos(topicRepository.findAll());

        return RetrieveTopicsResponse.builder()
                .topics(TopicMapper.INSTANCE.topicsToTopicDtos(topicRepository.findAll()))
                .build();
    }
}
