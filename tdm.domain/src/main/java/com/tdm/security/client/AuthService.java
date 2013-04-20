package com.tdm.security.client;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.tdm.domain.model.user.dto.LocalUser;

@RemoteServiceRelativePath("checkAuth")
public interface AuthService extends RemoteService {
	LocalUser checkAuthentication();
}
