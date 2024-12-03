package com.agile.survey.repositories;

import com.agile.survey.entities.TeamSurvey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface TeamSurveyRepository extends JpaRepository<TeamSurvey, Long> {

    @Query("SELECT COUNT(s) = 0 " +
            "FROM Survey s " +
            "INNER JOIN s.teamSurvey ts " +
            "WHERE ts.id = :teamSurveyId " +
            "AND s.completed = false")
    boolean isTeamSurveyDone(Long teamSurveyId);
}
