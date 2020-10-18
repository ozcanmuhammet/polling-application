package com.ozcanmuhammet.polling.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ozcanmuhammet.polling.common.enums.Exception;
import com.ozcanmuhammet.polling.dto.PollDTO;
import com.ozcanmuhammet.polling.entity.Poll;
import com.ozcanmuhammet.polling.mapper.PollMapper;
import com.ozcanmuhammet.polling.repository.PollRepository;

@Service
public class PollServiceImpl implements PollService {

	@Autowired
	PollRepository pollRepository;
	
	@Autowired
	PollMapper pollMapper;

	@Override
	public List<PollDTO> getPolls() {
		List<Poll> pollList = pollRepository.findAll();
		List<PollDTO> pollDtoList = pollMapper.toDtoList(pollList);
		return pollDtoList;
	}

	@Override
	public PollDTO getPollById(Long id) {
		Poll poll = pollRepository.findById(id).orElseThrow(Exception.POLL_NOT_FOUND::exception);
		PollDTO pollDTO = pollMapper.toDTO(poll);
		return pollDTO;
	}

}
