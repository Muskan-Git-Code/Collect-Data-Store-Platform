package com.hackerrank.stereotypes.controller;

import com.hackerrank.stereotypes.model.*;
import com.hackerrank.stereotypes.service.ContactService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
public class ContactController {

  @Autowired  
  ContactService contactService;

    @PostMapping( "/user/save")
    public ResponseEntity<User> userSave(@RequestBody User user){
        User saved = contactService.save(user);
        return new ResponseEntity(saved, HttpStatus.CREATED);
    }

    @GetMapping( "/user/get")
    public ResponseEntity<User> userGet(){
        List<User> saved = contactService.getAll();
        return new ResponseEntity(saved, HttpStatus.OK);
    }

    @PostMapping( "/form/save")
    public ResponseEntity<Form> formSave(@RequestBody Form form){
        Form saved = contactService.save(form);
        return new ResponseEntity(saved, HttpStatus.CREATED);
    }

    @PostMapping( "/question/save")
    public ResponseEntity<Question> questionSave(@RequestBody Question question){
        Question saved = contactService.save(question);
        return new ResponseEntity(saved, HttpStatus.CREATED);
    }

    @PostMapping( "/answer/saveall")
    public ResponseEntity<Answer> answerSaveAll(@RequestBody List<Answer> answer){
        List<Answer> saved = contactService.saveAllAnswers(answer);
        return new ResponseEntity(saved, HttpStatus.CREATED);
    }

    @GetMapping( "/answer/get")
    public ResponseEntity<Answer> ansGet(){
        List<Answer> saved = contactService.getAllAns();
        return new ResponseEntity(saved, HttpStatus.OK);
    }

    @PostMapping( "/response/save")
    public ResponseEntity<Response> responseSave(@RequestBody Response response){
        Response saved = contactService.save(response);
        return new ResponseEntity(saved, HttpStatus.CREATED);
    }

    @GetMapping("/response/get")
    public ResponseEntity<Response> responseGet(){
        List<Response> responses = contactService.getAllResponses();
        return new ResponseEntity(responses, HttpStatus.OK);
    }

    @GetMapping( "/response/retrieve/{id}")
    public ResponseEntity<Response> getResponse(@PathVariable("id") Integer id){
        Response person = contactService.getResponse(id);
        return new ResponseEntity(person, HttpStatus.OK);
    }

    @GetMapping("/response/count")
    public ResponseEntity<Response> responseCount(){

        List<Object[]> responses = contactService.getCountResponse();
        return new ResponseEntity(responses, HttpStatus.OK);
    }


}
