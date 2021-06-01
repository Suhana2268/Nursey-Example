package com.ec.onlineplantnursery.order.service;

import java.time.LocalDate;
import java.util.ArrayList;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ec.onlineplantnursery.customer.entity.Customer;
import com.ec.onlineplantnursery.customer.repository.ICustomerRepository;
import com.ec.onlineplantnursery.customer.service.ICustomerServiceImpl;
import com.ec.onlineplantnursery.dto.OrderDTO;
import com.ec.onlineplantnursery.dto.OrderEntityDTO;
import com.ec.onlineplantnursery.exceptions.InvalidInputException;
import com.ec.onlineplantnursery.exceptions.OrderIdNotFoundException;
import com.ec.onlineplantnursery.exceptions.ResourceNotFoundException;
import com.ec.onlineplantnursery.order.entity.Order;
import com.ec.onlineplantnursery.order.repository.CustomOrderRepository;
import com.ec.onlineplantnursery.order.repository.CustomOrderRepositoryImpl;
import com.ec.onlineplantnursery.order.repository.IOrderRepository;

import com.ec.onlineplantnursery.planter.entity.Planter;
import com.ec.onlineplantnursery.planter.repository.IPlanterRepository;
import com.ec.onlineplantnursery.planter.service.IPlanterServiceImpl;


@Service
public class IOrderServiceImpl implements IOrderService{
	
	@Autowired
	IOrderRepository orderRep;
	/**@Autowired
	IPlanterServiceImpl planterService;
	@Autowired
	ICustomerServiceImpl custService;**/
	
	@Autowired
	ICustomerRepository custRepo;
	@Autowired
	IPlanterRepository planterRepo;
	
	
	public IOrderServiceImpl() {
		super();
	}
	
	

	public IOrderServiceImpl(IPlanterRepository planterRepo) {
		super();
		this.planterRepo = planterRepo;
	}



	public IOrderServiceImpl(ICustomerRepository custRepo) {
		super();
		this.custRepo = custRepo;
	}



	/**public IOrderServiceImpl(ICustomerServiceImpl custService) {
		super();
		this.custService = custService;
	}**/
	



	public IOrderServiceImpl(IOrderRepository orderRep) {
		super();
		this.orderRep = orderRep;
	}
	
	
	



	/**public ICustomerServiceImpl getCustService() {
		return custService;
	}



	public void setCustService(ICustomerServiceImpl custService) {
		this.custService = custService;
	}
	**/

	public boolean validCost(double cost)
	{
		if(cost >= 20 & cost <= 2000) {
			return true;
		}
		return false;
	}
	
	public boolean validQuantity(int quantity)
	{
		if(quantity >= 0 & quantity <= 25) {
			return true;
		}
		return false;
	}


	@Override
	@Transactional
	public Optional<Order> addOrder(Order order) throws ResourceNotFoundException{
		
		System.out.println("Inside addOrder 1 customer rep"+ custRepo);
		System.out.println("Inside addOrder 2 planter rep"+ planterRepo);
		System.out.println("Inside addOrder 3 customer Service"+ orderRep);
		
		//Customer cust = custService.viewCustomer(order.getCustId());
		Optional<Customer> cust = custRepo.findById(order.getCustId());
		
		List<Planter> pList = planterRepo.findAll();
		//List<Planter> pList = planterService.viewAllPlanters();
		List<Integer> pId = order.getId();//planters in the order
		List<Planter> orderedPlanters = new ArrayList<Planter>();
		double cost = 0, totalBillCost = 0;
		for(Integer i : pId)
		{
		   Planter p = planterRepo.findById(i).get();
		   orderedPlanters.add(p);   
		   if(p.getPlant() != null) {
				cost += p.getPlant().getPlantCost();
			}
			else if(p.getSeed() != null) {
				cost += p.getSeed().getSeedsCost();
			}
			else {
				cost += p.getPlant().getPlantCost() + p.getSeed().getSeedsCost();
			}
			
			totalBillCost = p.getPlanterCost()+cost;
		}
		
		order.setCustomer(cust.get());
		order.setPlanters(orderedPlanters);
		order.setTotalCost(totalBillCost);
		orderRep.save(order);
		Optional<Order> savedOrder = Optional.of(order);
		return savedOrder;
	}
	
	
	public Order addOrderDTO(OrderEntityDTO orderdto) throws ResourceNotFoundException, InvalidInputException{
	
		Order order = new Order();
		if(validQuantity(orderdto.getQuantity())) {
			order.setQuantity(orderdto.getQuantity());
		}
		else {
			throw new InvalidInputException();
		}
		if(validCost(orderdto.getTotalCost())) {
			order.setTotalCost(orderdto.getTotalCost());
		}
		else {
			throw new InvalidInputException();
		}
		order.setBookingOrderId(orderdto.getBookingOrderId());
		order.setCustId(orderdto.getCustId());
		order.setCustomer(orderdto.getCustomer());
		order.setId(orderdto.getId());
		order.setTransactionMode(orderdto.getTransactionMode());
		order.setPlanters(orderdto.getPlanters());
		order.setOrderDate(orderdto.getOrderDate());  
		Optional<Order> optionalOrder = addOrder(order);
		return optionalOrder.get();
	
	}
	
	
	
	@Override
	@Transactional
	public Order updateOrder(Order order) throws ResourceNotFoundException {
		
		Optional<Order> op = orderRep.findById(order.getBookingOrderId());
		
		if(op.isEmpty()) throw new ResourceNotFoundException();
		
		Order o = orderRep.findById(order.getBookingOrderId()).get();
		
		o.setBookingOrderId(order.getBookingOrderId());
		o.setOrderDate(order.getOrderDate());
		o.setQuantity(order.getQuantity());
		o.setTotalCost(order.getTotalCost());
		o.setTransactionMode(order.getTransactionMode());
		return orderRep.save(o);
	}

	@Override
	public Optional<Order> deleteOrder(int orderId) {
		
		Optional<Order> o = orderRep.findById(orderId);
		orderRep.deleteById(orderId);
		return o;
	}

	@Override
	@Transactional
	public Optional<Order> viewOrder(int orderId) throws OrderIdNotFoundException {
		
		Optional<Order> op = orderRep.findById(orderId);
		
		
		if(op.isEmpty()) throw new OrderIdNotFoundException(orderId);
		
		//Optional<Order> o =  Optional.of(orderRep.findById(orderId).get());
		return op;
	}

	@Override
	public List<Order> viewAllOrders() {
	
		return orderRep.findAll();
	}
	
	@Override
	public List<Planter> viewPlanterByOrderId(int orderId) throws ResourceNotFoundException{
		
		return orderRep.getPlanterByOrderId(orderId);
	}


	public List<OrderDTO> displayAllOrders(List<Order> oList) {
	
		List<Order> orders = viewAllOrders();
		List<OrderDTO> oDTOList = new ArrayList<OrderDTO>();
		for(Order o : orders) {
		//Customer cust = custService.viewCustomer(o.getCustId());
		Customer cust = custRepo.findById(o.getCustId()).get();
		oDTOList.add(new OrderDTO(o.getCustId(), o.getTotalCost(), o.getPlanters() , cust.getCustomerName(), cust.getAddress()));
		}
		return oDTOList;
	
	
	}
	

	public OrderDTO displayOrderDetails(Order savedOrder)  {
		
		
		//Customer cust = custService.viewCustomer(savedOrder.getCustId());
		Customer cust = custRepo.findById(savedOrder.getCustId()).get();
		OrderDTO orderDTO = new OrderDTO(savedOrder.getBookingOrderId(), savedOrder.getTotalCost(), savedOrder.getPlanters() , cust.getCustomerName(), cust.getAddress());
		return orderDTO;
		
	}

	
	
}
