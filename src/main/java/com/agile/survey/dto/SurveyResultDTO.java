package com.agile.survey.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@Schema(name = "SurveyResultDTO", description = "Survey result data transfer object")
@Data
public class SurveyResultDTO {

    private String title;

    private Map<String, Double> results = new HashMap<>();
}
