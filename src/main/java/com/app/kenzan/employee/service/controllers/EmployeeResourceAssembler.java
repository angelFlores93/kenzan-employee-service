package com.app.kenzan.employee.service.controllers;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import com.app.kenzan.employee.commons.models.entity.Employee;
/**
 * 
 * @author aaflo
 *
 */
@Component
public class EmployeeResourceAssembler implements RepresentationModelAssembler<Employee, EntityModel<Employee>> {
	@Override
	public EntityModel<Employee> toModel(Employee employee) {
		return new EntityModel<Employee>(employee,
				linkTo(methodOn(EmployeeController.class).findById(employee.getId())).withSelfRel(),
				linkTo(methodOn(EmployeeController.class).getAll()).withRel("employees"));
	}
}
