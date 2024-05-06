package com.mtech.qmsquestion.repository;

import com.mtech.qmsquestion.entity.UserProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;
import java.util.UUID;

public interface UserProfileRepository extends JpaRepository<UserProfile, UUID> {
//    Optional<UserProfile> findById(long id);

    Optional<UserProfile> findByAccountUuid(String accountUuid);

}