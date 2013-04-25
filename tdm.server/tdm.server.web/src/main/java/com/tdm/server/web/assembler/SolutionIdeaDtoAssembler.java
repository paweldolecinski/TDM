package com.tdm.server.web.assembler;

import java.util.ArrayList;
import java.util.List;

import com.google.appengine.api.datastore.KeyFactory;
import com.tdm.domain.model.idea.SolutionIdea;
import com.tdm.domain.model.idea.dto.SolutionIdeaDTO;

public class SolutionIdeaDtoAssembler {

	/**
	 * Simply creates {@code RequestDTO} based on JPA Request entity.
	 */
	public SolutionIdeaDTO fromEntity(SolutionIdea entity) {
		SolutionIdeaDTO dto = new SolutionIdeaDTO();
		dto.setId(KeyFactory.keyToString(entity.getKey()));
		dto.setProblemId(KeyFactory.keyToString(entity.getProblemId()));
		dto.setName(entity.getName());
		dto.setDetails(entity.getDetails());
		dto.setCreationDate(entity.getCreationDate());
		return dto;
	}

	public SolutionIdea toEntity(SolutionIdeaDTO dto) {
		SolutionIdea entity = new SolutionIdea();
		entity.setName(dto.getName());
		entity.setDetails(dto.getDetails());
		return entity;
	}

	public List<SolutionIdeaDTO> fromEntityList(List<SolutionIdea> entities) {
		ArrayList<SolutionIdeaDTO> res = new ArrayList<SolutionIdeaDTO>();
		for (SolutionIdea solution : entities) {
			res.add(fromEntity(solution));
		}
		return res;
	}
}
