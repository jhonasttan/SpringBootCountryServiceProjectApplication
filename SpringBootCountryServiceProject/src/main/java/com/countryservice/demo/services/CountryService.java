package com.countryservice.demo.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import com.countryservice.demo.bean.Country;
import com.countryservice.demo.controllers.AddResponse;
import com.countryservice.demo.repositories.CountryRepository;

@Service
@Component
public class CountryService {
	
	@Autowired
	CountryRepository countryrep;
	
	public List getAllCountries() {
		return countryrep.findAll();
		
	}
	
	public Country getCountryById(int id) {
		return countryrep.findById(id).get();
	}
	
	public Country getCountryByName(String countryName) {
		List<Country> countries = countryrep.findAll();
		Country country = null;
		
		for(Country con: countries) {
			if(con.getCountryName().equalsIgnoreCase(countryName))
				country=con;
		}
		return country;	
	}
	
	public Country addCountry(Country country) {
		country.setId(getMaxId());
		countryrep.save(country);
		return country;
	}
	
	public int getMaxId() {
		return countryrep.findAll().size()+1;
	}
	
	public Country updateCountry(Country country) {
		countryrep.save(country);
		return country;
	}
	
	public AddResponse deleteCountry(Country country) {
		countryrep.delete(country);
		AddResponse res = new AddResponse();
		res.setMsg("Country Deleted");
		res.setId(country.getId());
		return res;
	}
	
//	public AddResponse deleteCountry(int id) {
//		countryrep.deleteById(id);
//		AddResponse res = new AddResponse();
//		res.setMsg("Country Deleted");
//		res.setId(id);
//		return res;
//	}
	


}
