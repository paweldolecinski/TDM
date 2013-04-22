package com.tdm.server.web.assembler;

import java.util.ArrayList;
import java.util.List;

import com.tdm.domain.model.idea.SolutionIdea;
import com.tdm.domain.model.idea.dto.SolutionIdeaDTO;
import com.tdm.domain.model.problem.Problem;
import com.tdm.domain.model.problem.ProblemId;
import com.tdm.server.application.problem.service.GdmProblemService;

public class SolutionIdeaDtoAssembler {

	/**
	 * Simply creates {@code RequestDTO} based on JPA Request entity.
	 */
	public SolutionIdeaDTO fromEntity(SolutionIdea entity) {
		SolutionIdeaDTO dto = new SolutionIdeaDTO();
		dto.setId(entity.getEncodedKey());
		dto.setName(entity.getName());
		dto.setDetails(entity.getDetails());
		dto.setCreationDate(entity.getCreationDate());
		return dto;
	}

	public SolutionIdea toEntity(SolutionIdeaDTO dto,
			GdmProblemService problemService) {
		SolutionIdea entity = new SolutionIdea();
		Problem problem = problemService.retrieveProblem(new ProblemId(dto
				.getProblemId()));
		entity.setName(dto.getName());
		entity.setDetails(dto.getDetails());
		entity.setProblem(problem);
		return entity;
	}

	public List<SolutionIdeaDTO> fromEntityList(List<SolutionIdea> entities) {
		ArrayList<SolutionIdeaDTO> res = new ArrayList<SolutionIdeaDTO>();
		for (SolutionIdea problem : entities) {
			res.add(fromEntity(problem));
		}
		return res;
	}
}
