package com.hackerrank.stereotypes.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Answer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    public Integer user_id;
    public  Integer question_id;
    public String field_value;

    public Answer() {
    }

    public Answer(Integer user_id, Integer question_id, String field_value) {
        this.user_id = user_id;
        this.question_id = question_id;
        this.field_value = field_value;
    }

    public Answer(Integer id, Integer user_id, Integer question_id, String field_value) {
        this.id = id;
        this.user_id = user_id;
        this.question_id = question_id;
        this.field_value = field_value;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getQuestion_id() {
        return question_id;
    }

    public void setQuestion_id(Integer question_id) {
        this.question_id = question_id;
    }

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public String getField_value() {
        return field_value;
    }

    public void setField_value(String field_value) {
        this.field_value = field_value;
    }
}
