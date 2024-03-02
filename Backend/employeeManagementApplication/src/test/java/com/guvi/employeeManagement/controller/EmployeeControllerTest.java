package com.guvi.employeeManagement.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.guvi.employeeManagement.entity.Employee;
import com.guvi.employeeManagement.exception.ResourceNotFoundException;
import com.guvi.employeeManagement.service.EmployeeService;

@ExtendWith(MockitoExtension.class)
@TestInstance(Lifecycle.PER_CLASS)
public class EmployeeControllerTest {

	@Mock
	private EmployeeService employeeService;

	@InjectMocks
	private EmployeeController employeeController;

	private static Employee testEmployee;

	@BeforeAll
	public void setUp() {
		// Create a sample employee for testing
		testEmployee = new Employee();
		testEmployee.setEmpId(1);
		testEmployee.setFirstName("John");
		testEmployee.setLastName("Doe");
		testEmployee.setEmail("john@example.com");
	}

	@Test
	public void testSaveEmployee() {
		// Mock the repository behavior
		when(employeeService.saveEmployee(testEmployee)).thenReturn(testEmployee);

		// Call the service method
		ResponseEntity<Employee> savedEmployee = employeeController.saveEmployee(testEmployee);

		// Verify the result
		assertEquals(HttpStatus.CREATED, savedEmployee.getStatusCode());
		assertEquals(testEmployee, savedEmployee.getBody());
	}

	@Test
	public void testFindByEmployees_ExistingId() {
		// Mock the repository behavior
		when(employeeService.findByEmployees(1)).thenReturn(testEmployee);

		// Call the service method
		ResponseEntity<Object> foundEmployee = employeeController.getEmployeeById(1);

		// Verify the result
		assertEquals(HttpStatus.OK, foundEmployee.getStatusCode());
		assertEquals(testEmployee, foundEmployee.getBody());
	}

	@Test
	public void testFindByEmployees_NonExistingId() {
		// Mock the repository behavior
		when(employeeService.findByEmployees(1))
				.thenThrow(new ResourceNotFoundException("Employee not found with ID: 1"));

		// Verify that ResourceNotFoundException is thrown
		assertThrows(ResourceNotFoundException.class, () -> {
			employeeController.getEmployeeById(1);
		});
	}

	@Test
	public void testGetAllEmployees() {
		// Create a list of employees for testing
		List<Employee> employeesList = new ArrayList<>();
		employeesList.add(testEmployee);

		// Mock the repository behavior
		when(employeeService.getAllEmployees()).thenReturn(employeesList);

		// Call the service method
		ResponseEntity<List<Employee>> allEmployees = employeeController.getAllEmployees();

		// Verify the result
		assertEquals(HttpStatus.OK, allEmployees.getStatusCode());
		assertEquals(employeesList, allEmployees.getBody());
	}

	@Test
	public void testUpdateEmployee() {
		// Mock the repository behavior
		when(employeeService.updateEmployee(testEmployee)).thenReturn(testEmployee);

		// Call the service method
		ResponseEntity<Employee> updatedEmployee = employeeController.updateEmployee(testEmployee);

		// Verify the result
		assertEquals(HttpStatus.OK, updatedEmployee.getStatusCode());
		assertEquals(testEmployee, updatedEmployee.getBody());
	}

	@Test
	public void testDeleteEmployeeById() {
		int employeeId = 1;
		// Mock the repository behavior
		doNothing().when(employeeService).deleteEmployeeById(employeeId);

		// Call the service method
		ResponseEntity<Void> response = employeeController.deleteEmployeeById(employeeId);

		// Verify the result
		assertEquals(HttpStatus.OK, response.getStatusCode());
	}
}
