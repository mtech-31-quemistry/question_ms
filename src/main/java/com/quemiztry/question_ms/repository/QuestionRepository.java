package com.quemiztry.question_ms.repository;


import com.quemiztry.question_ms.entity.Question;
import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.data.repository.CrudRepository;

@EnableScan
public interface QuestionRepository extends CrudRepository<Question, String> {

}
