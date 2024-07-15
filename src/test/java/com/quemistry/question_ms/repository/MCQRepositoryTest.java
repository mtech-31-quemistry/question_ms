package com.quemistry.question_ms.repository;


import com.quemistry.question_ms.entity.MCQ;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@SpringBootTest
class MCQRepositoryTest {

    @Autowired
    private MCQRepository mcqRepository;

    @Test
    void testFindByTopicIds() {
        List<Long> topicIds = Arrays.asList(1L, 2L);
        // Call the repository method
        List<MCQ> result = mcqRepository.findByTopicOrSkill(topicIds, Collections.emptyList());

        Assertions.assertThat(result).isEmpty();
    }

}
