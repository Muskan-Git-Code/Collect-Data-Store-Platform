package com.hackerrank.stereotypes.service;

import com.hackerrank.stereotypes.model.*;
import com.hackerrank.stereotypes.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;


@Service
public class ContactService {
  
    @Autowired
    UserRepository userRepository;

    @Autowired
    FormRepository formRepository;

    @Autowired
    AnswerRepository answerRepository;

    @Autowired
    QuestionRepository questionRepository;

    @Autowired
    ResponseRepository responseRepository;


    public User save(User person){
        return userRepository.save(person);
    }

    public List<Answer> saveAllAnswers(List<Answer> answers){

        try {

            Integer uid = answers.get(0).getUser_id();
            Optional<User> user = userRepository.findById(uid);

            Integer qid = answers.get(0).getQuestion_id();
            Optional<Question> question = questionRepository.findById(qid);

            if (!user.isPresent()) {
                throw new NullPointerException("UserId doesn't exist. Please register the user!");
            } else if (!question.isPresent()) {
                throw new NullPointerException("Question doesn't exist!");
            }
            if (user.isPresent() && question.isPresent()) {
                Integer form_id = question.get().getForm_id();

                Response response = new Response(form_id, uid);

                //Enter into result set only if not already present
                if (!responseRepository.findByFormIdAndUserId(form_id, uid).isPresent()) {
                    responseRepository.save(response);
                    answerRepository.saveAll(answers);
                }
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }

        return null;
    }

    public Question save(Question person){

        try {

            Integer id = person.getForm_id();
            Optional<Form> form = formRepository.findById(id);

            if (!form.isPresent()) {
                throw new NullPointerException("There is no form exists with this id!");
            }

            return questionRepository.save(person);

        }
        catch (Exception e){
            e.printStackTrace();
        }

        return null;
    }

    public Form save(Form person) {

        try {

            Integer id = person.getOwner_id();
            Optional<User> user = userRepository.findById(id);

            if (!user.isPresent()) {
                throw new NullPointerException("UserId doesn't exist. Please register the user!");
            } else {
                user.get().setIs_owner(true);
            }

            return formRepository.save(person);
        }
        catch (Exception e){
            e.printStackTrace();
        }

        return null;
    }

    public Response save(Response person){
        return responseRepository.save(person);
    }

    public List<User> getAll(){
        return userRepository.findAll();
    }

    public List<Answer> getAllAns(){
        return answerRepository.findAll();
    }

    public List<Response> getAllResponses(){
        return responseRepository.findAll();
    }

    public Response getResponse(@PathVariable("id") Integer id){
        return responseRepository.findById(id).get();
    }

    public List<Object[]> getCountResponse(){
        return responseRepository.countResponseByForm();
    }
}
