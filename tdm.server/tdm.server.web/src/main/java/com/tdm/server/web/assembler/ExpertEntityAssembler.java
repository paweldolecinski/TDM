package com.tdm.server.web.assembler;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.tdm.domain.model.expert.Expert;
import com.tdm.domain.model.expert.dto.ExpertDTO;
import com.tdm.domain.model.expert.dto.ExpertRole;

public class ExpertEntityAssembler {

	public ExpertDTO fromEntity(Expert expert) {
		ExpertDTO dto = new ExpertDTO(expert.getId(), ExpertRole.valueOf(expert
				.getRole().name()));
		return dto;
	}

	public Expert toEntity(ExpertDTO expert) {
		Expert dto = new Expert(expert.getUserId(),
				com.tdm.domain.model.expert.ExpertRole.valueOf(expert.getRole()
						.name()));

		return dto;
	}

	public List<ExpertDTO> fromEntityList(Set<Expert> entities) {
		ArrayList<ExpertDTO> res = new ArrayList<ExpertDTO>();
		for (Expert entity : entities) {
			res.add(fromEntity(entity));
		}
		return res;
	}
}
