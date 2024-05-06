package com.mtech.qmsquestion.service;

import com.mtech.qmsquestion.entity.Education;
import com.mtech.qmsquestion.entity.UserProfile;
import com.mtech.qmsquestion.entity.WorkExperience;
import com.mtech.qmsquestion.model.EducationDto;
import com.mtech.qmsquestion.model.UpdateUserDto;
import com.mtech.qmsquestion.model.UserProfileDto;
import com.mtech.qmsquestion.model.WorkExperienceDto;
import com.mtech.qmsquestion.repository.EducationRepository;
import com.mtech.qmsquestion.repository.UserProfileCustomRepository;
import com.mtech.qmsquestion.repository.UserProfileRepository;
import com.mtech.qmsquestion.repository.WorkExperienceRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.Optional;

import static org.mockito.Mockito.*;

class UserProfileServiceImplTest {

    @InjectMocks
    private UserProfileServiceImpl userProfileService;

    @Mock
    private UserProfileRepository userProfileRepository;

    @Mock
    private UserProfileCustomRepository userProfileCustomRepository;

    @Mock
    private EducationRepository educationRepository;

    @Mock
    private WorkExperienceRepository workExperienceRepository;

    @Mock
    private SnsService snsService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    // Write tests for findByAccountUuid method
    @Test
    void testFindByAccountUuid() {
        // Create a UserProfile instance for testing
        UserProfile userProfile = getUserProfile();

        // Define the behavior of userProfileRepository.findByAccountUuid
        Mockito.when(userProfileRepository.findByAccountUuid("testAccountUuid"))
                .thenReturn(Optional.of(userProfile));
        Mockito.when(userProfileCustomRepository.findByAccountUuid("testAccountUuid"))
                .thenReturn(Optional.of(userProfile));

        // Call the method to be tested
        UserProfileDto result = userProfileService.findByAccountUuid("testAccountUuid");

        // Assertions to verify the result
        Assertions.assertNotNull(result);
        Assertions.assertEquals("testAccountUuid", result.getAccountUuid());
        Assertions.assertEquals(userProfile.getAccountUuid(), result.getAccountUuid());
        Assertions.assertEquals(userProfile.getId(), result.getId());
        Assertions.assertEquals(userProfile.getName(), result.getName());
        Assertions.assertEquals(userProfile.getImage(), result.getImage());
        Assertions.assertEquals(userProfile.getAbout(), result.getAbout());
        Assertions.assertEquals(userProfile.getJobTitle(), result.getJobTitle());

        Assertions.assertNotNull(result.getEducation());
        Assertions.assertEquals(userProfile.getEducation().size(), result.getEducation().size());
        Assertions.assertEquals(userProfile.getEducation().get(0).getId(), result.getEducation().get(0).getId());
        Assertions.assertEquals(userProfile.getEducation().get(0).getSchool(), result.getEducation().get(0).getSchool());
        Assertions.assertEquals(userProfile.getEducation().get(0).getLogo(), result.getEducation().get(0).getLogo());
        Assertions.assertEquals(userProfile.getEducation().get(0).getYearStart(), result.getEducation().get(0).getYearStart());
        Assertions.assertEquals(userProfile.getEducation().get(0).getYearEnd(), result.getEducation().get(0).getYearEnd());
        Assertions.assertEquals(userProfile.getEducation().get(0).getDescription(), result.getEducation().get(0).getDescription());

        Assertions.assertNotNull(result.getWorkExperience());
        Assertions.assertEquals(userProfile.getWorkExperience().size(), result.getWorkExperience().size());
        Assertions.assertEquals(userProfile.getWorkExperience().get(0).getId(), result.getWorkExperience().get(0).getId());
        Assertions.assertEquals(userProfile.getWorkExperience().get(0).getCompany(), result.getWorkExperience().get(0).getCompany());
        Assertions.assertEquals(userProfile.getWorkExperience().get(0).getLogo(), result.getWorkExperience().get(0).getLogo());
        Assertions.assertEquals(userProfile.getWorkExperience().get(0).getYearStart(), result.getWorkExperience().get(0).getYearStart());
        Assertions.assertEquals(userProfile.getWorkExperience().get(0).getYearEnd(), result.getWorkExperience().get(0).getYearEnd());
        Assertions.assertEquals(userProfile.getWorkExperience().get(0).getExperience(), result.getWorkExperience().get(0).getExperience());
        Assertions.assertEquals(userProfile.getWorkExperience().get(0).getJobTitle(), result.getWorkExperience().get(0).getJobTitle());
    }

    private static UserProfile getUserProfile() {
        var workingExperience = new WorkExperience();
        workingExperience.setLogo("logo");
        workingExperience.setExperience("experience");
        workingExperience.setJobTitle("jobTitle");
        workingExperience.setYearStart("start");
        workingExperience.setYearEnd("end");
        workingExperience.setCompany("company");
        workingExperience.setId(2L);

        var workingExperiences = new ArrayList<WorkExperience>();
        workingExperiences.add(workingExperience);

        var educationalExperience = new Education();
        educationalExperience.setId(1L);
        educationalExperience.setLogo("logo");
        educationalExperience.setDescription("description");
        educationalExperience.setSchool("school");
        educationalExperience.setYearStart("start");
        educationalExperience.setYearEnd("end");


        var educationalExperiences = new ArrayList<Education>();
        educationalExperiences.add(educationalExperience);

        UserProfile userProfile = new UserProfile();
        userProfile.setAccountUuid("testAccountUuid");
        userProfile.setSeeking(false);
        userProfile.setId(1L);
        userProfile.setAbout("about");
        userProfile.setImage("image");
        userProfile.setEducation(educationalExperiences);
        userProfile.setWorkExperience(workingExperiences);
        userProfile.setJobTitle("jobTitle");
        userProfile.setName("name");
        return userProfile;
    }

    @Test
    void testFindByAccountUuid_UserProfileDoesNotExist() {
        // Create a UserProfile instance for testing
        UserProfile userProfile = new UserProfile();
        userProfile.setAccountUuid("testAccountUuid");

        // Define the behavior of userProfileRepository.findByAccountUuid
        Mockito.when(userProfileRepository.findByAccountUuid("testAccountUuid"))
                .thenReturn(Optional.empty());

        Assertions.assertThrows(ResponseStatusException.class, () -> userProfileService.findByAccountUuid("testAccountUuid"));
    }

    // Write tests for updateUserProfile method
    @Test
    void testUpdateUserProfile() {
        // Create a UserProfile instance for testing
        UserProfile userProfile = new UserProfile();
        userProfile.setAccountUuid("testAccountUuid");

        // Define the behavior of userProfileRepository.findByAccountUuid
        Mockito.when(userProfileCustomRepository.findByAccountUuid("testAccountUuid"))
                .thenReturn(Optional.of(userProfile));
        Mockito.when(userProfileRepository.findByAccountUuid("testAccountUuid"))
                .thenReturn(Optional.of(userProfile));

        // Define the behavior of snsService.sendMessageToSnsTopic
        Mockito.doNothing().when(snsService).sendMessageToSnsTopic(Mockito.anyString());

        // Create an UpdateUserDto for testing
        UpdateUserDto updateUserDto = new UpdateUserDto();
        updateUserDto.setSeekingJob(true);

        // Call the method to be tested
        UserProfileDto result = userProfileService.updateUserProfile("testAccountUuid", updateUserDto);

        // Assertions to verify the result
        Assertions.assertNotNull(result);
        Assertions.assertEquals("testAccountUuid", result.getAccountUuid());
        Assertions.assertTrue(result.isSeeking());
    }

    @Test
    void testUpdateUserProfile_UserProfileDoesNotExist_ThrowException() {
        // Create a UserProfile instance for testing
        UserProfile userProfile = new UserProfile();
        userProfile.setAccountUuid("testAccountUuid");

        // Define the behavior of userProfileRepository.findByAccountUuid
        Mockito.when(userProfileRepository.findByAccountUuid(Mockito.anyString()))
                .thenReturn(Optional.empty());

        // Define the behavior of snsService.sendMessageToSnsTopic
        Mockito.doNothing().when(snsService).sendMessageToSnsTopic(Mockito.anyString());

        // Create an UpdateUserDto for testing
        UpdateUserDto updateUserDto = new UpdateUserDto();
        updateUserDto.setSeekingJob(true);

        Assertions.assertThrows(ResponseStatusException.class, () -> userProfileService.updateUserProfile("testAccountUuid", updateUserDto));

    }

    @Test
    void testSaveUserProfile() {
        // Mock UserProfileDto
        UserProfileDto userProfileDto = new UserProfileDto();
        userProfileDto.setAccountUuid("accountUuid");
        // Populate userProfileDto with necessary fields

        // Mock UserProfile and UserProfileOptional
        UserProfile oldUserProfile = new UserProfile();
        oldUserProfile.setId(1L);
        oldUserProfile.setAccountUuid("accountUuid");
        // Populate oldUserProfile with necessary fields
        Optional<UserProfile> userProfileOptional = Optional.of(oldUserProfile);

        // expected dto
        UserProfileDto expectedUserProfileDto = new UserProfileDto();
        expectedUserProfileDto.setId(1L);
        expectedUserProfileDto.setAccountUuid("accountUuid");

        // Mock repositories behavior
        when(userProfileRepository.findByAccountUuid(any())).thenReturn(userProfileOptional);
        when(userProfileRepository.saveAndFlush(any())).thenReturn(oldUserProfile);

        when(userProfileCustomRepository.findByAccountUuid(any())).thenReturn(userProfileOptional);
        when(userProfileCustomRepository.saveAndFlush(any())).thenReturn(oldUserProfile);

        // Call the method under test
        UserProfileDto resultUserProfileDto = userProfileService.saveUserProfile(userProfileDto);

        // Asserts or verifications based on the behavior of the method
        // For example:
        // Verify that findByAccountUuid was called once
        verify(userProfileCustomRepository, times(1)).findByAccountUuid(any());

        // Assert that the returned UserProfileDto matches expectations
        Assertions.assertEquals (expectedUserProfileDto, resultUserProfileDto);
    }

    @Test
    void testSaveUserProfile_AbleToUpdate() {
        var workingExperience = new WorkExperienceDto();
        workingExperience.setLogo("logo");
        workingExperience.setExperience("experience");
        workingExperience.setJobTitle("jobTitle");
        workingExperience.setYearStart("start");
        workingExperience.setYearEnd("end");
        workingExperience.setCompany("company");
        workingExperience.setId(0L);

        var workingExperiences = new ArrayList<WorkExperienceDto>();
        workingExperiences.add(workingExperience);

        var educationalExperience = new EducationDto();
        educationalExperience.setId(0L);
        educationalExperience.setLogo("logo");
        educationalExperience.setDescription("description");
        educationalExperience.setSchool("school");
        educationalExperience.setYearStart("start");
        educationalExperience.setYearEnd("end");


        var educationalExperiences = new ArrayList<EducationDto>();
        educationalExperiences.add(educationalExperience);


        // Mock UserProfileDto
        UserProfileDto userProfileDto = new UserProfileDto();
        userProfileDto.setAccountUuid("accountUuid");
        userProfileDto.setEducation(educationalExperiences);
        userProfileDto.setWorkExperience(workingExperiences);
        // Populate userProfileDto with necessary fields

        // Mock UserProfile and UserProfileOptional
        UserProfile oldUserProfile = getUserProfile();
        oldUserProfile.setId(1L);
        oldUserProfile.setAccountUuid("accountUuid");

        UserProfile expectedOldUserProfile = getUserProfile();
        expectedOldUserProfile.setId(1L);
        expectedOldUserProfile.setAccountUuid("accountUuid");
        // Populate oldUserProfile with necessary fields
        Optional<UserProfile> userProfileOptional = Optional.of(oldUserProfile);

        // expected dto
        UserProfileDto expectedUserProfileDto = new UserProfileDto();
        expectedUserProfileDto.setName("name");
        expectedUserProfileDto.setJobTitle("jobTitle");
        expectedUserProfileDto.setId(1L);
        expectedUserProfileDto.setAccountUuid("accountUuid");
        expectedUserProfileDto.setImage("image");
        expectedUserProfileDto.setAbout("about");
        expectedUserProfileDto.setEducation(educationalExperiences);
        expectedUserProfileDto.setWorkExperience(workingExperiences);

        // Mock repositories behavior
        when(userProfileRepository.findByAccountUuid(any())).thenReturn(userProfileOptional);
        when(userProfileCustomRepository.findByAccountUuid(any())).thenReturn(userProfileOptional);

        when(userProfileRepository.saveAndFlush(any())).thenReturn(oldUserProfile);
        when(userProfileCustomRepository.saveAndFlush(any())).thenReturn(oldUserProfile);

        // Call the method under test
        UserProfileDto resultUserProfileDto = userProfileService.saveUserProfile(userProfileDto);

        // Asserts or verifications based on the behavior of the method
        // For example:
        // Verify that findByAccountUuid was called once
        verify(userProfileCustomRepository, times(1)).findByAccountUuid(any());

        // Assert that the returned UserProfileDto matches expectations
        Assertions.assertEquals(expectedUserProfileDto, resultUserProfileDto);

        Assertions.assertEquals(expectedOldUserProfile.getEducation().get(0).getDescription(), oldUserProfile.getEducation().get(0).getDescription());
        Assertions.assertEquals(expectedOldUserProfile.getEducation().get(0).getId(), 1L);
        Assertions.assertEquals(expectedOldUserProfile.getEducation().get(0).getLogo(), oldUserProfile.getEducation().get(0).getLogo());
        Assertions.assertEquals(expectedOldUserProfile.getEducation().get(0).getSchool(), oldUserProfile.getEducation().get(0).getSchool());
        Assertions.assertEquals(expectedOldUserProfile.getEducation().get(0).getYearEnd(), oldUserProfile.getEducation().get(0).getYearEnd());
        Assertions.assertEquals(expectedOldUserProfile.getEducation().get(0).getYearStart(), oldUserProfile.getEducation().get(0).getYearStart());
        Assertions.assertNull(expectedOldUserProfile.getEducation().get(0).getUserProfile());

        Assertions.assertEquals(expectedOldUserProfile.getWorkExperience().get(0).getExperience(), oldUserProfile.getWorkExperience().get(0).getExperience());
        Assertions.assertEquals(expectedOldUserProfile.getWorkExperience().get(0).getCompany(), oldUserProfile.getWorkExperience().get(0).getCompany());
        Assertions.assertEquals(expectedOldUserProfile.getWorkExperience().get(0).getLogo(), oldUserProfile.getWorkExperience().get(0).getLogo());
        Assertions.assertEquals(expectedOldUserProfile.getWorkExperience().get(0).getId(), 2L);
        Assertions.assertEquals(expectedOldUserProfile.getWorkExperience().get(0).getJobTitle(), oldUserProfile.getWorkExperience().get(0).getJobTitle());
        Assertions.assertEquals(expectedOldUserProfile.getWorkExperience().get(0).getYearEnd(), oldUserProfile.getWorkExperience().get(0).getYearEnd());
        Assertions.assertEquals(expectedOldUserProfile.getWorkExperience().get(0).getYearStart(), oldUserProfile.getWorkExperience().get(0).getYearStart());
        Assertions.assertNull(expectedOldUserProfile.getWorkExperience().get(0).getUserProfile());
    }

}
