package com.ec.onlineplantnursery.planter.entity;

import javax.annotation.Generated;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import com.ec.onlineplantnursery.customer.entity.Customer;
import com.ec.onlineplantnursery.order.entity.Order;
import com.ec.onlineplantnursery.plant.entity.Plant;
import com.ec.onlineplantnursery.seed.entity.Seed;

@Entity
@Table(name = "Planter")
@TableGenerator(name = "planter_generator", initialValue = 0, allocationSize = 50)
public class Planter {

	@Id
	@GeneratedValue(strategy = GenerationType.TABLE,generator = "planter_generator")
	private Integer planterId;
	
	private float planterheight;
	
	@Min(value = 1, message = "Capacity cannot be less than 1")
	private int planterCapacity;
	
	private int drinageHoles;
	private int planterColor;
	
	@NotEmpty(message = "Planter shape cannot be left blank or null")
	@Size(min = 3,max = 15, message = "Invalid Planter shape")
	private String planterShape;
	
	@Min(value = 1, message = "In stock cannot be less than 1")
	private int planterStock;
	
	@Min(value = 50, message = "Cost cannot be less than 50")
	private int planterCost;
	

	@OneToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "Plant_info", referencedColumnName = "plantId")
	private Plant plant;
	
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "Seed_Info", referencedColumnName = "seedId")
	private Seed seed;


	public Planter() {
		super();
		// TODO Auto-generated constructor stub
	}

	
	
	


	public Planter(Integer planterId, float planterheight,
			@Min(value = 1, message = "Capacity cannot be less than 1") int planterCapacity, int drinageHoles,
			int planterColor,
			@NotEmpty(message = "Planter shape cannot be left blank or null") @Size(min = 3, max = 15, message = "Invalid Planter shape") String planterShape,
			@Min(value = 1, message = "In stock cannot be less than 1") int planterStock,
			@Min(value = 50, message = "Cost cannot be less than 50") int planterCost, Plant plant, Seed seed) {
		super();
		this.planterId = planterId;
		this.planterheight = planterheight;
		this.planterCapacity = planterCapacity;
		this.drinageHoles = drinageHoles;
		this.planterColor = planterColor;
		this.planterShape = planterShape;
		this.planterStock = planterStock;
		this.planterCost = planterCost;
		this.plant = plant;
		this.seed = seed;
	}






	public Planter(Integer planterId, float planterheight,
			@Min(value = 1, message = "Capacity cannot be less than 1") int planterCapacity, int drinageHoles,
			int planterColor,
			@NotEmpty(message = "Planter shape cannot be left blank or null") @Size(min = 3, max = 15, message = "Invalid Planter shape") String planterShape,
			@Min(value = 1, message = "In stock cannot be less than 1") int planterStock,
			@Min(value = 50, message = "Cost cannot be less than 50") int planterCost) {
		super();
		this.planterId = planterId;
		this.planterheight = planterheight;
		this.planterCapacity = planterCapacity;
		this.drinageHoles = drinageHoles;
		this.planterColor = planterColor;
		this.planterShape = planterShape;
		this.planterStock = planterStock;
		this.planterCost = planterCost;
	}






	public Planter(Integer planterId, float planterheight,
			@Min(value = 1, message = "Capacity cannot be less than 1") int planterCapacity, int drinageHoles,
			int planterColor,
			@NotEmpty(message = "Planter shape cannot be left blank or null") @Size(min = 3, max = 15, message = "Invalid Planter shape") String planterShape,
			@Min(value = 1, message = "In stock cannot be less than 1") int planterStock,
			@Min(value = 50, message = "Cost cannot be less than 50") int planterCost, Seed seed) {
		super();
		this.planterId = planterId;
		this.planterheight = planterheight;
		this.planterCapacity = planterCapacity;
		this.drinageHoles = drinageHoles;
		this.planterColor = planterColor;
		this.planterShape = planterShape;
		this.planterStock = planterStock;
		this.planterCost = planterCost;
		this.seed = seed;
	}






	public Planter(Integer planterId, float planterheight,
			@Min(value = 1, message = "Capacity cannot be less than 1") int planterCapacity, int drinageHoles,
			int planterColor,
			@NotEmpty(message = "Planter shape cannot be left blank or null") @Size(min = 3, max = 15, message = "Invalid Planter shape") String planterShape,
			@Min(value = 1, message = "In stock cannot be less than 1") int planterStock,
			@Min(value = 50, message = "Cost cannot be less than 50") int planterCost, Plant plant) {
		super();
		this.planterId = planterId;
		this.planterheight = planterheight;
		this.planterCapacity = planterCapacity;
		this.drinageHoles = drinageHoles;
		this.planterColor = planterColor;
		this.planterShape = planterShape;
		this.planterStock = planterStock;
		this.planterCost = planterCost;
		this.plant = plant;
	}






	public Integer getPlanterId() {
		return planterId;
	}


	public void setPlanterId(Integer planterId) {
		this.planterId = planterId;
	}


	public float getPlanterheight() {
		return planterheight;
	}


	public void setPlanterheight(float planterheight) {
		this.planterheight = planterheight;
	}


	public int getPlanterCapacity() {
		return planterCapacity;
	}


	public void setPlanterCapacity(int planterCapacity) {
		this.planterCapacity = planterCapacity;
	}


	public int getDrinageHoles() {
		return drinageHoles;
	}


	public void setDrinageHoles(int drinageHoles) {
		this.drinageHoles = drinageHoles;
	}


	public int getPlanterColor() {
		return planterColor;
	}


	public void setPlanterColor(int planterColor) {
		this.planterColor = planterColor;
	}


	public String getPlanterShape() {
		return planterShape;
	}


	public void setPlanterShape(String planterShape) {
		this.planterShape = planterShape;
	}


	public int getPlanterStock() {
		return planterStock;
	}


	public void setPlanterStock(int planterStock) {
		this.planterStock = planterStock;
	}


	public int getPlanterCost() {
		return planterCost;
	}


	public void setPlanterCost(int planterCost) {
		this.planterCost = planterCost;
	}


	public Plant getPlant() {
		return plant;
	}


	public void setPlant(Plant plant) {
		this.plant = plant;
	}


	public Seed getSeed() {
		return seed;
	}


	public void setSeed(Seed seed) {
		this.seed = seed;
	}
	
	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + drinageHoles;
		result = prime * result + ((plant == null) ? 0 : plant.hashCode());
		result = prime * result + planterCapacity;
		result = prime * result + planterColor;
		result = prime * result + planterCost;
		result = prime * result + ((planterId == null) ? 0 : planterId.hashCode());
		result = prime * result + ((planterShape == null) ? 0 : planterShape.hashCode());
		result = prime * result + planterStock;
		result = prime * result + Float.floatToIntBits(planterheight);
		result = prime * result + ((seed == null) ? 0 : seed.hashCode());
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
		Planter other = (Planter) obj;
		if (drinageHoles != other.drinageHoles)
			return false;
		if (plant == null) {
			if (other.plant != null)
				return false;
		} else if (!plant.equals(other.plant))
			return false;
		if (planterCapacity != other.planterCapacity)
			return false;
		if (planterColor != other.planterColor)
			return false;
		if (planterCost != other.planterCost)
			return false;
		if (planterId == null) {
			if (other.planterId != null)
				return false;
		} else if (!planterId.equals(other.planterId))
			return false;
		if (planterShape == null) {
			if (other.planterShape != null)
				return false;
		} else if (!planterShape.equals(other.planterShape))
			return false;
		if (planterStock != other.planterStock)
			return false;
		if (Float.floatToIntBits(planterheight) != Float.floatToIntBits(other.planterheight))
			return false;
		if (seed == null) {
			if (other.seed != null)
				return false;
		} else if (!seed.equals(other.seed))
			return false;
		return true;
	}


	@Override
	public String toString() {
		return "Planter [planterId=" + planterId + ", planterheight=" + planterheight + ", planterCapacity="
				+ planterCapacity + ", drinageHoles=" + drinageHoles + ", planterColor=" + planterColor
				+ ", planterShape=" + planterShape + ", planterStock=" + planterStock + ", planterCost=" + planterCost
				+ ", plant=" + plant + ", seed=" + seed + "]";
	}
	
}
