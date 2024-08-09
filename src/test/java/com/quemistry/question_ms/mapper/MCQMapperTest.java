package com.quemistry.question_ms.mapper;

import com.quemistry.question_ms.entity.MCQ;
import com.quemistry.question_ms.entity.Skill;
import com.quemistry.question_ms.entity.Topic;
import com.quemistry.question_ms.enums.QuestionStatus;
import com.quemistry.question_ms.enums.SkillStatus;
import com.quemistry.question_ms.enums.TopicStatus;
import com.quemistry.question_ms.model.CreateMcqRequest;
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

    private MCQ createMCQ(Long id, String stem, QuestionStatus status) {
        QuestionOption option1 = new QuestionOption(1, "Option 1", "Explanation 1", true);
        QuestionOption option2 = new QuestionOption(2, "Option 2", "Explanation 2", false);
        List<QuestionOption> options = Arrays.asList(option1, option2);

        Skill skill1 = getSkill();
        List<Skill> skills = Collections.singletonList(skill1);
        Topic topic1 = getTopic();
        List<Topic> topics = Collections.singletonList(topic1);

        MCQ mcq = new MCQ();
        mcq.setId(id);
        mcq.setStem(stem);
        mcq.setOptions(options);
        mcq.setTopics(topics);
        mcq.setSkills(skills);
        mcq.setStatus(status);
        mcq.setPublishedOn(new Date());
        mcq.setPublishedBy("Author");
        mcq.setArchivedOn(new Date());
        mcq.setArchivedBy("Admin");
        mcq.setCreatedTs(new Date());
        mcq.setCreatedBy("Author");

        return mcq;
    }

    private Topic getTopic (){
        return new Topic(1L, "Java", TopicStatus.ACTIVE, Collections.singletonList(getSkill()));
    }

    private Skill getSkill (){
        return new Skill(1L, "Programming", SkillStatus.ACTIVE);
    }

    @Test
    void testMcqToMcqDto() {
        // Arrange
        MCQ mcq = createMCQ(1L, "What is Java?", QuestionStatus.PUBLISHED);

        // Act
        MCQDto mcqDto = mcqMapper.mcqToMcqDto(mcq);

        // Assert
        assertThat(mcqDto).isNotNull();
        assertThat(mcqDto.getId()).isEqualTo(1L);
        assertThat(mcqDto.getStem()).isEqualTo("What is Java?");
        assertThat(mcqDto.getOptions()).hasSize(2);
        assertThat(mcqDto.getTopics()).hasSize(1);
        assertThat(mcqDto.getSkills()).hasSize(1);
        assertThat(mcqDto.getStatus()).isEqualTo(QuestionStatus.PUBLISHED);
        assertThat(mcqDto.getPublishedOn()).isNotNull();
        assertThat(mcqDto.getPublishedBy()).isEqualTo("Author");
        assertThat(mcqDto.getArchivedOn()).isNotNull();
        assertThat(mcqDto.getArchivedBy()).isEqualTo("Admin");
        assertThat(mcqDto.getCreatedTs()).isNotNull();
        assertThat(mcqDto.getCreatedBy()).isEqualTo("Author");
    }

    @Test
    void testMcqsToMcqDtos() {
        // Arrange
        MCQ mcq1 = createMCQ(1L, "What is Java?", QuestionStatus.PUBLISHED);
        MCQ mcq2 = createMCQ(2L, "What is Spring?", QuestionStatus.DRAFT);
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
    void testCreateMcqRequestToMcq() {
        // Arrange
        QuestionOption option1 = new QuestionOption(1, "Option 1", "Explanation 1", true);
        QuestionOption option2 = new QuestionOption(2, "Option 2", "Explanation 2", false);
        List<QuestionOption> options = Arrays.asList(option1, option2);

        CreateMcqRequest createMcqRequest = new CreateMcqRequest();
        createMcqRequest.setStem("What is Java?");
        createMcqRequest.setOptions(options);
        createMcqRequest.setTopics(List.of(1L));
        createMcqRequest.setSkills(List.of(2L));
        createMcqRequest.setPublishedOn(new Date());
        createMcqRequest.setPublishedBy("Author");
        createMcqRequest.setArchivedOn(new Date());
        createMcqRequest.setArchivedBy("Admin");
        createMcqRequest.setCreatedTs(new Date());
        createMcqRequest.setCreatedBy("Author");

        // Act
        MCQ mcq = mcqMapper.createMcqRequestToMcq(createMcqRequest);

        // Assert
        assertThat(mcq).isNotNull();
        assertThat(mcq.getStem()).isEqualTo("What is Java?");
        assertThat(mcq.getOptions()).hasSize(2);
        assertThat(mcq.getStatus()).isEqualTo(QuestionStatus.DRAFT);
        assertThat(mcq.getPublishedOn()).isNotNull();
        assertThat(mcq.getPublishedBy()).isEqualTo("Author");
        assertThat(mcq.getArchivedOn()).isNotNull();
        assertThat(mcq.getArchivedBy()).isEqualTo("Admin");
        assertThat(mcq.getCreatedTs()).isNotNull();
        assertThat(mcq.getCreatedBy()).isEqualTo("Author");
    }
}
