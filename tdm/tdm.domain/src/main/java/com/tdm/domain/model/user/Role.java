package com.tdm.domain.model.user;


public class Role {
    public static final String USER_ROLE = "ROLE_USER";

    private String authority;

    public Role(String authority) {
	this.authority = authority;
    }

    public String getAuthority() {
	return authority;
    }

    public void setAuthority(String authority) {
	this.authority = authority;
    }
}
