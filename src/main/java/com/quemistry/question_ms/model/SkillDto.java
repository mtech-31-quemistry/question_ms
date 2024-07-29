package com.quemistry.question_ms.model;


import com.quemistry.question_ms.enums.SkillStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SkillDto {

    private Long id;
    private String name;
    private SkillStatus status;
//    private Long topicId;
}
