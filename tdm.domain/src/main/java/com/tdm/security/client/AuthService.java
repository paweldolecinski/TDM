package com.tdm.security.client;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("checkAuth")
public interface AuthService extends RemoteService {
    boolean checkAuthentication();
}
