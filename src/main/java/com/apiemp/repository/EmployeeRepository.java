package com.apiemp.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.apiemp.model.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

	public Optional<Employee> findByName(String name);
}
