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

import java.util.Collection;

import javax.jdo.PersistenceManager;
import javax.jdo.PersistenceManagerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import com.tdm.domain.model.expert.ExpertId;
import com.tdm.domain.model.expert.ExpertRole;
import com.tdm.domain.model.handling.ConstraintsViolationException;
import com.tdm.domain.model.handling.ObjectNotFoundException;
import com.tdm.domain.model.problem.GdmProblem;
import com.tdm.domain.model.problem.GdmProblemId;
import com.tdm.domain.model.problem.ProblemRepository;
import com.tdm.server.persistance.jdo.assembler.GdmProblemEntityAssembler;
import com.tdm.server.persistance.jdo.entities.GdmProblemEntity;

/**
 * @author Paweł Doleciński
 * 
 * @see ProblemRepository
 */
@Repository
public class JdoGdmProblemRepository implements ProblemRepository {

	@Autowired(required = true)
	private PersistenceManagerFactory persistenceManagerFactory;

	public JdoGdmProblemRepository() {
		System.out
				.println("JdoGdmProblemRepositoryJdoGdmProblemRepositoryJdoGdmProblemRepository");
	}

	/**
	 * @inheritDoc
	 */
	@Override
	public GdmProblem read(GdmProblemId id) throws ObjectNotFoundException {
		GdmProblemEntity request = findRequest(id);
		GdmProblemEntityAssembler assembler = new GdmProblemEntityAssembler();
		return assembler.fromEntity(request);
	}

	/**
	 * @inheritDoc
	 */
	@Override
	public void store(GdmProblem problem) throws ObjectNotFoundException,
			ConstraintsViolationException {
		GdmProblemEntity entity = null;
		if (problem.getId().getIdString() == null) {
			GdmProblemEntityAssembler assembler = new GdmProblemEntityAssembler();
			entity = assembler.toEntity(problem);
		} else {
			entity = findRequest(problem.getId());
		}
		PersistenceManager pm = getPersistenceManager();
		try {
			pm.makePersistent(entity);
		} finally {
			pm.close();
		}
	}

	/**
	 * @inheritDoc
	 */
	@Override
	public void delete(GdmProblemId id) throws ObjectNotFoundException {
		PersistenceManager pm = getPersistenceManager();
		try {
			pm.deletePersistent(findRequest(id));
		} finally {
			pm.close();
		}
	}

	@Override
	public Collection<GdmProblem> findAllAssignedTo(ExpertId expertId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection<GdmProblem> findAllAssignedTo(ExpertId id,
			ExpertRole owner) {
		// TODO Auto-generated method stub
		return null;
	}

	private GdmProblemEntity findRequest(GdmProblemId gdmProblemId)
			throws ObjectNotFoundException {
		Key key = KeyFactory.createKey(GdmProblemEntity.class.getSimpleName(),
				gdmProblemId.getIdString());
		PersistenceManager pm = getPersistenceManager();
		GdmProblemEntity problem = null;
		try {
			problem = pm.getObjectById(GdmProblemEntity.class, key);
		} finally {
			pm.close();
		}

		return problem;
	}

	private PersistenceManager getPersistenceManager() {
		return persistenceManagerFactory.getPersistenceManager();
	}

}
