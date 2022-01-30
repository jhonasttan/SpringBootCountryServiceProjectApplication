package com.countryservice.demo;

import org.mockito.Mock;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.ClassOrderer.OrderAnnotation;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.mockito.InjectMocks;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.countryservice.demo.bean.Country;
import com.countryservice.demo.controllers.CountryController;
import com.countryservice.demo.services.CountryService;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest(classes= {ControllerMackitoTests.class})
public class ControllerMackitoTests {
	
	@Mock
	CountryService countryService;
	
	@InjectMocks
	CountryController countryController;
	
	List<Country> mycountries;
	Country country;
	
	@Test
	@Order(1)
	public void test_getAllCountries() {
		mycountries = new ArrayList<Country>();
		mycountries.add(new Country(1, "India", "Delhi"));
		mycountries.add(new Country(2, "USA", "Washington, D.C"));
		
		when(countryService.getAllCountries()).thenReturn(mycountries);
		ResponseEntity<List<Country>> res = countryController.getCountries();
		
		Assertions.assertEquals(HttpStatus.FOUND ,res.getStatusCode());
		Assertions.assertEquals(2, res.getBody().size());
	}
	
	@Test
	@Order(2)
	public void test_getCountryById() {
		Country country = new Country(2, "USA", "Washington, D.C");
		int countryId = 2;
		
		when(countryService.getCountryById(countryId)).thenReturn(country);
		ResponseEntity<Country> res = countryController.getCountryById(countryId);
		
		Assertions.assertEquals(HttpStatus.FOUND ,res.getStatusCode());
		Assertions.assertEquals(countryId, res.getBody().getId());
	}
	
	@Test
	@Order(3)
	public void test_getCountryByName() {
		country = new Country(2, "USA", "Washington, D.C");
		
		String countryName = "USA";
		
		when(countryService.getCountryByName(countryName)).thenReturn(country);
		ResponseEntity<Country> res = countryController.getCountryByName(countryName);
		
		Assertions.assertEquals(HttpStatus.FOUND ,res.getStatusCode());
		Assertions.assertEquals(countryName, res.getBody().getCountryName());
	}
	
	@Test
	@Order(4)
	public void test_addCountry() {
		country = new Country(2, "USA", "Washington, D.C");
		
		String countryName = "USA";
		
		when(countryService.addCountry(country)).thenReturn(country);
		ResponseEntity<Country> res = countryController.addCountry(country);
		
		Assertions.assertEquals(HttpStatus.FOUND ,res.getStatusCode());
		Assertions.assertEquals(country, res.getBody());
	}
	
	@Test
	@Order(5)
	public void test_updateCountry() {
		country = new Country(3, "Japan", "Tokyo");
		int countryId = 3;
		
		
		when(countryService.getCountryById(countryId)).thenReturn(country);
		when(countryService.updateCountry(country)).thenReturn(country);
		ResponseEntity<Country> res = countryController.updateCountry(countryId, country);
		
		Assertions.assertEquals(HttpStatus.OK ,res.getStatusCode());
		Assertions.assertEquals(countryId, res.getBody().getId());
		Assertions.assertEquals("Japan", res.getBody().getCountryName());
		Assertions.assertEquals("Tokyo", res.getBody().getCountryCapital());
	}
	
	@Test
	@Order(6)
	public void test_deleteCountry() {
		country = new Country(2, "USA", "Washington, D.C");
		int countryId = 2;
		
		when(countryService.getCountryById(countryId)).thenReturn(country);
		countryService.deleteCountry(country);
		verify(countryService, times(1)).deleteCountry(country);
		ResponseEntity<Country> res = countryController.deleteCountry(country);
		
		Assertions.assertEquals(HttpStatus.OK ,res.getStatusCode());
		Assertions.assertEquals(country, res.getBody());
	}

}
