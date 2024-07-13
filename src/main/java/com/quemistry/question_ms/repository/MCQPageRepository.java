package com.quemistry.question_ms.repository;


import com.quemistry.question_ms.entity.MCQ;
import com.quemistry.question_ms.entity.Topic;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface MCQPageRepository extends PagingAndSortingRepository<MCQ, String> {

    Page<MCQ> findByTopicsIn(List<Topic> topics, Pageable pageable);

}
