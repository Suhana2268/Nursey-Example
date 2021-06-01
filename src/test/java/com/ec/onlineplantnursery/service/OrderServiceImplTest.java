package com.ec.onlineplantnursery.service;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import com.ec.onlineplantnursery.customer.entity.Address;
import com.ec.onlineplantnursery.customer.entity.Customer;
import com.ec.onlineplantnursery.customer.repository.ICustomerRepository;
import com.ec.onlineplantnursery.customer.service.ICustomerServiceImpl;
import com.ec.onlineplantnursery.dto.OrderDTO;
import com.ec.onlineplantnursery.exceptions.InvalidInputException;
import com.ec.onlineplantnursery.exceptions.OrderIdNotFoundException;
import com.ec.onlineplantnursery.exceptions.ResourceNotFoundException;
import com.ec.onlineplantnursery.exceptions.SeedIdNotFoundException;
import com.ec.onlineplantnursery.order.entity.Order;
import com.ec.onlineplantnursery.order.repository.IOrderRepository;
import com.ec.onlineplantnursery.order.service.IOrderServiceImpl;
import com.ec.onlineplantnursery.plant.entity.Plant;
import com.ec.onlineplantnursery.planter.entity.Planter;
import com.ec.onlineplantnursery.planter.repository.IPlanterRepository;
import com.ec.onlineplantnursery.planter.service.IPlanterServiceImpl;
import com.ec.onlineplantnursery.seed.entity.Seed;

@SpringBootTest
public class OrderServiceImplTest {
	
	IOrderRepository orderRepo;
	ICustomerRepository custRepo;
	IPlanterRepository planterRepo;
	@InjectMocks
	private static IOrderServiceImpl orderService;
	private static ICustomerServiceImpl customerService;
	private static IPlanterServiceImpl planterService;
	private static AutoCloseable ac;

	@BeforeEach
	public void doinit() {
		orderRepo = mock(IOrderRepository.class);
		custRepo = mock(ICustomerRepository.class);
		planterRepo = mock(IPlanterRepository.class);
		//orderService = new IOrderServiceImpl(orderRepo);
		customerService = new ICustomerServiceImpl(custRepo);
		planterService = new IPlanterServiceImpl(planterRepo);
		/**orderRepo = spy(IOrderRepository.class);
		planterRepo = spy(IPlanterRepository.class);
		custRepo = spy(ICustomerRepository.class);**/
		
		ac = MockitoAnnotations.openMocks(this);
		
	}
	@AfterEach
	public void doEnd() throws Exception{
		ac.close();
	}
	
	
	
	@Test
	@Disabled
	void testSaveOrder() throws ResourceNotFoundException{
		List<Integer> planterIds = Arrays.asList(1, 2); 
		Address address = new Address(20, "8-90", "Yusufguda", "Hyderabad", "Telangana", (long) 500045);
		Customer cust = new Customer(1, "Suhana", "suhana123@gmail.com", "suhana2268", "12345678", address);
		Seed seed = new Seed(1, "mango", "10 days", "twicw a day", "hard", "30 degrees", "dry", "Mango seed", 20 , 70.0, 2);
		Plant plant = new Plant(1, 4,35, "Rose","2 weeks", "No", "Medium", "25 degrees", "short", "Rose plant", "half", 50.00);
		Planter p1 = new Planter(1,12,3,2,23,"Round",45,30, seed);
		Planter p2 = new Planter(2,12,3,2,23,"Round",45,30,plant);
		List<Planter> pList = Arrays.asList(p1, p2);
		/**orderService.setCustService(customerService);
		Customer cust = mock(Customer.class);
		List<Planter> pList = mock(List.class);**/
		LocalDate date = LocalDate.now();
		Order input = new Order(101, date, "online", 1, 20.00, 1, planterIds, cust, pList);
		//OrderDTO savedOrder = new OrderDTO(101, 20.00, pList, cust.getCustomerName(), cust.getAddress());
		//Optional<Order> saved = Optional.of(new Order(101, date, "online", 1, 20.00, 1, planterIds, cust, pList));
		/*Order input = mock(Order.class);
		Order saved = mock(Order.class);*/
		Order saved = new Order(101, date, "online", 1, 20.00, 1, planterIds, cust, pList);
		
		when(orderRepo.save(input)).thenReturn(saved);
		Order actualOrder = orderService.addOrder(input).get();
		verify(orderRepo).save(input);
		assertEquals(saved,actualOrder);
	
	}
	
	
	@Test
	void testValidateCost() {
		double cost = 0;
		boolean result = orderService.validCost(cost);
		assertFalse(result);
	}
	
	@Test
	void testValidQuantity() {
		int quantity = -1;
		boolean result = orderService.validQuantity(quantity);
		assertFalse(result);
	}
	
	@Test
	void testGetOrderById() throws OrderIdNotFoundException {
		int input = 101;
		Order order = mock(Order.class);
		Optional<Order> optional = Optional.of(order);
		
		
		when(orderRepo.findById(input)).thenReturn(optional);
		orderService.viewOrder(input);
		verify(orderRepo).findById(input);
	}
	
	@Test
	@DisplayName("Test-Get All Orders, Args:- No Args to pass")
	void testGetAllOrders() {
		List<Order> orderList = mock(List.class);
		when(orderRepo.findAll()).thenReturn(orderList);
		orderService.viewAllOrders();
		verify(orderRepo).findAll();
	}
	
	
	
	
	@Test
	@DisplayName("Test-Delete Order , Args:- No Args to pass")
	void testDeletePlant() {

		List<Integer> planterIds = Arrays.asList(1, 2); 
		Address address = new Address(20, "8-90", "Yusufguda", "Hyderabad", "Telangana", (long) 500045);
		Customer cust = new Customer(1, "Suhana", "suhana123@gmail.com", "suhana2268", "123456", address);
		Planter p1 = new Planter(1, 2.3f, 4, 3, 1, "round", 46, 25);
		Planter p2 = new Planter(1, 5.3f, 2, 2, 1, "square", 20, 30);
		List<Planter> pList = Arrays.asList(p1, p2);
		LocalDate date = LocalDate.now();
		Order input = new Order(101, date, "online", 1, 20.00, 1, planterIds, cust, pList);
		Order savedOrder = new Order(101, date, "online", 1, 20.00, 1, planterIds, cust, pList);
		
		
		doNothing().
		 when(orderRepo).deleteById(input.getBookingOrderId());

		orderService.deleteOrder(input.getBookingOrderId());

		verify(orderRepo).deleteById(input.getBookingOrderId());
		assertEquals(input,savedOrder);
		  
	}
	
	@Test
	void testUpdateOrder() throws ResourceNotFoundException { 
		List<Integer> planterIds = Arrays.asList(1, 2); 
		Address address = new Address(20, "8-90", "Yusufguda", "Hyderabad", "Telangana", (long) 500045);
		Customer cust = new Customer(1, "Suhana", "suhana123@gmail.com", "suhana2268", "123456", address);
		Planter p1 = new Planter(1, 2.3f, 4, 3, 1, "round", 46, 25);
		Planter p2 = new Planter(1, 5.3f, 2, 2, 1, "square", 20, 30);
		List<Planter> pList = Arrays.asList(p1, p2);
		LocalDate date = LocalDate.now();
		Order input = new Order(101, date, "online", 1, 20.00, 1, planterIds, cust, pList);
		Order savedOrder = new Order(101, date, "online", 1, 20.00, 1, planterIds, cust, pList);
		
		
		try{
			when(orderRepo.save(input)).thenReturn(savedOrder);
			
			orderService.updateOrder(input);

			assertEquals(input,savedOrder);
		}
		catch(ResourceNotFoundException e) {
			
		}
		
	}
	
	
	public OrderServiceImplTest() {
		// TODO Auto-generated constructor stub
	}

}
