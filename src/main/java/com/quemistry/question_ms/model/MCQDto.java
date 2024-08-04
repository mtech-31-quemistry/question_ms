package com.quemistry.question_ms.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MCQDto {

    private Long id;
    private String stem;
    private List<QuestionOption> options;
    private List<TopicDto> topics;
    private List<SkillDto> skills;
    private String status;
    private Date publishedOn;
    private String publishedBy;
    private Date closedOn;
    private String closedBy;
    private Date updatedTs;
    private Date createdTs;
    private String createdBy;
}
