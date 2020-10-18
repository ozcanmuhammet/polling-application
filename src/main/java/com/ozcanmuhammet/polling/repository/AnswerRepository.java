package com.ozcanmuhammet.polling.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ozcanmuhammet.polling.entity.Answer;

@Repository
public interface AnswerRepository extends JpaRepository<Answer, Long> {

}
