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
public class RetrieveMCQResponse {

    private List<MCQDto> mcqs;

    private Integer pageNumber;

    private Integer pageSize;

    private Integer totalPages;

    private Long totalRecords;

    private String keywords;

}
