package com.ec.onlineplantnursery.web;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ec.onlineplantnursery.exceptions.ResourceNotFoundException;
import com.ec.onlineplantnursery.planter.entity.Planter;
import com.ec.onlineplantnursery.planter.service.IPlanterService;

import javax.validation.Valid;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Validated
@RestController
@RequestMapping("/api")
@Api(value = "Online Nursery Store",description = "Various api works on laptop inventory management")
public class MyRestController {
	
	Set<String> categoryList = new HashSet<>();
	
	@Autowired
	IPlanterService service;
	
	public MyRestController() {
		System.out.println("My Rest Controller Constructor");
	}
	
	@GetMapping("/home")
	public String homeRequest() {
		return "Welcome : Nursery Shopping App " + LocalDateTime.now();
	}
	
	@ApiOperation(value = "planter post mapping. Give plantId and seedId as zero in url if creating new plant and seed instance through planter." , response = Planter.class)
	@PostMapping("/planter/insert/{plantId}/{seedId}")
	public Planter insertPlanter(@RequestBody @Valid Planter planter, @PathVariable int plantId, @PathVariable int seedId) throws ResourceNotFoundException {
		service.addPlanter(planter, plantId, seedId);
		return planter;
	}
	
	@PutMapping("planter/update")
	public Planter updatePlanter(@RequestBody Planter planter) throws ResourceNotFoundException{
		return service.updatePlanter(planter);
	}

	@DeleteMapping("/planter/delete/{planterId}")
	public Planter deletePlanter(@PathVariable int planterId) throws ResourceNotFoundException//through id
	{
		return service.deletePlanter(planterId);
	}

	
	@GetMapping("/planter/view/Id/{planterId}")
	public Planter viewPlanter(@PathVariable int planterId) throws ResourceNotFoundException
	{
		return service.viewPlanter(planterId);
	}


	
	@ApiOperation(value = "Planter Get mapping to fetch all planters" , response = List.class)
	@GetMapping("/planters/all")
	public List<Planter> viewAllPlanters() throws ResourceNotFoundException{
		return service.viewAllPlanters();
	}

	
	@GetMapping("/planters/cost/range/{minCost}/{maxCost}")
	public List<Planter> viewAllPlanters(@PathVariable double minCost, @PathVariable double maxCost) throws ResourceNotFoundException{ 
		return service.viewAllPlanters(minCost, maxCost);
	}
	
	
//	Planter viewPlanter(String planterShape);
	
	@GetMapping("/planters/Shape/{planterShape}")
	public List<Planter> viewPlanter(@PathVariable String planterShape) throws ResourceNotFoundException{ 
		return service.viewPlanter(planterShape);
	}
}
