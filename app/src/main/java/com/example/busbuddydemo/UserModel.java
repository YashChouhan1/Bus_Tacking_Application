package com.example.busbuddydemo;

public class UserModel {

    private String name, email, dob, password, number;

    public UserModel(){

    }

    public UserModel(String name, String email, String dob, String password , String number) {
        this.name = name;
        this.email = email;
        this.dob = dob;
        this.password = password;
        this.number = number;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
