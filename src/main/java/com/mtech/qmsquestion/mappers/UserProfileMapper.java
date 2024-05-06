package com.mtech.qmsquestion.mappers;

import com.mtech.qmsquestion.entity.UserProfile;
import com.mtech.qmsquestion.model.UserProfileDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper (uses = {EducationMapper.class, WorkExperienceMapper.class})
public interface UserProfileMapper {

    UserProfileMapper INSTANCE = Mappers.getMapper(UserProfileMapper.class);

    public UserProfileDto toDto(UserProfile userProfile);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "workExperience", ignore = true)
    @Mapping(target = "education", ignore = true)
    public UserProfile toEntity(UserProfileDto userProfileDto);

}
