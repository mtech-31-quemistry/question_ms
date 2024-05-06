package com.mtech.qmsquestion.controller;

import com.mtech.qmsquestion.model.UserProfileDto;
import com.mtech.qmsquestion.service.UserProfileService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


@Slf4j
@RestController
@RequestMapping("/v1/questions")
public class QuestionController {

    private final UserProfileService userProfileService; // Inject the service

    public QuestionController(UserProfileService userProfileService) {
        this.userProfileService = userProfileService;
    }

    @GetMapping("/test")
    public ResponseEntity<String> test() {
        return ResponseEntity.ok("test ok");
    }

    @GetMapping("/me")
    public ResponseEntity<UserProfileDto> getUserProfile(@RequestHeader("user-id") String userId) {
        log.debug("getUserProfile called with userId " + userId);
            UserProfileDto userProfileDto = userProfileService.findByAccountUuid(userId);
        if (userProfileDto != null) {
            return ResponseEntity.ok(userProfileDto);
        } else {
            UserProfileDto errorResponse = new UserProfileDto();
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
        }
    }

    @PostMapping
    public ResponseEntity<UserProfileDto> saveUser(@RequestHeader HttpHeaders headers, @Validated @RequestBody UserProfileDto userProfileDto) {
        log.debug("saveUser called with headers={},body={}",headers, userProfileDto);
        String userId = headers.getFirst("user-id");
        log.debug("saveUser called with userId={}", userId);
        userProfileDto.setAccountUuid(userId);
        UserProfileDto createdUserProfileDto = userProfileService.saveUserProfile(userProfileDto);
        return ResponseEntity.ok(createdUserProfileDto);
    }
}
