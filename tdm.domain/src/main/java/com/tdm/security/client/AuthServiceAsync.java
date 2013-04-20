package com.tdm.security.client;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.tdm.domain.model.user.dto.LocalUser;

public interface AuthServiceAsync {
    void checkAuthentication(AsyncCallback<LocalUser> callback);

}
