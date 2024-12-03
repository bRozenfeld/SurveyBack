package com.agile.survey.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "survey")
@Data
public class Survey {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "link", unique = true, nullable = false)
    private String link;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "survey")
    private List<RatedQuestion> ratedQuestions = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "team_survey_id", referencedColumnName = "id", nullable = false)
    private TeamSurvey teamSurvey;

    private boolean completed = false;

}
