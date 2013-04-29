package com.tdm.domain.model.expert.dto;

import java.util.List;

public class ExpertsInvitationDTO implements ExpertsInvitation {

	private List<String> emails;
	private String message;

	@Override
	public List<String> getEmails() {
		return emails;
	}

	@Override
	public String getMessage() {
		return message;
	}

}
