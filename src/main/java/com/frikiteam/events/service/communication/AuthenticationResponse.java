package com.frikiteam.events.service.communication;

import java.io.Serializable;

public class AuthenticationResponse implements Serializable {
    private Long id;
    private String username;
    private String token;

    public AuthenticationResponse(String username, String token, Long id) {
        this.username = username;
        this.token = token;
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public String getToken() {
        return token;
    }

    public Long getId() {return id; }
}
