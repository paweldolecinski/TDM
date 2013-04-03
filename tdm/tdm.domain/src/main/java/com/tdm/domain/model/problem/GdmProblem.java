package com.tdm.domain.model.problem;

import java.util.Date;
import java.util.List;

import com.tdm.domain.model.expert.Expert;

public interface GdmProblem {

	GdmProblemId getId();

	String getName();

	String getDescription();

	Date getCreationDate();

	List<Expert> getExperts();

	GdmProblemCurrentConsensus getCurrentConsensus();

	void addExpert(Expert expert);

	void setId(GdmProblemId id);

	void setName(String name);

	void setDescription(String description);
}