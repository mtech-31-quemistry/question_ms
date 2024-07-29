package com.quemistry.question_ms.service;

import com.quemistry.question_ms.model.RetrieveTopicsResponse;
import com.quemistry.question_ms.model.SaveTopicsRequest;

public interface TopicService {

    RetrieveTopicsResponse getTopics();

    RetrieveTopicsResponse saveTopics(SaveTopicsRequest saveTopicsRequest);
}
