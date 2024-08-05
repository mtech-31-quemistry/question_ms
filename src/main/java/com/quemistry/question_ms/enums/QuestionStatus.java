package com.quemistry.question_ms.enums;


import com.quemistry.question_ms.util.BaseEnumConverter;

public enum QuestionStatus implements  BaseEnum{

    DRAFT("DRAFT"),
    PUBLISHED("PUBLISHED"),
    ARCHIVED("ARCHIVED"),
    ;

    private String value;

    QuestionStatus(String value) {
        this.value = value;
    }

    @Override
    public String getValue() {
        return value;
    }

    public static class Converter extends BaseEnumConverter<QuestionStatus> {

        public Converter(){ super(QuestionStatus.class);}
    }
}
