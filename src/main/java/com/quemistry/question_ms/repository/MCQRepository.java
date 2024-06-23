package com.quemistry.question_ms.repository;


import com.quemistry.question_ms.entity.MCQ;
import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.data.repository.CrudRepository;

@EnableScan
public interface MCQRepository extends CrudRepository<MCQ, String> {

}
