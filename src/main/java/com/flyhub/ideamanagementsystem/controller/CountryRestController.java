package com.flyhub.ideamanagementsystem.controller;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.flyhub.ideamanagementsystem.Entity.Country;
import com.flyhub.ideamanagementsystem.service.CountryService;


@RestController
@CrossOrigin
@RequestMapping("api/v1/countries")
public class CountryRestController {
	
	@Autowired
	CountryService countryService;
	
	@GetMapping
	public List<Country> findAllCountries(){
		return countryService.findAllCountries();
	}
	
	@GetMapping("/{id}")
	public Country findCountry(@PathVariable("id") Long id){
		return countryService.findCountry(id);
	}
	
	@PostMapping
	public Country createCountry(@RequestBody Country country) {
		return countryService.createCountry(country);
	}
	
	@DeleteMapping("/{id}")
	public void deleteCountry(@PathVariable("id") Long id) {
		countryService.deleteCountry(id);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Country> updateCountry(@RequestBody Country country, @PathVariable("id") Long id){
		Country oldCountry = countryService.findCountry(id);
		if(Objects.isNull(oldCountry)) {
			return ResponseEntity.notFound().build();
		}
		country.setId(id);
		return ResponseEntity.ok(this.countryService.createCountry(country));
	}
	
	@PatchMapping("/{id}")
	public ResponseEntity<Country> updateSomeCountryFields(@RequestBody Country country, @PathVariable("id") Long id){
		Country oldCountry = countryService.findCountry(id);
		if(Objects.isNull(oldCountry)) {
			return ResponseEntity.notFound().build();
		}
		country.setId(id);
		return ResponseEntity.ok(this.countryService.createCountry(country));
	}
}
