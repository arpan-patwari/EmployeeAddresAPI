package com.apiemp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.apiemp.model.Address;
import com.apiemp.model.Employee;
import com.apiemp.model.EmployeeAddress;



public interface EmployeeAddressRepository extends JpaRepository<EmployeeAddress, Long> {
	
	public List<EmployeeAddress> findByEmployee(Employee employee);
	public List<EmployeeAddress> findByAddress(Address address);
	
}
