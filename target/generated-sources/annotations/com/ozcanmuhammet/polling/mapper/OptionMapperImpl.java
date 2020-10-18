package com.ozcanmuhammet.polling.mapper;

import com.ozcanmuhammet.polling.dto.OptionDTO;
import com.ozcanmuhammet.polling.entity.Option;
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
public class OptionMapperImpl implements OptionMapper {

    @Override
    public OptionDTO toDTO(Option option) {
        if ( option == null ) {
            return null;
        }

        OptionDTO optionDTO = new OptionDTO();

        optionDTO.setId( option.getId() );
        optionDTO.setOptionText( option.getOptionText() );

        return optionDTO;
    }

    @Override
    public List<OptionDTO> toDtoList(List<Option> optionList) {
        if ( optionList == null ) {
            return null;
        }

        List<OptionDTO> list = new ArrayList<OptionDTO>( optionList.size() );
        for ( Option option : optionList ) {
            list.add( toDTO( option ) );
        }

        return list;
    }

    @Override
    public Option toEntity(OptionDTO optionDto) {
        if ( optionDto == null ) {
            return null;
        }

        Option option = new Option();

        option.setId( optionDto.getId() );
        option.setOptionText( optionDto.getOptionText() );

        return option;
    }

    @Override
    public List<Option> toEntityList(List<OptionDTO> optionDtoList) {
        if ( optionDtoList == null ) {
            return null;
        }

        List<Option> list = new ArrayList<Option>( optionDtoList.size() );
        for ( OptionDTO optionDTO : optionDtoList ) {
            list.add( toEntity( optionDTO ) );
        }

        return list;
    }
}
