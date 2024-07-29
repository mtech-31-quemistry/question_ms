package com.quemistry.question_ms.entity;

import com.quemistry.question_ms.enums.SkillStatus;
import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Skill {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(name = "status")
    @Convert(converter = SkillStatus.Converter.class)
    private SkillStatus status;
//    @ManyToOne
//    @JoinColumn(name = "topic_id", referencedColumnName = "id")
//    private Topic topic;

}
