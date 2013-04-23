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
		try {
			Problem createdPersistent = pm.makePersistent(problem);
			return pm.detachCopy(createdPersistent);
		} finally {
			pm.close();
		}

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

		Query q = pm.newQuery(Problem.class);
		q.setOrdering("creationDate asc");

		try {
			@SuppressWarnings("unchecked")
			List<Problem> results = (List<Problem>) q.execute();
			if (!results.isEmpty()) {
				return (List<Problem>) pm.detachCopyAll(results);
			} else {
				return Collections.emptyList();
			}
		} finally {
			q.closeAll();
		}

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
