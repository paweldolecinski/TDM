package com.tdm.server.logic.problem;

import java.util.Collection;
import java.util.HashSet;

import com.tdm.server.logic.model.ExpertId;
import com.tdm.server.logic.model.ExpertRole;
import com.tdm.server.logic.model.GdmProblem;
import com.tdm.server.logic.model.GdmProblemId;
import com.tdm.server.logic.requests.GdmProblemService;

public class DefaultGdmProblemService implements GdmProblemService {

	@Override
	public GdmProblem createEmptyProblem() {
		return new GdmProblem();
	}

	@Override
	public GdmProblemId addProblem(GdmProblem problem) {
		return GdmProblemId.create(1);
	}

	@Override
	public GdmProblem retrieveProblem(GdmProblemId id) {
		return new GdmProblem();
	}

	@Override
	public void assignExpertToProblem(GdmProblemId problemId,
			ExpertId expertId, ExpertRole expertRole) {
		// TODO Auto-generated method stub

	}

	@Override
	public Collection<GdmProblemId> retrieveProblemsIdsForExpert(ExpertId id) {
		HashSet<GdmProblemId> problemsIds = new HashSet<GdmProblemId>();
		problemsIds.add(GdmProblemId.create(1));
		return problemsIds;
	}

	@Override
	public Collection<GdmProblemId> retrieveProblemsOwnedByExpert(ExpertId id) {
		HashSet<GdmProblemId> problemsIds = new HashSet<GdmProblemId>();
		problemsIds.add(GdmProblemId.create(1));
		return problemsIds;
	}

	@Override
	public Collection<GdmProblemId> retrieveProblemsModeratedByExpert(ExpertId id) {
		HashSet<GdmProblemId> problemsIds = new HashSet<GdmProblemId>();
		problemsIds.add(GdmProblemId.create(1));
		return problemsIds;
	}

	@Override
	public void setOwnerOfProblem(GdmProblemId problemId, ExpertId id) {
		// TODO Auto-generated method stub
		
	}

}
