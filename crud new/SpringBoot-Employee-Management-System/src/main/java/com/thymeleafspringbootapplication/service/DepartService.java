package com.thymeleafspringbootapplication.service;

import java.util.List;
import java.util.Optional;

import com.thymeleafspringbootapplication.model.Departs;


public interface DepartService {

	void deleteAll();

	void deleteAll(List<Departs> entities);

	void delete(Departs entity);

	void deleteById(String id);

	long count();

	boolean existsById(String id);

	Optional<Departs> findById(String id);

	List<Departs> saveAll(List<Departs> entities);

	List<Departs> findAllById(List<String> ids);

	List<Departs> findAll();

	Departs save(Departs  entity);
	
}
