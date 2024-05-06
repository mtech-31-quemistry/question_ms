package com.mtech.qmsquestion.service;

import com.mtech.qmsquestion.model.UpdateUserDto;
import com.mtech.qmsquestion.model.UserProfileDto;

public interface UserProfileService {

    UserProfileDto findByAccountUuid(String accountUuid);

    UserProfileDto updateUserProfile(String accountUuid, UpdateUserDto updateUserDto);

    UserProfileDto saveUserProfile(UserProfileDto userProfileDto);
}
