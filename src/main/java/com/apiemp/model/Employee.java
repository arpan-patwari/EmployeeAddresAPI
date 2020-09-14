package com.apiemp.model;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
@Table
public class Employee {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="EMPLOYEE_ID")
	private Long id;
	
	
	@Column(nullable=false)
	@Size(min=3)
	private String name;
	private LocalDate dateOfBirth;
	
	
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler","employee"})
	@OneToMany(mappedBy="employee",cascade = {CascadeType.PERSIST, CascadeType.MERGE,CascadeType.REFRESH})
	private List<EmployeeAddress> associatedEmpAddress;

	public Long getId() {
		return id;
	}

	@Override
	public String toString() {
		return "Employee [id=" + id + ", name=" + name + ", dateOfBirth=" + dateOfBirth + ", associatedEmpAddress="
				+ associatedEmpAddress + "]";
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public LocalDate getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(LocalDate dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
	
	public List<EmployeeAddress> getAssociatedEmpAddress() {
		return associatedEmpAddress;
	}
	
	public void setAssociatedEmpAddress(List<EmployeeAddress> associatedEmpAddress) {
		this.associatedEmpAddress = associatedEmpAddress;
	}

}
