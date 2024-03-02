package com.guvi.employeeManagement.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "employees")
@Getter
@Setter
@NoArgsConstructor
public class Employee {

	@Id
	@Column(name = "empId")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int empId;

	@NotNull(message = "First Name is required")
	@Column(name = "firstName")
	private String firstName;

	@NotNull(message = "Last Name is required")
	@Column(name = "lastName")
	private String lastName;

	@Email(message = "Invalid Email Address")
	@Column(name = "email")
	private String email;
}
