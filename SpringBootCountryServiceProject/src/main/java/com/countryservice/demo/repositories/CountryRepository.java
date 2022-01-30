package com.countryservice.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.countryservice.demo.bean.Country;

public interface CountryRepository extends JpaRepository<Country,Integer> {

}
