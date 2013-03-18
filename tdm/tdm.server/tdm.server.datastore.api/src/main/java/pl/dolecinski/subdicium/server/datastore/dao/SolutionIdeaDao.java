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

import java.util.Collection;

import pl.dolecinski.subdicium.server.datastore.dto.ProblemDTO;
import pl.dolecinski.subdicium.server.datastore.dto.SolutionIdeaDTO;
import pl.dolecinski.subdicium.server.datastore.exception.ConstraintsViolationException;
import pl.dolecinski.subdicium.server.datastore.exception.ObjectNotFoundException;

/**
 * Data Access Object for Requests. Allows all CRUD operations.
 * <p>
 * To create new request dto use
 * {@link #create(java.lang.String, java.lang.String) create()} method, change
 * what you want and
 * {@link #update(pl.dolecinski.subdicium.server.datastore.dto.ProblemDTO)
 * update(...)} with changed requestDTO
 * <p>
 * To get recent Requests, call {@link #findOlderThan(java.util.Date, int)
 * findOlderThan(...)} with current Date, e.g.
 * 
 * <pre>
 * List&lt;RequestDTO&gt; recentRequests = requestDao.findOlderThan(new Date(), 20);
 * </pre>
 * 
 * @author Paweł Doleciński
 */
public interface SolutionIdeaDao {

	SolutionIdeaDTO create(long problemId, String solutionIdeaName);

	SolutionIdeaDTO read(long problemId, long solutionIdeaId) throws ObjectNotFoundException;

	/**
	 * Updates request with values taken from given {@link ProblemDTO}. If
	 * request with id taken from {@code RequestDTO}.
	 * <p>
	 * <b>WARNING:</b> for now only modifable property is status of request. All
	 * other changes are omitted.
	 * 
	 * @param request
	 *            contains values to set and <strong>id</strong> of updated
	 *            request
	 * @throws ObjectNotFoundException
	 *             when Request with given id doesn't exist in database, so it
	 *             cannot be updated
	 * @throws ConstraintsViolationException
	 *             when trying to change unmodifable properties or make changes
	 *             that makes inconsistency
	 */
	void update(SolutionIdeaDTO soultionIdea) throws ObjectNotFoundException,
			ConstraintsViolationException;

	/**
	 * Deletes request with given id from database
	 * 
	 * @param id
	 *            of request to delete
	 * @throws ObjectNotFoundException
	 *             when Request with given id doesn't exist in database, so it
	 *             cannot be deleted
	 */
	void delete(long id) throws ObjectNotFoundException;

	Collection<SolutionIdeaDTO> findAllAssignedTo(long problemId);

}
