package com.example.beproject.models;

public class InsertAadharData {

    private String aadharNo;
    private String name;
    private String email;
    private String contact;
    private String address;
    private String DOB;

    public InsertAadharData() {

    }

    public String getAadharNo() {
        return aadharNo;
    }

    public InsertAadharData setAadharNo(String aadharNo) {
        this.aadharNo = aadharNo;
        return this;
    }

    public String getName() {
        return name;
    }

    public InsertAadharData setName(String name) {
        this.name = name;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public InsertAadharData setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getContact() {
        return contact;
    }

    public InsertAadharData setContact(String contact) {
        this.contact = contact;
        return this;
    }

    public String getAddress() {
        return address;
    }

    public InsertAadharData setAddress(String address) {
        this.address = address;
        return this;
    }

    public String getDOB() {
        return DOB;
    }

    public InsertAadharData setDOB(String DOB) {
        this.DOB = DOB;
        return this;
    }
}
