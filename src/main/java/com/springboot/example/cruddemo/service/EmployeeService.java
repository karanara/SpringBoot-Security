package com.springboot.example.cruddemo.service;

import java.util.List;

import com.springboot.example.cruddemo.entity.Employee;

public interface EmployeeService {

	List<Employee> findAll();
}
