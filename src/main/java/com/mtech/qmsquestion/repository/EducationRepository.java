package com.mtech.qmsquestion.repository;

import com.mtech.qmsquestion.entity.Education;
import com.mtech.qmsquestion.entity.UserProfile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EducationRepository extends JpaRepository<Education, Long> {

    int deleteByUserProfile(UserProfile userProfile);

}