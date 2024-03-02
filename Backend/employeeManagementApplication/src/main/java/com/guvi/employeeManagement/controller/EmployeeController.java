package com.guvi.employeeManagement.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.guvi.employeeManagement.entity.Employee;
import com.guvi.employeeManagement.service.EmployeeService;

import jakarta.validation.Valid;

@RestController
@CrossOrigin(origins = "*")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @PreAuthorize("hasRole('ROLE_group1')")
    @PostMapping("/save")
    public ResponseEntity<Employee> saveEmployee(@Valid @RequestBody Employee employee) {
        Employee savedEmployee = employeeService.saveEmployee(employee);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedEmployee);
    }

    @PreAuthorize("hasRole('ROLE_group1')")
    @GetMapping("/get-employee/{empId}")
	public ResponseEntity<Object> getEmployeeById(@PathVariable Integer empId) {
		Employee employee = employeeService.findByEmployees(empId);
		return ResponseEntity.ok().body(employee);
	}
    
    @PreAuthorize("hasRole('ROLE_group1')")
    @GetMapping("/employees")
    public ResponseEntity<List<Employee>> getAllEmployees() {
        List<Employee> Employee = employeeService.getAllEmployees();
        return ResponseEntity.ok(Employee);
    }

    @PreAuthorize("hasRole('ROLE_group1')")
    @PutMapping("/update-employee")
    public ResponseEntity<Employee> updateEmployee(@Valid @RequestBody Employee emp) {
        Employee updatedEmployee = employeeService.updateEmployee(emp);
        return ResponseEntity.ok(updatedEmployee);
    }
   
    @PreAuthorize("hasRole('ROLE_group1')")
    @DeleteMapping("/delete-employee/{id}")
    public ResponseEntity<Void> deleteEmployeeById(@PathVariable Integer id) {
         employeeService.deleteEmployeeById(id);
        return ResponseEntity.ok().build();
    }

   
    
}
