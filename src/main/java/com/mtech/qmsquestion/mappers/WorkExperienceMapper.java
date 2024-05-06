package com.mtech.qmsquestion.mappers;

import com.mtech.qmsquestion.entity.WorkExperience;
import com.mtech.qmsquestion.model.WorkExperienceDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface WorkExperienceMapper {

    WorkExperienceMapper INSTANCE = Mappers.getMapper(WorkExperienceMapper.class);

    public WorkExperienceDto toDto(WorkExperience workExperience);

    @Mapping(target = "userProfile", ignore = true)
    @Mapping(target = "id", ignore = true)
    public WorkExperience toEntity(WorkExperienceDto workExperienceDto);

}
