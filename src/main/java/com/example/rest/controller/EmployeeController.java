package com.example.rest.controller;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.afford;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.LinkRelationProvider;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.rest.model.Employee;
import com.example.rest.service.EmployeeService;

@RestController
@RequestMapping(path = "/employees")
public class EmployeeController implements InitializingBean {

	Logger logger = LoggerFactory.getLogger(EmployeeController.class);

	private EmployeeService employeeService;

	private LinkRelationProvider linkRelationProvider;

	@Autowired
	public EmployeeController(final EmployeeService employeeService, LinkRelationProvider linkRelationProvider) {
		this.employeeService = employeeService;
		this.linkRelationProvider = linkRelationProvider;
	}

	@RequestMapping(method = RequestMethod.GET)
	@ResponseStatus(code = HttpStatus.OK)
	public CollectionModel<EntityModel<Employee>> all() {
		List<Employee> employees = employeeService.findAll();
		List<EntityModel<Employee>> employeesEntityModels = employees.stream().map(employee -> {
			return new EntityModel<Employee>(employee,
					linkTo(methodOn(EmployeeController.class).findById(employee.getId())).withSelfRel()
							.andAffordance(afford(methodOn(EmployeeController.class).updateById(employee.getId())))
							.andAffordance(afford(methodOn(EmployeeController.class).delete(employee.getId()))),
					linkTo(methodOn(EmployeeController.class).all()).withRel("employees"));
		}).collect(Collectors.toList());

		CollectionModel<EntityModel<Employee>> collectionModel = new CollectionModel<>(employeesEntityModels,
				linkTo(methodOn(EmployeeController.class).all()).withSelfRel()
						.andAffordance(afford(methodOn(EmployeeController.class).create(null))));
		return collectionModel;
	}

	@RequestMapping(method = RequestMethod.POST)
	@ResponseStatus(code = HttpStatus.CREATED)
	public Employee create(@RequestBody Employee employee) {
		return employeeService.createEmployee(employee);
	}

	@GetMapping(path = "/{id}")
	@ResponseStatus(code = HttpStatus.OK)
	public EntityModel<Employee> findById(@PathVariable Long id) {
		logger.info("called with id {}", id);
		Optional<Employee> employee = employeeService.findById(id);
		if (employee.isEmpty()) {
			throw new ResourceNotFoundException("Employee not found by Id " + id);
		}
		EntityModel<Employee> employeeEntityModel = null;
		employeeEntityModel = new EntityModel<>(employee.get(),
				linkTo(methodOn(EmployeeController.class).findById(id)).withSelfRel()
						.andAffordance(afford(methodOn(EmployeeController.class).updateById(id)))
						.andAffordance(afford(methodOn(EmployeeController.class).delete(id))),
				linkTo(methodOn(EmployeeController.class).all())
						.withRel(linkRelationProvider.getCollectionResourceRelFor(Employee.class)));
		return employeeEntityModel;
	}

	@DeleteMapping(path = "/{id}")
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	public ResponseEntity<Object> delete(@PathVariable Long id) {
		Optional<Employee> employee = employeeService.findById(id);
		if (employee.isEmpty()) {
			throw new ResourceNotFoundException("Employee not found by Id " + id);
		}
		employeeService.deleteById(id);
		return ResponseEntity.noContent().build();
	}

	@PutMapping(path = "/{id}")
	@ResponseStatus(code = HttpStatus.OK)
	public Employee updateById(@PathVariable Long id) {
		Optional<Employee> employee = employeeService.findById(id);
		if (employee.isEmpty()) {
			throw new ResourceNotFoundException("Employee not found by Id " + id);
		}

		return employee.get();
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		Assert.notNull(employeeService, "EmployeeService injected null");
		Assert.notNull(linkRelationProvider, "LinkRelationProvider bean injected null");
	}
}
