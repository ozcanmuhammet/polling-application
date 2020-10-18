package com.ozcanmuhammet.polling.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ozcanmuhammet.polling.entity.Option;

@Repository
public interface OptionRepository extends JpaRepository<Option, Long> {

}
