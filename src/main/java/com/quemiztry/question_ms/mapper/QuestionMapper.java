package com.quemiztry.question_ms.mapper;

import com.quemiztry.question_ms.entity.Question;
import com.quemiztry.question_ms.model.QuestionDto;
import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.factory.Mappers;

@Mapper(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface QuestionMapper {

    QuestionMapper INSTANCE = Mappers.getMapper(QuestionMapper.class);

    QuestionDto questionToQuestionDto(Question question);


    Question questionDtoToQuestion(QuestionDto questionDto);
}
