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
	
	@ApiOperation(value = "planter post mapping" , response = Planter.class)
	@PostMapping("/planter/insert")
	public Planter insertPlanter(@RequestBody @Valid Planter planter) {
		service.addPlanter(planter);
		return planter;
	}
	
	@PutMapping("/update")
	public Planter updatePlanter(@RequestBody Planter planter) {
		return service.updatePlanter(planter);
	}

	@DeleteMapping("/planter/delete/{planterId}")
	public Planter deletePlanter(@PathVariable int planterId)//through id
	{
		return service.deletePlanter(planterId);
	}

	
	@GetMapping("/planter/view/{planterId}")
	public Planter viewPlanter(@PathVariable int planterId)
	{
		return service.viewPlanter(planterId);
	}


	
	@ApiOperation(value = "Planter Get mapping to fetch all planters" , response = List.class)
	@GetMapping("/planters")
	public List<Planter> viewAllPlanters() {
		return service.viewAllPlanters();
	}

	
	@GetMapping("/planters/{minCost}/{maxCost}")
	public List<Planter> viewAllPlanters(@PathVariable double minCost, @PathVariable double maxCost) { 
		return service.viewAllPlanters(minCost, maxCost);
	}
	
	
//	Planter viewPlanter(String planterShape);
	
	@GetMapping("/plantersByShape/{planterShape}")
	public List<Planter> viewPlanter(@PathVariable String planterShape) { 
		return service.viewPlanter(planterShape);
	}
}
