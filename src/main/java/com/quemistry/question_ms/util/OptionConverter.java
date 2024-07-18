package com.quemistry.question_ms.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.quemistry.question_ms.model.QuestionOption;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.io.IOException;
import java.util.List;

@Converter
public class OptionConverter implements AttributeConverter<List<QuestionOption>, String> {

    private final static ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public String convertToDatabaseColumn(List<QuestionOption> attribute) {
        try {
            return objectMapper.writeValueAsString(attribute);
        } catch (JsonProcessingException e) {
            throw new IllegalArgumentException("Error converting options to JSON string", e);
        }
    }

    @Override
    public List<QuestionOption> convertToEntityAttribute(String dbData) {
        try {
            return objectMapper.readValue(dbData, new com.fasterxml.jackson.core.type.TypeReference<List<QuestionOption>>() {});
        } catch (IOException e) {
            throw new IllegalArgumentException("Error converting JSON string to options", e);
        }
    }
}
