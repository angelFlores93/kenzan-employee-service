package com.app.kenzan.employee.service.services;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.app.kenzan.employee.commons.models.entity.Employee;
import com.app.kenzan.employee.commons.utils.Status;

/**
 * 
 * @author aaflo
 *
 */
@RepositoryRestResource(path="employees")
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
	public List<Employee> findByStatus(Status status);
	public Optional <Employee> findByIdAndStatus(Long id, Status status);
}