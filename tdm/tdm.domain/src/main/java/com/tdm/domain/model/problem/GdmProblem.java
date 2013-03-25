package com.tdm.domain.model.problem;

import java.util.Date;
import java.util.List;

import com.tdm.domain.model.expert.Expert;
import com.tdm.domain.model.expert.ExpertId;
import com.tdm.domain.model.expert.ExpertRole;

public interface GdmProblem {

	GdmProblemId getId();

	String getName();

	String getDescription();

	Date getCreationDate();
	
	List<Expert> getExperts();

	GdmProblemCurrentConsensus getCurrentConsensus();

	void addExpert(ExpertId expertId, ExpertRole role);

	void setId(GdmProblemId id);

	void setName(String name);

	void setDescription(String description);
}