package com.springboot.example.cruddemo.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.springboot.example.cruddemo.entity.Employee;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

@Repository
public class EmployeeDAOImpl implements EmployeeDAO {
	
	private EntityManager entityManager;
	
    @Autowired
	public EmployeeDAOImpl(EntityManager entityManager) {
		this.entityManager = entityManager;
	}


	@Override
	public List<Employee> findAll() {
		// TODO Auto-generated method stub
		TypedQuery<Employee> typedQuery = entityManager.createQuery(" from Employee",Employee.class);
		List<Employee> employees = typedQuery.getResultList();
		return employees;
	}


	@Override
	public Employee findById(int theId) {
		// TODO Auto-generated method stub
		Employee employee = entityManager.find(Employee.class, theId);
		return employee ;
	}


	@Override
	public Employee save(Employee theEmployee) {
		// TODO Auto-generated method stub
		Employee employee = entityManager.merge(theEmployee);
		return employee;
	}


	@Override
	public void deleteById(int theId) {
		// TODO Auto-generated method stub
		Employee employee = entityManager.find(Employee.class, theId);
		entityManager.remove(employee);		
	}

}
