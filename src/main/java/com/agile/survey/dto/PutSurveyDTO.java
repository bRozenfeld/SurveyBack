package com.agile.survey.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;

@Schema(name = "PutSurveyDTO", description = "Put survey data transfer object")
@Data
public class PutSurveyDTO {

    @NotNull
    private List<QuestionDTO> questions;
}
