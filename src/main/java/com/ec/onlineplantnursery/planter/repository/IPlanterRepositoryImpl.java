package com.ec.onlineplantnursery.planter.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ec.onlineplantnursery.planter.entity.Planter;

@Service
public class IPlanterRepositoryImpl implements  IPlanterRepository{
	
	@Autowired
	private PlanterRepository p;
	

	@Override
	public Planter addPlanter(Planter planter) {
		// TODO Auto-generated method stub
		p.save(planter);

		return 	planter;
	}

	@Override
	public Planter updatePlanter(Planter planter) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Planter deletePlanter(Planter planter) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Planter viewPlanter(int planterId) {
		// TODO Auto-generated method stub
		return p.findById(planterId).get();
	}

	@Override
	public Planter viewPlanter(String planterShape) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Planter> viewAllPlanters() {
		// TODO Auto-generated method stub
		return p.findAll();
	}

	@Override
	public List<Planter> viewAllPlanters(double minCost, double maxCost) {
		// TODO Auto-generated method stub
		return null;
	}

}
