package com.tdm.server.logic.requests;

import java.util.Collection;

import com.tdm.server.logic.model.ExpertId;
import com.tdm.server.logic.model.ExpertRole;
import com.tdm.server.logic.model.GdmProblem;
import com.tdm.server.logic.model.GdmProblemId;

public interface GdmProblemService {

	GdmProblem createEmptyProblem();

	GdmProblemId addProblem(GdmProblem problem);

	GdmProblem retrieveProblem(GdmProblemId id);

	void assignExpertToProblem(GdmProblemId problemId, ExpertId expertId,
			ExpertRole expertRole);

	Collection<GdmProblemId> retrieveProblemsIdsForExpert(ExpertId id);

	Collection<GdmProblemId> retrieveProblemsOwnedByExpert(ExpertId id);

	Collection<GdmProblemId> retrieveProblemsModeratedByExpert(ExpertId id);

	void setOwnerOfProblem(GdmProblemId problemId, ExpertId id);

}