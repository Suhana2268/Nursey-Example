package com.ec.onlineplantnursery.seed.repository;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.ec.onlineplantnursery.seed.entity.Seed;

@Service
public class ISeedRepositoryImpl implements ISeedRepository {

	@Autowired
	SeedRepository seedRepo;
	
	@Override
	public Seed addSeed(Seed seed) {
		// TODO Auto-generated method stub
		seedRepo.save(seed);
		return seed;
	}

	@Override
	public Seed updateSeed(Seed seed) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Seed deleteSeed(Seed seed) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Seed viewSeed(int seedId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Seed viewSeed(String commonName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Seed> viewAllSeeds() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Seed> viewAllSeeds(String typeOfSeed) {
		// TODO Auto-generated method stub
		return null;
	}

}