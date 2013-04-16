package com.tdm.domain.model.problem.vo;

import java.util.Date;
import java.util.List;

import com.tdm.domain.model.expert.vo.Expert;

public interface GdmProblem {

	GdmProblemKey getKey();

	String getName();

	String getDescription();

	Date getCreationDate();

	List<Expert> getExperts();

	GdmProblemCurrentConsensus getCurrentConsensus();

	void addExpert(Expert expert);

	void setName(String name);

	void setDescription(String description);

}