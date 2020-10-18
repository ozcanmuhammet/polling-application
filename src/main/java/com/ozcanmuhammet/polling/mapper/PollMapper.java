package com.ozcanmuhammet.polling.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.ozcanmuhammet.polling.dto.PollDTO;
import com.ozcanmuhammet.polling.entity.Poll;

@Mapper(componentModel = "spring")
public interface PollMapper {

	PollMapper INSTANCE = Mappers.getMapper(PollMapper.class);

	public PollDTO toDTO(Poll poll);

	public List<PollDTO> toDtoList(List<Poll> pollList);

	public Poll toEntity(PollDTO pollDTO);

	public List<Poll> toEntityList(List<PollDTO> pollDtoList);

}
