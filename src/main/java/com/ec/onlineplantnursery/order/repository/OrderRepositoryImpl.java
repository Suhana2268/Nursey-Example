package com.ec.onlineplantnursery.order.repository;

import java.time.LocalDate;

import java.util.ArrayList;
import java.util.List;


import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.accept.ParameterContentNegotiationStrategy;

import com.ec.onlineplantnursery.customer.entity.Customer;
import com.ec.onlineplantnursery.customer.repository.ICustomerRepositoryImpl;
import com.ec.onlineplantnursery.customer.service.ICustomerServiceImpl;
import com.ec.onlineplantnursery.order.entity.Order;
import com.ec.onlineplantnursery.plant.entity.Plant;
import com.ec.onlineplantnursery.plant.repository.IPlantRepositoryImpl;
import com.ec.onlineplantnursery.plant.repository.PlantRepository;
import com.ec.onlineplantnursery.plant.service.IPlantServiceImpl;
import com.ec.onlineplantnursery.planter.entity.Planter;
import com.ec.onlineplantnursery.planter.repository.IPlanterRepositoryImpl;
import com.ec.onlineplantnursery.planter.service.IPlanterServiceImpl;
import com.ec.onlineplantnursery.seed.entity.Seed;
import com.ec.onlineplantnursery.seed.repository.ISeedRepositoryImpl;
import com.ec.onlineplantnursery.seed.service.ISeedServiceImpl;

public class OrderRepositoryImpl implements IOrderRepository{
	
	@Autowired
	OrderRepository orderRep;
	@Autowired
	IPlantServiceImpl planRep;
	@Autowired
	ICustomerServiceImpl custRep;
	@Autowired
	IPlanterServiceImpl planterRep;
	@Autowired
	ISeedServiceImpl seedRepo;
	
	
	@Override
	public Order addOrder(Order order) {
		/**Customer cust= custRep.viewCustomer(cid);
		order.setCustomer(cust);
		Planter planter1 = planterRep.viewPlanter(pid);
		order.setPlanter(planter1);
		
		orderRep.save(order);**/
		Customer cust = custRep.viewCustomer(order.getCustId());
		List<Planter> pList = planterRep.viewAllPlanters();
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
		// TODO Auto-generated method stub
		
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
	@Transactional
	public Order deleteOrder(int orderId) {
		// TODO Auto-generated method stub
		boolean isDeleted = false;
		Order o1 = null;
		List<Order> oList = viewAllOrders();
		for(Order order : oList) {
			if(order.getBookingOrderId() == orderId) {
				isDeleted = true;
				o1 = order;
			}
		}
		orderRep.delete(o1);
		return o1;
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
	
	
	
	
}
