package com.hackerrank.stereotypes.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    public Integer form_id;
    public String field_value;

    public Question() {
    }

    public Question(Integer form_id, String field_value) {
        this.form_id = form_id;
        this.field_value = field_value;
    }

    public Question(Integer id, Integer form_id, String field_value) {
        this.id = id;
        this.form_id = form_id;
        this.field_value = field_value;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getForm_id() {
        return form_id;
    }

    public void setForm_id(Integer form_id) {
        this.form_id = form_id;
    }

    public String getField_value() {
        return field_value;
    }

    public void setField_value(String field_value) {
        this.field_value = field_value;
    }
}
