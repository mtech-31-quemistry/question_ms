package com.mtech.qmsquestion.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EducationDto {
//    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

//    private BigInteger profile_id;

    private String school;

    private String yearStart;
    private String yearEnd;

    private String logo;

    private String description;
}
