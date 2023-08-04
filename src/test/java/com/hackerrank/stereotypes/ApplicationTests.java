package com.hackerrank.stereotypes;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hackerrank.stereotypes.controller.ContactController;
import com.hackerrank.stereotypes.model.*;
import com.hackerrank.stereotypes.repository.*;
import com.hackerrank.stereotypes.service.ContactService;
import com.jayway.jsonpath.JsonPath;
import org.json.JSONArray;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;

import javax.persistence.Entity;
import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@AutoConfigureMockMvc
class ApplicationTests {
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

    @Autowired
    private MockMvc mockMvc;

    @Before
    public void setup() {
        userRepository.deleteAll();
        formRepository.deleteAll();
        questionRepository.deleteAll();
        answerRepository.deleteAll();
        responseRepository.deleteAll();
    }

    @Test
    public void checkController() {
        boolean hasAnnotation = false;

        Annotation annotation = AnnotationUtils.findAnnotation(ContactController.class, Controller.class);
        if (annotation != null) {
            hasAnnotation = true;
        }

        assertTrue(hasAnnotation);
    }


    @Test
    public void checkService() {
        boolean hasAnnotation = false;

        Annotation annotation = AnnotationUtils.findAnnotation(ContactService.class, Service.class);
        if (annotation != null) {
            hasAnnotation = true;
        }

        assertTrue(hasAnnotation);
    }

    @Test
    public void checkRepository() {
        boolean hasAnnotation = false;

        Annotation annotation = AnnotationUtils.findAnnotation(UserRepository.class, Repository.class);
        if (annotation != null) {
            hasAnnotation = true;
        }
        assertTrue(hasAnnotation);

        hasAnnotation = false;
        annotation = AnnotationUtils.findAnnotation(QuestionRepository.class, Repository.class);
        if (annotation != null) {
            hasAnnotation = true;
        }
        assertTrue(hasAnnotation);

        hasAnnotation = false;
        annotation = AnnotationUtils.findAnnotation(AnswerRepository.class, Repository.class);
        if (annotation != null) {
            hasAnnotation = true;
        }
        assertTrue(hasAnnotation);

        hasAnnotation = false;
        annotation = AnnotationUtils.findAnnotation(FormRepository.class, Repository.class);
        if (annotation != null) {
            hasAnnotation = true;
        }
        assertTrue(hasAnnotation);

        hasAnnotation = false;
        annotation = AnnotationUtils.findAnnotation(ResponseRepository.class, Repository.class);
        if (annotation != null) {
            hasAnnotation = true;
        }
        assertTrue(hasAnnotation);
    }

    @Test
    public void checkEntity() {
        boolean hasAnnotation = false;

        Annotation annotation = AnnotationUtils.findAnnotation(User.class, Entity.class);
        if (annotation != null) {
            hasAnnotation = true;
        }
        assertTrue(hasAnnotation);

        hasAnnotation = false;
        annotation = AnnotationUtils.findAnnotation(Question.class, Entity.class);
        if (annotation != null) {
            hasAnnotation = true;
        }
        assertTrue(hasAnnotation);

        hasAnnotation = false;
        annotation = AnnotationUtils.findAnnotation(Answer.class, Entity.class);
        if (annotation != null) {
            hasAnnotation = true;
        }
        assertTrue(hasAnnotation);

        hasAnnotation = false;
        annotation = AnnotationUtils.findAnnotation(Form.class, Entity.class);
        if (annotation != null) {
            hasAnnotation = true;
        }
        assertTrue(hasAnnotation);

        hasAnnotation = false;
        annotation = AnnotationUtils.findAnnotation(Response.class, Entity.class);
        if (annotation != null) {
            hasAnnotation = true;
        }
        assertTrue(hasAnnotation);
    }

    @Test
    public Integer testPostUser(String name, String phone_number, String email, String password) throws Exception {
        User person = new User(name, phone_number, email, password);
        ObjectMapper objectMapper = new ObjectMapper();

        MockHttpServletResponse response = mockMvc.perform(post("/user/save")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(person)))
                .andDo(print())
                .andExpect(jsonPath("$.*", hasSize(6)))
                .andExpect(jsonPath("$.id", greaterThan(0)))
                .andExpect(jsonPath("$.name").value(name))
                .andExpect(jsonPath("$.phone_number").value(phone_number))
                .andExpect(jsonPath("$.email").value(email))
                .andExpect(jsonPath("$.password").value(password))
                .andExpect(status().isCreated()).andReturn().getResponse();

        Integer id = JsonPath.parse(response.getContentAsString()).read("$.id");
        assertEquals(true, userRepository.findById(id).isPresent());

        return id;
    }


    @Test
    public Integer testPostForm(Integer owner_id, String title, String description) throws Exception {
        Form person = new Form(owner_id, title, description);
        ObjectMapper objectMapper = new ObjectMapper();

        MockHttpServletResponse response = mockMvc.perform(post("/form/save")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(person)))
                .andDo(print())
                .andExpect(jsonPath("$.*", hasSize(4)))
                .andExpect(jsonPath("$.id", greaterThan(0)))
                .andExpect(jsonPath("$.owner_id").value(owner_id))
                .andExpect(jsonPath("$.title").value(title))
                .andExpect(status().isCreated()).andReturn().getResponse();

        Integer id = JsonPath.parse(response.getContentAsString()).read("$.id");
        assertEquals(true, formRepository.findById(id).isPresent());

        return id;
    }


    @Test
    public Integer testPostQuestion(Integer form_id, String field_value) throws Exception {
        Question person = new Question(form_id, field_value);
        ObjectMapper objectMapper = new ObjectMapper();

        MockHttpServletResponse response = mockMvc.perform(post("/question/save")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(person)))
                .andDo(print())
                .andExpect(jsonPath("$.*", hasSize(3)))
                .andExpect(jsonPath("$.id", greaterThan(0)))
                .andExpect(jsonPath("$.form_id").value(form_id))
                .andExpect(jsonPath("$.field_value").value(field_value))
                .andExpect(status().isCreated()).andReturn().getResponse();

        Integer id = JsonPath.parse(response.getContentAsString()).read("$.id");
        assertEquals(true, questionRepository.findById(id).isPresent());

        return id;
    }

    //@Test
    public Integer testPostAnswers(Integer user_id, Integer question_id, String field_value) throws Exception {
        Answer person = new Answer(user_id, question_id, field_value);
        ObjectMapper objectMapper = new ObjectMapper();

        MockHttpServletResponse response = mockMvc.perform(post("/answer/save")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(person)))
                .andDo(print())
                .andExpect(jsonPath("$.*", hasSize(4)))
                .andExpect(jsonPath("$.id", greaterThan(0)))
                .andExpect(jsonPath("$.user_id").value(user_id))
                .andExpect(jsonPath("$.question_id").value(question_id))
                .andExpect(jsonPath("$.field_value").value(field_value))
                .andExpect(status().isCreated()).andReturn().getResponse();

        Integer id = JsonPath.parse(response.getContentAsString()).read("$.id");
        assertEquals(true, answerRepository.findById(id).isPresent());

        Integer qid = answerRepository.findById(id).get().getQuestion_id();
        questionRepository.findById(qid).get().getForm_id();


        return id;
    }

    //@Test
    public void testPostAnswers(List<Answer> answers) throws Exception {

        ObjectMapper objectMapper = new ObjectMapper();

        MockHttpServletResponse response = mockMvc.perform(post("/answer/saveall")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(answers)))
                        .andDo(print())
                        .andExpect(status().isCreated()).andReturn().getResponse();

    }

    //@Test
//    public void testPostAnswers(Integer user_id, Integer question_id, String field_value) throws Exception {
//
//        Answer person = new Answer(user_id, question_id, field_value);
//        ObjectMapper objectMapper = new ObjectMapper();
//
//        MockHttpServletResponse response = mockMvc.perform(post("/answer/save")
//                        .contentType("application/json")
//                        .content(objectMapper.writeValueAsString(person)))
//                .andDo(print())
//                .andExpect(status().isCreated()).andReturn().getResponse();
//
//        Integer id = JsonPath.parse(response.getContentAsString()).read("$.id");
//        assertEquals(true, answerRepository.findById(id).isPresent());
//    }

    @Test
    public void testGetAllUsers() throws Exception {

        postUserData();

        mockMvc.perform(get("/user/get/"))
                .andDo(print())
                .andExpect(status().isOk());


    }

    @Test
    public void testGetAllAnswers() throws Exception {

        postUserData();

        mockMvc.perform(get("/answer/get/"))
                .andDo(print())
                .andExpect(status().isOk());
    }


    /*
    Get All the responses
     */
    @Test
    public void testGetAllResponses() throws Exception {
        postUserData();

        mockMvc.perform(get("/response/get/"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void testGetResponse() throws Exception{
        Integer id = 1;

        postUserData();

        mockMvc.perform(get("/response/retrieve/"+ id))
                .andDo(print())
                .andExpect(jsonPath("$.id").value(id))
                .andExpect(status().isOk());

    }

    @Test
    public void testCountResponsesPerForm() throws Exception {
        postUserData();

        mockMvc.perform(get("/response/count/"))
                .andDo(print())
                .andExpect(status().isOk());
    }


    @Test
    public void postUserData() throws Exception {

        //Create Users
        Integer user_id_1 = testPostUser("User1", "8476283495", "user1@gmail.com", "1234");
        Integer user_id_2 = testPostUser("User2", "1234567890", "user2@gmail.com", "1231");
        Integer user_id_3 = testPostUser("User3", "7476283497", "user3@gmail.com", "1232");
        Integer user_id_4 = testPostUser("User4", "5476283957", "user4@gmail.com", "1233");
        Integer user_id_5 = testPostUser("User5", "4476284957", "user5@gmail.com", "1235");
        Integer user_id_6 = testPostUser("User6", "3476234957", "user6@gmail.com", "1236");
        Integer user_id_7 = testPostUser("User7", "2476834957", "user7@gmail.com", "1237");

        //Form Created by form owner
        Integer form_id_1 = testPostForm(user_id_1, "College Detail Form", "");
        Integer form_id_2 = testPostForm(user_id_2, "Work Detail Form", "");

        //Add questions in form
        Integer question_id_1 = testPostQuestion(form_id_1, "College name?");
        Integer question_id_2 = testPostQuestion(form_id_1, "Which course?");

        Integer question_id_3 = testPostQuestion(form_id_2, "Company name?");
        Integer question_id_4 = testPostQuestion(form_id_2, "Designation?");
        Integer question_id_5 = testPostQuestion(form_id_2, "Salary?");


        //Add answers given by users, and also at same time add responses in response table
        List<Answer> answers = new ArrayList<>();

        // user3 filling form1
        Answer a = new Answer(user_id_3, question_id_1, "IIT, Delhi");      answers.add(a);
        a = new Answer(user_id_3, question_id_2, "B.Tech");     answers.add(a);
        testPostAnswers(answers);

        //user4 filling form1
        answers = new ArrayList<>();
        a = new Answer(user_id_4, question_id_1, "IIT, KGP");      answers.add(a);
        a = new Answer(user_id_4, question_id_2, "M.Tech");     answers.add(a);
        testPostAnswers(answers);

        //user5 filling form1
        answers = new ArrayList<>();
        a = new Answer(user_id_5, question_id_1, "IIT, Gandhinagar");   answers.add(a);
        a = new Answer(user_id_5, question_id_2, "MBA");    answers.add(a);
        testPostAnswers(answers);

        //user6 filling form1
        answers = new ArrayList<>();
        a = new Answer(user_id_6, question_id_1, "IIT, Delhi"); answers.add(a);
        a = new Answer(user_id_6, question_id_2, "MS"); answers.add(a);
        testPostAnswers(answers);

        //user5 filing form2
        answers = new ArrayList<>();
        a = new Answer(user_id_5, question_id_3, "Intuit"); answers.add(a);
        a = new Answer(user_id_5, question_id_4, "SWE");    answers.add(a);
        a = new Answer(user_id_5, question_id_5, "40000");  answers.add(a);
        testPostAnswers(answers);

        //user6 filling form2
        answers = new ArrayList<>();
        a = new Answer(user_id_6, question_id_3, "Atlan");  answers.add(a);
        a = new Answer(user_id_6, question_id_4, "SWE-2");  answers.add(a);
        a = new Answer(user_id_6, question_id_5, "90000");  answers.add(a);
        testPostAnswers(answers);

        //user7 filling form2
        answers = new ArrayList<>();
        a = new Answer(user_id_7, question_id_3, "Atlan");  answers.add(a);
        a = new Answer(user_id_7, question_id_4, "SWE");    answers.add(a);
        a = new Answer(user_id_7, question_id_5, "70000");  answers.add(a);
        testPostAnswers(answers);



        //Add answers given by users
//        testPostAnswers(user_id_3, question_id_1, "IIT, Delhi");
//        testPostAnswers(user_id_4, question_id_1, "IIT, KGP");
//        testPostAnswers(user_id_5, question_id_1, "IIT, Gandhinagar");
//        testPostAnswers(user_id_6, question_id_1, "IIT, Delhi");
//
//        testPostAnswers(user_id_3, question_id_2, "B.Tech");
//        testPostAnswers(user_id_4, question_id_2, "B.Tech");
//        testPostAnswers(user_id_5, question_id_2, "MBA");
//        testPostAnswers(user_id_6, question_id_2, "MS");
//
//        testPostAnswers(user_id_5, question_id_3, "Intuit");
//        testPostAnswers(user_id_6, question_id_3, "Atlan");
//        testPostAnswers(user_id_7, question_id_3, "Atlan");
//
//        testPostAnswers(user_id_5, question_id_4, "SWE");
//        testPostAnswers(user_id_6, question_id_4, "SWE-2");
//        testPostAnswers(user_id_7, question_id_4, "SWE");
//
//        testPostAnswers(user_id_5, question_id_5, "40000");
//        testPostAnswers(user_id_6, question_id_5, "90000");
//        testPostAnswers(user_id_7, question_id_5, "70000");

    }
}