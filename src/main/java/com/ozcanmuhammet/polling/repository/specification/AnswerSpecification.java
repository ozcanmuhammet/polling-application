package com.ozcanmuhammet.polling.repository.specification;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ozcanmuhammet.polling.entity.Answer;
import com.ozcanmuhammet.polling.entity.Option;
import com.ozcanmuhammet.polling.entity.Question;

@Component
public class AnswerSpecification {

	@Autowired
	EntityManager entityManager;

	public List<Object[]> findAnswerCount() {

		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Object[]> query = criteriaBuilder.createQuery(Object[].class);

		Root<Answer> answer = query.from(Answer.class);
		Join<Answer, Question> questionList = answer.join("question", JoinType.INNER);
		Join<Answer, Option> optionList = answer.join("option", JoinType.INNER);

		query.groupBy(questionList.get("id"), questionList.get("text"), optionList.get("id"), optionList.get("optionText"));
		query.multiselect(questionList.get("id"), questionList.get("text"), optionList.get("id"), optionList.get("optionText"),
				criteriaBuilder.count(answer));

		TypedQuery<Object[]> typedQuery = entityManager.createQuery(query);
		List<Object[]> resultList = typedQuery.getResultList();

		return resultList;
	}

}
