package com.tdm.server.persistance.jdo.assembler;

import com.tdm.domain.model.problem.GdmProblem;
import com.tdm.domain.model.problem.dto.GdmProblemDto;
import com.tdm.domain.model.problem.dto.GdmProblemIdDto;
import com.tdm.server.persistance.jdo.entities.GdmProblemEntity;

public class GdmProblemEntityAssembler {

	/**
	 * Simply creates {@code RequestDTO} based on JPA Request entity.
	 */
	public GdmProblem fromEntity(GdmProblemEntity entity) {
		GdmProblemDto gdmProblemDto = new GdmProblemDto();
		gdmProblemDto.setId(new GdmProblemIdDto(entity.getKey().toString()));
		gdmProblemDto.setName(entity.getName());
		gdmProblemDto.setDescription(entity.getDescription());
		gdmProblemDto.setCreationDate(entity.getCreationDate());
		return gdmProblemDto;
	}

	public GdmProblemEntity toEntity(GdmProblem problem) {
		GdmProblemEntity entity = new GdmProblemEntity();
		entity.setName(problem.getName());
		entity.setDescription(problem.getDescription());
		return entity;
	}
}
