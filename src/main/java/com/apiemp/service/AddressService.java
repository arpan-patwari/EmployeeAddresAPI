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
import com.apiemp.model.Address;
import com.apiemp.repository.AddressRepository;
import com.apiemp.repository.EmployeeAddressRepository;
import com.apiemp.repository.AddressRepository;
@Service
public class AddressService {
	
	@Autowired
	private AddressRepository addRepo;
	@Autowired
	private EmployeeAddressRepository empAddRepo;

	
	public ResponseEntity<List<Address>> findAllAddress() {
		List<Address> addresss=addRepo.findAll();
		
		return new ResponseEntity<>(addresss,HttpStatus.OK);
	}

	public ResponseEntity<Address> create(Address address) {
		
	//	empRepo.save(address);
	//	address newaddress=new address();
		
		List<EmployeeAddress> newEmpAddressList=new ArrayList<EmployeeAddress>();
		
		if(address.getAssociatedEmpAddress().size()>0)
			for(EmployeeAddress empAddress:address.getAssociatedEmpAddress()) {
				if(empAddress.getEmployee()==null) continue;
				else {
					System.out.println(empAddress.getAddress());
					System.out.println(empAddress.getAddressType());
					
					empAddress.setAddress(address);
					newEmpAddressList.add(empAddress);
				}
				
			
			}
	//	System.out.println(address);
		address.setAssociatedEmpAddress(newEmpAddressList);
		
		addRepo.save(address);
		
		
		return new ResponseEntity<>(address,HttpStatus.CREATED);
	}

	
	
	
	public ResponseEntity<Address> findById(Long id) {
		Optional<Address> addressOpt=addRepo.findById(id);
	//	if(!orderOpt.isPresent()) throw new EntityNotFoundException(id,Orders.class.getCanonicalName());
	//	System.out.println("Requested order is "+orderOpt.get());
		
		return new ResponseEntity<>(addressOpt.get(),HttpStatus.OK);
	}
	
	@Transactional
	public ResponseEntity<Address> deleteAddress(Address add){
		Optional<Address> addOpt=addRepo.findById(add.getId());
		if(!addOpt.isPresent())
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);;
		
		List<EmployeeAddress> empAddressList=empAddRepo.findByAddress(addOpt.get());
		
		System.out.println(empAddressList.get(0).getAddress().getAddrLineOne());
		if(empAddressList.size()>0)
			empAddRepo.deleteAll(empAddressList);
		
		addRepo.deleteById(add.getId());
		return new ResponseEntity<>(null,HttpStatus.OK);
	}
	
	
	@Transactional
	public ResponseEntity<Address> deleteAddressById(Long id){
		Optional<Address> addOpt=addRepo.findById(id);
		if(!addOpt.isPresent())
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);;
		
		List<EmployeeAddress> empAddressList=empAddRepo.findByAddress(addOpt.get());
		
		System.out.println(empAddressList.get(0).getAddress().getAddrLineOne());
		if(empAddressList.size()>0)
			empAddRepo.deleteAll(empAddressList);
		
		addRepo.deleteById(id);
		return new ResponseEntity<>(null,HttpStatus.OK);
	}
	
	
	public ResponseEntity<Address> updateAddressById(Long id,@RequestBody Address newAddress){
		
		Optional<Address> AddressOpt=addRepo.findById(id);
		
		if(!AddressOpt.isPresent())
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);;
		
		Address address=AddressOpt.get();
		address.setAddrLineOne(newAddress.getAddrLineOne());
		address.setAddrLineTwo(newAddress.getAddrLineTwo());
		address.setCity(newAddress.getCity());
		address.setAssociatedEmpAddress(null);
		
		List<EmployeeAddress> newEmpAddressList=new ArrayList<EmployeeAddress>();
		
		if(newAddress.getAssociatedEmpAddress().size()>0)
			for(EmployeeAddress newEmpAddress:newAddress.getAssociatedEmpAddress()) {
				if(newEmpAddress.getEmployee()==null) continue;
				else {
			//		System.out.println(newEmpAddress.getAddress());
			//		System.out.println(newEmpAddress.getAddressType());
					
					newEmpAddress.setAddress(address);
					newEmpAddressList.add(newEmpAddress);
				}
			}
		
		address.setAssociatedEmpAddress(newEmpAddressList);
		addRepo.save(address);			
		
		return new ResponseEntity<>(address,HttpStatus.OK);

		
		
		
		
	}
	
	
	public ResponseEntity<Address> updateAddressPartial(@RequestBody Address newAddress){
		Optional<Address> addressOpt=addRepo.findById(newAddress.getId());
		if(!addressOpt.isPresent())
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);;
		
		Address address=addressOpt.get();
		if(!newAddress.getAddrLineOne().isEmpty()) address.setAddrLineOne(newAddress.getAddrLineOne());
		if(!newAddress.getAddrLineTwo().isEmpty()) address.setAddrLineOne(newAddress.getAddrLineTwo());
		if(!newAddress.getCity().isEmpty()) address.setAddrLineOne(newAddress.getCity());
		if(!newAddress.getAssociatedEmpAddress().isEmpty()) {
			
			
			List<EmployeeAddress> newEmpAddressList=new ArrayList<EmployeeAddress>();
			address.setAssociatedEmpAddress(null);
			if(newAddress.getAssociatedEmpAddress().size()>0)
				for(EmployeeAddress newEmpAddress:newAddress.getAssociatedEmpAddress()) {
					if(newEmpAddress.getEmployee()==null) continue;//throw invalid employee
					else {
				//		System.out.println(newEmpAddress.getAddress());
				//		System.out.println(newEmpAddress.getAddressType());
						
						newEmpAddress.setAddress(address);
						newEmpAddressList.add(newEmpAddress);
					}
				}
			
			address.setAssociatedEmpAddress(newEmpAddressList);
			
		}
		
		
		addRepo.save(address);			
		
		return new ResponseEntity<>(address,HttpStatus.OK);
	}
	
	
	


}
