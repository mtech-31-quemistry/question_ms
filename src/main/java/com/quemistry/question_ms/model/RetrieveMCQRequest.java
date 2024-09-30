package com.quemistry.question_ms.model;

import com.quemistry.question_ms.enums.QuestionStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RetrieveMCQRequest {

    private List<Long> topics;
    private List<Long> skills;
    @Builder.Default
    private List<Long> excludeIds = new ArrayList<>();
    @Builder.Default
    private List<QuestionStatus> statuses = new ArrayList<>();


    @Builder.Default
    private Integer pageNumber = 0;
    @Builder.Default
    private Integer pageSize = 60;

}
