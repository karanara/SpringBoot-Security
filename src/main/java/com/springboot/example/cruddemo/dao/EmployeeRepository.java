package com.springboot.example.cruddemo.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.springboot.example.cruddemo.entity.Employee;

//@RepositoryRestResource(path="memebers")
//if we want to change the default endpoint
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

}
