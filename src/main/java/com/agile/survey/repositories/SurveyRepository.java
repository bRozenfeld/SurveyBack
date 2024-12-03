package com.agile.survey.repositories;

import com.agile.survey.entities.Survey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface SurveyRepository extends JpaRepository<Survey, Long> {

    @Query("SELECT s FROM Survey s " + "" +
            "INNER JOIN s.teamSurvey ts " +
            "INNER JOIN FETCH s.ratedQuestions rq " +
            "INNER JOIN FETCH rq.question q " +
            "WHERE s.link = :link")
    Optional<Survey> findByLink(@Param("link") String link);

    @Query("SELECT q.question, AVG(rq.rate)" +
            "FROM TeamSurvey ts " +
            "INNER JOIN ts.surveys s " +
            "INNER JOIN s.ratedQuestions rq " +
            "INNER JOIN rq.question q " +
            "WHERE ts.id = :id " +
            "AND rq.rate > 0 " +
            "GROUP BY q.question")
    List<Object[]> getResults(@Param("id") Long id);
}
