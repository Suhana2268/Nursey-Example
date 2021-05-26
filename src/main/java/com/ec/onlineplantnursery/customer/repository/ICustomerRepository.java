package com.ec.onlineplantnursery.customer.repository;

import java.util.List;

import com.ec.onlineplantnursery.customer.entity.Customer;

public interface ICustomerRepository {
	Customer addCustomer(Customer customer);

	Customer updateCustomer(Customer tenant);

	Customer deleteCustomer(Customer tenant);

	Customer viewCustomer(int customerId);

	List<Customer> viewAllCustomers();

	boolean validateCustomer(String userName, String password);
}
