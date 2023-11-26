package com.springboot.example.cruddemo.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.springboot.example.cruddemo.entity.Employee;
import com.springboot.example.cruddemo.service.EmployeeService;

@RestController
@RequestMapping("/api")
public class EmployeeRestController {
 
	/*Refactoring all the code to Service Layer
	 * private EmployeeDAO employeeDAO;

	public EmployeeRestController(EmployeeDAO employeeDAO) {
		this.employeeDAO = employeeDAO;
	}
	
	@GetMapping("/employee")
    public List<Employee> findAll(){
    	return employeeDAO.findAll();
    }*/
	
	private EmployeeService employeeService;

	@Autowired
	public EmployeeRestController(EmployeeService employeeService) {
		this.employeeService = employeeService;
	}
	
	@GetMapping("/employee")
	public List<Employee> findAll(){
		return employeeService.findAll();
	}
	//adding a getMapping for finding a employee by id
	@GetMapping("/employee/{employeeId}")
	public Employee findById(@PathVariable int employeeId) {
		 Employee employee = employeeService.findById(employeeId);
		 if(employee==null) {
			 throw new RuntimeException("Employee ID not found" + employeeId);
		 }
		 return employee;
	}
	
	//post mapping to add an emplpyee
	@PostMapping("/employee")
	public Employee addEmployee(@RequestBody Employee theEmployee) {
		//incase if they pass id in json,by making it to 0 results in force to save an item instead of updating
		theEmployee.setId(0);
		return employeeService.save(theEmployee);
	}
	
	//add mapping to update an employee
	@PutMapping("/employee")
	public Employee updateEmployee(@RequestBody Employee theEmployee) {
		return employeeService.save(theEmployee);
	}
	//delete employee with delete method
	@DeleteMapping("/employee/{Id}")
	public String deleteEmployee( @PathVariable int Id) {
		Employee employee = employeeService.findById(Id);
		 if(employee==null) {
			 throw new RuntimeException("Employee ID not found" + Id);
		 }
		 employeeService.deleteById(Id);
		return "deleted the employee with  "+ Id;
		 
	}
}
