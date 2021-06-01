package com.ec.onlineplantnursery.planter.repository;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;

import com.ec.onlineplantnursery.planter.entity.Planter;

public class CustomPlanterRepositoryImpl implements CustomPlanterRepository{
	
	@Autowired
	EntityManager entityManager;
	
	@Override
	public Optional<List<Planter>> viewPlanter(String shape) {
		
		Query q = entityManager.createQuery("from Planter where planterShape=:abc");
		q.setParameter("abc", shape); 
		return Optional.of(q.getResultList());
		
	}
	
	@Override
	public Optional<List<Planter>> getPlantersByRange(double minCost, double maxCost) {
		
		Query q = entityManager.createQuery("from Planter where minCost >=:minCost and maxCost <=: maxCost");
		q.setParameter("minCost", minCost);
		q.setParameter("maxCost", maxCost);
		return Optional.of(q.getResultList());
		
	}

}
