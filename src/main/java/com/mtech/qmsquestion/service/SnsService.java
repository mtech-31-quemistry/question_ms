package com.mtech.qmsquestion.service;

import com.amazonaws.services.sns.AmazonSNS;
import com.amazonaws.services.sns.model.PublishRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class SnsService {

    private final AmazonSNS amazonSNS;

    private final String topicArn;

    @Autowired
    public SnsService(AmazonSNS amazonSNS, @Value("${aws.sns.topic.arn.job-seeker-status-update}") String topicArn) {
        this.amazonSNS = amazonSNS;
        this.topicArn = topicArn;
    }

    public void sendMessageToSnsTopic(String message) {
        log.info("snsMessage sent, topic: {}, message: {}", topicArn, message);
        PublishRequest request = new PublishRequest(topicArn, message);
        amazonSNS.publish(request);
    }
}
