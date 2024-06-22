package com.quemiztry.question_ms.mapper;

import com.quemiztry.question_ms.entity.MCQ;
import com.quemiztry.question_ms.model.MCQDto;
import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.factory.Mappers;

@Mapper(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface QuestionMapper {

    QuestionMapper INSTANCE = Mappers.getMapper(QuestionMapper.class);

    MCQDto questionToQuestionDto(MCQ MCQ);


    MCQ questionDtoToQuestion(MCQDto MCQDto);
}
