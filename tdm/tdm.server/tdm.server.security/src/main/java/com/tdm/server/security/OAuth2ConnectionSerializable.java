package com.tdm.server.security;

import java.io.Serializable;

import org.springframework.social.connect.ConnectionKey;

@SuppressWarnings("serial")
public class OAuth2ConnectionSerializable implements Serializable {

    private ConnectionKey key;

    private String displayName;

    private String profileUrl;

    private String imageUrl;

    private boolean valuesInitialized;

    private String accessToken;

    private String refreshToken;

    private Long expireTime;

}
