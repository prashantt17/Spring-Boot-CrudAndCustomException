package com.prashant.springbootcrud.Service;

import java.util.List;

import com.prashant.springbootcrud.Model.Employee;

public interface EmployeeServiceInterface {

	public Employee createEmployee(Employee employee);

	public void createEmployee(List<Employee> employeeList);

	public void deleteEmployee();

	public List<Employee> getAllEmployee();

}
