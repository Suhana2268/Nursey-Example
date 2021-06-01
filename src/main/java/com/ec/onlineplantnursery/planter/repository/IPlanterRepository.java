package com.ec.onlineplantnursery.planter.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ec.onlineplantnursery.planter.entity.Planter;

public interface IPlanterRepository extends JpaRepository<Planter, Integer>, CustomPlanterRepository{
	/**Planter addPlanter(Planter planter);

	Planter updatePlanter(Planter planter);

	Planter deletePlanter(Planter planter);

	Planter viewPlanter(int planterId);

	Planter viewPlanter(String planterShape);

	List<Planter> viewAllPlanters();

	List<Planter> viewAllPlanters(double minCost, double maxCost);**/
}
