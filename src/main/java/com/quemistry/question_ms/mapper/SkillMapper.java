package com.quemistry.question_ms.mapper;

import com.quemistry.question_ms.entity.Skill;
import com.quemistry.question_ms.model.SkillDto;
import com.quemistry.question_ms.model.TopicDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;


@Mapper
public interface SkillMapper {

    SkillMapper INSTANCE = Mappers.getMapper(SkillMapper.class);


    SkillDto skillToSkillDto(Skill skill);


    Skill skillDtoToSkill(SkillDto skillDto);

    List<SkillDto> skillsToSkillDtos(List<Skill> skills);

    List<Skill> skillDtosToSkills(List<TopicDto> SkillDtos);
}
