package com.agile.survey.services;

import com.agile.survey.dto.PutSurveyDTO;
import com.agile.survey.dto.QuestionDTO;
import com.agile.survey.dto.SurveyDTO;
import com.agile.survey.dto.SurveyResultDTO;
import com.agile.survey.entities.RatedQuestion;
import com.agile.survey.entities.Survey;
import com.agile.survey.entities.TeamSurvey;
import com.agile.survey.exceptions.AlreadyCompletedException;
import com.agile.survey.exceptions.ExpiredSurveyException;
import com.agile.survey.exceptions.SurveyNotFoundException;
import com.agile.survey.mappers.SurveyMapper;
import com.agile.survey.repositories.SurveyRepository;
import com.agile.survey.repositories.TeamSurveyRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SurveyService {

    private static final Logger logger = LoggerFactory.getLogger(SurveyService.class);

    private final SurveyRepository surveyRepository;

    private final TeamSurveyRepository teamSurveyRepository;

    private final SurveyMapper surveyMapper;

    /**
     * Find a Survey by its link
     * Convert from Survey to SurveyDTO
     * Check if the survey is still valid
     * @param link String unique link to find the survey
     * @return SurveyDTO if found or throw SurveyNotFoundException
     */
    public SurveyDTO findByLink(String link) {
        logger.info("{} findByLink({})", this.getClass(), link);
        Survey s = surveyRepository.findByLink(link)
                .orElseThrow(() -> new SurveyNotFoundException("Survey with link " + link + " not found"));

        logger.info("{} findByLink({}) => Survey : {}", this.getClass(), link, s);

        if(s.isCompleted()) throw new AlreadyCompletedException("Survey with link " + link + " déjà complété");

        SurveyDTO surveyDTO = surveyMapper.toDTO(s);

        logger.info("{} findByLink({}) => SurveyDTO : {}", this.getClass(), link, surveyDTO);

        return surveyDTO;
    }

    /**
     * Update a Survey by its link
     * Check if the survey is still valid
     * Check if the survey is already completed
     * Update the rated questions with the new rates
     * @param link String unique link to retrieve the survey
     * @param updatedSurvey PutSurveyDTO containing the new rates for the questions
     * @throws SurveyNotFoundException if the survey is not found
     * @throws ExpiredSurveyException if the survey is expired
     * @throws AlreadyCompletedException if the survey is already completed
     */
    public void updateSurvey(String link, PutSurveyDTO updatedSurvey) {
        logger.info("{} updateSurvey({}, {})", this.getClass(), link, updatedSurvey);

        Survey survey = surveyRepository.findByLink(link)
                .orElseThrow(() -> new SurveyNotFoundException("Survey with link " + link + " not found"));

        if(!isSurveyStillValid(survey)) {
            throw new ExpiredSurveyException("Survey with link " + link + " is expired");
        }

        if(survey.isCompleted()) {
            throw new AlreadyCompletedException("Survey with link " + link + " déjà complété");
        }

        for(RatedQuestion ratedQuestion : survey.getRatedQuestions()) {
            for(QuestionDTO questionDTO : updatedSurvey.getQuestions()) {
                if(ratedQuestion.getId().equals(questionDTO.getId())) {
                    ratedQuestion.setRate(questionDTO.getRate());
                }
            }
        }

        survey.setCompleted(true);
        surveyRepository.save(survey);

    }

    /**
     * Renvoie les résultats d'un sondage TeamSurvey
     * @param id Long unique id of TeamSurvey
     * @return SurveyResultDTO containing the results of the survey
     * @throws SurveyNotFoundException if the TeamSurvey is not found
     */
    public SurveyResultDTO getSurveyResults(Long id) {
        logger.info("{} getSurveyResults({})", this.getClass(), id);

        TeamSurvey teamSurvey = teamSurveyRepository.findById(id)
                .orElseThrow(() -> new SurveyNotFoundException("Survey with id " + id + " not found"));

        List<Object[]> res = surveyRepository.getResults(id);
        logger.info("Results : {}", res);

        SurveyResultDTO surveyResultDTO = surveyMapper.toSurveyResultDTO(teamSurvey);

        HashMap <String, Double> results = new HashMap<>();
        for(Object[] obj : res) {
            results.put((String) obj[0], (Double) obj[1]);
        }
        surveyResultDTO.setResults(results);

        return surveyResultDTO;
    }


    private boolean isSurveyStillValid(SurveyDTO survey) {
        return survey.getCreationDate().plusDays(survey.getValidityDays()).isAfter(java.time.LocalDateTime.now());
    }

    private boolean isSurveyStillValid(Survey survey) {
        return survey.getTeamSurvey().getCreationDate().plusDays(survey.getTeamSurvey().getValidityDays()).isAfter(java.time.LocalDateTime.now());
    }

}
