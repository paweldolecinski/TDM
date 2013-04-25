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
		Problem detachCopy;
		try {
			Problem problem = pm.getObjectById(Problem.class, key);
			detachCopy = pm.detachCopy(problem);
		} finally {
			pm.close();
		}
		return detachCopy;
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
		} finally {
			if (tx.isActive()) {
				tx.rollback();
			}
		}
		return problem;
	}

	@Override
	public Problem update(Problem request) {
		// TODO Auto-generated method stub
		return null;
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
			Query q_expert = pm.newQuery(Expert.class, "userId == userIdParam");
			q_expert.declareParameters("String userIdParam");
			@SuppressWarnings("unchecked")
			List<Expert> ids = (List<Expert>) q_expert.execute(expertId
					.getIdString());
			for (Expert expert : ids) {
				Problem problem = expert.getProblem();
				problem.getExperts().size();
				problem.getCurrentConsensus();
				problems.add(pm.detachCopy(problem));
			}

			// Query q = pm.newQuery(Problem.class,
			// "cities.contains(this.address.city)");
			// q.declareParameters("Collection cities");
			// q.setOrdering("creationDate asc");
			// @SuppressWarnings("unchecked")
			// List<Problem> results = (List<Problem>) q.execute(ids);
			// results.size();
			// for (Problem problem : results) {
			// problem.getExperts().size();
			// problem.getCurrentConsensus();
			// problems.add(pm.detachCopy(problem));
			// }

			tx.commit();
		} catch (Exception e) {
			logger.log(Level.SEVERE, "Error during fetching problems.", e);
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
