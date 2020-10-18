package com.ozcanmuhammet.polling.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.ozcanmuhammet.polling.dto.AnswerDTO;
import com.ozcanmuhammet.polling.entity.Answer;

@Mapper(componentModel = "spring")
public interface AnswerMapper {

	AnswerMapper INSTANCE = Mappers.getMapper(AnswerMapper.class);

	public AnswerDTO toDTO(Answer answer);

	public List<AnswerDTO> toDtoList(List<Answer> answerList);

	public Answer toEntity(AnswerDTO answerDto);

	public List<Answer> toEntityList(List<AnswerDTO> answerDtoList);

}
