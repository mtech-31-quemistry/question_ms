package com.mtech.qmsquestion.repository;

import com.mtech.qmsquestion.entity.UserProfile;
import com.mtech.qmsquestion.entity.WorkExperience;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WorkExperienceRepository extends JpaRepository<WorkExperience, Long> {

    int deleteByUserProfile(UserProfile userProfile);

}