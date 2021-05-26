package com.ec.onlineplantnursery.customer.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ec.onlineplantnursery.customer.entity.Customer;
import com.ec.onlineplantnursery.customer.repository.ICustomerRepositoryImpl;

@Service
public class ICustomerServiceImpl implements ICustomerService{
	
	@Autowired
	private ICustomerRepositoryImpl custRepo;
	
	public ICustomerServiceImpl() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	@Transactional
	public Customer addCustomer(Customer customer) {
		// TODO Auto-generated method stub
		return custRepo.addCustomer(customer);
	}
	
	@Override
	public Customer updateCustomer(Customer tenant) {
		// TODO Auto-generated method stub
		return custRepo.updateCustomer(tenant);
	}

	@Override
	@Transactional
	public Customer deleteCustomer(Customer tenant) {
		// TODO Auto-generated method stub
		return custRepo.deleteCustomer(tenant);
	}

	@Override
	public Customer viewCustomer(int customerId) {
		// TODO Auto-generated method stub
		return custRepo.viewCustomer(customerId);
	}

	@Override
	public List<Customer> viewAllCustomers() {
		// TODO Auto-generated method stub
		return custRepo.viewAllCustomers();
	}

	@Override
	public boolean validateCustomer(String userName, String password) {
		// TODO Auto-generated method stub
		return custRepo.validateCustomer(userName, password);
	}


}
