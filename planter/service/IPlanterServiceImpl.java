package com.ec.onlineplantnursery.planter.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ec.onlineplantnursery.planter.entity.Planter;
import com.ec.onlineplantnursery.planter.repository.PlanterRepository;

@Service
public class IPlanterServiceImpl implements IPlanterService {
	
	@Autowired
	PlanterRepository repo;

	@Override
	public Planter addPlanter(Planter planter) {
		// TODO Auto-generated method stub
		repo.save(planter);
		return planter;
	}

	@Override
	public Planter updatePlanter(Planter planter) {
		// TODO Auto-generated method stub
		Planter p = repo.findById(planter.getPlanterId()).get();
		if(p != null) {
			p.setPlanterheight(planter.getPlanterheight());
			p.setPlanterCapacity(planter.getPlanterCapacity());
			p.setDrinageHoles(planter.getDrinageHoles());
			p.setPlanterColor(planter.getPlanterColor());
			p.setPlanterShape(planter.getPlanterShape());
			p.setPlanterStock(planter.getPlanterStock());
			p.setPlanterCost(planter.getPlanterCost());
		}
		return repo.save(p);
	}

	@Override
	public Planter deletePlanter(int planterId) {
		// TODO Auto-generated method stub
		//boolean isDel = true;
		Planter p = repo.findById(planterId).get();
		repo.deleteById(planterId);
		return p;
		
	}

	@Override
	public Planter viewPlanter(int planterId) {
		// TODO Auto-generated method stub
		return repo.findById(planterId).get();
	}

	
	@Override
	public List<Planter> viewPlanter(String planterShape) {
		return repo.viewPlanter(planterShape);
	}
	
	
	@Override
	public List<Planter> viewAllPlanters() {
		// TODO Auto-generated method stub
		return repo.findAll();
	}

	@Override
	public List<Planter> viewAllPlanters(double minCost, double maxCost) {
		// TODO Auto-generated method stub
		List<Planter> planterList = new ArrayList<Planter>();
		for(Planter planter : repo.findAll()) {
			if(planter.getPlanterCost() >= minCost && planter.getPlanterCost() <= maxCost) {
				planterList.add(planter);
			}
		}
		return planterList;
	}

}
