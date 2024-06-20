package com.quemiztry.question_ms.controller;

import com.quemiztry.question_ms.model.QuestionDto;
import com.quemiztry.question_ms.service.QuestionService;
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
@RequestMapping("/v1/questions")
public class QuestionController {

    private final QuestionService questionService;

    public QuestionController(QuestionService questionService) {
        this.questionService = questionService;
    }


    @GetMapping("/test")
    public ResponseEntity<String> test() {
        return ResponseEntity.ok("test ok");
    }

    @PostMapping
    public ResponseEntity<QuestionDto> saveQuestion(@RequestHeader HttpHeaders headers, @RequestBody QuestionDto questionDto) {
        return ResponseEntity.ok(questionService.saveQuestion(questionDto));
    }
}
