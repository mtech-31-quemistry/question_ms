package com.quemistry.question_ms.repository;


import com.quemistry.question_ms.entity.MCQ;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MCQRepository extends JpaRepository<MCQ, String> {

    @Query("SELECT q FROM MCQ q JOIN q.topics t WHERE t.id IN :topicIds")
    List<MCQ> findByTopicIds(@Param("topicIds") List<Long> topicIds);

}
