package com.ozcanmuhammet.polling.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ozcanmuhammet.polling.common.enums.Exception;
import com.ozcanmuhammet.polling.common.enums.QuestionStatus;
import com.ozcanmuhammet.polling.common.enums.Roles;
import com.ozcanmuhammet.polling.dto.OptionDTO;
import com.ozcanmuhammet.polling.dto.QuestionDTO;
import com.ozcanmuhammet.polling.entity.Option;
import com.ozcanmuhammet.polling.entity.Poll;
import com.ozcanmuhammet.polling.entity.Question;
import com.ozcanmuhammet.polling.entity.User;
import com.ozcanmuhammet.polling.mapper.OptionMapper;
import com.ozcanmuhammet.polling.mapper.QuestionMapper;
import com.ozcanmuhammet.polling.model.QuestionRequest;
import com.ozcanmuhammet.polling.model.QuestionUpdateRequest;
import com.ozcanmuhammet.polling.repository.AnswerRepository;
import com.ozcanmuhammet.polling.repository.PollRepository;
import com.ozcanmuhammet.polling.repository.QuestionRepository;
import com.ozcanmuhammet.polling.repository.UserRepository;

@Service
public class QuestionServiceImpl implements QuestionService {

	@Autowired
	QuestionRepository questionRepository;
	
	@Autowired
	QuestionMapper questionMapper;
	
	@Autowired
	OptionMapper optionMapper;
	
	@Autowired
	PollRepository pollRepository;
	
	@Autowired
	UserRepository userRepository;

	@Autowired
	AnswerRepository answerRepository;

	@Override
	public List<QuestionDTO> getActiveQuestions() {
		List<Question> questionList = questionRepository.findByStatus(QuestionStatus.ACTIVE.getValue());
		return getQuestionDtoList(questionList);
	}

	@Override
	public List<QuestionDTO> getPendingQuestions() {
		List<Question> questionList = questionRepository.findByStatus(QuestionStatus.PENDING.getValue());
		return getQuestionDtoList(questionList);
	}

	@Override
	@Transactional
	public QuestionDTO saveQuestion(QuestionRequest questionRequest) {

		Poll poll = pollRepository.findById(questionRequest.getPollId())
				.orElseThrow(Exception.POLL_NOT_FOUND::exception);

		User user = userRepository.findById(questionRequest.getUserId())
				.orElseThrow(Exception.USER_NOT_FOUND::exception);

		Question question = new Question();

		if (user.getRoleId().equals(Roles.ADMIN_USER.getValue())) {
			question.setStatus(QuestionStatus.ACTIVE.getValue());
		} else {
			question.setStatus(QuestionStatus.PENDING.getValue());
		}

		question.setPoll(poll);
		question.setText(questionRequest.getQuestionText());

		List<Option> optionList = new ArrayList<Option>();
		questionRequest.getOptionList().forEach(text -> {
			Option option = new Option();
			option.setOptionText(text);
			option.setQuestion(question);
			optionList.add(option);
		});

		question.setOptions(optionList);

		Question savedQuestion = questionRepository.save(question);
		return getQuestionDto(savedQuestion);
	}

	@Override
	public List<QuestionDTO> getQuestionsByPollId(Long pollId) {
		Poll poll = pollRepository.findById(pollId).orElseThrow(Exception.POLL_NOT_FOUND::exception);
		List<Question> questionList = questionRepository.findByPollAndStatus(poll, "1");
		return getQuestionDtoList(questionList);
	}

	private QuestionDTO getQuestionDto(Question question) {
		QuestionDTO questionDto = questionMapper.toDTO(question);
		List<OptionDTO> optionDtoList = new ArrayList<OptionDTO>();

		question.getOptions().forEach(option -> {
			OptionDTO optionDto = new OptionDTO();
			optionDto.setId(option.getId());
			optionDto.setOptionText(option.getOptionText());
			optionDto.setQuestionId(option.getQuestion().getId());
			optionDtoList.add(optionDto);
		});

		questionDto.setOptionDtoList(optionDtoList);

		return questionDto;
	}

	private List<QuestionDTO> getQuestionDtoList(List<Question> questionList) {
		List<QuestionDTO> questionDtoList = new ArrayList<QuestionDTO>();

		questionList.forEach(question -> {
			QuestionDTO questionDto = getQuestionDto(question);
			questionDtoList.add(questionDto);
		});

		return questionDtoList;

	}

	@Override
	@Transactional
	public QuestionDTO updateQuestion(Long questionId, QuestionUpdateRequest request) {

		Question question = questionRepository.findById(questionId)
				.orElseThrow(Exception.QUESTION_NOT_FOUND::exception);

		List<Option> optionList = new ArrayList<Option>();
		request.getOptionList().forEach(optionDto -> {
			Option option = optionMapper.toEntity(optionDto);
			option.setQuestion(question);
			optionList.add(option);
		});

		question.setStatus(QuestionStatus.ACTIVE.getValue());
		question.setText(request.getQuestionText());
		question.setOptions(optionList);

		Question updatedQuestion = questionRepository.save(question);

		return getQuestionDto(updatedQuestion);
	}

}
