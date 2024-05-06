package com.mtech.qmsquestion.service;


import com.amazonaws.services.sns.AmazonSNS;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.springframework.boot.test.mock.mockito.MockBean;

public class SnsServiceTest {

    @InjectMocks
    private SnsService snsService;

    @MockBean
    private AmazonSNS amazonSNS;

    void init() {
        amazonSNS = Mockito.mock(AmazonSNS.class);
        snsService = new SnsService(amazonSNS, "sns-topic");
    }

    @Test
    void testSendMessageToSnsTopic_AbleToSendTopic() {
        init();
        String message = "test";
        snsService.sendMessageToSnsTopic(message);
    }
}
