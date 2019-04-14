package com.example.beproject.models;

public class AadharData {

    private String aadharNo;
    private String name;
    private String email;
    private String contact;
    private String address;
    private String DOB;

    public AadharData() {

    }

    public String getAadharNo() {
        return aadharNo;
    }

    public AadharData setAadharNo(String aadharNo) {
        this.aadharNo = aadharNo;
        return this;
    }

    public String getName() {
        return name;
    }

    public AadharData setName(String name) {
        this.name = name;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public AadharData setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getContact() {
        return contact;
    }

    public AadharData setContact(String contact) {
        this.contact = contact;
        return this;
    }

    public String getAddress() {
        return address;
    }

    public AadharData setAddress(String address) {
        this.address = address;
        return this;
    }

    public String getDOB() {
        return DOB;
    }

    public AadharData setDOB(String DOB) {
        this.DOB = DOB;
        return this;
    }
}
