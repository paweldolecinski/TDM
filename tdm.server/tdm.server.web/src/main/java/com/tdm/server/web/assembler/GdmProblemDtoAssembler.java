package com.tdm.server.web.assembler;

import java.util.ArrayList;
import java.util.List;

import com.google.appengine.api.datastore.KeyFactory;
import com.tdm.domain.model.expert.Expert;
import com.tdm.domain.model.expert.dto.ExpertDTO;
import com.tdm.domain.model.expert.dto.ExpertRole;
import com.tdm.domain.model.problem.Problem;
import com.tdm.domain.model.problem.dto.ProblemDTO;

public class GdmProblemDtoAssembler {

	/**
	 * Simply creates {@code RequestDTO} based on JPA Request entity.
	 */
	public ProblemDTO fromEntity(Problem entity) {
		ProblemDTO gdmProblemDto = new ProblemDTO();
		gdmProblemDto.setKey(KeyFactory.keyToString(entity.getKey()));
		gdmProblemDto.setName(entity.getName());
		gdmProblemDto.setDescription(entity.getDescription());
		gdmProblemDto.setCreationDate(entity.getCreationDate());
		List<Expert> experts = entity.getExperts();
		for (Expert expert : experts) {
			gdmProblemDto.addExpert(new ExpertDTO(expert.getId(), ExpertRole
					.valueOf(expert.getRole().name())));
		}
		return gdmProblemDto;
	}

	public Problem toEntity(ProblemDTO problem) {
		Problem entity = new Problem();
		entity.setName(problem.getName());
		entity.setDescription(problem.getDescription());
		return entity;
	}

	public List<ProblemDTO> fromEntityList(List<Problem> problems) {
		ArrayList<ProblemDTO> res = new ArrayList<ProblemDTO>();
		for (Problem problem : problems) {
			res.add(fromEntity(problem));
		}
		return res;
	}
}
