package com.ec.onlineplantnursery.order.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import com.ec.onlineplantnursery.order.entity.Order;


public interface OrderRepository extends JpaRepository<Order, Integer>, IOrderRepository, CustomOrderRepository{

}
