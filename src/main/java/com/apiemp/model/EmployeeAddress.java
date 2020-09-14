package com.apiemp.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
@Table

public class EmployeeAddress {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="EMP_ADDR_ID")
	private Long id;
	
	@ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE,CascadeType.REFRESH}, fetch = FetchType.LAZY)
	@JoinColumn(name="ADDRESS_ID")
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler","associatedEmpAddress"})
	private Address address;
	
	
	@ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE,CascadeType.REFRESH}, fetch = FetchType.LAZY)
	@JoinColumn(name="EMPLOYEE_ID")
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler","associatedEmpAddress"})
	private Employee employee;

	@Column(nullable=true)
	private String addressType;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}
	
	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public String getAddressType() {
		return addressType;
	}

	public void setAddressType(String addressType) {
		this.addressType = addressType;
	}

	@Override
	public String toString() {
		return "EmployeeAddress [id=" + id + ", address=" + address + ", employee=" + employee + ", addressType="
				+ addressType + "]";
	}
}
