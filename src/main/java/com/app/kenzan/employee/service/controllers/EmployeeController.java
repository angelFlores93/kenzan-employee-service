package com.app.kenzan.employee.service.controllers;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.app.kenzan.employee.commons.models.entity.Employee;
import com.app.kenzan.employee.commons.utils.Status;
import com.app.kenzan.employee.service.exceptions.EmployeeNotFoundException;
import com.app.kenzan.employee.service.services.EmployeeRepository;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;

/**
 * 
 * @author aaflo
 *
 */
@RestController
public class EmployeeController {
	@Autowired
	private EmployeeRepository repository;
	@Autowired
	private EmployeeResourceAssembler assembler;

	
	@GetMapping("/employees")
	public CollectionModel<EntityModel<Employee>> getAll() {
		List<EntityModel<Employee>> employees = repository.findByStatus(Status.ACTIVE).stream().map(assembler::toModel)
				.collect(Collectors.toList());

		return new CollectionModel<EntityModel<Employee>>(employees,
				linkTo(methodOn(EmployeeController.class).getAll()).withSelfRel());
	}

	@PostMapping("/employees")
	public ResponseEntity<?> newEmployee(@RequestBody Employee newEmployee) throws URISyntaxException {
		Employee employee = repository.save(newEmployee);
		EntityModel<Employee> resource = assembler.toModel(employee);
		return ResponseEntity.created(new URI(resource.getLink("employees").get().getHref())).body(resource);
	}
	// Single item

	@GetMapping("/employees/{id}")
	public EntityModel<Employee> findById(@PathVariable Long id) {
		Employee employee = repository.findByIdAndStatus(id, Status.ACTIVE).orElseThrow(() -> new EmployeeNotFoundException(id));
		return assembler.toModel(employee);
	}

	@PutMapping("/employees/{id}")
	public ResponseEntity<?> updateEmployee(@RequestBody Employee newEmployee, @PathVariable Long id)
			throws URISyntaxException {
		Employee updatedEmployee = repository.findById(id).map(employee -> {
			employee.setFirstName(newEmployee.getFirstName());
			employee.setMiddleInitial(newEmployee.getMiddleInitial());
			employee.setLastName(newEmployee.getLastName());
			employee.setDateOfEmployment(newEmployee.getDateOfEmployment());
			employee.setDateOfBirth(newEmployee.getDateOfBirth());
			employee.setStatus(newEmployee.getStatus());
			return repository.save(employee);
		}).orElseGet(() -> {
			newEmployee.setId(id);
			return repository.save(newEmployee);
		});
		EntityModel<Employee> resource = assembler.toModel(updatedEmployee);
		return ResponseEntity.created(new URI(resource.getLink("employees").get().getHref())).body(resource);
	}

	@DeleteMapping("/employees/{id}")
	public ResponseEntity<Object> deleteEmployee(@PathVariable Long id) throws Exception {
		Employee deletedEmployee = repository.findById(id).map(employee -> {
			employee.setStatus(Status.INATIVE);
			return repository.save(employee);
		}).orElseThrow(() -> new EmployeeNotFoundException(id));
		EntityModel<Employee> resource = assembler.toModel(deletedEmployee);
		return ResponseEntity.created(new URI(resource.getLink("employees").get().getHref())).body(resource);
	}
}
