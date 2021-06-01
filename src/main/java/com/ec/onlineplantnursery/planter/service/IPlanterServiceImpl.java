package com.ec.onlineplantnursery.planter.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ec.onlineplantnursery.exceptions.ResourceNotFoundException;
import com.ec.onlineplantnursery.plant.entity.Plant;
import com.ec.onlineplantnursery.plant.repository.IPlantRepository;
import com.ec.onlineplantnursery.planter.entity.Planter;
import com.ec.onlineplantnursery.planter.repository.IPlanterRepository;
import com.ec.onlineplantnursery.seed.entity.Seed;
import com.ec.onlineplantnursery.seed.repository.ISeedRepository;


@Service
public class IPlanterServiceImpl implements IPlanterService
{

	@Autowired
	private IPlanterRepository planterRepo;
	
	@Autowired
	IPlantRepository plantRepo;
	
	@Autowired
	ISeedRepository seedRepo;
	
	
	

	public IPlanterServiceImpl() {
		super();
		this.planterRepo = planterRepo;
	}
	
	
	

	public IPlanterServiceImpl(ISeedRepository seedRepo) {
		super();
		this.seedRepo = seedRepo;
	}




	public IPlanterServiceImpl(IPlantRepository plantRepo) {
		super();
		this.plantRepo = plantRepo;
	}




	public IPlanterServiceImpl(IPlanterRepository planterRepo) {
		super();
		this.planterRepo = planterRepo;
	}



	@Override
	public Planter addPlanter(Planter planter, int plantId, int seedId) throws ResourceNotFoundException{
		// TODO Auto-generated method stub
		if(plantId != 0) {
			Optional<Plant> p = plantRepo.findById(plantId);
			if(p.isPresent()) {
				planter.setPlant(plantRepo.findById(plantId).get());
			}
			if(p.isPresent() == false){
				throw new ResourceNotFoundException();
			}
		}
		if(seedId != 0) {
			Optional<Seed> s = seedRepo.findById(seedId);
			if(s.isPresent()) {
				planter.setSeed(seedRepo.findById(seedId).get());
			}
			if(s.isPresent() == false){
				throw new ResourceNotFoundException();
			}
		}
		planterRepo.save(planter);
		return planter;
	}

	@Override
	public Planter updatePlanter(Planter planter) throws ResourceNotFoundException{
		Optional<Planter> pl = planterRepo.findById(planter.getPlanterId());
		if(pl.isPresent()) {
			Planter p = planterRepo.findById(planter.getPlanterId()).get();
			p.setPlanterheight(planter.getPlanterheight());
			p.setPlanterCapacity(planter.getPlanterCapacity());
			p.setDrinageHoles(planter.getDrinageHoles());
			p.setPlanterColor(planter.getPlanterColor());
			p.setPlanterShape(planter.getPlanterShape());
			p.setPlanterStock(planter.getPlanterStock());
			p.setPlanterCost(planter.getPlanterCost());
			return planterRepo.save(p);
		}
		throw new ResourceNotFoundException();
	}

	@Override
	public Planter deletePlanter(int planterId) throws ResourceNotFoundException{
		Optional<Planter> pl = planterRepo.findById(planterId);
		if(pl.isPresent() == false) {
			throw new ResourceNotFoundException("No Planter found with the Id");
		}
		
		planterRepo.deleteById(planterId);
		return pl.get();
	}

	@Override
	@Transactional
	public Planter viewPlanter(int planterId) throws ResourceNotFoundException {
		Optional<Planter> pl = planterRepo.findById(planterId);
		if(pl.isPresent() == false) {
			throw new ResourceNotFoundException("No Planter found with the Id");
		}
		return planterRepo.findById(planterId).get();
	}

	@Override
	public List<Planter> viewPlanter(String planterShape) throws ResourceNotFoundException{
		Optional<List<Planter>> plist = planterRepo.viewPlanter(planterShape);
		if(!plist.isEmpty()) {
			return plist.get();
		}
		throw new ResourceNotFoundException();
	}

	@Override
	public List<Planter> viewAllPlanters() {
		// TODO Auto-generated method stub
		return planterRepo.findAll();
	}

	@Override
	public Optional<List<Planter>> viewAllPlanters(double minCost, double maxCost)throws ResourceNotFoundException {
			Optional<List<Planter>> planter = planterRepo.getPlantersByRange(minCost, maxCost);
			if(planter.isEmpty()) {
				throw new ResourceNotFoundException();
			}
			return planter;
		}
		
	}

