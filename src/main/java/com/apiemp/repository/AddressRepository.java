package com.apiemp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.apiemp.model.Address;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {

}
