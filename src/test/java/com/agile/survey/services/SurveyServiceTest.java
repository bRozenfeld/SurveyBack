package com.agile.survey.services;

import com.agile.survey.exceptions.SurveyNotFoundException;
import com.agile.survey.repositories.SurveyRepository;
import com.agile.survey.repositories.TeamSurveyRepository;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class SurveyServiceTest {

    @Test
    void testFindSurveyByLinkThrowsException() {
        String badLink = "link4";
        SurveyRepository mockRepository = mock(SurveyRepository.class);
        when(mockRepository.findByLink(badLink)).thenReturn(Optional.empty());

        SurveyService surveyService = new SurveyService(mockRepository, null, null);

        Exception exception = assertThrows(SurveyNotFoundException.class, () -> {
            surveyService.findByLink(badLink);
        });

        assertEquals("Survey with link " + badLink + " not found", exception.getMessage());
    }

}
