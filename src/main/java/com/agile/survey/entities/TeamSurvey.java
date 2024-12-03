package com.agile.survey.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "team_survey")
@Data
@ToString(exclude = {"surveys"})
public class TeamSurvey {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "teamSurvey")
    private Set<Survey> surveys = new HashSet<>();

    private LocalDateTime creationDate = LocalDateTime.now();

    private int validityDays;

    private String title;

    boolean completed = false;

    public void addSurvey(Survey survey) {
        surveys.add(survey);
    }
}
