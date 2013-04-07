package com.tdm.server.persistance.jdo.assembler;

import com.tdm.domain.model.problem.vo.GdmProblem;
import com.tdm.domain.model.problem.vo.dto.GdmProblemDto;
import com.tdm.domain.model.problem.vo.dto.GdmProblemKeyDto;
import com.tdm.server.persistance.jdo.entities.GdmProblemEntity;

public class GdmProblemEntityAssembler {

	/**
	 * Simply creates {@code RequestDTO} based on JPA Request entity.
	 */
	public GdmProblemDto fromEntity(GdmProblemEntity entity) {
		GdmProblemDto gdmProblemDto = new GdmProblemDto();
		gdmProblemDto.setGdmProblemKey(new GdmProblemKeyDto(entity.getKey()));
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
