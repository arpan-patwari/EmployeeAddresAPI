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

import com.apiemp.model.Address;
import com.apiemp.service.AddressService;

@RestController
@RequestMapping("/api/address/")
public class AddressController {
	@Autowired
	private AddressService adddService;

	@PostMapping("/createAddress")
	public ResponseEntity<Address> createAddress(@RequestBody Address newAddress) {
		return adddService.create(newAddress);
	}
	
	@PutMapping("/updateAddress/{id}")
	public ResponseEntity<Address> updateAddressById(@PathVariable Long id, @RequestBody Address emp) {
	
		return adddService.updateAddressById(id, emp);
	}
	
	@DeleteMapping
	public ResponseEntity<Address> deleteAddress(@PathVariable Long id) {
		return adddService.deleteAddressById(id);
	}
	
	@GetMapping("/getAllAddress")
	public ResponseEntity<List<Address>> getAllAddress() {
		
		return adddService.findAllAddress();
	}
	
	@GetMapping("/getAddress/{id}")
	public ResponseEntity<Address> getAddressById(@PathVariable Long id) {
		
		return adddService.findById(id);
	}
	
}
