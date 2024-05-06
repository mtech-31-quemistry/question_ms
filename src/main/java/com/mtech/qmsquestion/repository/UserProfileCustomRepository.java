package com.mtech.qmsquestion.repository;

import com.mtech.qmsquestion.entity.UserProfile;

import jakarta.persistence.EntityManager;
import java.util.Optional;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class UserProfileCustomRepository{

    @Value("${pii.encrypt.key}")
    private String encryptKey;

    @Autowired
    private EntityManager entityManager;

    @Autowired
    UserProfileRepository userProfileRepository;

    public Optional<UserProfile> findByAccountUuid(String accountUuid){
        Session currSession = entityManager.unwrap(Session.class);
        currSession.doWork(connection -> {
            connection.createStatement().execute(String.format("SET LOCAL var.encrypt_key = %s",encryptKey));
        });

        return userProfileRepository.findByAccountUuid(accountUuid);
    }

    public <S extends UserProfile> S saveAndFlush(S entity){
        Session currSession = entityManager.unwrap(Session.class);
        currSession.doWork(connection -> {
            connection.createStatement().execute(String.format("SET LOCAL var.encrypt_key = %s",encryptKey));
        });

        return userProfileRepository.saveAndFlush(entity);
    }
}
