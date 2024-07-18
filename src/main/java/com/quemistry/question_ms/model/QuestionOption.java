package com.quemistry.question_ms.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class QuestionOption {
    private Integer no;

    private String text;

    private String explanation;

    private Boolean isAnswer;
}
