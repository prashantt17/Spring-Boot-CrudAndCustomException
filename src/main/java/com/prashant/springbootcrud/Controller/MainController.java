package com.prashant.springbootcrud.Controller;

import java.util.List;

import javax.sound.midi.ControllerEventListener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.prashant.springbootcrud.ExceptionHandeling.BusinessException;
import com.prashant.springbootcrud.ExceptionHandeling.ControllerException;
import com.prashant.springbootcrud.Model.Employee;
import com.prashant.springbootcrud.Repositories.EmployeeRepository;
import com.prashant.springbootcrud.Service.EmployeeServiceInterface;

@RestController
@RequestMapping("/rest")
public class MainController {

	@Autowired
	EmployeeServiceInterface employeeServiceInterface;

	@GetMapping("/getAllEmployee")
	public List<Employee> getAllEmployee() {
		System.out.println("Inside Employee Repository");
		return employeeServiceInterface.getAllEmployee();
	}

	@PostMapping("/create")
	public ResponseEntity<?> createEmployee(@RequestBody Employee employee) {
		try {
			Employee empCreated = employeeServiceInterface.createEmployee(employee);
			return new ResponseEntity<Employee>(empCreated, HttpStatus.CREATED);
		} catch (BusinessException e) {
			ControllerException ce = new ControllerException(e.getErrorCode(), e.getErrorMessage());
			return new ResponseEntity<ControllerException>(ce, HttpStatus.BAD_REQUEST);
		}

	}

	@PostMapping("/createMultiple")
	public void createEmployeeList(@RequestBody List<Employee> employeeList) {

		employeeServiceInterface.createEmployee(employeeList);
	}

	@DeleteMapping("/deleteAll")
	public void deleteEmployees() {
		employeeServiceInterface.deleteEmployee();
	}

}
