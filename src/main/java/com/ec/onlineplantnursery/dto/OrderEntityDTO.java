package com.ec.onlineplantnursery.dto;

import java.time.LocalDate;
import java.util.List;



import com.ec.onlineplantnursery.customer.entity.Customer;
import com.ec.onlineplantnursery.planter.entity.Planter;

public class OrderEntityDTO {
	
	private Integer bookingOrderId;
	private LocalDate orderDate;
	private String transactionMode;
	private int quantity;
	private double totalCost;
	private int custId;
	private List<Integer> id;
	private Customer customer;
	private List<Planter> planters;
	

	public OrderEntityDTO() {
		// TODO Auto-generated constructor stub
	}


	public OrderEntityDTO(Integer bookingOrderId, LocalDate orderDate, String transactionMode, int quantity,
			double totalCost, int custId, List<Integer> id, Customer customer, List<Planter> planters) {
		super();
		this.bookingOrderId = bookingOrderId;
		this.orderDate = orderDate;
		this.transactionMode = transactionMode;
		this.quantity = quantity;
		this.totalCost = totalCost;
		this.custId = custId;
		this.id = id;
		this.customer = customer;
		this.planters = planters;
	}


	public Integer getBookingOrderId() {
		return bookingOrderId;
	}


	public void setBookingOrderId(Integer bookingOrderId) {
		this.bookingOrderId = bookingOrderId;
	}


	public LocalDate getOrderDate() {
		return orderDate;
	}


	public void setOrderDate(LocalDate orderDate) {
		this.orderDate = orderDate;
	}


	public String getTransactionMode() {
		return transactionMode;
	}


	public void setTransactionMode(String transactionMode) {
		this.transactionMode = transactionMode;
	}


	public int getQuantity() {
		return quantity;
	}


	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}


	public double getTotalCost() {
		return totalCost;
	}


	public void setTotalCost(double totalCost) {
		this.totalCost = totalCost;
	}


	public int getCustId() {
		return custId;
	}


	public void setCustId(int custId) {
		this.custId = custId;
	}


	public List<Integer> getId() {
		return id;
	}


	public void setId(List<Integer> id) {
		this.id = id;
	}


	public Customer getCustomer() {
		return customer;
	}


	public void setCustomer(Customer customer) {
		this.customer = customer;
	}


	public List<Planter> getPlanters() {
		return planters;
	}


	public void setPlanters(List<Planter> planters) {
		this.planters = planters;
	}
	
	


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((bookingOrderId == null) ? 0 : bookingOrderId.hashCode());
		result = prime * result + custId;
		result = prime * result + ((customer == null) ? 0 : customer.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((orderDate == null) ? 0 : orderDate.hashCode());
		result = prime * result + ((planters == null) ? 0 : planters.hashCode());
		result = prime * result + quantity;
		long temp;
		temp = Double.doubleToLongBits(totalCost);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((transactionMode == null) ? 0 : transactionMode.hashCode());
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		OrderEntityDTO other = (OrderEntityDTO) obj;
		if (bookingOrderId == null) {
			if (other.bookingOrderId != null)
				return false;
		} else if (!bookingOrderId.equals(other.bookingOrderId))
			return false;
		if (custId != other.custId)
			return false;
		if (customer == null) {
			if (other.customer != null)
				return false;
		} else if (!customer.equals(other.customer))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (orderDate == null) {
			if (other.orderDate != null)
				return false;
		} else if (!orderDate.equals(other.orderDate))
			return false;
		if (planters == null) {
			if (other.planters != null)
				return false;
		} else if (!planters.equals(other.planters))
			return false;
		if (quantity != other.quantity)
			return false;
		if (Double.doubleToLongBits(totalCost) != Double.doubleToLongBits(other.totalCost))
			return false;
		if (transactionMode == null) {
			if (other.transactionMode != null)
				return false;
		} else if (!transactionMode.equals(other.transactionMode))
			return false;
		return true;
	}


	@Override
	public String toString() {
		return "OrderEntityDTO [bookingOrderId=" + bookingOrderId + ", orderDate=" + orderDate + ", transactionMode="
				+ transactionMode + ", quantity=" + quantity + ", totalCost=" + totalCost + ", custId=" + custId
				+ ", id=" + id + ", customer=" + customer + ", planters=" + planters + "]";
	}
	
	

}
