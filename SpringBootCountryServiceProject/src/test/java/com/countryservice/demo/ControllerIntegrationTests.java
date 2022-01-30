package com.countryservice.demo;

import org.json.JSONException;
import org.json.JSONObject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;

import com.countryservice.demo.bean.Country;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@ComponentScan(basePackages = "com.countryservice.demo")
@AutoConfigureMockMvc
@ContextConfiguration
@SpringBootTest
public class ControllerIntegrationTests {

	@Test
	@Order(1)
	void getAllCountriesIntegrationTest() throws JSONException {
		String expected = "[\n"
				+ "    {\n"
				+ "        \"id\": 1,\n"
				+ "        \"countryName\": \"India\",\n"
				+ "        \"countryCapital\": \"Delhi\"\n"
				+ "    },\n"
				+ "    {\n"
				+ "        \"id\": 2,\n"
				+ "        \"countryName\": \"USA\",\n"
				+ "        \"countryCapital\": \"Washington\"\n"
				+ "    }]";
		
		TestRestTemplate restTemplate = new TestRestTemplate();
		ResponseEntity res = restTemplate.getForEntity("http://localhost:8080/getcountries", String.class);
		System.out.println(res.getStatusCode());
		System.out.println(res.getBody());
		
		JSONAssert.assertEquals(expected, res.getBody().toString(), false);
	}
	
	@Test
	@Order(2)
	void getAllCountriesByIdIntegrationTest() throws JSONException {
		String expected = "    {\n"
				+ "        \"id\": 2,\n"
				+ "        \"countryName\": \"USA\",\n"
				+ "        \"countryCapital\": \"Washington\"\n"
				+ "    }";
		
		TestRestTemplate restTemplate = new TestRestTemplate();
		ResponseEntity res = restTemplate.getForEntity("http://localhost:8080/getcountries/2", String.class);
		System.out.println(res.getStatusCode());
		System.out.println(res.getBody());
		
		JSONAssert.assertEquals(expected, res.getBody().toString(), false);
	}
	
	@Test
	@Order(3)
	void getCountryByNameIntegrationTest() throws JSONException {
		String expected = "    {\n"
				+ "        \"id\": 2,\n"
				+ "        \"countryName\": \"USA\",\n"
				+ "        \"countryCapital\": \"Washington\"\n"
				+ "    }";
		
		TestRestTemplate restTemplate = new TestRestTemplate();
		ResponseEntity res = restTemplate.getForEntity("http://localhost:8080/getcountries/countryname?name=USA", String.class);
		System.out.println(res.getStatusCode());
		System.out.println(res.getBody());
		
		JSONAssert.assertEquals(expected, res.getBody().toString(), false);
	}
	
	@Test
	@Order(4)
	void addCountryIntegrationTest() throws JSONException {
		Country country = new Country(3, "Germany", "Berlin");
		String expected = "    {\n"
				+ "        \"id\": 3,\n"
				+ "        \"countryName\": \"Germany\",\n"
				+ "        \"countryCapital\": \"Berlin\"\n"
				+ "    }";
		
		TestRestTemplate restTemplate = new TestRestTemplate();
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<Country> request = new HttpEntity<Country>(country, headers);
		
		ResponseEntity<String> res = restTemplate.postForEntity("http://localhost:8080/addcountry", request, String.class);
		System.out.println(res.getStatusCode());
		System.out.println(res.getBody());
		
		Assertions.assertEquals(HttpStatus.CREATED, res.getStatusCode());
		JSONAssert.assertEquals(expected, res.getBody().toString(), false);
	}
	
	@Test
	@Order(5)
	void updateCountryIntegrationTest() throws JSONException {
		Country country = new Country(3, "Japan", "Tokyo");
		String expected = "    {\n"
				+ "        \"id\": 3,\n"
				+ "        \"countryName\": \"Japan\",\n"
				+ "        \"countryCapital\": \"Tokyo\"\n"
				+ "    }";
		
		TestRestTemplate restTemplate = new TestRestTemplate();
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<Country> request = new HttpEntity<Country>(country, headers);
		
		ResponseEntity<String> res = restTemplate.exchange("http://localhost:8080/updatecountry/3", HttpMethod.PUT, request, String.class);
		System.out.println(res.getStatusCode());
		System.out.println(res.getBody());
		
		Assertions.assertEquals(HttpStatus.OK, res.getStatusCode());
		JSONAssert.assertEquals(expected, res.getBody().toString(), false);
	}
	
	@Test
	@Order(6)
	void deleteCountryIntegrationTest() throws JSONException {
		Country country = new Country(3, "Japan", "Tokyo");
		String expected = "    {\n"
				+ "        \"id\": 3,\n"
				+ "        \"countryName\": \"Japan\",\n"
				+ "        \"countryCapital\": \"Tokyo\"\n"
				+ "    }";
		
		TestRestTemplate restTemplate = new TestRestTemplate();
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<Country> request = new HttpEntity<Country>(country, headers);
		
		ResponseEntity<String> res = restTemplate.exchange("http://localhost:8080/deletecountry", HttpMethod.DELETE, request, String.class);
		System.out.println(res.getStatusCode());
		System.out.println(res.getBody());
		
		Assertions.assertEquals(HttpStatus.OK, res.getStatusCode());
		JSONAssert.assertEquals(expected, res.getBody().toString(), false);
	}
}
