package com.mtech.qmsquestion.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnTransformer;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class UserProfile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String accountUuid;
    @Column
    @ColumnTransformer(read = "pgp_sym_decrypt(email::BYTEA, current_setting('var.encrypt_key'))",
                        write = "pgp_sym_encrypt(?, current_setting('var.encrypt_key'))")
    private String email;
    private String name;
    private boolean seeking;
    private String jobTitle;
    private String image;
    @Column(length = 512)
    private String about;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "userProfile")
    private List<WorkExperience> workExperience;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "userProfile")
    private List<Education> education;

}