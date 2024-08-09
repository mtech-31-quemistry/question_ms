package com.quemistry.question_ms.service;

import com.quemistry.question_ms.model.MCQDto;
import com.quemistry.question_ms.model.RetrieveMCQByIdsRequest;
import com.quemistry.question_ms.model.RetrieveMCQRequest;
import com.quemistry.question_ms.model.RetrieveMCQResponse;
import com.quemistry.question_ms.model.CreateMcqRequest;
import com.quemistry.question_ms.model.SaveMcqRequest;

public interface MCQService {

    MCQDto createQuestion(CreateMcqRequest createMcqRequest);

    MCQDto saveQuestion(SaveMcqRequest saveMcqRequest);

    RetrieveMCQResponse retrieveMCQs(RetrieveMCQRequest retrieveMCQRequest);

    RetrieveMCQResponse retrieveByIds(RetrieveMCQByIdsRequest retrieveMCQByIdsRequest);

    RetrieveMCQResponse retrieveMCQs();


}
