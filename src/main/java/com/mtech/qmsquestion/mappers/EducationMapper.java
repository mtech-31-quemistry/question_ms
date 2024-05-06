package com.mtech.qmsquestion.mappers;

import com.mtech.qmsquestion.entity.Education;
import com.mtech.qmsquestion.model.EducationDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface EducationMapper {

    EducationMapper INSTANCE = Mappers.getMapper(EducationMapper.class);

    public EducationDto toDto(Education education);

    @Mapping(target = "userProfile", ignore = true)
    @Mapping(target = "id", ignore = true)
    public Education toEntity(EducationDto educationDto);

}
