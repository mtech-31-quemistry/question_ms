package com.mtech.qmsquestion.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class WorkExperience {
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "profile_id", nullable = false)
    private UserProfile userProfile;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String company;
    private String yearStart;
    private String yearEnd;
    private String jobTitle;

    private String logo;

    @Column(length = 512)
    private String experience;

    // getters and setters


    @Override
    public String toString() {
        return "WorkExperience{" +
                "userProfile=" + userProfile.getId() +
                ", id=" + id +
                ", company='" + company + '\'' +
                ", yearStart='" + yearStart + '\'' +
                ", yearEnd='" + yearEnd + '\'' +
                ", jobTitle='" + jobTitle + '\'' +
                ", logo='" + logo + '\'' +
                ", experience='" + experience + '\'' +
                '}';
    }
}