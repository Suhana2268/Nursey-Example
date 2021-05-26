package com.ec.onlineplantnursery.customer.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ec.onlineplantnursery.customer.entity.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Integer>{

}
