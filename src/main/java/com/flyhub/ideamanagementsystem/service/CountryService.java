package com.flyhub.ideamanagementsystem.service;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.flyhub.ideamanagementsystem.DaO.CountryRepository;
import com.flyhub.ideamanagementsystem.Entity.Country;
import com.flyhub.ideamanagementsystem.exception.CountryNotFoundException;
@Service
public class CountryService {
	@Autowired
	CountryRepository countryRepo;
	
	public List<Country> findAllCountries(){
		return countryRepo.findAll();
	}
	
	public Country findCountry(Long id){
		return countryRepo.findById(id).orElseThrow(()-> 
		new CountryNotFoundException("Country with id "+id+" doesn't exist.") );
	}
	
	public Country createCountry(Country country) {
		return countryRepo.save(country);
	}
	
	public void deleteCountry(Long id) {
		if(Objects.nonNull(findCountry(id))) {
			countryRepo.deleteById(id);
		}		
	}
}
