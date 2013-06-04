/**
 * Copyright 2011 Paweł Doleciński.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package com.tdm.server.persistance.jdo;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.jdo.PersistenceManager;
import javax.jdo.PersistenceManagerFactory;
import javax.jdo.Query;
import javax.jdo.Transaction;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import com.tdm.domain.model.expert.Expert;
import com.tdm.domain.model.expert.ExpertId;
import com.tdm.domain.model.expert.ExpertRole;
import com.tdm.domain.model.handling.ObjectNotFoundException;
import com.tdm.domain.model.preferences.IdeaPreference;
import com.tdm.domain.model.problem.Problem;
import com.tdm.domain.model.problem.ProblemId;
import com.tdm.domain.model.problem.ProblemRepository;

/**
 * @author Paweł Doleciński
 * 
 * @see ProblemRepository
 */
@Repository
public class JdoGdmProblemRepository implements ProblemRepository {

	protected final Logger logger = Logger
			.getLogger(JdoGdmProblemRepository.class.getName());

	@Autowired
	@Qualifier("proxy")
	private PersistenceManagerFactory persistenceManagerFactory;

	/**
	 * @inheritDoc
	 */
	@Override
	public Problem read(ProblemId id) throws ObjectNotFoundException {
		Key key = KeyFactory.stringToKey(id.getIdString());
		PersistenceManager pm = getPersistenceManager();

		try {
			Problem problem = pm.getObjectById(Problem.class, key);
			problem.getCurrentConsensus();
			problem.getExperts();
			Problem detachCopy = pm.detachCopy(problem);
			return detachCopy;
		} catch (Exception e) {
			throw new ObjectNotFoundException(Problem.class, key);
		} finally {
			pm.close();
		}

	}

	public Problem readFor(ProblemId problemId, ExpertId expertId) {
		PersistenceManager pm = getPersistenceManager();
		Transaction tx = pm.currentTransaction();
		try {
			tx.begin();
			Query q = pm.newQuery("select from " + Problem.class.getName()
					+ " where" + " key == problemIdParam &&"
					+ " experts.contains(e) && e.userId == userIdParam");

			q.declareParameters(Key.class.getName()
					+ " problemIdParam, String userIdParam");
			q.declareVariables(Expert.class.getName() + " e");
			Key key = KeyFactory.stringToKey(problemId.getIdString());

			@SuppressWarnings("unchecked")
			List<Problem> ids = (List<Problem>) q.execute(key,
					expertId.getIdString());

			if (ids.size() == 1) {
				Problem p = ids.get(0);
				p.getCurrentConsensus();
				p.getExperts();
				Problem detachCopy = pm.detachCopy(p);
				tx.commit();
				return detachCopy;
			} else {
				throw new ObjectNotFoundException(Problem.class,
						problemId.getIdString());
			}
		} catch (Exception e) {
			logger.log(Level.SEVERE, "Error during fetching problems.", e);
			throw new ObjectNotFoundException(Problem.class,
					problemId.getIdString());
		} finally {
			if (tx.isActive()) {
				tx.rollback();
			}
			pm.close();
		}
	}

	/**
	 * @inheritDoc
	 */
	@Override
	public Problem create(Problem problem) {
		PersistenceManager pm = getPersistenceManager();
		Transaction tx = pm.currentTransaction();
		try {
			tx.begin();
			pm.setDetachAllOnCommit(true);
			pm.makePersistent(problem);
			tx.commit();
		} catch (Exception e) {
			logger.log(Level.SEVERE, "Error during creating problem.", e);
			throw new IllegalStateException(e);
		} finally {
			if (tx.isActive()) {
				tx.rollback();
			}
		}
		return problem;
	}

	@Override
	public Problem update(Problem request) {
		PersistenceManager pm = getPersistenceManager();
		try {
			pm.currentTransaction().begin();
			Problem makePersistent = pm.makePersistent(request);
			Problem detachCopy = pm.detachCopy(makePersistent);
			pm.currentTransaction().commit();
			return detachCopy;
		} catch (Exception e) {
			logger.log(Level.SEVERE, "Error during updating problem.", e);
			pm.currentTransaction().rollback();
			throw new IllegalStateException(e);
		} finally {
			pm.close();
		}
	}

	@Override
	public Expert update(Expert request, List<IdeaPreference> currentPreferences) {
		PersistenceManager pm = getPersistenceManager();
		try {
			pm.currentTransaction().begin();
			Expert oldOne = pm.getObjectById(Expert.class, request.getKey());
			oldOne.setCurrentPreferences(currentPreferences);
			Expert makePersistent = pm.makePersistent(oldOne);
			Expert detachCopy = pm.detachCopy(makePersistent);
			pm.currentTransaction().commit();
			return detachCopy;
		} catch (Exception e) {
			logger.log(Level.SEVERE, "Error during updating problem.", e);
			pm.currentTransaction().rollback();
			throw new IllegalStateException(e);
		} finally {
			pm.close();
		}
	}

	/**
	 * @inheritDoc
	 */
	@Override
	public void delete(ProblemId id) throws ObjectNotFoundException {
		PersistenceManager pm = getPersistenceManager();
		try {
			pm.deletePersistent(findRequest(id));
		} finally {
			pm.close();
		}
	}

	@Override
	public List<Problem> findAllAssignedTo(ExpertId expertId) {
		PersistenceManager pm = getPersistenceManager();
		List<Problem> problems = new ArrayList<Problem>();
		Transaction tx = pm.currentTransaction();
		try {
			tx.begin();
			Query q = pm.newQuery("select from " + Problem.class.getName()
					+ " where "
					+ "experts.contains(e) && e.userId == userIdParam");
			q.declareParameters("String userIdParam");
			q.declareVariables(Expert.class.getName() + " e");

			@SuppressWarnings("unchecked")
			List<Problem> ids = (List<Problem>) q.execute(expertId
					.getIdString());
			for (Problem problem : ids) {
				problems.add(pm.detachCopy(problem));
			}

			tx.commit();
		} catch (Exception e) {
			logger.log(Level.SEVERE, "Error during fetching problems.", e);
			throw new IllegalStateException(e);
		} finally {
			if (tx.isActive()) {
				tx.rollback();
			}
			pm.close();
		}
		return problems;
	}

	@Override
	public List<Problem> findAllAssignedTo(ExpertId id, ExpertRole owner) {
		// TODO Auto-generated method stub
		return null;
	}

	private Problem findRequest(ProblemId gdmProblemId)
			throws ObjectNotFoundException {
		Key key = KeyFactory.createKey(Problem.class.getSimpleName(),
				gdmProblemId.getIdString());
		PersistenceManager pm = getPersistenceManager();
		Problem problem = null;
		try {
			problem = pm.getObjectById(Problem.class, key);
		} finally {
			pm.close();
		}

		return problem;
	}

	private PersistenceManager getPersistenceManager() {
		return persistenceManagerFactory.getPersistenceManager();
	}

}
