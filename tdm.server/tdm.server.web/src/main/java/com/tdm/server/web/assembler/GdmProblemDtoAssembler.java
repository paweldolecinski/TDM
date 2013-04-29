package com.tdm.server.web.assembler;

import java.util.ArrayList;
import java.util.List;

import com.google.appengine.api.datastore.KeyFactory;
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
		entity.getCurrentConsensus();
		return gdmProblemDto;
	}

	public ProblemDTO fromBasicEntity(Problem entity) {
		ProblemDTO gdmProblemDto = new ProblemDTO();
		gdmProblemDto.setKey(KeyFactory.keyToString(entity.getKey()));
		gdmProblemDto.setName(entity.getName());
		gdmProblemDto.setDescription(entity.getDescription());
		gdmProblemDto.setCreationDate(entity.getCreationDate());
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
			res.add(fromBasicEntity(problem));
		}
		return res;
	}
}
