package com.quemistry.question_ms.model;

import com.quemistry.question_ms.enums.QuestionStatus;
import jakarta.validation.constraints.NotNull;
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
public class SaveMcqRequest {

    @NotNull
    private Long id;
    private String stem;
    private List<QuestionOption> options;
    private List<Long> topics;
    private List<Long> skills;
    private QuestionStatus status;
    private Date publishedOn;
    private String publishedBy;
    private Date closedOn;
    private String closedBy;
    private String updatedBy;
    private Date updatedOn;
    private String createdBy;
    private Date createdOn;

}
