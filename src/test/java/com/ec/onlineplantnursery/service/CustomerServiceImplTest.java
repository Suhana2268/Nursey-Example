package com.ec.onlineplantnursery.service;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.persistence.NoResultException;
import javax.persistence.PersistenceException;

import org.assertj.core.util.Arrays;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import com.ec.onlineplantnursery.customer.entity.Address;
import com.ec.onlineplantnursery.customer.entity.Customer;

import com.ec.onlineplantnursery.customer.repository.ICustomerRepository;
import com.ec.onlineplantnursery.customer.service.ICustomerServiceImpl;
import com.ec.onlineplantnursery.exceptions.ResourceNotFoundException;


@SpringBootTest
class CustomerServiceImplTest {

	ICustomerRepository customerRepo;
	private static ICustomerServiceImpl customerService;
	private static AutoCloseable ac;
	
	@BeforeEach
	public void doinit()
	{
	      customerRepo = mock(ICustomerRepository.class); // test through approach 2
	      customerService = new  ICustomerServiceImpl(customerRepo); 	// Approach 2
		ac = MockitoAnnotations.openMocks(this);
	}
	
	@AfterEach
	public void doAtEnd()throws Exception
	{
		ac.close();
	}
	
	@Test
	//@Disabled
	@DisplayName("Test-Save Seed")
	void testSaveCustomer() {
		
		
		Customer input = new Customer();
		input.setCustomerId(1);
		input.setCustomerName("srividya");
		input.setCustomerEmail("vaddi.srividya0103@gmail.com");
		input.setUsername("srividya");
		input.setPassword("srividya0103");
		Address add = new Address();
		add.setAddressId(1);
		add.setCity("hyderabad");
		add.setColony("laxmi nagar");
        add.setHouseNo("12-1-508");
        add.setPincode(500017l);
        add.setState("Telangana");
        input.setAddress(add);
        
        
        Customer output = new Customer();
        output.setCustomerId(1);
        output.setCustomerName("srividya");
        output.setCustomerEmail("vaddi.srividya0103@gmail.com");
        output.setUsername("srividya");
        output.setPassword("srividya0103");
		Address add1 = new Address();
		add1.setAddressId(1);
		add1.setCity("hyderabad");
		add1.setColony("laxmi nagar");
        add1.setHouseNo("12-1-508");
        add1.setPincode(500017l);
        add1.setState("Telangana");
        output.setAddress(add1);
		
		
		when(customerRepo.save(input)).thenReturn(output);
	    customerService.addCustomer(input);
	 	verify(customerRepo).save(input);
		assertEquals(input,output);
	}



	@Test
	//@Disabled
	@DisplayName("Test-Get All Seeds")
	void testGetAllCustomers() {
		
		Customer c1 = new Customer();
		c1.setCustomerId(1);
		c1.setCustomerName("srividya");
		c1.setCustomerEmail("vaddi.srividya0103@gmail.com");
		c1.setUsername("srividya");
		c1.setPassword("srividya0103");
		Address add = new Address();
		add.setAddressId(1);
		add.setCity("hyderabad");
		add.setColony("laxmi nagar");
        add.setHouseNo("12-1-508");
        add.setPincode(500017l);
        add.setState("Telangana");
        c1.setAddress(add);
        
        
        Customer c2 = new Customer();
        c2.setCustomerId(2);
        c2.setCustomerName("srividya");
        c2.setCustomerEmail("vaddi.srividya0103@gmail.com");
        c2.setUsername("srividya");
        c2.setPassword("srividya0103");
		Address add1 = new Address();
		add1.setAddressId(1);
		add1.setCity("hyderabad");
		add1.setColony("laxmi nagar");
        add1.setHouseNo("12-1-508");
        add1.setPincode(500017l);
        add1.setState("Telangana");
        c2.setAddress(add1);
		
		
		
		List<Customer> customerList1 = new ArrayList<>();
		customerList1.add(c1);
		customerList1.add(c2);
		
		when(customerRepo.findAll()).thenReturn(customerList1);
		
		List<Customer> seedListOutput = customerService.viewAllCustomers();
		
		
		verify(customerRepo).findAll();
		assertIterableEquals(customerList1, seedListOutput);
		
	}
	
	@Test
	//@Disabled
	@DisplayName("Test-Get Seed by Id")
	void testViewCustomerById(){
		
		Optional<Customer> s = Optional.empty();
		
		when(customerRepo.findById(2)).thenReturn(s);
		Executable executable = ()->{
			assertNotNull(customerService.viewCustomer(2));
		};
		assertThrows(ResourceNotFoundException.class, executable);
		
	}
	
	

	@Test
	//@Disabled
	@DisplayName("Test-Delete seed")
	void testDeleteCustomer() throws ResourceNotFoundException {
		Customer input = new Customer();
		input.setCustomerId(1);
		input.setCustomerName("srividya");
		input.setCustomerEmail("vaddi.srividya0103@gmail.com");
		input.setUsername("srividya");
		input.setPassword("srividya0103");
		Address add = new Address();
		add.setAddressId(1);
		add.setCity("hyderabad");
		add.setColony("laxmi nagar");
        add.setHouseNo("12-1-508");
        add.setPincode(500017l);
        add.setState("Telangana");
        input.setAddress(add);
        
        
        Customer output = new Customer();
        output.setCustomerId(1);
        output.setCustomerName("srividya");
        output.setCustomerEmail("vaddi.srividya0103@gmail.com");
        output.setUsername("srividya");
        output.setPassword("srividya0103");
		Address add1 = new Address();
		add1.setAddressId(1);
		add1.setCity("hyderabad");
		add1.setColony("laxmi nagar");
        add1.setHouseNo("12-1-508");
        add1.setPincode(500017l);
        add1.setState("Telangana");
        output.setAddress(add1);
		
		
		try{
			doNothing().
			 when(customerRepo).delete(input);

			customerService.deleteCustomer(input);

			verify(customerRepo).delete(input);
			assertEquals(input,output);

		}
		catch(ResourceNotFoundException e) {
			
		}
		  
	}
	
	@Test
	//@Disabled
	@DisplayName("Test-Update seed")
	void testUpdateSeed() throws ResourceNotFoundException {
		
		Customer input = new Customer();
		input.setCustomerId(1);
		input.setCustomerName("srividya");
		input.setCustomerEmail("vaddi.srividya0103@gmail.com");
		input.setUsername("srividya");
		input.setPassword("srividya0103");
		Address add = new Address();
		add.setAddressId(1);
		add.setCity("hyderabad");
		add.setColony("laxmi nagar");
        add.setHouseNo("12-1-508");
        add.setPincode(500017l);
        add.setState("Telangana");
        input.setAddress(add);
        
        
        Customer output = new Customer();
        output.setCustomerId(1);
        output.setCustomerName("srividya");
        output.setCustomerEmail("vaddi.srividya0103@gmail.com");
        output.setUsername("srividya");
        output.setPassword("srividya0103");
		Address add1 = new Address();
		add1.setAddressId(1);
		add1.setCity("hyderabad");
		add1.setColony("laxmi nagar");
        add1.setHouseNo("12-1-508");
        add1.setPincode(500017l);
        add1.setState("Telangana");
        output.setAddress(add1);
		
		try{
			when(customerRepo.save(input)).thenReturn(output);
			
			customerService.updateCustomer(input);

			assertEquals(input,output);
		}
		catch(ResourceNotFoundException e) {
			
		}
	}
	

	
	
}