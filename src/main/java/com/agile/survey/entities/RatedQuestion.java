package com.agile.survey.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;

@Entity
@Table(name = "rated_question")
@Data
@ToString(exclude = {"survey"})
public class RatedQuestion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "question_id", referencedColumnName = "id", nullable = false)
    private Question question;

    @ManyToOne
    @JoinColumn(name = "survey_id", referencedColumnName = "id", nullable = false)
    private Survey survey;

    private int rate;

}
