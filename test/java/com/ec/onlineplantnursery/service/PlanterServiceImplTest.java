package com.ec.onlineplantnursery.service;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;

import com.ec.onlineplantnursery.exceptions.ResourceNotFoundException;
import com.ec.onlineplantnursery.planter.entity.Planter;
import com.ec.onlineplantnursery.planter.repository.PlanterRepository;
import com.ec.onlineplantnursery.planter.service.IPlanterServiceImpl;

public class PlanterServiceImplTest {
	PlanterRepository planterRepo;
	private static IPlanterServiceImpl planterService;
	private static AutoCloseable ac;
	
	@BeforeEach
	public void doinit()
	{
		planterRepo = mock(PlanterRepository.class); // test through approach 2
		planterService = new  IPlanterServiceImpl(planterRepo); 	// Approach 2
		ac = MockitoAnnotations.openMocks(this);
	}

	@AfterEach
	public void doAtEnd()throws Exception
	{
		ac.close();
	}
	
	@Test
	@Disabled
	void testSavePlanter() {
		Planter input = new Planter();
		Planter savedPlanter = new Planter();

		when(planterRepo.save(input)).thenReturn(savedPlanter);
		
		try {
			planterService.addPlanter(input, 0, 0);
		} catch (ResourceNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		verify(planterRepo).save(input);

	}


	@Test
	//@Disabled
	@DisplayName("Test-Get All Planters , Args:- No Args to pass")
	void testGetAllSeeds() throws ResourceNotFoundException {


		List<Planter> planterList = mock(List.class); 
		//when() and 	//thenReturn()
		when(planterRepo.findAll()).thenReturn(planterList);
		//call the actual method 
		planterService.viewAllPlanters();
		//verify
		verify(planterRepo).findAll();

	}
/*
	@Test
	@Disabled
	@DisplayName("Test-Get Seed by Id , Args:- No Args to pass")
	void testViewSeedById(){


		Optional<Seed> s = Optional.empty();
		//when() and 	//thenReturn()
		when(seedRepo.findById(2)).thenReturn(s);
		//call the actual method 
		try {
			seedService.viewSeed(2);
		} catch (SeedIdNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//verify
		verify(seedRepo).findById(2);

	}

	@Test
	@Disabled
	@DisplayName("Test-Get Seed by common Name , Args:- No Args to pass")
	void testViewSeedByName() throws ResourceNotFoundException {


		Seed s = mock(Seed.class);
		//when() and 	//thenReturn()
		when(seedRepo.getSeedByCommonName("Maize")).thenReturn(s);
		//call the actual method 
		seedService.viewSeed("Maize");
		//verify
		verify(seedRepo).getSeedByCommonName("Maize");

	}

	@Test
	@Disabled
	@DisplayName("Test-Get Seed by type of seed , Args:- No Args to pass")
	void testViewSeedByTypeOfSeed() throws ResourceNotFoundException {


		List<Seed> seedList = mock(List.class); 
		//when() and 	//thenReturn()
		when(seedRepo.getSeedsByTypeOfSeed("Monocotyledonous")).thenReturn(seedList);
		//call the actual method 
		seedService.viewAllSeeds("Monocotyledonous");
		//verify
		verify(seedRepo).getSeedsByTypeOfSeed("Monocotyledonous");

	}

	@Test
	@Disabled
	@DisplayName("Test-Delete seed , Args:- No Args to pass")
	void testDeleteSeed() {

		Seed input = new Seed(1,"Mango","Morning", "Twice a day", "easy","25ºC","Monocotyledonous",
				"This seed is a small embryonic plant",250,300,2000);
		Seed savedSeed = new Seed(1,"Mango","Morning", "Twice a day", "easy","25ºC","Monocotyledonous",
				"This seed is a small embryonic plant",250,300,2000);


		seedService.deleteSeed(input.getSeedId());
		verify(seedRepo).delete(input);
	}

	@Test
	@Disabled
	@DisplayName("Test-Update seed , Args:- No Args to pass")
	void testUpdateSeed() throws ResourceNotFoundException {
		Seed input = new Seed(1,"Mango","Morning", "Twice a day", "easy","25ºC","Monocotyledonous",
				"This seed is a small embryonic plant",250,300,2000);
		Seed savedSeed = new Seed(1,"Mango","Morning", "Twice a day", "easy","25ºC","Monocotyledonous",
				"This seed is a small embryonic plant",250,300,2000);

		when(seedRepo.save(input)).thenReturn(savedSeed);
		seedService.updateSeed(input);
		verify(seedRepo).save(input);

	}
*/	
}
