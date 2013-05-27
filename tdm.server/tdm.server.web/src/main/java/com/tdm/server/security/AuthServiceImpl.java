package com.tdm.server.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.tdm.domain.model.user.dto.LocalUser;
import com.tdm.security.client.AuthService;
import com.tdm.server.web.assembler.UserEntityAssembler;

@SuppressWarnings("serial")
public class AuthServiceImpl extends RemoteServiceServlet implements
		AuthService {

	@Override
	public LocalUser checkAuthentication() {

		Authentication authentication = SecurityContextHolder.getContext()
				.getAuthentication();

		if (authentication == null) {
			return null;
		} else {
			Object principal = authentication.getPrincipal();
			if (principal instanceof GaeSocialUser) {
				UserEntityAssembler assembler = new UserEntityAssembler();
				LocalUser localUser = assembler
						.fromEntity((GaeSocialUser) principal);
				return localUser;
			}
			return null;
		}
	}
}
