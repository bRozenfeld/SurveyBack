package com.agile.survey.repositories;

import com.agile.survey.entities.Survey;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
public class SurveyRepositoryTest {

    @Autowired
    private SurveyRepository surveyRepository;

    @Test
    void givenValidSurvey_whenFindByLink_thenReturnSurvey() {
        String validLink = "link1";

        Optional<Survey> savedSurvey = surveyRepository.findByLink(validLink);

        assertTrue(savedSurvey.isPresent());

        Survey survey = savedSurvey.get();

        assertNotNull(survey.getId());
        assertEquals("link1", survey.getLink());
        assertEquals(1L, survey.getId());

    }


}
