package com.ozcanmuhammet.polling.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ozcanmuhammet.polling.entity.Poll;
import com.ozcanmuhammet.polling.entity.Question;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Long> {

	public List<Question> findByStatus(String status);

	public List<Question> findByPollAndStatus(Poll poll, String status);

}
