package com.tdm.security.client;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface AuthServiceAsync {
    void checkAuthentication(AsyncCallback<Boolean> callback);

}
