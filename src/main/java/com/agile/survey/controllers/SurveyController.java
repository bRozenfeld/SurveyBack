package com.agile.survey.controllers;

import com.agile.survey.dto.PutSurveyDTO;
import com.agile.survey.dto.SurveyDTO;
import com.agile.survey.dto.SurveyResultDTO;
import com.agile.survey.services.SurveyService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@Tag(name = "Survey API", description = "Operations related to Surveys")
@RestController
@RequestMapping("/api/surveys")
@RequiredArgsConstructor
@CrossOrigin(origins = "*") // Allow requests from any origin
public class SurveyController {

    private final static Logger logger = LoggerFactory.getLogger(SurveyController.class);

    private final SurveyService surveyService;


    @GetMapping("/{link}")
    @Operation(summary = "Get a survey by his unique link", description = "Retrieve a survey by his unique link")
    @ApiResponse(responseCode = "200", description = "Survey found successfully")
    @ApiResponse(responseCode = "404", description = "Survey not found.")
    public ResponseEntity<SurveyDTO> getSurveyByLink(@PathVariable String link) {
        logger.info("{} :  getSurveyByLink({})", this.getClass(), link);

        SurveyDTO s = surveyService.findByLink(link);

        return ResponseEntity.ok(s);

    }

    @PutMapping("/{link}")
    @Operation(summary = "update a Survey", description = "Method to update a survey by his unique link en mettant à jour les questions")
    @ApiResponse(responseCode = "204", description = "Survey updated successfully.")
    @ApiResponse(responseCode = "404", description = "Survey not found.")
    public ResponseEntity<Void> updateSurvey(@PathVariable String link, @RequestBody PutSurveyDTO survey) {
        logger.info("{} updateSurvey({},{})", this.getClass(), link, survey);

        surveyService.updateSurvey(link, survey);

        return ResponseEntity.noContent().build();
    }


    @GetMapping("/{id}/results")
    @Operation(summary = "Renvoie les résultats d'un sondage TeamSurvey", description = "Montre les résultats d'un ensemble de Survey")
    @ApiResponse(responseCode = "200", description = "Affiche en format json les résultats")
    @ApiResponse(responseCode = "404", description = "TeamSurvey not found.")
    public ResponseEntity<SurveyResultDTO> getSurveyResults(@PathVariable Long id) {
        logger.info("{} :  getSurveyResults({})", this.getClass(), id);

        SurveyResultDTO s = surveyService.getSurveyResults(id);

        return ResponseEntity.ok(s);

    }
}
