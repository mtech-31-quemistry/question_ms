package com.quemistry.question_ms.mapper;

import com.quemistry.question_ms.entity.MCQ;
import com.quemistry.question_ms.model.MCQDto;
import com.quemistry.question_ms.model.SaveMcqRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE, uses = {TopicMapper.class, SkillMapper.class} )
public interface MCQMapper {

    MCQMapper INSTANCE = Mappers.getMapper(MCQMapper.class);

    @Mapping(source = "topics", target = "topics")
    MCQDto mcqToMcqDto(MCQ MCQ);

    @Mapping(source = "topics", target = "topics")
    List<MCQDto> mcqsToMcqDtos(List<MCQ> mcqs);

    @Mapping(source = "topics", target = "topics", ignore = true)
    @Mapping(source = "skills", target = "skills", ignore = true)
    MCQ mcqDtoToMcq(SaveMcqRequest saveMcqRequest);
}
