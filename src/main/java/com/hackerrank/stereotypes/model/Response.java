package com.hackerrank.stereotypes.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Response {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    public Integer formId;
    public Integer userId;

    public Response() {
    }

    public Response(Integer formId, Integer userId) {
        this.formId = formId;
        this.userId = userId;
    }

    public Response(Integer id, Integer formId, Integer userId) {
        this.id = id;
        this.formId = formId;
        this.userId = userId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getformId() {
        return formId;
    }

    public void setformId(Integer formId) {
        this.formId = formId;
    }

    public Integer getuserId() {
        return userId;
    }

    public void setuserId(Integer userId) {
        this.userId = userId;
    }
}
