package com.testDynamoDB.testDynamoDB.configuration;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DynamoDBConfig {

    @Value("${amazon.access.key}")
    private String awsAccessKey;

    @Value("${amazon.access.secret-key}")
    private String awsSecretkey;

    @Value("${amazon.end-point.url}")
    private String awsDynamoDBEndPoint;

    @Bean
    public AWSCredentials amazonAWSCredentials() {
        return new BasicAWSCredentials(awsAccessKey, awsSecretkey);
    }

    public AWSCredentialsProvider amazonAWSCredentialsProvider() {
        return new AWSStaticCredentialsProvider(amazonAWSCredentials());
    }

    @Bean
    public AmazonDynamoDB amazonDynamoDB() {
        return AmazonDynamoDBClientBuilder
                .standard()
                .withEndpointConfiguration(
                        new AwsClientBuilder
                                .EndpointConfiguration(awsDynamoDBEndPoint, ""))
                .withCredentials(amazonAWSCredentialsProvider())
                .build();
    }

    @Bean
    public DynamoDBMapper mapper() {
        return new DynamoDBMapper(amazonDynamoDB());
    }


}
