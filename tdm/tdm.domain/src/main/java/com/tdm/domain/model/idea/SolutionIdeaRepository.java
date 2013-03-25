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
package com.tdm.domain.model.idea;

import java.util.Collection;

import com.tdm.domain.model.handling.ConstraintsViolationException;
import com.tdm.domain.model.handling.ObjectNotFoundException;
import com.tdm.domain.model.problem.GdmProblemId;

/**
 * @author Paweł Doleciński
 * 
 */
public interface SolutionIdeaRepository {

	SolutionIdea read(GdmProblemId problemId, SolutionIdeaId solutionIdeaId);

	void store(SolutionIdea soultionIdea) throws ObjectNotFoundException,
			ConstraintsViolationException;

	void delete(SolutionIdeaId id) throws ObjectNotFoundException;

	Collection<SolutionIdea> findAllAssignedTo(GdmProblemId problemId);

}
