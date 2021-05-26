package com.ec.onlineplantnursery.order.service;

import java.util.ArrayList;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ec.onlineplantnursery.customer.entity.Customer;
import com.ec.onlineplantnursery.customer.repository.CustomerRepository;
import com.ec.onlineplantnursery.customer.service.ICustomerServiceImpl;
import com.ec.onlineplantnursery.order.entity.Order;
import com.ec.onlineplantnursery.order.repository.CustomOrderRepository;
import com.ec.onlineplantnursery.order.repository.CustomOrderRepositoryImpl;
import com.ec.onlineplantnursery.order.repository.OrderRepository;
import com.ec.onlineplantnursery.order.repository.OrderRepositoryImpl;
import com.ec.onlineplantnursery.plant.repository.PlantRepository;
import com.ec.onlineplantnursery.planter.entity.Planter;
import com.ec.onlineplantnursery.planter.repository.PlanterRepository;
import com.ec.onlineplantnursery.planter.service.IPlanterServiceImpl;
import com.ec.onlineplantnursery.seed.repository.SeedRepository;

@Service
public class IOrderServiceImpl implements IOrderService{
	
	/**@Autowired
	OrderRepository orderRep;
	@Autowired
	IPlanterServiceImpl planterService;
	@Autowired
	ICustomerServiceImpl custService;
	
	
	@Override
	public Order addOrder(Order order) {
		// TODO Auto-generated method stub
		Customer cust = custService.viewCustomer(order.getCustId());
		List<Planter> pList = planterService.viewAllPlanters();
		List<Integer> pId = order.getId();
		List<Planter> orderedPlanters = new ArrayList<Planter>();
		double cost = 0, cost1 =0;
		for(Planter p : pList) {
			for(int id : pId) {
				if(p.getPlanterId() == id) {
					orderedPlanters.add(p);
				}
			}
		}
		
		for(Planter p : orderedPlanters) {
			if(p.getPlant() != null) {
				cost += p.getPlant().getPlantCost();
			}
			else if(p.getSeed() != null) {
				cost += p.getSeed().getSeedsCost();
			}
			else {
				cost += cost += p.getPlant().getPlantCost() + p.getSeed().getSeedsCost();
			}
			
			cost1 += p.getPlanterCost();
		}
		
		order.setCustomer(cust);
		order.setPlanters(orderedPlanters);
		order.setTotalCost(cost+cost1);
		orderRep.save(order);
		
		return order;
	}

	@Override
	public Order updateOrder(Order order) {
		// TODO Auto-generated method stub
		int id = order.getBookingOrderId();
		Optional<Order> or = orderRep.findById(id);
		if(or.isPresent()) {
			Order ord = or.get();
			ord.setCustomer(order.getCustomer());
			//ord.setOrderDate(order.getOrderDate());
			ord.setQuantity(order.getQuantity());
			ord.setTotalCost(order.getTotalCost());
			ord.setBookingOrderId(order.getBookingOrderId());
			ord.setTransactionMode(order.getTransactionMode());
			return orderRep.save(ord);
		}
		return null;
	}

	@Override
	public Order deleteOrder(int orderId) {
		// TODO Auto-generated method stub
		Order o = orderRep.findById(orderId).get();
		orderRep.delete(o);
		return o;
	}

	@Override
	public Order viewOrder(int orderId) {
		// TODO Auto-generated method stub
		return orderRep.findById(orderId).get();
	}

	@Override
	public List<Order> viewAllOrders() {
		// TODO Auto-generated method stub
		return orderRep.findAll();
	}

	@Override
	public List<Planter> viewPlanterByOrderId(int orderId) {
		// TODO Auto-generated method stub
		return custOrderRep.getPlanterByOrderId(orderId);
	}**/

	@Autowired
	OrderRepository orderRep;
	
	@Autowired
	OrderRepositoryImpl ord;
	
	@Autowired
	CustomOrderRepositoryImpl custOrderRep;
	
	public IOrderServiceImpl(OrderRepositoryImpl ord) {
		super();
		this.ord = ord;
	}

	public IOrderServiceImpl(OrderRepository orderRep) {
		super();
		this.orderRep = orderRep;
	}

	public IOrderServiceImpl() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public Order updateOrder(Order order) {
		// TODO Auto-generated method stub
		return ord.updateOrder(order);
	}

	@Override
	public Order deleteOrder(int orderId) {
		// TODO Auto-generated method stub
		return ord.deleteOrder(orderId);
	}

	@Override
	public Order viewOrder(int orderId) {
		// TODO Auto-generated method stub
		return ord.viewOrder(orderId);
	}

	@Override
	public List<Order> viewAllOrders() {
		// TODO Auto-generated method stub
		return ord.viewAllOrders();
	}

	@Override
	public Order addOrder(Order order) {
		// TODO Auto-generated method stub lest try git hub
		
		//Hi Harshh
		return ord.addOrder(order);
	}

	@Override
	public List<Planter> viewPlanterByOrderId(int orderId) {
		// TODO Auto-generated method stub
		return custOrderRep.getPlanterByOrderId(orderId);
	}
	
	
}
