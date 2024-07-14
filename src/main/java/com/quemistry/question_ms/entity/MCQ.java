package com.quemistry.question_ms.entity;

import com.quemistry.question_ms.util.OptionConverter;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MCQ {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;


    private String stem;

    @Convert(converter = OptionConverter.class)
//    @ElementCollection
    private List<Option> options;


    @ManyToMany
    @JoinTable(
            name = "mcq_topics",
            joinColumns = @JoinColumn(name = "mcq_id"),
            inverseJoinColumns = @JoinColumn(name = "topic_id")
    )
    private List<Topic> topics;

    @ManyToMany
    @JoinTable(
            name = "mcq_skills", // Ensure this is the correct join table name
            joinColumns = @JoinColumn(name = "mcq_id"),
            inverseJoinColumns = @JoinColumn(name = "skill_id")
    )
    private List<Skill> skills;

    private String status;

    private Date publishedOn;

    private String publishedBy;

    private Date closedOn;

    private String closedBy;

    private Date createdOn;

    private String createdBy;


    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class Option {
        private Integer no;

        private String text;

        private String explanation;

        private Boolean isAnswer;
    }

}
