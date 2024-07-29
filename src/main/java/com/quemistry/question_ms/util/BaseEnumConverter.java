package com.quemistry.question_ms.util;

import com.quemistry.question_ms.enums.BaseEnum;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.util.stream.Stream;

@Converter(
        autoApply = true
)
public abstract class BaseEnumConverter <T extends Enum<T> & BaseEnum> implements AttributeConverter<T, String> {
    private final Class<T> clazz;

    public BaseEnumConverter(Class<T> clazz) {
        this.clazz = clazz;
    }

    @Override
    public String convertToDatabaseColumn(T attribute) {
        return attribute == null ? null : ((BaseEnum)attribute).getValue();
    }

    @Override
    public T convertToEntityAttribute(String dbData) {
        return dbData == null ? null : (T)
                Stream.of((Enum[])this.clazz.getEnumConstants()).filter(
                (data) -> {
                    return ((BaseEnum)data).getValue().equalsIgnoreCase(dbData);
                }
        ).findFirst().orElseThrow(IllegalArgumentException::new);
    }
}
