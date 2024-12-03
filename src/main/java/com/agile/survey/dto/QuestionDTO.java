package com.agile.survey.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.hibernate.validator.constraints.Range;


@Schema(name = "QuestionDTO", description = "Question data transfer object")
@Data
public class QuestionDTO {

    @NotNull(message = "Id is mandatory")
    private Long id;

    @NotBlank(message = "Question is mandatory")
    private String question;

    @Range(min = 0, max = 5, message = "Rate must be between 0 and 5")
    private int rate;
}
