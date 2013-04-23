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
import javax.jdo.Transaction;
import com.tdm.domain.model.handling.ObjectNotFoundException;
import com.tdm.domain.model.idea.SolutionIdea;
import com.tdm.domain.model.idea.SolutionIdeaId;
import com.tdm.domain.model.idea.SolutionIdeaRepository;
import com.tdm.domain.model.problem.Problem;
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
		Transaction tx = pm.currentTransaction();
		SolutionIdea detachCopy;
		try {
			tx.begin();
			SolutionIdea createdPersistent = pm.makePersistent(soultionIdea);
			Problem problem = soultionIdea.getProblem();
			// problem.addSolutionIdea(createdPersistent);
			pm.makePersistent(problem);
			tx.commit();
			detachCopy = pm.detachCopy(createdPersistent);
		} finally {
			pm.close();
			if (tx.isActive()) {
				tx.rollback();
			}
		}
		return detachCopy;
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
