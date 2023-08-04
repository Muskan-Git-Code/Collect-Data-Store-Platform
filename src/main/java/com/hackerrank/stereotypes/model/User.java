package com.hackerrank.stereotypes.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    public String name;
    public String phone_number;
    public String email;
    public String password;
    public boolean is_owner = false;

    public User(){
    }

    public User(String name, String phone_number, String email, String password) {
        this.name = name;
        this.phone_number = phone_number;
        this.email = email;
        this.password = password;
    }

    public User(String name, String phone_number, String email, String password, boolean is_owner) {
        this.name = name;
        this.phone_number = phone_number;
        this.email = email;
        this.password = password;
        this.is_owner = is_owner;
    }

    public User(Integer id, String name, String phone_number, String email, String password, boolean is_owner) {
        this.id = id;
        this.name = name;
        this.phone_number = phone_number;
        this.email = email;
        this.password = password;
        this.is_owner = is_owner;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
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

    public boolean isIs_owner() {
        return is_owner;
    }

    public void setIs_owner(boolean is_owner) {
        this.is_owner = is_owner;
    }


}
