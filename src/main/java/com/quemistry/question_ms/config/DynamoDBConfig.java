package com.quemistry.question_ms.config;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.auth.DefaultAWSCredentialsProviderChain;
import com.amazonaws.regions.Region;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;
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

    public DynamoDBConfig(
            @Value("${amazon.aws.accesskey}") String amazonAWSAccessKey,
            @Value("${amazon.aws.secretkey}") String amazonAWSSecretKey) {

        this.amazonAWSAccessKey = amazonAWSAccessKey;
        this.amazonAWSSecretKey = amazonAWSSecretKey;
    }

    @Bean
    public AmazonDynamoDB amazonDynamoDB() {
        AmazonDynamoDB amazonDynamoDB
                = new AmazonDynamoDBClient(amazonAWSCredentials());
        amazonDynamoDB.setRegion(Region.getRegion(Regions.AP_SOUTHEAST_1));

        return amazonDynamoDB;
    }

    @Bean
    public AWSCredentials amazonAWSCredentials() {
        return new BasicAWSCredentials(
                amazonAWSAccessKey, amazonAWSSecretKey);
    }
}
