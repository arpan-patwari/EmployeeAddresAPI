package com.apiemp.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.apiemp.model.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

	public Optional<Employee> findByName(String name);
}
