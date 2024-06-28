package com.quemistry.question_ms.config;

import com.amazonaws.ClientConfigurationFactory;
import com.amazonaws.Protocol;
import com.amazonaws.auth.DefaultAWSCredentialsProviderChain;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import org.socialsignin.spring.data.dynamodb.repository.config.EnableDynamoDBRepositories;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableDynamoDBRepositories(basePackages = "com.quemistry.question_ms.repository")
public class DynamoDBConfig {
    @Bean
    public AmazonDynamoDB amazonDynamoDB() {

        var clientConfiguration = new ClientConfigurationFactory();
        clientConfiguration.getConfig().setProtocol(Protocol.HTTP);

        return AmazonDynamoDBClientBuilder
                .standard()
                .withCredentials(DefaultAWSCredentialsProviderChain.getInstance())
                .withClientConfiguration(clientConfiguration.getConfig())
                .build();
    }

//    @Bean
//    public AmazonDynamoDB amazonDynamoDB() {
//        return AmazonDynamoDBClientBuilder.standard()
//                .withEndpointConfiguration(new AwsClientBuilder.EndpointConfiguration("http://localhost:8000", "ap-southeast-1"))
//                .build();
//    }
}
