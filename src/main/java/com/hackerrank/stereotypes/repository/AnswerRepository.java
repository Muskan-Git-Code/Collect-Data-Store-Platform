package com.hackerrank.stereotypes.repository;


import com.hackerrank.stereotypes.model.Answer;
import com.hackerrank.stereotypes.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AnswerRepository extends JpaRepository<Answer,Integer> {
}
