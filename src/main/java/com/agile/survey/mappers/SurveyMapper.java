package com.agile.survey.mappers;

import com.agile.survey.dto.QuestionDTO;
import com.agile.survey.dto.SurveyDTO;
import com.agile.survey.dto.SurveyResultDTO;
import com.agile.survey.entities.RatedQuestion;
import com.agile.survey.entities.Survey;
import com.agile.survey.entities.TeamSurvey;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring", uses = {QuestionMapper.class})
public interface SurveyMapper {

    SurveyMapper INSTANCE = Mappers.getMapper(SurveyMapper.class);

    @Mapping(target = "ratedQuestions", source = "questions")
    @Mapping(target = "teamSurvey.validityDays", source = "validityDays")
    @Mapping(target = "teamSurvey.title", source = "title")
    @Mapping(target = "teamSurvey.creationDate", source = "creationDate")
    @Mapping(target = "completed", source = "completed")
    Survey toEntity(SurveyDTO surveyDTO);

    @InheritInverseConfiguration
    SurveyDTO toDTO(Survey survey);

    List<RatedQuestion> toEntityList(List<QuestionDTO> questionDTOs);

    List<QuestionDTO> toDTOList(List<RatedQuestion> ratedQuestions);

    @Mapping(target = "title", source = "teamSurvey.title")
    @Mapping(target = "results", ignore = true)
    SurveyResultDTO toSurveyResultDTO(TeamSurvey teamSurvey);

}
