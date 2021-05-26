package com.ec.onlineplantnursery.plant.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ec.onlineplantnursery.plant.entity.Plant;

public interface PlantRepository extends JpaRepository<Plant, Integer>, IPlantRepository{

}
