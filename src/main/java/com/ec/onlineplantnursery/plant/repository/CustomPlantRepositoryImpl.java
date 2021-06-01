package com.ec.onlineplantnursery.plant.repository;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;


import com.ec.onlineplantnursery.plant.entity.Plant;

public class CustomPlantRepositoryImpl implements CustomPlantRepository{

	@Autowired
	EntityManager entityManager;
	
	@Override
	public Optional<Plant> viewPlant(String commonName) {
	
		Query q = entityManager.createQuery("from Plant where commonName=:commonName");
		q.setParameter("commonName", commonName);
		Plant p = (Plant) q.getSingleResult();
		Optional<Plant> plant = Optional.of(p);
		return plant;
	}

	@Override
	public Optional<List<Plant>> viewAllPlants(String typeOfPlant) {
		
		Query q = entityManager.createQuery("from Plant where typeOfPlant=:typeOfPlant");
		q.setParameter("typeOfPlant", typeOfPlant);
		return Optional.of(q.getResultList());
	}

}
