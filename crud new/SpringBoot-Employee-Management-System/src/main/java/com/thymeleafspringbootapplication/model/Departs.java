package com.thymeleafspringbootapplication.model;

import java.util.Collection;
import java.util.Set;

import javax.persistence.*;					
@Entity
@Table(name = "Departs")	
public class Departs {

	@Id 
	@Column(length = 10)
	private String id;
	@Column(length = 50)
	private String name;
	@OneToMany(mappedBy = "departs")
	private Set<Employee> employees;
	public Departs() {
		super();
	}
	public Departs(String id, String name) {
		super();
		this.id = id;
		this.name = name;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;	
	}
	public void setName(String name) {
		this.name = name;
	}
	
	
}
