package com.apiemp.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.apiemp.model.Address;
import com.apiemp.model.Employee;
import com.apiemp.model.EmployeeAddress;
import com.apiemp.model.Employee;
import com.apiemp.repository.AddressRepository;
import com.apiemp.repository.EmployeeAddressRepository;
import com.apiemp.repository.EmployeeRepository;

@Service
public class EmployeeService {
	
	@Autowired
	private EmployeeRepository empRepo;
	@Autowired
	private EmployeeAddressRepository empAddRepo;
	@Autowired
	private AddressRepository addRepo;
	
	public ResponseEntity<List<Employee>> findAllEmployees() {
		List<Employee> employees=empRepo.findAll();
		
		return new ResponseEntity<>(employees,HttpStatus.OK);
	}

	
	
	public ResponseEntity<Employee> create(Employee employee) {
		
		
		List<EmployeeAddress> newEmpAddressList=new ArrayList<EmployeeAddress>();
		
		if(employee.getAssociatedEmpAddress().size()>0)
			for(EmployeeAddress empAddress:employee.getAssociatedEmpAddress()) {
				if(empAddress.getAddress()==null) continue;//Address not given
				else {
		//			System.out.println(empAddress.getAddress());
		//			System.out.println(empAddress.getAddressType());
					
					empAddress.setEmployee(employee);
					newEmpAddressList.add(empAddress);
				}
				
			
			}
		employee.setAssociatedEmpAddress(newEmpAddressList);
		empRepo.save(employee);
		return new ResponseEntity<>(employee,HttpStatus.CREATED);
	}

	
	
	
	public ResponseEntity<Employee> findById(Long id) {
		Optional<Employee> employeeOpt=empRepo.findById(id);
		if(!employeeOpt.isPresent())
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);;
		
		
		return new ResponseEntity<>(employeeOpt.get(),HttpStatus.OK);
	}
	
	
	
	@Transactional
	public ResponseEntity<Employee> deleteEmployee(Long id){
		
		Optional<Employee> empOpt=empRepo.findById(id);
		if(!empOpt.isPresent())
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);;
		
		List<EmployeeAddress> empAddressList=empAddRepo.findByEmployee(empOpt.get());
		
		System.out.println(empAddressList.get(0).getAddress().getAddrLineOne());
		if(empAddressList.size()>0)
			empAddRepo.deleteAll(empAddressList);
		
		empRepo.deleteById(id);
		return new ResponseEntity<>(null,HttpStatus.OK);
	}
	
	
	
	public ResponseEntity<Employee> updateEmployeeById(Long id,@RequestBody Employee newEmployee){
		Optional<Employee> employeeOpt=empRepo.findById(id);
		
		if(!employeeOpt.isPresent())
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);;
		
		Employee employee=employeeOpt.get();
		employee.setName(newEmployee.getName());
		employee.setDateOfBirth(newEmployee.getDateOfBirth());
		employee.setAssociatedEmpAddress(null);
		
		List<EmployeeAddress> newEmpAddressList=new ArrayList<EmployeeAddress>();
		
		if(newEmployee.getAssociatedEmpAddress().size()>0)
			for(EmployeeAddress newEmpAddress:newEmployee.getAssociatedEmpAddress()) {
				if(newEmpAddress.getAddress()==null) continue;//throw invalid address
				else {
					newEmpAddress.setEmployee(employee);
					newEmpAddressList.add(newEmpAddress);
				}
			}
		
		employee.setAssociatedEmpAddress(newEmpAddressList);
		empRepo.save(employee);			
		
		return new ResponseEntity<>(employee,HttpStatus.OK);
		
	}



}
