package com.ozcanmuhammet.polling.mapper;

import com.ozcanmuhammet.polling.dto.PollDTO;
import com.ozcanmuhammet.polling.entity.Poll;
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
public class PollMapperImpl implements PollMapper {

    @Override
    public PollDTO toDTO(Poll poll) {
        if ( poll == null ) {
            return null;
        }

        PollDTO pollDTO = new PollDTO();

        pollDTO.setId( poll.getId() );
        pollDTO.setName( poll.getName() );

        return pollDTO;
    }

    @Override
    public List<PollDTO> toDtoList(List<Poll> pollList) {
        if ( pollList == null ) {
            return null;
        }

        List<PollDTO> list = new ArrayList<PollDTO>( pollList.size() );
        for ( Poll poll : pollList ) {
            list.add( toDTO( poll ) );
        }

        return list;
    }

    @Override
    public Poll toEntity(PollDTO pollDTO) {
        if ( pollDTO == null ) {
            return null;
        }

        Poll poll = new Poll();

        poll.setId( pollDTO.getId() );
        poll.setName( pollDTO.getName() );

        return poll;
    }

    @Override
    public List<Poll> toEntityList(List<PollDTO> pollDtoList) {
        if ( pollDtoList == null ) {
            return null;
        }

        List<Poll> list = new ArrayList<Poll>( pollDtoList.size() );
        for ( PollDTO pollDTO : pollDtoList ) {
            list.add( toEntity( pollDTO ) );
        }

        return list;
    }
}
