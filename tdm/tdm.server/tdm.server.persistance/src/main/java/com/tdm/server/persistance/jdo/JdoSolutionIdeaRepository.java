package com.tdm.server.persistance.jdo;

import java.util.Collection;

import org.springframework.stereotype.Repository;

import com.tdm.domain.model.handling.ConstraintsViolationException;
import com.tdm.domain.model.handling.ObjectNotFoundException;
import com.tdm.domain.model.idea.SolutionIdea;
import com.tdm.domain.model.idea.SolutionIdeaId;
import com.tdm.domain.model.idea.SolutionIdeaRepository;
import com.tdm.domain.model.problem.vo.GdmProblemKey;

@Repository
public class JdoSolutionIdeaRepository implements SolutionIdeaRepository {

	@Override
	public SolutionIdea read(GdmProblemKey problemId,
			SolutionIdeaId solutionIdeaId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void store(SolutionIdea soultionIdea)
			throws ObjectNotFoundException, ConstraintsViolationException {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(SolutionIdeaId id) throws ObjectNotFoundException {
		// TODO Auto-generated method stub

	}

	@Override
	public Collection<SolutionIdea> findAllAssignedTo(GdmProblemKey problemId) {
		// TODO Auto-generated method stub
		return null;
	}

}
