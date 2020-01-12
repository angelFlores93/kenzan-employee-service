package com.app.kenzan.employee.service.services;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import com.app.kenzan.employee.service.entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
	public List<Employee> findByStatus(int status);
	public Optional <Employee> findByIdAndStatus(Long id, int status);
}