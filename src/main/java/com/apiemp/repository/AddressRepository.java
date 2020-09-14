package com.apiemp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.apiemp.model.Address;

public interface AddressRepository extends JpaRepository<Address, Long> {

}
