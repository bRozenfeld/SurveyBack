package com.agile.survey.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
public class SurveyDTO {

    @NotEmpty(message = "Survey must have at least one question")
    @Valid
    private List<QuestionDTO> questions = new ArrayList<>();

    private LocalDateTime creationDate;

    private int validityDays;

    private String title;

    private boolean completed;
}
