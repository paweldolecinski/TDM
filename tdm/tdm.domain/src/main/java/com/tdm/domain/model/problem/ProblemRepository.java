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
package com.tdm.domain.model.problem;

import java.util.Collection;

import com.tdm.domain.model.expert.ExpertId;
import com.tdm.domain.model.expert.ExpertRole;

/**
 * @author Paweł Doleciński
 */
public interface ProblemRepository {

	GdmProblem read(GdmProblemId id);

	void store(GdmProblem request);

	void delete(GdmProblemId id);

	Collection<GdmProblem> findAllAssignedTo(ExpertId expert);

	Collection<GdmProblem> findAllAssignedTo(ExpertId expert, ExpertRole owner);

}