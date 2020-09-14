package com.apiemp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.apiemp.handler.CustomException;
import com.apiemp.model.Address;
import com.apiemp.service.AddressService;

@RestController
@RequestMapping("/api/address/")
public class AddressController {
	@Autowired
	private AddressService adddService;

	@PostMapping("/createAddress")
	public ResponseEntity<Address> createAddress(@RequestBody Address newAddress) {
		try {
			return adddService.create(newAddress);
		}
		catch(Exception e) {
			throw new CustomException("Failed to Create Address");
		}
	}
	
	@PutMapping("/updateAddress/{id}")
	public ResponseEntity<Address> updateAddressById(@PathVariable Long id, @RequestBody Address emp) {
		try {
		return adddService.updateAddressById(id, emp);
		}
		catch(Exception e) {
			throw new CustomException("Failed to update Address");
		}
	}
	
	@PutMapping("/updateAddressPartiallly")
	public ResponseEntity<Address> updateAddressPartial(@RequestBody Address add) {
		try {
			return adddService.updateAddressPartial(add);
		}
		catch(Exception e) {
			throw new CustomException("Failed to update Address");
		}
	}
	
	@DeleteMapping("/deleteAddress/{id}")
	public ResponseEntity<Address> deleteAddress(@PathVariable Long id) {
		try {
			return adddService.deleteAddressById(id);
		}
		catch(Exception e) {
			throw new CustomException("Failed to delete Address");
		}
		
	}
	
	@GetMapping("/getAllAddress")
	public ResponseEntity<List<Address>> getAllAddress() {
		try {
			return adddService.findAllAddress();
		}
		catch(Exception e) {
			throw new CustomException("Failed to Fetch Address");
		}
	}
	
	@GetMapping("/getAddress/{id}")
	public ResponseEntity<Address> getAddressById(@PathVariable Long id) {
		try {
			return adddService.findById(id);
		}
		catch(Exception e) {
			throw new CustomException("Failed to Fetch Address");
		}
	}
	
}
