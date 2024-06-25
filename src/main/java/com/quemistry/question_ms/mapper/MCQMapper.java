package com.quemistry.question_ms.mapper;

import com.quemistry.question_ms.entity.MCQ;
import com.quemistry.question_ms.model.MCQDto;
import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface MCQMapper {

    MCQMapper INSTANCE = Mappers.getMapper(MCQMapper.class);

    MCQDto mcqToMcqDto(MCQ MCQ);

    List<MCQDto> mcqsToMcqDtos(List<MCQ> mcqs);


    MCQ mcqDtoToMcq(MCQDto MCQDto);
}
