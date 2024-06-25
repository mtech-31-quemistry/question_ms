package com.quemistry.question_ms.entity;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAutoGeneratedKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTypeConverter;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.IOException;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@DynamoDBTable(tableName = "Skill")
public class Skill {

    @DynamoDBHashKey(attributeName = "id")
    @DynamoDBAutoGeneratedKey
    private String id;

    @DynamoDBAttribute(attributeName = "name")
    private String name;

    @DynamoDBAttribute(attributeName = "topic_id")
    private Integer topicId;


    public static class SkillListConverter implements DynamoDBTypeConverter<String, List<Skill>> {
        private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

        @Override
        public String convert(List<Skill> skills) {
            try {
                return OBJECT_MAPPER.writeValueAsString(skills);
            } catch (JsonProcessingException e) {
                throw new RuntimeException("Error converting list of skills to JSON string", e);
            }
        }

        @Override
        public List<Skill> unconvert(String json) {
            try {
                return OBJECT_MAPPER.readValue(json, OBJECT_MAPPER.getTypeFactory().constructCollectionType(List.class, Skill.class));
            } catch (IOException e) {
                throw new RuntimeException("Error converting JSON string to list of skills", e);
            }
        }
    }
}
