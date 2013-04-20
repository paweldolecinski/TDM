package com.tdm.security.server;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.tdm.domain.model.user.dto.LocalUser;
import com.tdm.security.client.AuthService;

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
			if (principal instanceof UserDetails) {
				UserDetails details = (UserDetails) principal;
				LocalUser localUser = new LocalUser();
				localUser.setUsername(details.getUsername());
				return localUser;
			}
			return null;
		}
	}
}
