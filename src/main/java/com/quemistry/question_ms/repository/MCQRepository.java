package com.quemistry.question_ms.repository;


import com.quemistry.question_ms.entity.MCQ;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MCQRepository extends JpaRepository<MCQ, Long> {

    @Query("SELECT q FROM MCQ q JOIN q.topics t JOIN q.skills s WHERE t.id IN :topicIds OR s.id IN :skillIds")
//    @Query(value = "SELECT q.* FROM mcq q " +
//            "JOIN mcq_skills ms ON q.id = ms.mcq_id " +
//            "JOIN skill s ON s.id = ms.skill_id " +
//            "WHERE s.topic_id IN :topicIds OR s.id IN :skillIds",
//            nativeQuery = true)
    List<MCQ> findByTopicOrSkill(@Param("topicIds") List<Long> topicIds, @Param("skillIds") List<Long> skillIds);

    @Query("SELECT q FROM MCQ q JOIN q.topics t JOIN q.skills s WHERE t.id IN :topicIds OR s.id IN :skillIds")
//    @Query(value = "SELECT q.* FROM mcq q " +
//            "JOIN mcq_skills ms ON q.id = ms.mcq_id " +
//            "JOIN skill s ON s.id = ms.skill_id " +
//            "WHERE s.topic_id IN :topicIds OR s.id IN :skillIds",
//            nativeQuery = true)
    Page<MCQ> findByTopicOrSkill(@Param("topicIds") List<Long> topicIds, @Param("skillIds") List<Long> skillIds, Pageable pageable);

    @Query("SELECT q FROM MCQ q WHERE q.id IN :ids")
    Page<MCQ> findByIds( List<Long> ids, Pageable pageable);
}
