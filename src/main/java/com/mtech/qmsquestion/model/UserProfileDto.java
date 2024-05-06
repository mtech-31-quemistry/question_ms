package com.mtech.qmsquestion.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserProfileDto {

    @JsonIgnore
    private long id;
    @JsonIgnore
    private String accountUuid;
    private String email;
    private String name;
    private boolean seeking;
    private String jobTitle;
    private String image;
    private String about;
    private List<WorkExperienceDto> workExperience;
    private List<EducationDto> education;

}