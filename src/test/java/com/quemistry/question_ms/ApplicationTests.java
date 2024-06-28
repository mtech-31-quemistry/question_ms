package com.quemistry.question_ms;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.test.context.TestPropertySource;

@SpringBootTest
@TestPropertySource(properties = {
		"amazon.aws.accesskey=testValue",
		"amazon.aws.secretkey=testValue",
		"amazon.aws.region=testValue"
})
class ApplicationTests {

	@Test
	void contextLoads() {
	}

}
