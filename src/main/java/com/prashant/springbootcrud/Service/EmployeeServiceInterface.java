package com.prashant.springbootcrud.Service;

import java.util.List;

import com.prashant.springbootcrud.Model.Employee;
import org.springframework.http.ResponseEntity;

public interface EmployeeServiceInterface {

	public ResponseEntity<Employee> createEmployee(Employee employee);

	public ResponseEntity<String> createEmployee(List<Employee> employeeList);

	public ResponseEntity<String> deleteEmployee();

	public ResponseEntity<List<Employee>> getAllEmployee();

}
