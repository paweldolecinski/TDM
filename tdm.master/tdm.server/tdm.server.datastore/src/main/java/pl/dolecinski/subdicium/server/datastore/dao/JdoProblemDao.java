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
package pl.dolecinski.subdicium.server.datastore.dao;

import javax.jdo.JDOHelper;
import javax.jdo.PersistenceManager;
import javax.jdo.PersistenceManagerFactory;
import javax.jdo.Query;

import org.springframework.stereotype.Repository;

import pl.dolecinski.subdicium.server.datastore.dao.ProblemDao;
import pl.dolecinski.subdicium.server.datastore.dto.ProblemDTO;
import pl.dolecinski.subdicium.server.datastore.exception.ConstraintsViolationException;
import pl.dolecinski.subdicium.server.datastore.exception.ObjectNotFoundException;
import pl.dolecinski.subdicium.server.datastore.jpa.entities.Problem;

/**
 * Defualt JDO implementation of ProblemDao
 * 
 * @author Paweł Doleciński
 * 
 * @see ProblemDao
 */
@Repository
public class JdoProblemDao implements ProblemDao {

	private static final PersistenceManagerFactory PMF = JDOHelper
			.getPersistenceManagerFactory("transactions-optional");

	/**
	 * @inheritDoc
	 */
	@Override
	public ProblemDTO create(String username, String resourceName) {

		Problem request = new Problem();
		request.setAuthorUsername(username);
		request.setResourceName(resourceName);
		PersistenceManager pm = getPersistenceManager();
		try {
			pm.makePersistent(request);
		} finally {
			pm.close();
		}
		return entityToDto(request);
	}

	/**
	 * @inheritDoc
	 */
	@Override
	public ProblemDTO create(String userName, String resourceName,
			String userMessage) {
		Problem request = new Problem();
		request.setAuthorUsername(userName);
		request.setResourceName(resourceName);
		request.setAuthorMessage(userMessage);
		PersistenceManager pm = getPersistenceManager();
		try {
			pm.makePersistent(request);
		} finally {
			pm.close();
		}
		return entityToDto(request);
	}

	/**
	 * @inheritDoc
	 */
	@Override
	public ProblemDTO read(long id) throws ObjectNotFoundException {
		Problem request = findRequest(id);
		return entityToDto(request);
	}

	/**
	 * @inheritDoc
	 */
	@Override
	public void update(ProblemDTO request) throws ObjectNotFoundException,
			ConstraintsViolationException {
		Problem updatedRequest = findRequest(request.getId());
	}

	/**
	 * @inheritDoc
	 */
	@Override
	public void delete(long id) throws ObjectNotFoundException {
		PersistenceManager pm = getPersistenceManager();
		try {
			pm.deletePersistent(findRequest(id));
		} finally {
			pm.close();
		}
	}

	/**
	 * Finds request with given id or throws exception when not not found.
	 */
	private Problem findRequest(long id) throws ObjectNotFoundException {
		PersistenceManager pm = getPersistenceManager();
		Problem request = null;
		try {
			Query q = pm.newQuery(Problem.class, "id == i");
			q.declareParameters("java.lang.Long i");
			request = (Problem) q.execute(id);
		} finally {
			pm.close();
		}

		if (request == null) {
			throw new ObjectNotFoundException(Problem.class, id);
		}
		return request;
	}

	/**
	 * Simply creates {@code RequestDTO} based on JPA Request entity.
	 */
	private ProblemDTO entityToDto(Problem request) {
		return null;
//		return new ProblemDTO(request.getId(), request.getCreationDate(),
//				request.getSentDate(), request.getStatus(),
//				request.getResourceName(), request.getAuthorUsername(),
//				request.getAuthorMessage());
	}

	private PersistenceManager getPersistenceManager() {
		return PMF.getPersistenceManager();
	}
}
