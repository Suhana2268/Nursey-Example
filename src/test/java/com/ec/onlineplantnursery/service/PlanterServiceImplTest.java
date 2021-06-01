package com.ec.onlineplantnursery.service;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import com.ec.onlineplantnursery.exceptions.ResourceNotFoundException;
import com.ec.onlineplantnursery.exceptions.SeedIdNotFoundException;
import com.ec.onlineplantnursery.plant.entity.Plant;
import com.ec.onlineplantnursery.plant.repository.IPlantRepository;
import com.ec.onlineplantnursery.plant.service.IPlantService;
import com.ec.onlineplantnursery.plant.service.IPlantServiceImpl;
import com.ec.onlineplantnursery.planter.entity.Planter;
import com.ec.onlineplantnursery.planter.repository.IPlanterRepository;
import com.ec.onlineplantnursery.planter.service.IPlanterServiceImpl;
import com.ec.onlineplantnursery.seed.entity.Seed;
import com.ec.onlineplantnursery.seed.repository.ISeedRepository;
import com.ec.onlineplantnursery.seed.service.ISeedService;
import com.ec.onlineplantnursery.seed.service.ISeedServiceImpl;

@SpringBootTest
public class PlanterServiceImplTest {

	IPlanterRepository planterRepo;
	IPlantRepository plantRepo;
	ISeedRepository seedRepo;
	private static IPlanterServiceImpl planterService;
	private static IPlantServiceImpl plantService;
	private static ISeedServiceImpl seedService;
	private static AutoCloseable ac;

	@BeforeEach
	public void doinit()
	{
		planterRepo = mock(IPlanterRepository.class); // test through approach 2
		planterService = new  IPlanterServiceImpl(planterRepo); 	// Approach 2
		plantRepo = mock(IPlantRepository.class);
		plantService = new IPlantServiceImpl(plantRepo);
		seedRepo = mock(ISeedRepository.class);
		seedService = new ISeedServiceImpl(seedRepo);
		ac = MockitoAnnotations.openMocks(this);
	}

	@AfterEach
	public void doAtEnd()throws Exception
	{
		ac.close();
	}

	@Test
	@DisplayName("Test-Save Planter with Seed Name")
	void testSavePlanterBySeed() throws ResourceNotFoundException {
		Seed sinput = new Seed(101,"Mango","Morning", "Twice a day", "easy","25ºC","Monocotyledonous",
				"This seed is a small embryonic plant",200,300,2000);
		
		Planter input = new Planter(1,12,3,2,23,"Round",45,30, sinput);
		Planter savedPlanter = new Planter(1,12,3,2,23,"Round",45,30, sinput);

		when(planterRepo.save(input)).thenReturn(savedPlanter);
		planterService.addPlanter(input, 0, 101);
		verify(planterRepo).save(input);

	}
	
	
	@Test
	@DisplayName("Test-Save Planter with Plant Name")
	void testSavePlanterByPlant() throws ResourceNotFoundException {
		Plant sinput = new Plant(123,12,380,"Mango","Once a day","medical","moderate","32",
				"fruit","This is a mango plant","high",3000);
		
		Planter input = new Planter(1,12,3,2,23,"Round",45,30,sinput);
		Planter savedPlanter = new Planter(1,12,3,2,23,"Round",45,30,sinput);

		when(planterRepo.save(input)).thenReturn(savedPlanter);
		planterService.addPlanter(input, 123, 0);
		verify(planterRepo).save(input);

	}
	
	
	@Test
	@DisplayName("Test-Save Planter with Both Plant and Seed Name")
	void testSavePlanterByBoth() throws ResourceNotFoundException {
		
		Seed sinput = new Seed(101,"Mango","Morning", "Twice a day", "easy","25ºC","Monocotyledonous",
				"This seed is a small embryonic plant",200,300,2000);
		Plant pinput = new Plant(123,12,380,"Mango","Once a day","medical","moderate","32",
				"fruit","This is a mango plant","high",3000);
		
		Planter input = new Planter(1,12,3,2,23,"Round",45,30,pinput,sinput);
		Planter savedPlanter = new Planter(1,12,3,2,23,"Round",45,30,pinput,sinput);

		when(planterRepo.save(input)).thenReturn(savedPlanter);
		planterService.addPlanter(input, 123, 101);
		verify(planterRepo).save(input);

	}
	
	@Test
	@DisplayName("Test-Save Planter without  Plant and Seed Name")
	void testSavePlanter() throws ResourceNotFoundException {
		
		Planter input = new Planter(1,12,3,2,23,"Round",45,30);
		Planter savedPlanter = new Planter(1,12,3,2,23,"Round",45,30);

		when(planterRepo.save(input)).thenReturn(savedPlanter);
		planterService.addPlanter(input, 0, 0);
		verify(planterRepo).save(input);

	}


	@Test
	@DisplayName("Test-Get All Planters , Args:- No Args to pass")
	void testGetAllPlanters() {


		List<Planter> pList = mock(List.class); 
		//when() and 	//thenReturn()
		when(planterRepo.findAll()).thenReturn(pList);
		//call the actual method 
		planterService.viewAllPlanters();
		//verify
		verify(planterRepo).findAll();

	}

	@Test
	@DisplayName("Test-Get Planter by Id , Args:- No Args to pass")
	void testViewPlanterById() throws ResourceNotFoundException{


		Optional<Planter> s = Optional.empty();
		//when() and 	//thenReturn()
		when(planterRepo.findById(2)).thenReturn(s);
		Executable executable = ()->planterService.viewPlanter(2);
		assertThrows(ResourceNotFoundException.class, executable);

	}

	@Test
	@DisplayName("Test-Get Planter by Planter Shape , Args:- No Args to pass")
	void testViewPlanterByShape() throws ResourceNotFoundException {


		String planterShape = "square";
		Optional<List<Planter>> s = Optional.empty();
		when(planterRepo.viewPlanter(planterShape)).thenReturn(s);
		Executable executable = ()->planterService.viewPlanter(planterShape);
		assertThrows(ResourceNotFoundException.class, executable);

	}

	@Test
	@DisplayName("Test-Get Planter by range , Args:- No Args to pass")
	void testViewPlanterByRange() throws ResourceNotFoundException {


		double minCost = 50;
		double maxCost = 100;
		Optional<List<Planter>> pList = Optional.empty(); 
		when(planterRepo.getPlantersByRange(minCost,maxCost)).thenReturn(pList);
		//call the actual method 
		Executable executable = ()->planterService.viewAllPlanters(minCost,maxCost);
		assertThrows(ResourceNotFoundException.class, executable);

	}

	@Test
	@Disabled
	@DisplayName("Test-Delete Planter , Args:- No Args to pass")
	void testDeletePlanter() throws ResourceNotFoundException {

		Seed sinput = new Seed(1,"Mango","Morning", "Twice a day", "easy","25ºC","Monocotyledonous",
				"This seed is a small embryonic plant",250,300,2000);
		
		Planter input = new Planter(1,12,3,2,23,"Round",45,30,null,sinput);
		Planter savedPlanter = new Planter(1,12,3,2,23,"Round",45,30,null,sinput);

		planterService.deletePlanter(input.getPlanterId());
		verify(planterRepo).deleteById(input.getPlanterId());
	}

	@Test
	@Disabled
	@DisplayName("Test-Update Planter , Args:- No Args to pass")
	void testUpdatePlanter() throws ResourceNotFoundException {
		Seed sinput = new Seed(1,"Mango","Morning", "Twice a day", "easy","25ºC","Monocotyledonous",
				"This seed is a small embryonic plant",250,300,2000);
		Planter input = new Planter(1,12,3,2,23,"Round",45,30,null,sinput);
		Planter savedPlanter = new Planter(1,12,3,2,23,"Round",45,30,null,sinput);

		when(planterRepo.save(input)).thenReturn(savedPlanter);
		planterService.updatePlanter(input);
		verify(planterRepo).save(input);

	}

}