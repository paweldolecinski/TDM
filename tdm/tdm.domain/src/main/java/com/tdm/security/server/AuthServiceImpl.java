package com.tdm.security.server;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.tdm.security.client.AuthService;

@SuppressWarnings("serial")
public class AuthServiceImpl extends RemoteServiceServlet implements
	AuthService {

    @Override
    public boolean checkAuthentication() {

	Authentication authentication = SecurityContextHolder.getContext()
		.getAuthentication();

	if (authentication == null) {
	    return false;
	} else {
	    return true;
	}
    }

}
