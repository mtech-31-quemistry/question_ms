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
public class CreateMcqRequest {

    private String stem;
    private List<QuestionOption> options;
    private List<Long> topics;
    private List<Long> skills;
//    private String status;
    private Date publishedOn;
    private String publishedBy;
    private Date archivedOn;
    private String archivedBy;
    private Date updatedOn;
    private Date createdOn;
    private String createdBy;
}
