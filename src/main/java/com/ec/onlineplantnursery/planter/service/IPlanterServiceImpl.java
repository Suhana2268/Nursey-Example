package com.ec.onlineplantnursery.planter.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ec.onlineplantnursery.planter.entity.Planter;
import com.ec.onlineplantnursery.planter.repository.IPlanterRepositoryImpl;

@Service
public class IPlanterServiceImpl implements IPlanterService{

	@Autowired
	IPlanterRepositoryImpl planterRepo;
	
	public IPlanterServiceImpl() {
		// TODO Auto-generated constructor stub
		super();
	}
	
	@Override
	public Planter addPlanter(Planter planter) {
		// TODO Auto-generated method stub
		return planterRepo.addPlanter(planter);
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
		return planterRepo.viewPlanter(planterId);
	}

	@Override
	public Planter viewPlanter(String planterShape) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Planter> viewAllPlanters() {
		// TODO Auto-generated method stub
		return planterRepo.viewAllPlanters();
	}

	@Override
	public List<Planter> viewAllPlanters(double minCost, double maxCost) {
		// TODO Auto-generated method stub
		return null;
	}

}
