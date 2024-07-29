package com.quemistry.question_ms.mapper;

import com.quemistry.question_ms.entity.Topic;
import com.quemistry.question_ms.model.TopicDto;
import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,  uses = {SkillMapper.class})
public interface TopicMapper {

    TopicMapper INSTANCE = Mappers.getMapper(TopicMapper.class);

    TopicDto topicToTopicDto(Topic topic);

    Topic topicDtoToTopic(TopicDto TopicDto);

    List<TopicDto> topicsToTopicDtos(List<Topic> topics);

    List<Topic> topicDtosToTopics(List<TopicDto> TopicDtos);
}
