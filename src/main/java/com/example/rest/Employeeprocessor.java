package com.example.rest;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.LinkRelation;
import org.springframework.hateoas.server.RepresentationModelProcessor;
import org.springframework.stereotype.Component;

import com.example.rest.model.Employee;

@Component
public class Employeeprocessor implements RepresentationModelProcessor<EntityModel<Employee>> {

	@Override
	public EntityModel<Employee> process(EntityModel<Employee> model) {
		model.add(new Link("/payments/{orderId}").withRel(LinkRelation.of("payments")) //
				.expand(model.getContent().getId()));
		return model;
	}

}
