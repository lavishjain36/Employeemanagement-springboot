package com.guvi.employeeManagement.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.guvi.employeeManagement.entity.Employee;
import com.guvi.employeeManagement.exception.ResourceNotFoundException;
import com.guvi.employeeManagement.repository.EmployeesRepository;

@ExtendWith(MockitoExtension.class)
@TestInstance(Lifecycle.PER_CLASS)
public class EmployeeServiceTest {

	@Mock
	private EmployeesRepository employeeRepository;

	@InjectMocks
	private EmployeeService employeeService;

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
		when(employeeRepository.save(testEmployee)).thenReturn(testEmployee);

		// Call the service method
		Employee savedEmployee = employeeService.saveEmployee(testEmployee);

		// Verify the result
		assertEquals(testEmployee, savedEmployee);
	}

	@Test
	public void testFindByEmployees_ExistingId() {
		// Mock the repository behavior
		when(employeeRepository.findById(1)).thenReturn(Optional.of(testEmployee));

		// Call the service method
		Employee foundEmployee = employeeService.findByEmployees(1);

		// Verify the result
		assertEquals(testEmployee, foundEmployee);
	}

	@Test
	public void testFindByEmployees_NonExistingId() {
		// Mock the repository behavior
		when(employeeRepository.findById(1)).thenReturn(Optional.empty());

		// Verify that ResourceNotFoundException is thrown
		assertThrows(ResourceNotFoundException.class, () -> {
			employeeService.findByEmployees(1);
		});
	}

	@Test
	public void testGetAllEmployees() {
		// Create a list of employees for testing
		List<Employee> employeesList = new ArrayList<>();
		employeesList.add(testEmployee);

		// Mock the repository behavior
		when(employeeRepository.findAll()).thenReturn(employeesList);

		// Call the service method
		List<Employee> allEmployees = employeeService.getAllEmployees();

		// Verify the result
		assertEquals(employeesList, allEmployees);
	}

	@Test
	public void testUpdateEmployee() {
		// Mock the repository behavior
		when(employeeRepository.findById(1)).thenReturn(Optional.of(testEmployee));
		when(employeeRepository.save(testEmployee)).thenReturn(testEmployee);

		// Update the employee
		testEmployee.setFirstName("UpdatedFirstName");

		// Call the service method
		Employee updatedEmployee = employeeService.updateEmployee(testEmployee);

		// Verify the result
		assertEquals(testEmployee, updatedEmployee);
	}

	@Test
	public void testDeleteEmployeeById() {
		int employeeId = 1;
		// Mock the repository behavior
		doNothing().when(employeeRepository).deleteById(employeeId);

		// Call the service method
		employeeService.deleteEmployeeById(employeeId);

		// Verify the result
		verify(employeeRepository, times(1)).deleteById(employeeId);
	}
}
