package com.ec.onlineplantnursery.order.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import com.ec.onlineplantnursery.order.entity.Order;

public interface IOrderRepository extends JpaRepository<Order, Integer>, CustomOrderRepository {
	

}
