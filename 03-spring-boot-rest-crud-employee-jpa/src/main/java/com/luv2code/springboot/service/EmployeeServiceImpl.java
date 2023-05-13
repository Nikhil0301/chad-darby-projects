package com.luv2code.springboot.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.luv2code.springboot.dao.EmployeeDao;
import com.luv2code.springboot.dao.EmployeeRepository;
import com.luv2code.springboot.entity.Employee;

import jakarta.transaction.Transactional;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	private EmployeeRepository employeeRepository;

	public EmployeeServiceImpl(EmployeeRepository employeeDao) {
		this.employeeRepository = employeeRepository;
	}

	public List<Employee> findAll() {
		return employeeRepository.findAll();
	}

	@Override
	@Transactional
	public Employee save(Employee employee) {
		Employee dbEmployee = employeeRepository.save(employee);
		return dbEmployee;
	}

	@Override
	public Employee findById(int id) {
		Optional<Employee> employee = employeeRepository.findById(Integer.valueOf(id));
		if(employee.isPresent())
			return employee.get();
		return null;
	}

	@Override
	public void update(Employee employee) {
		employeeRepository.save(employee);
	}

	@Override
	public void deleteById(Integer id) {
		employeeRepository.deleteById(id);
	}

}
