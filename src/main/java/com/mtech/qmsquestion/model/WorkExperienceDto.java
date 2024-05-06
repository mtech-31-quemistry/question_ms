package com.mtech.qmsquestion.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class WorkExperienceDto {
//    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

//    private BigInteger profile_id;

    private  String company;

    private String yearStart;
    private String yearEnd;


    private String jobTitle;

    private String logo;

    private String experience;

}
