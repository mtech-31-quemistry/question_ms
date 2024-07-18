package com.quemistry.question_ms.mapper;

import com.quemistry.question_ms.entity.Topic;
import com.quemistry.question_ms.model.TopicDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class TopicMapperTest {

    private TopicMapper topicMapper;

    @BeforeEach
    void setUp() {
        topicMapper = TopicMapper.INSTANCE;
    }

    private Topic createTopic(Long id, String name) {
        return new Topic(id, name);
    }

    private TopicDto createTopicDto(Long id, String name) {
        return new TopicDto(id, name);
    }

    @Test
    void testTopicToTopicDto() {
        // Arrange
        Topic topic = createTopic(1L, "Science");

        // Act
        TopicDto topicDto = topicMapper.topicToTopicDto(topic);

        // Assert
        assertThat(topicDto).isNotNull();
        assertThat(topicDto.getId()).isEqualTo(1L);
        assertThat(topicDto.getName()).isEqualTo("Science");
    }

    @Test
    void testTopicDtoToTopic() {
        // Arrange
        TopicDto topicDto = createTopicDto(1L, "Science");

        // Act
        Topic topic = topicMapper.topicDtoToTopic(topicDto);

        // Assert
        assertThat(topic).isNotNull();
        assertThat(topic.getId()).isEqualTo(1L);
        assertThat(topic.getName()).isEqualTo("Science");
    }

    @Test
    void testTopicsToTopicDtos() {
        // Arrange
        List<Topic> topics = Arrays.asList(
                createTopic(1L, "Science"),
                createTopic(2L, "Mathematics")
        );

        // Act
        List<TopicDto> topicDtos = topicMapper.topicsToTopicDtos(topics);

        // Assert
        assertThat(topicDtos).isNotNull();
        assertThat(topicDtos).hasSize(2);
        assertThat(topicDtos.get(0).getId()).isEqualTo(1L);
        assertThat(topicDtos.get(0).getName()).isEqualTo("Science");
        assertThat(topicDtos.get(1).getId()).isEqualTo(2L);
        assertThat(topicDtos.get(1).getName()).isEqualTo("Mathematics");
    }

    @Test
    void testTopicDtosToTopics() {
        // Arrange
        List<TopicDto> topicDtos = Arrays.asList(
                createTopicDto(1L, "Science"),
                createTopicDto(2L, "Mathematics")
        );

        // Act
        List<Topic> topics = topicMapper.topicDtosToTopics(topicDtos);

        // Assert
        assertThat(topics).isNotNull();
        assertThat(topics).hasSize(2);
        assertThat(topics.get(0).getId()).isEqualTo(1L);
        assertThat(topics.get(0).getName()).isEqualTo("Science");
        assertThat(topics.get(1).getId()).isEqualTo(2L);
        assertThat(topics.get(1).getName()).isEqualTo("Mathematics");
    }
}

