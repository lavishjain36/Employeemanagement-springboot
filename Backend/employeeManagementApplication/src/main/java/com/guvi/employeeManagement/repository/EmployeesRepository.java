package com.guvi.employeeManagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.guvi.employeeManagement.entity.Employee;

@Repository
public interface EmployeesRepository extends JpaRepository<Employee, Integer>{

	
}
