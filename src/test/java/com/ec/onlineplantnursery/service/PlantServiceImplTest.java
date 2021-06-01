package com.ec.onlineplantnursery.service;


import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.NoSuchElementException;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;

import com.ec.onlineplantnursery.exceptions.PlantIdNotFoundException;
import com.ec.onlineplantnursery.exceptions.ResourceNotFoundException;
import com.ec.onlineplantnursery.exceptions.SeedIdNotFoundException;
import com.ec.onlineplantnursery.plant.entity.Plant;
import com.ec.onlineplantnursery.plant.repository.IPlantRepository;
import com.ec.onlineplantnursery.plant.service.IPlantServiceImpl;
import com.ec.onlineplantnursery.seed.entity.Seed;

import java.util.Optional;

@SpringBootTest
public class PlantServiceImplTest {

	//@Mock
	IPlantRepository plantRepo;
	private static IPlantServiceImpl plantService;
	private static AutoCloseable ac;
	
	@BeforeEach
	public void doinit() {
		plantRepo = mock(IPlantRepository.class);
		plantService = new IPlantServiceImpl(plantRepo);
		ac = MockitoAnnotations.openMocks(this);
	}
	
	@AfterEach
	public void doAtEnd() throws Exception{
		ac.close();
	}
	
	@Test
	void testSavePlant() {
		Plant input = new Plant(1,10,10,"Ice Plant","Throughout year","culinary","Easy to grow","20-35 C","Succulent","This is a delightful,easy to grow plant","6 inches",150.0);
		
		Plant savedProduct = new Plant(1,10,10,"Ice Plant","Throughout year","culinary","Easy to grow","20-35 C","Succulent","This is a delightful,easy to grow plant","6 inches",150.0);
		
	
		when(plantRepo.save(input)).thenReturn(savedProduct);
		plantService.addPlant(input);
		verify(plantRepo).save(input);
		
	}
	
	@Test
	@DisplayName("test- get all Plants")
	void testGetAllPlants() {
		
		List<Plant> pList = mock(List.class);
		when(plantRepo.findAll()).thenReturn(pList);
		plantService.viewAllPlants();
		verify(plantRepo).findAll();
		
	}
	
	@Test
	void testViewPlantByName() throws ResourceNotFoundException {
		String commonName = "abc";
		Optional<Plant> p = Optional.empty();
		when(plantRepo.viewPlant(commonName)).thenReturn(p);
		Executable executable = ()->plantService.viewPlant(commonName);
		assertThrows(ResourceNotFoundException.class, executable);
		
	}
	
	@Test
	void testViewPlantByTypeOfPlant() throws ResourceNotFoundException {
		String typeOfPlant = "abc";
		Optional<List<Plant>> plantList = Optional.empty();
		when(plantRepo.viewAllPlants(typeOfPlant)).thenReturn(plantList);
		Executable executable = ()->plantService.viewAllPlants(typeOfPlant);
		assertThrows(ResourceNotFoundException.class, executable);
		
	}
	
	
	@Test
    void viewPlantById() {
		Optional<Plant> p = Optional.empty();
		when(plantRepo.findById(1)).thenReturn(p);
		Executable executable = ()->plantService.viewPlant(1);
		assertThrows(PlantIdNotFoundException.class, executable);
		
	}
	
	
}//end