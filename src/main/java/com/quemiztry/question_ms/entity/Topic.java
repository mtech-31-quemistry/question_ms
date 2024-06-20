package com.quemiztry.question_ms.entity;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@DynamoDBTable(tableName = "Option")
public class Topic {

    @DynamoDBAttribute(attributeName = "id")
    private Integer id;

    @DynamoDBAttribute(attributeName = "name")
    private String name;
}
