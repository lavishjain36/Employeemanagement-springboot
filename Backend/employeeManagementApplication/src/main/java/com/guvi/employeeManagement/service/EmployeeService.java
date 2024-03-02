package com.guvi.employeeManagement.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.guvi.employeeManagement.entity.Employee;
import com.guvi.employeeManagement.exception.ResourceNotFoundException;
import com.guvi.employeeManagement.repository.EmployeesRepository;

@Service
public class EmployeeService {

	@Autowired
	private EmployeesRepository employeeRepository;

	// Create
	public Employee saveEmployee(Employee std) {
		return employeeRepository.save(std);
	}

	// Read

	/*
	 * public Employees findByEmployees(Integer id) { Optional<Employees> employee =
	 * Optional.ofNullable(employeeRepository.getById(id));
	 * if(!employee.isPresent()) throw new
	 * ResourceNotFoundException("Employee not found with "+id); return
	 * employee.get();
	 * 
	 * }
	 */
	public Employee findByEmployees(Integer id) {
		Employee employee = employeeRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Employee not found with ID: " + id));
		return employee;
	}

	// findall
	public List<Employee> getAllEmployees() {
		return employeeRepository.findAll();
	}

	// update
	public Employee updateEmployee(Employee emp) {
		Employee e = employeeRepository.findById(emp.getEmpId()).orElse(null);
		if (e != null) {
			e.setFirstName(emp.getFirstName());
			e.setLastName(emp.getLastName());
			e.setEmail(emp.getEmail());
		}
		return employeeRepository.save(e);
	}

	/*
	 * public Employees deleteEmployeeById(Integer id) { // Retrieve the employee
	 * before deletion Employees deletedEmployee = employeeRepository.findById(id)
	 * .orElseThrow(() -> new
	 * ResourceNotFoundException("Employee not found with ID: " + id));
	 * 
	 * // Delete the employee employeeRepository.deleteById(id);
	 * 
	 * // Return the deleted employee return deletedEmployee; }
	 */

	public void deleteEmployeeById(Integer id) {
		employeeRepository.deleteById(id);
	}

}
