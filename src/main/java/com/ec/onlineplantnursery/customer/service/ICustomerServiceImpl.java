package com.ec.onlineplantnursery.customer.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ec.onlineplantnursery.customer.entity.Customer;
import com.ec.onlineplantnursery.customer.repository.ICustomerRepository;
import com.ec.onlineplantnursery.exceptions.ResourceNotFoundException;


@Service
public class ICustomerServiceImpl implements ICustomerService{
	
	@Autowired
	private ICustomerRepository custRepo;
	
	
	

	public ICustomerServiceImpl() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ICustomerServiceImpl(ICustomerRepository custRepo) {
		super();
		this.custRepo = custRepo;
	}

	@Override
	@Transactional
	public Customer addCustomer(Customer customer) {
		// TODO Auto-generated method stub
		custRepo.save(customer);
		return customer;
	}

	
	@Transactional
	@Override
	public Customer updateCustomer(Customer tenant) throws ResourceNotFoundException{
		// TODO Auto-generated method stub
		int custId = tenant.getCustomerId();
		Optional<Customer> s = custRepo.findById(custId);
		Customer s1 = null;
		if(s.isPresent()) {
			s1 = s.get();
			s1.setCustomerId(tenant.getCustomerId());
			s1.setCustomerEmail(tenant.getCustomerEmail());
			s1.setCustomerName(tenant.getCustomerName());
			s1.setUsername(tenant.getUsername());
			s1.setPassword(tenant.getPassword());
		
			custRepo.save(s1);
		}
		return s1;
	}

	@Transactional
	@Override
	public Customer deleteCustomer(Customer tenant) throws ResourceNotFoundException{
		// TODO Auto-generated method stub
		boolean isDeleted = false;
		int custId = tenant.getCustomerId();
		Optional<Customer> s = custRepo.findById(custId);
		if(s.isEmpty()) {
			throw new ResourceNotFoundException();
		}
		else {
			custRepo.delete(tenant);
		}
		return tenant;
		
	}

	@Override
	public Customer viewCustomer(int customerId) throws ResourceNotFoundException {
		Optional<Customer> s = custRepo.findById(customerId);
	      if(s.isEmpty()) {
	    	  throw new ResourceNotFoundException();
	      }
	      else {
			return custRepo.findById(customerId).get();
		}
	}

	@Override
	public List<Customer> viewAllCustomers() {
		// TODO Auto-generated method stub
		return custRepo.findAll();
	}

	@Override
	public boolean validateCustomer(String userName, String password) {
		// TODO Auto-generated method stub
		Boolean validBoolean = false;
		List<Customer> custList = custRepo.findAll();
		for(Customer c:custList) {
			if((c.getUsername().equalsIgnoreCase(userName))){
				if((c.getPassword()).equals(password)) {
					validBoolean= true;
				}
				
			}
		}
		return validBoolean;
	}
	
	
}
