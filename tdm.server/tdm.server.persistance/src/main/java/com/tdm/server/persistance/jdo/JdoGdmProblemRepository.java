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
import com.tdm.domain.model.expert.vo.ExpertId;
import com.tdm.domain.model.expert.vo.ExpertRole;
import com.tdm.domain.model.handling.ObjectNotFoundException;
import com.tdm.domain.model.problem.ProblemRepository;
import com.tdm.domain.model.problem.vo.GdmProblem;
import com.tdm.domain.model.problem.vo.GdmProblemKey;
import com.tdm.domain.model.problem.vo.dto.GdmProblemDto;
import com.tdm.server.persistance.jdo.assembler.GdmProblemEntityAssembler;
import com.tdm.server.persistance.jdo.entities.GdmProblemEntity;

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
	public GdmProblem read(GdmProblemKey id) throws ObjectNotFoundException {
		GdmProblemEntity request = findRequest(id);
		GdmProblemEntityAssembler assembler = new GdmProblemEntityAssembler();
		return assembler.fromEntity(request);
	}

	/**
	 * @inheritDoc
	 */
	@Override
	public GdmProblemDto create(GdmProblem problem) {
		GdmProblemEntityAssembler assembler = new GdmProblemEntityAssembler();
		GdmProblemEntity entity = assembler.toEntity(problem);
		PersistenceManager pm = getPersistenceManager();
		try {
			GdmProblemEntity createdPersistent = pm.makePersistent(entity);
			return assembler.fromEntity(createdPersistent);
		} finally {
			pm.close();
		}

	}

	@Override
	public GdmProblemDto update(GdmProblem request) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * @inheritDoc
	 */
	@Override
	public void delete(GdmProblemKey id) throws ObjectNotFoundException {
		PersistenceManager pm = getPersistenceManager();
		try {
			pm.deletePersistent(findRequest(id));
		} finally {
			pm.close();
		}
	}

	@Override
	public List<GdmProblem> findAllAssignedTo(ExpertId expertId) {
		PersistenceManager pm = getPersistenceManager();

		Query q = pm.newQuery(GdmProblemEntity.class);
		q.setOrdering("creationDate asc");

		try {
			@SuppressWarnings("unchecked")
			List<GdmProblemEntity> results = (List<GdmProblemEntity>) q
					.execute();

			if (!results.isEmpty()) {
				GdmProblemEntityAssembler assembler = new GdmProblemEntityAssembler();
				ArrayList<GdmProblem> res = new ArrayList<>();
				for (GdmProblemEntity p : results) {

					res.add(assembler.fromEntity(p));
				}
				return res;
			} else {
				return Collections.emptyList();
			}
		} finally {
			q.closeAll();
		}

	}

	@Override
	public List<GdmProblem> findAllAssignedTo(ExpertId id, ExpertRole owner) {
		// TODO Auto-generated method stub
		return null;
	}

	private GdmProblemEntity findRequest(GdmProblemKey gdmProblemId)
			throws ObjectNotFoundException {
		Key key = KeyFactory.createKey(GdmProblemEntity.class.getSimpleName(),
				gdmProblemId.getId());
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
