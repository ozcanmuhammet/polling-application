package com.ozcanmuhammet.polling.mapper;

import com.ozcanmuhammet.polling.dto.AnswerDTO;
import com.ozcanmuhammet.polling.entity.Answer;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2020-10-18T20:08:38+0300",
    comments = "version: 1.3.0.Final, compiler: javac, environment: Java 1.8.0_221 (Oracle Corporation)"
)
@Component
public class AnswerMapperImpl implements AnswerMapper {

    @Override
    public AnswerDTO toDTO(Answer answer) {
        if ( answer == null ) {
            return null;
        }

        AnswerDTO answerDTO = new AnswerDTO();

        answerDTO.setId( answer.getId() );

        return answerDTO;
    }

    @Override
    public List<AnswerDTO> toDtoList(List<Answer> answerList) {
        if ( answerList == null ) {
            return null;
        }

        List<AnswerDTO> list = new ArrayList<AnswerDTO>( answerList.size() );
        for ( Answer answer : answerList ) {
            list.add( toDTO( answer ) );
        }

        return list;
    }

    @Override
    public Answer toEntity(AnswerDTO answerDto) {
        if ( answerDto == null ) {
            return null;
        }

        Answer answer = new Answer();

        answer.setId( answerDto.getId() );

        return answer;
    }

    @Override
    public List<Answer> toEntityList(List<AnswerDTO> answerDtoList) {
        if ( answerDtoList == null ) {
            return null;
        }

        List<Answer> list = new ArrayList<Answer>( answerDtoList.size() );
        for ( AnswerDTO answerDTO : answerDtoList ) {
            list.add( toEntity( answerDTO ) );
        }

        return list;
    }
}
