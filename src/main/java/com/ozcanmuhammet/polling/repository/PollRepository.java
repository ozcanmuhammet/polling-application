package com.ozcanmuhammet.polling.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ozcanmuhammet.polling.entity.Poll;

@Repository
public interface PollRepository extends JpaRepository<Poll, Long> {

}
