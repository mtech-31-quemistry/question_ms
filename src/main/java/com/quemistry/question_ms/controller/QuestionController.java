package com.quemistry.question_ms.controller;

import com.quemistry.question_ms.model.MCQDto;
import com.quemistry.question_ms.model.RetrieveMCQRequest;
import com.quemistry.question_ms.model.RetrieveMCQResponse;
import com.quemistry.question_ms.service.MCQService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/v1/questions")
public class QuestionController {

    private final MCQService mcqService;

    public QuestionController(MCQService mcqService) {
        this.mcqService = mcqService;
    }

    @GetMapping("health")
    public ResponseEntity<Object> health(){
        Map<String, String> responseBody = new HashMap<>();
        responseBody.put("service", "auth");
        responseBody.put("status", "UP");
        return ResponseEntity.status(HttpStatus.OK).body(responseBody);
    }


    @PostMapping
    public ResponseEntity<MCQDto> saveQuestion(@RequestHeader HttpHeaders headers, @RequestBody MCQDto MCQDto) {
        log.info("POST /v1/questions");
        return ResponseEntity.ok(mcqService.saveQuestion(MCQDto));
    }

    @GetMapping
    public ResponseEntity<RetrieveMCQResponse> getQuestions(@RequestHeader HttpHeaders headers) {
        log.info("GET /v1/questions");
        return ResponseEntity.ok(mcqService.retrieveMCQs());
    }

//    @PostMapping("retrieve")
//    public ResponseEntity<RetrieveMCQResponse> retrieveQuestion(@RequestHeader HttpHeaders headers, @RequestBody RetrieveMCQRequest retrieveMCQRequest) {
//        log.info("POST /v1/questions/retrieve");
//        return ResponseEntity.ok(mcqService.retrieveMCQs(retrieveMCQRequest));
//    }
}
