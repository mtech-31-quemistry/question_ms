package com.quemistry.question_ms.model;

import com.quemistry.question_ms.enums.TopicStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TopicDto {

    private Long id;
    private String name;
    private TopicStatus status;
    private List<SkillDto> skills;
}
