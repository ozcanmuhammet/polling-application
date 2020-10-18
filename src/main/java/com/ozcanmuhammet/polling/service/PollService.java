package com.ozcanmuhammet.polling.service;

import java.util.List;

import com.ozcanmuhammet.polling.dto.PollDTO;

public interface PollService {

	public List<PollDTO> getPolls();

	public PollDTO getPollById(Long pollId);
}
