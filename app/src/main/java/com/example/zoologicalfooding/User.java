package com.example.zoologicalfooding;

public class User {
    private String first_name,last_name, address,email,password;

    public User(String fname, String lname, String email, String address, String password) {
        this.first_name = fname;
        this.last_name = lname;
        this.email = email;
        this.password = password;
        this.address = address;
    }

    public String getLname() {
        return last_name;
    }

    public String getFname() {
        return first_name;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public String getAddress() {
        return address;
    }


}
