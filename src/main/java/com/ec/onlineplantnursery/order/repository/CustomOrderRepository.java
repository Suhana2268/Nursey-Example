package com.ec.onlineplantnursery.order.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ec.onlineplantnursery.order.entity.Order;
import com.ec.onlineplantnursery.planter.entity.Planter;



public interface CustomOrderRepository {
	
	public List<Planter> getPlanterByOrderId(int orderId);

	

}
