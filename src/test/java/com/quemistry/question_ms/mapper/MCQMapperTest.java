package com.quemistry.question_ms.mapper;

import com.quemistry.question_ms.entity.MCQ;
import com.quemistry.question_ms.entity.Skill;
import com.quemistry.question_ms.entity.Topic;
import com.quemistry.question_ms.mapper.MCQMapper;
import com.quemistry.question_ms.model.MCQDto;
import com.quemistry.question_ms.model.QuestionOption;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class MCQMapperTest {

    private final MCQMapper mcqMapper = MCQMapper.INSTANCE;

    private MCQ createMCQ(Long id, String stem, String status) {
        QuestionOption option1 = new QuestionOption(1, "Option 1", "Explanation 1", true);
        QuestionOption option2 = new QuestionOption(2, "Option 2", "Explanation 2", false);
        List<QuestionOption> options = Arrays.asList(option1, option2);
        Topic topic1 = new Topic(1L, "Java");
        List<Topic> topics = Collections.singletonList(topic1);
        Skill skill1 = new Skill(1L, "Programming", 2L);
        List<Skill> skills = Collections.singletonList(skill1);

        MCQ mcq = new MCQ();
        mcq.setId(id);
        mcq.setStem(stem);
        mcq.setOptions(options);
        mcq.setTopics(topics);
        mcq.setSkills(skills);
        mcq.setStatus(status);
        mcq.setPublishedOn(new Date());
        mcq.setPublishedBy("Author");
        mcq.setClosedOn(new Date());
        mcq.setClosedBy("Admin");
        mcq.setCreatedOn(new Date());
        mcq.setCreatedBy("Author");

        return mcq;
    }

    private Topic getTopic (){
        return new Topic(1L, "Java");
    }

    private Skill getSkill (){
        return new Skill(1L, "Programming", 2L);
    }

    @Test
    void testMcqToMcqDto() {
        // Arrange
        MCQ mcq = createMCQ(1L, "What is Java?", "Published");

        // Act
        MCQDto mcqDto = mcqMapper.mcqToMcqDto(mcq);

        // Assert
        assertThat(mcqDto).isNotNull();
        assertThat(mcqDto.getId()).isEqualTo(1L);
        assertThat(mcqDto.getStem()).isEqualTo("What is Java?");
        assertThat(mcqDto.getOptions()).hasSize(2);
        assertThat(mcqDto.getTopics()).hasSize(1);
        assertThat(mcqDto.getSkills()).hasSize(1);
        assertThat(mcqDto.getStatus()).isEqualTo("Published");
        assertThat(mcqDto.getPublishedOn()).isNotNull();
        assertThat(mcqDto.getPublishedBy()).isEqualTo("Author");
        assertThat(mcqDto.getClosedOn()).isNotNull();
        assertThat(mcqDto.getClosedBy()).isEqualTo("Admin");
        assertThat(mcqDto.getCreatedOn()).isNotNull();
        assertThat(mcqDto.getCreatedBy()).isEqualTo("Author");
    }

    @Test
    void testMcqsToMcqDtos() {
        // Arrange
        MCQ mcq1 = createMCQ(1L, "What is Java?", "Published");
        MCQ mcq2 = createMCQ(2L, "What is Spring?", "Draft");
        List<MCQ> mcqs = Arrays.asList(mcq1, mcq2);

        // Act
        List<MCQDto> mcqDtos = mcqMapper.mcqsToMcqDtos(mcqs);

        // Assert
        assertThat(mcqDtos).isNotNull();
        assertThat(mcqDtos).hasSize(2);
        assertThat(mcqDtos.get(0).getId()).isEqualTo(1L);
        assertThat(mcqDtos.get(0).getStem()).isEqualTo("What is Java?");
        assertThat(mcqDtos.get(1).getId()).isEqualTo(2L);
        assertThat(mcqDtos.get(1).getStem()).isEqualTo("What is Spring?");
    }

    @Test
    void testMcqDtoToMcq() {
        // Arrange
        QuestionOption option1 = new QuestionOption(1, "Option 1", "Explanation 1", true);
        QuestionOption option2 = new QuestionOption(2, "Option 2", "Explanation 2", false);
        List<QuestionOption> options = Arrays.asList(option1, option2);

        MCQDto mcqDto = new MCQDto();
        mcqDto.setId(1L);
        mcqDto.setStem("What is Java?");
        mcqDto.setOptions(options);
        mcqDto.setTopics(TopicMapper.INSTANCE.topicsToTopicDtos(Collections.singletonList(getTopic())));
        mcqDto.setSkills(SkillMapper.INSTANCE.skillsToSkillDtos(Collections.singletonList(getSkill())));
        mcqDto.setStatus("Published");
        mcqDto.setPublishedOn(new Date());
        mcqDto.setPublishedBy("Author");
        mcqDto.setClosedOn(new Date());
        mcqDto.setClosedBy("Admin");
        mcqDto.setCreatedOn(new Date());
        mcqDto.setCreatedBy("Author");

        // Act
        MCQ mcq = mcqMapper.mcqDtoToMcq(mcqDto);

        // Assert
        assertThat(mcq).isNotNull();
        assertThat(mcq.getId()).isEqualTo(1L);
        assertThat(mcq.getStem()).isEqualTo("What is Java?");
        assertThat(mcq.getOptions()).hasSize(2);
        assertThat(mcq.getTopics()).hasSize(1);
        assertThat(mcq.getSkills()).hasSize(1);
        assertThat(mcq.getStatus()).isEqualTo("Published");
        assertThat(mcq.getPublishedOn()).isNotNull();
        assertThat(mcq.getPublishedBy()).isEqualTo("Author");
        assertThat(mcq.getClosedOn()).isNotNull();
        assertThat(mcq.getClosedBy()).isEqualTo("Admin");
        assertThat(mcq.getCreatedOn()).isNotNull();
        assertThat(mcq.getCreatedBy()).isEqualTo("Author");
    }
}
