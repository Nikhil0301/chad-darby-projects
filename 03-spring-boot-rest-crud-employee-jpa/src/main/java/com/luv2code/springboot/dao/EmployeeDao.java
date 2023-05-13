package com.luv2code.springboot.dao;

import java.util.List;
import com.luv2code.springboot.entity.Employee;

public interface EmployeeDao {
	Employee save(Employee employee);

	Employee findById(int id);

	List<Employee> findAll();

	void deleteById(Integer id);

	void update(Employee employee);
}
