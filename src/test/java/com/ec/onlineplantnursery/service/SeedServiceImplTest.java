package com.ec.onlineplantnursery.service;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import javax.persistence.NoResultException;
import javax.persistence.PersistenceException;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import com.ec.onlineplantnursery.exceptions.ResourceNotFoundException;
import com.ec.onlineplantnursery.exceptions.SeedIdNotFoundException;
import com.ec.onlineplantnursery.seed.entity.Seed;
import com.ec.onlineplantnursery.seed.repository.ISeedRepository;
import com.ec.onlineplantnursery.seed.service.ISeedServiceImpl;

@SpringBootTest
class SeedServiceImplTest {

	ISeedRepository seedRepo;
	private static ISeedServiceImpl seedService;
	private static AutoCloseable ac;
	
	@BeforeEach
	public void doinit()
	{
		seedRepo = mock(ISeedRepository.class); // test through approach 2
		seedService = new  ISeedServiceImpl(seedRepo); 	// Approach 2
		ac = MockitoAnnotations.openMocks(this);
	}
	
	@AfterEach
	public void doAtEnd()throws Exception
	{
		ac.close();
	}
	
	
	
	@Test
	@DisplayName("Test-Save Seed")
	void testSaveSeed() {
		Seed input = new Seed(101,"Mango","Morning", "Twice a day", "easy","25ºC","Monocotyledonous",
				"This seed is a small embryonic plant",200,300,2000);
		Seed savedSeed = new Seed(101,"Mango","Morning", "Twice a day", "easy","25ºC","Monocotyledonous",
				"This seed is a small embryonic plant",200,300,2000);
	
		when(seedRepo.save(input)).thenReturn(savedSeed);
		seedService.addSeed(input);
		verify(seedRepo).save(input);
		
	}
	

	@Test
	@DisplayName("Test-Get All Seeds")
	void testGetAllSeeds() {
		
		
		List<Seed> seedList = mock(List.class); 
		//when() and 	//thenReturn()
		when(seedRepo.findAll()).thenReturn(seedList);
		//call the actual method 
		seedService.viewAllSeeds();
		//verify
		verify(seedRepo).findAll();
		
	}
	
	@Test
	@DisplayName("Test-Get Seed by Id")
	void testViewSeedById(){
		
		
		Optional<Seed> s = Optional.empty();
		//when() and 	//thenReturn()
		when(seedRepo.findById(2)).thenReturn(s);
		Executable executable = ()->seedService.viewSeed(2);
		assertThrows(SeedIdNotFoundException.class, executable);
		
	}
	
	/*tejashwini*/
	@Test
	@DisplayName("Test-Get Seed by common Name ")
	void testViewSeedByName()  {
		
		String commonName = "abc";
		Optional<Seed> s = Optional.empty();
		when(seedRepo.getSeedByCommonName(commonName)).thenReturn(s);
		Executable executable = ()->seedService.viewSeed(commonName);
		assertThrows(ResourceNotFoundException.class, executable);
		
		
	}
	
	@Test
	@DisplayName("Test-Get Seed by type of seed ")
	void testViewSeedByTypeOfSeed(){
		
		String typeOfSeed = "Monocotyledonous";
		//"Monocotyledonous"
		Optional<List<Seed>> seedList = Optional.empty(); 
		//when() and 	//thenReturn()
		when(seedRepo.getSeedsByTypeOfSeed(typeOfSeed)).thenReturn(seedList);
		//call the actual method 
		Executable executable = ()->seedService.viewAllSeeds(typeOfSeed);
		assertThrows(ResourceNotFoundException.class, executable);
		//assertThrows(NullPointerException.class, executable);
	}
	
	@Test
	@Disabled
	@DisplayName("Test-Delete seed")
	void testDeleteSeed() throws SeedIdNotFoundException {
		
		Seed input = new Seed(1,"Mango","Morning", "Twice a day", "easy","25ºC","Monocotyledonous",
				"This seed is a small embryonic plant",250,300,2000);
		Seed savedSeed = new Seed(1,"Mango","Morning", "Twice a day", "easy","25ºC","Monocotyledonous",
				"This seed is a small embryonic plant",250,300,2000);

		
		//when(seedRepo.delete(input)).thenThrow(savedSeed);
		
		/*//doThrow(new PersistenceException("Exception occured")).when(seedRepo).delete(input);
		
		seedService.deleteSeed(input);
		verify(seedRepo).delete(input);
		sut.deleteDose(doseId);
        // verify the mocks
        verify(doseRepository, times(1)).deleteById(eq(doseId));*/
		
		seedService.deleteSeed(input.getSeedId());
		verify(seedRepo).delete(input);
	}
	
	@Test
	@Disabled
	@DisplayName("Test-Update seed")
	void testUpdateSeed() throws SeedIdNotFoundException {
		Seed input = new Seed(1,"Mango","Morning", "Twice a day", "easy","25ºC","Monocotyledonous",
				"This seed is a small embryonic plant",250,300,2000);
		Seed savedSeed = new Seed(1,"Mango","Morning", "Twice a day", "easy","25ºC","Monocotyledonous",
				"This seed is a small embryonic plant",250,300,2000);
	
		when(seedRepo.save(input)).thenReturn(savedSeed);
		seedService.updateSeed(input);
		verify(seedRepo).save(input);
		
	}

	
}