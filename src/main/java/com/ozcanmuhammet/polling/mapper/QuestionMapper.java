package com.ozcanmuhammet.polling.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.ozcanmuhammet.polling.dto.QuestionDTO;
import com.ozcanmuhammet.polling.entity.Question;

@Mapper(componentModel = "spring")
public interface QuestionMapper {

	QuestionMapper INSTANCE = Mappers.getMapper(QuestionMapper.class);

	public QuestionDTO toDTO(Question question);

	public List<QuestionDTO> toDtoList(List<Question> questionList);

	public Question toEntity(QuestionDTO questionDto);

	public List<Question> toEntityList(List<QuestionDTO> questionDtoList);

}
