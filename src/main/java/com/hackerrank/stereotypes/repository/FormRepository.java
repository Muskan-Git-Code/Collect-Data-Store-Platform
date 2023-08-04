package com.hackerrank.stereotypes.repository;


import com.hackerrank.stereotypes.model.Form;
import com.hackerrank.stereotypes.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FormRepository extends JpaRepository<Form,Integer> {
}
