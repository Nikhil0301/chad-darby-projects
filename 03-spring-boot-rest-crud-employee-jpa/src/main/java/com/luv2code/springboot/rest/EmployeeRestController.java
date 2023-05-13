package com.luv2code.springboot.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.*;
import com.luv2code.springboot.entity.Employee;
import com.luv2code.springboot.service.EmployeeService;

@RestController
@RequestMapping("/api")
public class EmployeeRestController {
	@Autowired
	private EmployeeService employeeService;

	public EmployeeRestController(EmployeeService employeeService) {
		super();
		this.employeeService = employeeService;
	}

	@GetMapping("/employees")
	public List<Employee> getAllEmployees() {
		return employeeService.findAll();
	}

	@PostMapping("/employees")
	public String addEmployee(@RequestBody Employee employee) {
		System.out.println();
		Employee emp = employeeService.save(employee);
		return "Employee added successfully with employee id:- " + emp.getId();
	}

	@GetMapping("/employees/{id}")
	public Employee findEmployeeById(@PathVariable int id) {
		Employee emp = employeeService.findById(id);
		if (emp == null) {
			throw new RuntimeException("employee with id " + id + " not found");
		}
		return emp;
	}

	@DeleteMapping("/employees/{employeeId}")
	public String deleteEmployee(@PathVariable Integer employeeId) {
		Employee emp = employeeService.findById(employeeId);
		if(emp!=null)
		  employeeService.deleteById(employeeId);
		return "Employee deleted successfully..";
	}
	
	@PutMapping("/employees")
	public void updateEmployee(@RequestBody Employee employee) {
		employeeService.update(employee);
		//return "Employee updated successfully of employee id:- " + emp.getId();
	}
	
//	@ExceptionHandler
//	public ResponseEntity<EmployeeErrorResponse> handleException(EmployeeNotFoundException ex) {
//		EmployeeErrorResponse error = new EmployeeErrorResponse();
//		error.setStatus(HttpStatus.NOT_FOUND.value());
//		error.setErrorMessage(ex.getMessage());
//		error.setTimeStamp(System.currentTimeMillis());
//		return new ResponseEntity<>(error,HttpStatus.NOT_FOUND);
//	}

}
