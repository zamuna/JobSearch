package com.myjob.model;

import javax.ejb.Local;
import java.sql.Timestamp;
import java.time.LocalDate;

/**
 * Created by manozct on 5/20/2017.
 */
public class User {
    public User() {
    }

    public User(String fullname, Integer gender, String state, String city, String street, Integer zipcode, Integer birthyear, String email, String password, Timestamp datecreated, Timestamp dateupdated) {
        this.fullname = fullname;
        this.gender = gender;
        this.state = state;
        this.city = city;
        this.street = street;
        this.zipcode = zipcode;
        this.birthyear = birthyear;
        this.email = email;
        this.password = password;
        this.datecreated = datecreated;
        this.dateupdated = dateupdated;
    }

    private Integer userid;
    private String fullname;
    private Integer gender;
    private String state;
    private String city;
    private String street;
    private Integer zipcode;
    private Integer birthyear;
    private String email;
    private String password;
    private Timestamp datecreated;
    private Timestamp dateupdated;

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public Integer getZipcode() {
        return zipcode;
    }

    public void setZipcode(int zipcode) {
        this.zipcode = zipcode;
    }

    public Integer getBirthyear() {
        return birthyear;
    }

    public void setBirthyear(int birthyear) {
        this.birthyear = birthyear;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Timestamp getDatecreated() {
        return datecreated;
    }

    public void setDatecreated(Timestamp datecreated) {
        this.datecreated = datecreated;
    }

    public Timestamp getDateupdated() {
        return dateupdated;
    }

    public void setDateupdated(Timestamp dateupdated) {
        this.dateupdated = dateupdated;
    }
}
