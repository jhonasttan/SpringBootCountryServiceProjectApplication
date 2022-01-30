package com.countryservice.demo.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.countryservice.demo.bean.Country;
import com.countryservice.demo.services.CountryService;

@RestController
public class CountryController {
	
	@Autowired
	CountryService countryService;
	
	@GetMapping("/getcountries")
	public ResponseEntity<List<Country>> getCountries() {
		
		try {
			List<Country> countries = countryService.getAllCountries();
			return new ResponseEntity<List<Country>>(countries, HttpStatus.FOUND);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@GetMapping("/getcountries/{id}")
	public ResponseEntity<Country> getCountryById(@PathVariable(value="id") int id) {
		
		try {
			Country country= countryService.getCountryById(id);
			return new ResponseEntity<Country>(country, HttpStatus.FOUND);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@GetMapping("/getcountries/countryname")
	public ResponseEntity<Country> getCountryByName(@RequestParam(value="name") String countryName) {
		
		try {
			Country country= countryService.getCountryByName(countryName);
			return new ResponseEntity<Country>(country, HttpStatus.FOUND);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@PostMapping("/addcountry")
	public ResponseEntity<Country> addCountry(@RequestBody Country country) {
		try {
			Country addedCountry = countryService.addCountry(country);
			return new ResponseEntity<Country>(addedCountry, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@PutMapping("/updatecountry/{id}")
	public ResponseEntity<Country> updateCountry(@PathVariable(value="id") int id, @RequestBody Country country) {
		try {
			Country existCountry = countryService.getCountryById(country.getId());
			existCountry.setCountryName(country.getCountryName());
			existCountry.setCountryCapital(country.getCountryCapital());
			Country updatedCountry = countryService.updateCountry(existCountry);
			return new ResponseEntity<Country>(updatedCountry, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.CONFLICT);
		}
		
	}
	
	@DeleteMapping("/deletecountry")
	public ResponseEntity<Country> deleteCountry(@RequestBody Country delCountry) {
		Country country = null;
		try {
			country = countryService.getCountryById(delCountry.getId());
			countryService.deleteCountry(country);
			return new ResponseEntity<Country>(country, HttpStatus.OK);
		} catch(Exception e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		
	}
	

}
