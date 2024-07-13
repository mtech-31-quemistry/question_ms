package com.quemistry.question_ms.repository;


import org.springframework.stereotype.Repository;

@Repository
public class MCQCustomRepositoryImpl implements MCQCustomRepository{

//    @Autowired
//    private DynamoDBMapper dynamoDBMapper;

////    @Override
//    public List<MCQ> findMCQsByTopics(List<Topic> topics) {
//        List<String> topicNames = topics.stream().map(Topic::getName).collect(Collectors.toList());
//
//        // Define your query expression based on the topics list
//        Map<String, AttributeValue> eav = new HashMap<>();
//        eav.put(":val1", new AttributeValue().withSS(topicNames));
//
//        DynamoDBQueryExpression<MCQ> queryExpression = new DynamoDBQueryExpression<MCQ>()
//                .withIndexName("topics-index")
//                .withConsistentRead(false)
//                .withExpressionAttributeValues(eav)
//                .withFilterExpression("topics IN :val1");
//
////        DynamoDBQueryExpression<MCQ> queryExpression = new DynamoDBQueryExpression<MCQ>()
////                .withIndexName("topics-index")
////                .withKeyConditionExpression("topics IN :val1")
////                .withExpressionAttributeValues(eav);
//
//        return dynamoDBMapper.query(MCQ.class, queryExpression);
//    }
}
