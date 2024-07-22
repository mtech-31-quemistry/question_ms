package com.quemistry.question_ms.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RetrieveMCQByIdsRequest {

    private List<Long> ids;
    private Integer pageNumber = 0; // default
    private Integer pageSize = 60;  // default

}
