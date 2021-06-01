package com.ec.onlineplantnursery.seed.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ec.onlineplantnursery.seed.entity.Seed;

public interface ISeedRepository extends JpaRepository<Seed, Integer>, CustomSeedRepository{
	/**Seed addSeed(Seed seed);

	Seed updateSeed(Seed seed);

	Seed deleteSeed(Seed seed);

	Seed viewSeed(int seedId);

	Seed viewSeed(String commonName);

	List<Seed> viewAllSeeds();

	List<Seed> viewAllSeeds(String typeOfSeed);**/
}
