package com.ds.nofication.Models.Backend;

public class UserAuthentication {
    private String username;
    private String password;

    public UserAuthentication(String _userName, String _password) {
        username = _userName;
        password = _password;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}
