package com.quemistry.question_ms.controller;

import com.quemistry.question_ms.model.RetrieveTopicsResponse;
import com.quemistry.question_ms.model.SaveTopicsRequest;
import com.quemistry.question_ms.service.TopicService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/v1/questions/topics")
public class TopicController {

    private final TopicService topicService;

    public TopicController(TopicService topicService) {
        this.topicService = topicService;
    }

    @GetMapping
    public ResponseEntity<RetrieveTopicsResponse> getTopics(@RequestHeader HttpHeaders headers) {
        return ResponseEntity.ok(topicService.getTopics());
    }

    @PostMapping
    public ResponseEntity<RetrieveTopicsResponse> saveTopics(@RequestHeader HttpHeaders headers, @RequestBody SaveTopicsRequest saveTopicsRequest) {
        return ResponseEntity.ok(topicService.saveTopics(saveTopicsRequest));
    }

}
