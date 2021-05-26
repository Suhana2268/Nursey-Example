package com.ec.onlineplantnursery.order.repository;

import java.util.List;

import com.ec.onlineplantnursery.planter.entity.Planter;



public interface CustomOrderRepository{
	
	public List<Planter> getPlanterByOrderId(int orderId);

	

}
