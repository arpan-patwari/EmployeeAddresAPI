package com.apiemp.model;

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

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table
public class Address {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="ADDRESS_ID")
	private Long id;
	
	
	@Column(nullable=false)
	@Size(min=5)
	private String addrLineOne;
	private String addrLineTwo;
	
	@Column(nullable=false)
	private String city;
	
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler","address"})
	@OneToMany(mappedBy="address",cascade = {CascadeType.PERSIST,CascadeType.MERGE,CascadeType.REFRESH})
	private List<EmployeeAddress> associatedEmpAddress;
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getAddrLineOne() {
		return addrLineOne;
	}

	public void setAddrLineOne(String addrLineOne) {
		this.addrLineOne = addrLineOne;
	}

	public String getAddrLineTwo() {
		return addrLineTwo;
	}

	public void setAddrLineTwo(String addrLineTwo) {
		this.addrLineTwo = addrLineTwo;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public List<EmployeeAddress> getAssociatedEmpAddress() {
		return associatedEmpAddress;
	}

	public void setAssociatedEmpAddress(List<EmployeeAddress> associatedEmpAddress) {
		this.associatedEmpAddress = associatedEmpAddress;
	}


	@Override
	public String toString() {
		return "Address [id=" + id + ", addrLineOne=" + addrLineOne + ", addrLineTwo=" + addrLineTwo + ", city=" + city
				+ ", associatedEmpAddress=" + associatedEmpAddress + "]";
	}

}
