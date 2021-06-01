package com.ec.onlineplantnursery.customer.entity;

import java.util.List;



import javax.persistence.CascadeType;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import com.ec.onlineplantnursery.order.entity.Order;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@Entity
@ApiModel(value = "Customer Bean")
@TableGenerator(name = "customer_generator", initialValue = 0, allocationSize = 50)
public class Customer {
	@Id
	
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "customer_generator")
	private Integer customerId;

	@ApiModelProperty(name = "CustomerName", value = "Hold the min 3 char Customer name", required = true)
	@NotEmpty(message = "Customer Name cannot be left blank or null")
	@Size(min = 3, max = 50, message = "Invalid Customer Name,Customer Name should have minimum 3 and maximum 50 characters")
	private String customerName;

	@ApiModelProperty(name = "CustomerEmail", value = "holds valid email id", required = true)
	@Email(message = "Email should be valid")
	@NotEmpty(message = "Customer Email cannot be left blank or null")
	private String customerEmail;

	@ApiModelProperty(name = "CustomerUserName", value = "Hold the min 3 char Customer username", required = true)
	@NotEmpty(message = "Customer UserName cannot be left blank or null")
	@Size(min = 3, max = 50, message = "Invalid Customer UserName,Customer UserName should have minimum 3 and maximum 50 characters")
	private String username;

	@ApiModelProperty(name = "Customer Password", value = "Hold the min 8 char Customer Password", required = true)
	@Size(min = 8, max = 15, message = "Invalid Customer Password ,Customer password should have minimum 8 and maximum 15 characters")
	@NotEmpty(message = "Please enter the password, password cannot be null")
	private String password;
	
	@Embedded
	@Valid
	private Address address;

	
	public Customer() {
		super();
		// TODO Auto-generated constructor stub
	}

	@OneToMany(cascade = CascadeType.PERSIST)
	@JoinColumn(name="customerId")
	List<Order> orders;


	public Customer(Integer customerId, String customerName, String customerEmail, String username, String password,
			Address address) {
		super();
		this.customerId = customerId;
		this.customerName = customerName;
		this.customerEmail = customerEmail;
		this.username = username;
		this.password = password;
		this.address = address;
	}


	public Integer getCustomerId() {
		return customerId;
	}


	public void setCustomerId(Integer customerId) {
		this.customerId = customerId;
	}


	public String getCustomerName() {
		return customerName;
	}


	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}


	public String getCustomerEmail() {
		return customerEmail;
	}


	public void setCustomerEmail(String customerEmail) {
		this.customerEmail = customerEmail;
	}


	public String getUsername() {
		return username;
	}


	public void setUsername(String username) {
		this.username = username;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public Address getAddress() {
		return address;
	}


	public void setAddress(Address address) {
		this.address = address;
	}


	public List<Order> getOrders() {
		return orders;
	}


	public void setOrders(List<Order> orders) {
		this.orders = orders;
	}

	
	

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((address == null) ? 0 : address.hashCode());
		result = prime * result + ((customerEmail == null) ? 0 : customerEmail.hashCode());
		result = prime * result + ((customerId == null) ? 0 : customerId.hashCode());
		result = prime * result + ((customerName == null) ? 0 : customerName.hashCode());
		result = prime * result + ((orders == null) ? 0 : orders.hashCode());
		result = prime * result + ((password == null) ? 0 : password.hashCode());
		result = prime * result + ((username == null) ? 0 : username.hashCode());
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
		Customer other = (Customer) obj;
		if (address == null) {
			if (other.address != null)
				return false;
		} else if (!address.equals(other.address))
			return false;
		if (customerEmail == null) {
			if (other.customerEmail != null)
				return false;
		} else if (!customerEmail.equals(other.customerEmail))
			return false;
		if (customerId == null) {
			if (other.customerId != null)
				return false;
		} else if (!customerId.equals(other.customerId))
			return false;
		if (customerName == null) {
			if (other.customerName != null)
				return false;
		} else if (!customerName.equals(other.customerName))
			return false;
		if (orders == null) {
			if (other.orders != null)
				return false;
		} else if (!orders.equals(other.orders))
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		if (username == null) {
			if (other.username != null)
				return false;
		} else if (!username.equals(other.username))
			return false;
		return true;
	}


	@Override
	public String toString() {
		return "Customer [customerId=" + customerId + ", customerName=" + customerName + ", customerEmail="
				+ customerEmail + ", username=" + username + ", password=" + password + ", address=" + address + "]";
	}


	
	
	

	
}
	