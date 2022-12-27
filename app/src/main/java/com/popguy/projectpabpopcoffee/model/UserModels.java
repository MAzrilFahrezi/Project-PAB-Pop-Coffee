package com.popguy.projectpabpopcoffee.model;

public class UserModels {

    private String nama, email;

    public UserModels() {
    }

    public UserModels(String username, String email) {
        this.nama = username;
        this.email = email;
    }

    public String getUsername() {
        return nama;
    }

    public void setUsername(String username) {
        this.nama = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
