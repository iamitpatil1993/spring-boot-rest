package com.example.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.example.rest.dao.EmployeeRepository;
import com.example.rest.model.Employee;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class DatabaseInitializer implements CommandLineRunner {

	private EmployeeRepository employeeRepository;

	@Autowired
	public DatabaseInitializer(EmployeeRepository employeeRepository) {
		this.employeeRepository = employeeRepository;
	}

	@Override
	public void run(String... args) throws Exception {
		log.info("Preloading " + employeeRepository.save(new Employee("Bilbo Baggins", "burglar")));
		log.info("Preloading " + employeeRepository.save(new Employee("Frodo Baggins", "thief")));
	}

}
	