package com.popguy.projectpabpopcoffee.model;

public class UserModels {

    private String nama, email, noHp;

    public UserModels() {
    }

    public UserModels(String nama, String email, String noHp) {
        this.nama = nama;
        this.email = email;
        this.noHp = noHp;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNoHp() {
        return noHp;
    }

    public void setNoHp(String noHp) {
        this.noHp = noHp;
    }
}
