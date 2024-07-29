package com.quemistry.question_ms.enums;


import com.quemistry.question_ms.util.BaseEnumConverter;

public enum SkillStatus implements BaseEnum{

    ACTIVE("ACTIVE"),
    INACTIVE("INACTIVE"),
    ;

    private String value;

    SkillStatus(String value) {
        this.value = value;
    }

    @Override
    public String getValue() {
        return value;
    }

    public static class Converter extends BaseEnumConverter<SkillStatus> {

        public Converter(){ super(SkillStatus.class);}
    }
}
