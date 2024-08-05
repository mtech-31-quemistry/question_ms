package com.quemistry.question_ms.mapper;

import com.quemistry.question_ms.entity.MCQ;
import com.quemistry.question_ms.entity.Skill;
import com.quemistry.question_ms.entity.Topic;
import com.quemistry.question_ms.enums.QuestionStatus;
import com.quemistry.question_ms.enums.SkillStatus;
import com.quemistry.question_ms.enums.TopicStatus;
import com.quemistry.question_ms.model.MCQDto;
import com.quemistry.question_ms.model.QuestionOption;
import com.quemistry.question_ms.model.SaveMcqRequest;
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
        mcq.setClosedOn(new Date());
        mcq.setClosedBy("Admin");
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
        assertThat(mcqDto.getClosedOn()).isNotNull();
        assertThat(mcqDto.getClosedBy()).isEqualTo("Admin");
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
    void testMcqDtoToMcq() {
        // Arrange
        QuestionOption option1 = new QuestionOption(1, "Option 1", "Explanation 1", true);
        QuestionOption option2 = new QuestionOption(2, "Option 2", "Explanation 2", false);
        List<QuestionOption> options = Arrays.asList(option1, option2);

        SaveMcqRequest saveMcqRequest = new SaveMcqRequest();
        saveMcqRequest.setId(1L);
        saveMcqRequest.setStem("What is Java?");
        saveMcqRequest.setOptions(options);
        saveMcqRequest.setTopics(List.of(1L));
        saveMcqRequest.setSkills(List.of(2L));
        saveMcqRequest.setStatus("PUBLISHED");
        saveMcqRequest.setPublishedOn(new Date());
        saveMcqRequest.setPublishedBy("Author");
        saveMcqRequest.setClosedOn(new Date());
        saveMcqRequest.setClosedBy("Admin");
        saveMcqRequest.setCreatedTs(new Date());
        saveMcqRequest.setCreatedBy("Author");

        // Act
        MCQ mcq = mcqMapper.mcqDtoToMcq(saveMcqRequest);

        // Assert
        assertThat(mcq).isNotNull();
        assertThat(mcq.getId()).isEqualTo(1L);
        assertThat(mcq.getStem()).isEqualTo("What is Java?");
        assertThat(mcq.getOptions()).hasSize(2);
        assertThat(mcq.getStatus().getValue()).isEqualTo("PUBLISHED");
        assertThat(mcq.getPublishedOn()).isNotNull();
        assertThat(mcq.getPublishedBy()).isEqualTo("Author");
        assertThat(mcq.getClosedOn()).isNotNull();
        assertThat(mcq.getClosedBy()).isEqualTo("Admin");
        assertThat(mcq.getCreatedTs()).isNotNull();
        assertThat(mcq.getCreatedBy()).isEqualTo("Author");
    }
}
