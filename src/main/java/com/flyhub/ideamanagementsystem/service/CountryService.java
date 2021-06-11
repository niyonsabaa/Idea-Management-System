package com.flyhub.ideamanagementsystem.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.flyhub.ideamanagementsystem.DaO.CountryRepository;
import com.flyhub.ideamanagementsystem.Entity.Country;
@Service
public class CountryService {
	@Autowired
	CountryRepository countryRepo;
	
	public List<Country> findAllCountries(){
		return countryRepo.findAll();
	}
	
	public Country findCountry(Long id){
		return countryRepo.findById(id).get();
	}
	
	public Country createCountry(Country country) {
		return countryRepo.save(country);
	}
	
	public void deleteCountry(Long id) {
		countryRepo.deleteById(id);
	}
}
