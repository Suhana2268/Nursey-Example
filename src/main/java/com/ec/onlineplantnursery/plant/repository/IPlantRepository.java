package com.ec.onlineplantnursery.plant.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ec.onlineplantnursery.plant.entity.Plant;

public interface IPlantRepository extends JpaRepository<Plant, Integer>,CustomPlantRepository{
	/**Plant addPlant(Plant plant);

	Plant updatePlant(Plant plant);

	Plant deletePlant(Plant plant);

	Plant viewPlant(int plantId);

	Plant viewPlant(String commonName);

	List<Plant> viewAllPlants();

	List<Plant> viewAllPlants(String typeOfPlant);**/
}
