package com.quemistry.question_ms.config;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import org.socialsignin.spring.data.dynamodb.repository.config.EnableDynamoDBRepositories;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableDynamoDBRepositories(basePackages = "com.quemistry.question_ms.repository")
public class DynamoDBConfig {

    private final String amazonAWSAccessKey;

    private final String amazonAWSSecretKey;

    private final String amazonAWSRegion;

    public DynamoDBConfig(
            @Value("${amazon.aws.accesskey}") String amazonAWSAccessKey,
            @Value("${amazon.aws.secretkey}") String amazonAWSSecretKey,
            @Value("${amazon.aws.region}") String amazonAWSRegion) {
        this.amazonAWSAccessKey = amazonAWSAccessKey;
        this.amazonAWSSecretKey = amazonAWSSecretKey;
        this.amazonAWSRegion = amazonAWSRegion;
    }

    @Bean
    public AmazonDynamoDB amazonDynamoDB() {
        return AmazonDynamoDBClientBuilder
                .standard().
                withCredentials(new AWSStaticCredentialsProvider(amazonAWSCredentials()))
                .withRegion(amazonAWSRegion)
                .build();
    }

    @Bean
    public AWSCredentials amazonAWSCredentials() {
        return new BasicAWSCredentials(
                amazonAWSAccessKey, amazonAWSSecretKey);
    }
}
