package com.ec.onlineplantnursery.planter.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ec.onlineplantnursery.exceptions.ResourceNotFoundException;
import com.ec.onlineplantnursery.plant.entity.Plant;
import com.ec.onlineplantnursery.plant.repository.PlantRepository;
import com.ec.onlineplantnursery.planter.entity.Planter;
import com.ec.onlineplantnursery.planter.repository.PlanterRepository;
import com.ec.onlineplantnursery.seed.entity.Seed;
import com.ec.onlineplantnursery.seed.repository.SeedRepository;

@Service
public class IPlanterServiceImpl implements IPlanterService {
	
	@Autowired
	PlanterRepository repo;
	
	@Autowired
	PlantRepository prepo;
	
	@Autowired
	SeedRepository srepo;
	
	
	
	public IPlanterServiceImpl(PlanterRepository repo) {
		super();
		this.repo = repo;
	}

	@Override
	public Planter addPlanter(Planter planter, int plantId, int seedId) throws ResourceNotFoundException{
		// TODO Auto-generated method stub
		if(plantId != 0) {
			Optional<Plant> p = prepo.findById(plantId);
			if(p.isPresent()) {
				planter.setPlants(prepo.findById(plantId).get());
			}
			if(p.isPresent() == false){
				throw new ResourceNotFoundException();
			}
		}
		if(seedId != 0) {
			Optional<Seed> s = srepo.findById(seedId);
			if(s.isPresent()) {
				planter.setSeeds(srepo.findById(seedId).get());
			}
			if(s.isPresent() == false){
				throw new ResourceNotFoundException();
			}
		}
		repo.save(planter);
		return planter;
	}

	@Override
	public Planter updatePlanter(Planter planter) throws ResourceNotFoundException{
		// TODO Auto-generated method stub
		Optional<Planter> pl = repo.findById(planter.getPlanterId());
		if(pl.isPresent()) {
			Planter p = repo.findById(planter.getPlanterId()).get();
			p.setPlanterheight(planter.getPlanterheight());
			p.setPlanterCapacity(planter.getPlanterCapacity());
			p.setDrinageHoles(planter.getDrinageHoles());
			p.setPlanterColor(planter.getPlanterColor());
			p.setPlanterShape(planter.getPlanterShape());
			p.setPlanterStock(planter.getPlanterStock());
			p.setPlanterCost(planter.getPlanterCost());
			return repo.save(p);
		}
		throw new ResourceNotFoundException();
	}

	@Override
	public Planter deletePlanter(int planterId) throws ResourceNotFoundException{
		// TODO Auto-generated method stub
		
		Optional<Planter> pl = repo.findById(planterId);
		if(pl.isPresent() == false) {
			throw new ResourceNotFoundException("No Planter found with the Id");
		}
		
		repo.deleteById(planterId);
		return repo.findById(planterId).get();
		
	}

	@Override
	@Transactional
	public Planter viewPlanter(int planterId) throws ResourceNotFoundException{
		// TODO Auto-generated method stub
		Optional<Planter> pl = repo.findById(planterId);
		if(pl.isPresent() == false) {
			throw new ResourceNotFoundException("No Planter found with the Id");
		}
		return repo.findById(planterId).get();
	}

	
	@Override
	public List<Planter> viewPlanter(String planterShape) throws ResourceNotFoundException{
		List<Planter> plist = repo.viewPlanter(planterShape);
		if(plist.size() > 0) {
			return plist;
		}
		throw new ResourceNotFoundException();
	}
	
	
	@Override
	public List<Planter> viewAllPlanters() throws ResourceNotFoundException{
		// TODO Auto-generated method stub
		List<Planter> plist = repo.findAll();
		if(plist.size() > 0) {
			return plist;
		}
		throw new ResourceNotFoundException();
	}

	@Override
	public List<Planter> viewAllPlanters(double minCost, double maxCost) throws ResourceNotFoundException{
		// TODO Auto-generated method stub
		List<Planter> planterList = new ArrayList<Planter>();
		for(Planter planter : repo.findAll()) {
			if(planter.getPlanterCost() >= minCost && planter.getPlanterCost() <= maxCost) {
				planterList.add(planter);
			}
		}
		if(planterList.size() > 0) {
			return planterList;
		}
		throw new ResourceNotFoundException();
	}

}
