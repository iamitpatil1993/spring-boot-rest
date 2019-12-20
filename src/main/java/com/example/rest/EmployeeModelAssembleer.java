package com.example.rest;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import com.example.rest.controller.EmployeeController;
import com.example.rest.model.Employee;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

public class EmployeeModelAssembleer implements RepresentationModelAssembler<Employee, EntityModel<Employee>> {

	@Override
	public EntityModel<Employee> toModel(Employee entity) {
		return new EntityModel<>(entity,
				linkTo(methodOn(EmployeeController.class).findById(entity.getId())).withSelfRel());
	}

}
