package com.quemistry.question_ms.enums;


import com.quemistry.question_ms.util.BaseEnumConverter;

public enum TopicStatus  implements  BaseEnum{

    ACTIVE("ACTIVE"),
    INACTIVE("INACTIVE"),
    ;

    private String value;

    TopicStatus(String value) {
        this.value = value;
    }

    @Override
    public String getValue() {
        return value;
    }

    public static class Converter extends BaseEnumConverter<TopicStatus> {

        public Converter(){ super(TopicStatus.class);}
    }
}
