package com.quemistry.question_ms.repository;


import com.quemistry.question_ms.entity.MCQ;
import com.quemistry.question_ms.enums.QuestionStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MCQRepository extends JpaRepository<MCQ, Long> {

    @Query("SELECT q FROM MCQ q JOIN q.topics t JOIN q.skills s WHERE t.id IN :topicIds OR s.id IN :skillIds")
    List<MCQ> findByTopicOrSkill(@Param("topicIds") List<Long> topicIds, @Param("skillIds") List<Long> skillIds);

    @Query("SELECT q FROM MCQ q JOIN q.topics t JOIN q.skills s " +
            "WHERE (t.id IN :topicIds OR s.id IN :skillIds) " +
            "AND q.id NOT IN :excludeIds")
    Page<MCQ> findByTopicOrSkill(@Param("topicIds") List<Long> topicIds,
                                 @Param("skillIds") List<Long> skillIds,
                                 @Param("excludeIds") List<Long> excludeIds,
                                 Pageable pageable);

    @Query("SELECT q FROM MCQ q JOIN q.topics t JOIN q.skills s " +
            "WHERE (t.id IN :topicIds OR s.id IN :skillIds) " +
            "AND q.status IN :statuses " +
            "AND q.id NOT IN :excludeIds")
    Page<MCQ> findByTopicSkillStatus(@Param("topicIds") List<Long> topicIds,
                                 @Param("skillIds") List<Long> skillIds,
                                 @Param("statuses") List<QuestionStatus> statuses,
                                 @Param("excludeIds") List<Long> excludeIds,
                                 Pageable pageable);

    @Query("SELECT q FROM MCQ q WHERE q.id IN :ids")
    Page<MCQ> findByIds( List<Long> ids, Pageable pageable);
}
