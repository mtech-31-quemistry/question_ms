package com.quemistry.question_ms.entity;

import com.quemistry.question_ms.enums.QuestionStatus;
import com.quemistry.question_ms.model.QuestionOption;
import com.quemistry.question_ms.util.OptionConverter;
import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "mcq", schema = "qms_question")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MCQ {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    private String stem;

    @Convert(converter = OptionConverter.class)
//    @ElementCollection
    private List<QuestionOption> options;


    @ManyToMany
    @JoinTable(
            name = "mcq_topics",
            schema = "qms_question",
            joinColumns = @JoinColumn(name = "mcq_id"),
            inverseJoinColumns = @JoinColumn(name = "topic_id")
    )
    private List<Topic> topics;

    @ManyToMany
    @JoinTable(
            name = "mcq_skills", // Ensure this is the correct join table name
            schema = "qms_question",
            joinColumns = @JoinColumn(name = "mcq_id"),
            inverseJoinColumns = @JoinColumn(name = "skill_id")
    )
    private List<Skill> skills;

    @Column(name = "status")
    @Convert(converter = QuestionStatus.Converter.class)
    private QuestionStatus status;

    private Date publishedOn;

    private String publishedBy;

    private Date archivedOn;

    private String archivedBy;

    private String updatedBy;

    @UpdateTimestamp
    private Date updatedOn;

    private String createdBy;

    @CreationTimestamp
    private Date createdOn;



}
