package com.frikiteam.events.service.communication;

import java.io.Serializable;

public class AuthenticationResponse implements Serializable {
    private Long id;
    private String username;
    private String token;
    private String role;

    public AuthenticationResponse(Long id, String username, String token, String role) {
        this.username = username;
        this.token = token;
        this.id = id;
        this.role = role;
    }

    public String getUsername() {
        return username;
    }

    public String getToken() {
        return token;
    }

    public Long getId() {return id; }

    public String getRole() { return role;}

}
