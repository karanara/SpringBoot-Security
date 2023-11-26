package com.springboot.example.cruddemo.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springboot.example.cruddemo.entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

}
