package com.tdm.server.persistance.jdo;

import java.util.Collections;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.PersistenceManagerFactory;
import javax.jdo.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import com.tdm.domain.model.handling.ObjectNotFoundException;
import com.tdm.domain.model.idea.SolutionIdea;
import com.tdm.domain.model.idea.SolutionIdeaId;
import com.tdm.domain.model.idea.SolutionIdeaRepository;
import com.tdm.domain.model.problem.ProblemId;

@Repository
public class JdoSolutionIdeaRepository implements SolutionIdeaRepository {

	@Autowired
	@Qualifier("proxy")
	private PersistenceManagerFactory persistenceManagerFactory;

	@Override
	public SolutionIdea read(ProblemId problemId, SolutionIdeaId solutionIdeaId) {
		return null;
	}

	@Override
	public SolutionIdea create(SolutionIdea soultionIdea) {
		PersistenceManager pm = getPersistenceManager();
		try {
			SolutionIdea createdPersistent = pm.makePersistent(soultionIdea);
			return pm.detachCopy(createdPersistent);
		} finally {
			pm.close();
		}
	}

	@Override
	public void delete(SolutionIdeaId id) throws ObjectNotFoundException {

	}

	@Override
	public List<SolutionIdea> findAllAssignedTo(ProblemId problemId) {
		PersistenceManager pm = getPersistenceManager();

		Key problemKey = KeyFactory.stringToKey(problemId.getIdString());

		Query q = pm.newQuery(SolutionIdea.class);
		q.setOrdering("creationDate asc");
		q.setFilter("problem == problemParam");
		q.declareParameters(Key.class.getName() + " problemParam");

		try {
			@SuppressWarnings("unchecked")
			List<SolutionIdea> results = (List<SolutionIdea>) q
					.execute(problemKey);
			if (!results.isEmpty()) {
				return (List<SolutionIdea>) pm.detachCopyAll(results);
			} else {
				return Collections.emptyList();
			}
		} finally {
			q.closeAll();
		}
	}

	private PersistenceManager getPersistenceManager() {
		return persistenceManagerFactory.getPersistenceManager();
	}

}
