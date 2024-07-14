package com.quemistry.question_ms.service;

import com.quemistry.question_ms.model.MCQDto;
import com.quemistry.question_ms.model.RetrieveMCQRequest;
import com.quemistry.question_ms.model.RetrieveMCQResponse;

public interface MCQService {

    MCQDto saveQuestion(MCQDto MCQDto);

    RetrieveMCQResponse retrieveMCQs(RetrieveMCQRequest retrieveMCQRequest);

    RetrieveMCQResponse retrieveMCQs();
}
