package com.agile.survey.mappers;

import com.agile.survey.dto.QuestionDTO;
import com.agile.survey.entities.RatedQuestion;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;


@Mapper(componentModel = "spring")
public interface QuestionMapper {

    QuestionMapper INSTANCE = Mappers.getMapper(QuestionMapper.class);

    @Mapping(target = "rate", source = "rate")
    @Mapping(target = "question.question", source = "question")
    @Mapping(target = "id", source = "id")
    RatedQuestion toEntity(QuestionDTO questionDTO);

    @InheritInverseConfiguration
    QuestionDTO toDTO(RatedQuestion ratedQuestion);


}
