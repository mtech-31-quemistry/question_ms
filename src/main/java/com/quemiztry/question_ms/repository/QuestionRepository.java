package com.quemiztry.question_ms.repository;


import com.quemiztry.question_ms.entity.MCQ;
import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.data.repository.CrudRepository;

@EnableScan
public interface QuestionRepository extends CrudRepository<MCQ, String> {

}
