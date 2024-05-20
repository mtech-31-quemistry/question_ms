package com.quemiztry.question_ms.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/v1/questions")
public class QuestionController {



    @GetMapping("/test")
    public ResponseEntity<String> test() {
        return ResponseEntity.ok("test ok");
    }

}
