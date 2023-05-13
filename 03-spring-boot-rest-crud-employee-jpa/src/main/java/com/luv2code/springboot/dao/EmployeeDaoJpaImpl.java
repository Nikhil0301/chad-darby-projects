package com.luv2code.springboot.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.luv2code.springboot.entity.Employee;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;

@Repository
public class EmployeeDaoJpaImpl implements EmployeeDao {
	private EntityManager entityManager;

	@Autowired
	public EmployeeDaoJpaImpl(EntityManager entityManager) {
		super();
		this.entityManager = entityManager;
	}

	public List<Employee> findAll() {
		System.out.println(entityManager.toString());
		TypedQuery<Employee> query = entityManager.createQuery("FROM Employee", Employee.class);
		List<Employee> employees = query.getResultList();
		return employees;
	}

	@Override
	public Employee save(Employee employee) {
		Employee dbEmployee = entityManager.merge(employee);
		return dbEmployee;
	}

	@Override
	public Employee findById(int id) {
		Employee employee = entityManager.find(Employee.class, id);
		return employee;
	}

	@Override
	public void update(Employee employee) {
		entityManager.merge(employee);
	}

	@Override
	public void deleteById(Integer id) {
		Employee emp = entityManager.find(Employee.class, id);
	    entityManager.remove(emp);
	}
}
