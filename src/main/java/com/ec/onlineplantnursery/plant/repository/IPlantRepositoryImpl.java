package com.ec.onlineplantnursery.plant.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ec.onlineplantnursery.plant.entity.Plant;

@Service
public class IPlantRepositoryImpl implements IPlantRepository{

	@Autowired
	PlantRepository plantRepo;
	
	@Override
	public Plant addPlant(Plant plant) {
		// TODO Auto-generated method stub
		plantRepo.save(plant);
		return plant;

	}

	@Override
	public Plant updatePlant(Plant plant) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Plant deletePlant(Plant plant) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Plant viewPlant(int plantId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Plant viewPlant(String commonName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Plant> viewAllPlants() {
		// TODO Auto-generated method stub
		return plantRepo.findAll();
	}

	@Override
	public List<Plant> viewAllPlants(String typeOfPlant) {
		// TODO Auto-generated method stub
		return plantRepo.findAll();
	}

}
