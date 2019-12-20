package com.example.rest.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.rest.dao.EmployeeRepository;
import com.example.rest.model.Employee;

@Service
public class EmployeeService {

	private EmployeeRepository employeeRepository;

	@Autowired
	public EmployeeService(EmployeeRepository employeeRepository) {
		this.employeeRepository = employeeRepository;
	}

	@Transactional(readOnly = true)
	public List<Employee> findAll() {
		return employeeRepository.findAll();
	}

	@Transactional
	public Employee createEmployee(final Employee employee) {
		return employeeRepository.save(employee);
	}
	
	@Transactional(readOnly = true)
	public Optional<Employee> findById(final Long id) {
		return employeeRepository.findById(id);
	}
	
	@Transactional
	public void deleteById(final Long id) {
		employeeRepository.deleteById(id);
	}
}
