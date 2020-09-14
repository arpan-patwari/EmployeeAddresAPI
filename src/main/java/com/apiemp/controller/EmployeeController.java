package com.apiemp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.apiemp.handler.CustomException;
import com.apiemp.model.Employee;
import com.apiemp.service.EmployeeService;

@RestController
@RequestMapping("/api/employee/")
public class EmployeeController {
	@Autowired
	private EmployeeService empService;

	@PostMapping("/createEmployee")
	public ResponseEntity<Employee> createEmployee(@RequestBody Employee newEmployee) {
		
		try {
			return empService.create(newEmployee);
		}
		catch(Exception e) {
			
			throw new CustomException("Failed to Create employee");
			
		}
		
	}
	
	@PutMapping("/updateEmployee/{id}")
	public ResponseEntity<Employee> updateEmployeeById(@PathVariable Long id, @RequestBody Employee emp) {
		
		try {
			return empService.updateEmployeeById(id, emp);
		}
		catch(Exception e) {
			throw new CustomException("Failed to Update employee");
		}
		
	}
	
	@DeleteMapping("/deleteEmployee/{id}")
	public ResponseEntity<Employee> deleteEmployee(@PathVariable Long id) {
		
		try {
			return empService.deleteEmployee(id);
		}
		catch(Exception e) {
			throw new CustomException("Failed to Delete employee");
		}
	}
	
	@GetMapping("/getAllEmployee")
	public ResponseEntity<List<Employee>> getAllEmployee() {
		try {
			return empService.findAllEmployees();
		}
		catch(Exception e) {
			throw new CustomException("Failed to Fetch employee");
		}
	}
	
	@GetMapping("/getEmployee/{id}")
	public ResponseEntity<Employee> getEmployeeById(@PathVariable Long id) {
		try {
			return empService.findById(id);
		}
		catch(Exception e) {
			throw new CustomException("Failed to Fetch employee");
		}
	}
	
}
