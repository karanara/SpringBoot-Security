package com.springboot.example.cruddemo.rest;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.springboot.example.cruddemo.entity.Employee;
import com.springboot.example.cruddemo.dao.EmployeeDAO;

@RestController
@RequestMapping("/api")
public class EmployeeRestController {
 
	private EmployeeDAO employeeDAO;

	public EmployeeRestController(EmployeeDAO employeeDAO) {
		this.employeeDAO = employeeDAO;
	}
	
	@GetMapping("/employee")
    public List<Employee> findAll(){
    	return employeeDAO.findAll();
    }
	
	
}
