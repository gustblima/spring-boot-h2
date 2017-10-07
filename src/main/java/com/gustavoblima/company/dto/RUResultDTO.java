package com.gustavoblima.company.dto;

public class RUResultDTO{
    private String gender;
    private RUNameDTO name;
    private String email;

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public RUNameDTO getName() {
        return name;
    }

    public void setName(RUNameDTO name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}