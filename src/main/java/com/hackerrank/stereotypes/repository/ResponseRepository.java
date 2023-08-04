package com.hackerrank.stereotypes.repository;


import com.hackerrank.stereotypes.model.Response;
import com.hackerrank.stereotypes.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ResponseRepository extends JpaRepository<Response,Integer> {

    public Optional<Response> findByFormId(Integer formId);

    public Optional<Response> findByFormIdAndUserId(Integer formId, Integer userId);

    @Query("SELECT r.formId, COUNT(r) FROM Response r GROUP BY r.formId")
    public List<Object[]> countResponseByForm();

}
