package com.tdm.domain.model.user.dto;

import java.io.Serializable;

public class Role implements Serializable {
	private static final long serialVersionUID = -1022102077930352548L;

	public static final String USER_ROLE = "ROLE_USER";

	private String authority;

	protected Role() {

	}

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
