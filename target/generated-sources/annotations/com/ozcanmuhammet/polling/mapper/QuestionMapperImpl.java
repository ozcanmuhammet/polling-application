package com.ozcanmuhammet.polling.mapper;

import com.ozcanmuhammet.polling.dto.QuestionDTO;
import com.ozcanmuhammet.polling.entity.Question;
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
public class QuestionMapperImpl implements QuestionMapper {

    @Override
    public QuestionDTO toDTO(Question question) {
        if ( question == null ) {
            return null;
        }

        QuestionDTO questionDTO = new QuestionDTO();

        questionDTO.setId( question.getId() );
        questionDTO.setText( question.getText() );
        questionDTO.setStatus( question.getStatus() );

        return questionDTO;
    }

    @Override
    public List<QuestionDTO> toDtoList(List<Question> questionList) {
        if ( questionList == null ) {
            return null;
        }

        List<QuestionDTO> list = new ArrayList<QuestionDTO>( questionList.size() );
        for ( Question question : questionList ) {
            list.add( toDTO( question ) );
        }

        return list;
    }

    @Override
    public Question toEntity(QuestionDTO questionDto) {
        if ( questionDto == null ) {
            return null;
        }

        Question question = new Question();

        question.setId( questionDto.getId() );
        question.setText( questionDto.getText() );
        question.setStatus( questionDto.getStatus() );

        return question;
    }

    @Override
    public List<Question> toEntityList(List<QuestionDTO> questionDtoList) {
        if ( questionDtoList == null ) {
            return null;
        }

        List<Question> list = new ArrayList<Question>( questionDtoList.size() );
        for ( QuestionDTO questionDTO : questionDtoList ) {
            list.add( toEntity( questionDTO ) );
        }

        return list;
    }
}
