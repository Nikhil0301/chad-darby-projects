package com.luv2code.springboot.service;

import java.util.List;

import com.luv2code.springboot.entity.Employee;

public interface EmployeeService {
	Employee save(Employee employee);

	Employee findById(int id);

	List<Employee> findAll();

	void update(Employee employee);

	void deleteById(Integer id);
}
