package com.ozcanmuhammet.polling.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.ozcanmuhammet.polling.dto.OptionDTO;
import com.ozcanmuhammet.polling.entity.Option;

@Mapper(componentModel = "spring")
public interface OptionMapper {

	OptionMapper INSTANCE = Mappers.getMapper(OptionMapper.class);

	public OptionDTO toDTO(Option option);

	public List<OptionDTO> toDtoList(List<Option> optionList);

	public Option toEntity(OptionDTO optionDto);

	public List<Option> toEntityList(List<OptionDTO> optionDtoList);

}
