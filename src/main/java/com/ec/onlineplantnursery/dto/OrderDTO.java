package com.ec.onlineplantnursery.dto;

import java.util.List;

import com.ec.onlineplantnursery.customer.entity.Address;
import com.ec.onlineplantnursery.planter.entity.Planter;

public class OrderDTO {
	
	private int orderId;
	private double totalCost;
	private List<Planter> planters;
	private String customerName;
	private Address customerAdress;
	

	public OrderDTO() {
		// TODO Auto-generated constructor stub
	}


	public OrderDTO(int orderId, double d, List<Planter> planters, String customerName, Address address) {
		super();
		this.orderId = orderId;
		this.totalCost = d;
		this.planters = planters;
		this.customerName = customerName;
		this.customerAdress = address;
	}


	public int getOrderId() {
		return orderId;
	}


	public double getTotalCost() {
		return totalCost;
	}


	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}


	public List<Planter> getPlanters() {
		return planters;
	}


	public void setPlanters(List<Planter> planters) {
		this.planters = planters;
	}


	public String getCustomerName() {
		return customerName;
	}


	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}


	public Address getCustomerAdress() {
		return customerAdress;
	}


	public void setCustomerAdress(Address customerAdress) {
		this.customerAdress = customerAdress;
	}


	public void setTotalCost(double totalCost) {
		this.totalCost = totalCost;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((customerAdress == null) ? 0 : customerAdress.hashCode());
		result = prime * result + ((customerName == null) ? 0 : customerName.hashCode());
		result = prime * result + orderId;
		result = prime * result + ((planters == null) ? 0 : planters.hashCode());
		long temp;
		temp = Double.doubleToLongBits(totalCost);
		result = prime * result + (int) (temp ^ (temp >>> 32));
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
		OrderDTO other = (OrderDTO) obj;
		if (customerAdress == null) {
			if (other.customerAdress != null)
				return false;
		} else if (!customerAdress.equals(other.customerAdress))
			return false;
		if (customerName == null) {
			if (other.customerName != null)
				return false;
		} else if (!customerName.equals(other.customerName))
			return false;
		if (orderId != other.orderId)
			return false;
		if (planters == null) {
			if (other.planters != null)
				return false;
		} else if (!planters.equals(other.planters))
			return false;
		if (Double.doubleToLongBits(totalCost) != Double.doubleToLongBits(other.totalCost))
			return false;
		return true;
	}


	@Override
	public String toString() {
		return "OrderDTO [orderId=" + orderId + ", totalCost=" + totalCost + ", planters=" + planters
				+ ", customerName=" + customerName + ", customerAdress=" + customerAdress + "]";
	}


	

}
