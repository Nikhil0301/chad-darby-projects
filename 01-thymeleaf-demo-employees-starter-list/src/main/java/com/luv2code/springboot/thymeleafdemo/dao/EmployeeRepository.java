package com.luv2code.springboot.thymeleafdemo.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.luv2code.springboot.thymeleafdemo.entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

	// that's it ... no need to write any code LOL!
	
	//add a method to sort by last name
	/*
	 * spring data jpa will parse the method name
	 * Looks for a specific format and pattern
	 * creates appropriate query ...behind the scenes
	 * 
	 * Refer this -->
	 * https://docs.spring.io/spring-data/jpa/docs/current/reference/html/#jpa.query-methods.query-creation
     */
     public List<Employee> findAllByOrderByLastNameAsc();
    
}
