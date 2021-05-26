package com.ec.onlineplantnursery.web;

import java.time.LocalDateTime;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ec.onlineplantnursery.customer.entity.Customer;
import com.ec.onlineplantnursery.customer.service.ICustomerServiceImpl;
import com.ec.onlineplantnursery.order.entity.Order;
import com.ec.onlineplantnursery.order.repository.IOrderRepository;
import com.ec.onlineplantnursery.order.service.IOrderServiceImpl;
import com.ec.onlineplantnursery.plant.entity.Plant;
import com.ec.onlineplantnursery.plant.repository.IPlantRepository;
import com.ec.onlineplantnursery.plant.service.IPlantServiceImpl;
import com.ec.onlineplantnursery.planter.entity.Planter;
import com.ec.onlineplantnursery.planter.service.IPlanterServiceImpl;
import com.ec.onlineplantnursery.seed.entity.Seed;
import com.ec.onlineplantnursery.seed.service.ISeedServiceImpl;

@RestController
@RequestMapping("/api")
public class NurseryRestController {
	
	@Autowired
	private IOrderServiceImpl ordService;
	
	@Autowired
	private ICustomerServiceImpl custService;
	
	@Autowired
	private ISeedServiceImpl seedService;
	
	@Autowired
	private IPlantServiceImpl plantService;
	
	@Autowired
	private IPlanterServiceImpl planterService;
	
	public NurseryRestController() {
		System.out.println("Nursery Rest Controller Cunstructor");
	}
	
	@GetMapping("/home")
	public String homeRequest() {
		return "Welcome : My Nursery App "+LocalDateTime.now();
	}
	

	
	@PostMapping("/order")//1=plant,2=seed3=both
	public Order insertProduct(@RequestBody Order order) {
		ordService.addOrder(order);
		return order;
	}
	
	@PostMapping("/customer")
	public Customer addCustomer(@RequestBody Customer customer) {
		custService.addCustomer(customer);
		return customer;
	}
	
	@PostMapping("/seed")
	public Seed addSeed(@RequestBody Seed seed) {
		seedService.addSeed(seed);
		return seed;
	}
	
	@PostMapping("/plant")
	public Plant addPlant(@RequestBody Plant plant) {
		plantService.addPlant(plant);
		return plant;
	}
	
	@PostMapping("/planter")
	public Planter addPlanter(@RequestBody Planter planter) {
		planterService.addPlanter(planter);
		return planter;
	}
	
	
	@GetMapping("/customer")
	public List<Customer> viewAllCustomers() {
		return custService.viewAllCustomers();
	}
	
	@GetMapping("/orders")
	public List<Order> viewAllOrders() {
		return ordService.viewAllOrders();
	}
	
	@PutMapping("/customer/{id}")
	public Customer updateCustomer(@RequestBody Customer c,@PathVariable int id)
	{
		return custService.updateCustomer(c);
	}

	@PostMapping("/customer/delete")
	public Customer deleteCustomer(@RequestBody Customer customer) {
		return custService.deleteCustomer(customer);
	}
	
	@GetMapping("/customer/validate/{uname}/{pass}")
	public boolean validateCustomer(@PathVariable String uname, @PathVariable String pass) {
		return custService.validateCustomer(uname, pass);
	}
	
	/**@GetMapping("/order/planter/{id}")
	public List<Planter> viewPlantersListByOrderId(@PathVariable int id) {
		return ordService.viewPlanterByOrderId(id);
	}**/
	
	
	
	

}
