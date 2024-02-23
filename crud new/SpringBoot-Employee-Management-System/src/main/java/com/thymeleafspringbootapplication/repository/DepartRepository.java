package com.thymeleafspringbootapplication.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.thymeleafspringbootapplication.model.Departs;

@Repository
public interface DepartRepository extends JpaRepository<Departs,String>{

}
