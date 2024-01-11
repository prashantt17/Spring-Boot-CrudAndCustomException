package com.prashant.springbootcrud.Controller;

import java.util.List;

import javax.sound.midi.ControllerEventListener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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


	public static final Logger log= LoggerFactory.getLogger("Controller logs");

	@Autowired
	EmployeeServiceInterface employeeServiceInterface;

	@GetMapping("/getAllEmployee")
	public ResponseEntity<List<Employee>> getAllEmployee() {
		try {
			log.info("Inside Employee Repository");
			return employeeServiceInterface.getAllEmployee();
		}catch (Exception e){
			log.error(e.getMessage());
			e.printStackTrace();
			return ResponseEntity.badRequest().build();
		}
	}

	@PostMapping("/create")
	public ResponseEntity<?> createEmployee(@RequestBody Employee employee) {
		try {
			return ResponseEntity.ok(employeeServiceInterface.createEmployee(employee));
		} catch (BusinessException e) {
			ControllerException ce = new ControllerException(e.getErrorCode(), e.getErrorMessage());
			return new ResponseEntity<ControllerException>(ce, HttpStatus.BAD_REQUEST);
		}
	}
	@PostMapping("/createMultiple")
	public ResponseEntity<String> createEmployeeList(@RequestBody List<Employee> employeeList) {
		try {
			return employeeServiceInterface.createEmployee(employeeList);
		}catch (Exception e){
			log.error(e.getMessage());
			e.printStackTrace();
			return ResponseEntity.badRequest().build();
		}
	}

	@DeleteMapping("/deleteAll")
	public ResponseEntity<String> deleteEmployees() {
		try {
			return employeeServiceInterface.deleteEmployee();
		}catch (Exception e){
			log.error(e.getMessage());
			e.printStackTrace();
			return ResponseEntity.badRequest().build();
		}
	}

}
