package com.quemistry.question_ms.repository;


import com.quemistry.question_ms.entity.MCQ;
import com.quemistry.question_ms.entity.Topic;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MCQRepository extends JpaRepository<MCQ, String> {

    List<MCQ> findByTopicsIn(List<Topic> topics);

}
