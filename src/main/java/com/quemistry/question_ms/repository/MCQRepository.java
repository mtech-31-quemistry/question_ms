package com.quemistry.question_ms.repository;


import com.quemistry.question_ms.entity.MCQ;
import com.quemistry.question_ms.entity.Topic;
import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

@EnableScan
public interface MCQRepository extends CrudRepository<MCQ, String> {

    List<MCQ> findByTopicsIn(List<Topic> topics);

}
