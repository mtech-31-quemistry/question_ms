package com.quemistry.question_ms.repository;


import com.quemistry.question_ms.entity.MCQ;
import com.quemistry.question_ms.entity.Topic;
import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

@EnableScan
public interface MCQPageRepository extends PagingAndSortingRepository<MCQ, String> {

    Page<MCQ> findByTopicsIn(List<Topic> topics, Pageable pageable);

}
