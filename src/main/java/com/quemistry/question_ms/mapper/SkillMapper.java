package com.quemistry.question_ms.mapper;

import com.quemistry.question_ms.entity.Skill;
import com.quemistry.question_ms.model.SkillDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;


@Mapper
public interface SkillMapper {

    SkillMapper INSTANCE = Mappers.getMapper(SkillMapper.class);

//    @Mapping(source = "topic.id", target = "topicId")
    SkillDto skillToSkillDto(Skill skill);

//    @Mapping(source = "topicId", target = "topic.id")
    Skill skillDtoToSkill(SkillDto skillDto);

//    @Mapping(source = "topic.id", target = "topicId")
    List<SkillDto> skillsToSkillDtos(List<Skill> skills);

//    @Mapping(source = "topicId", target = "topic.id")
    List<Skill> skillDtosToSkills(List<SkillDto> SkillDtos);
}
