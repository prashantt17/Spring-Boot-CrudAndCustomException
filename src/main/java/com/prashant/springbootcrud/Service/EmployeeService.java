package com.prashant.springbootcrud.Service;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.prashant.springbootcrud.ExceptionHandeling.BusinessException;
import com.prashant.springbootcrud.Model.Employee;
import com.prashant.springbootcrud.Repositories.EmployeeRepository;
import com.prashant.springbootcrud.Util.SessionFactoryHelper;

@Service
public class EmployeeService implements EmployeeServiceInterface {
	public static final Logger log = LoggerFactory.getLogger("Service Logs");
	@Autowired
	EmployeeRepository employeeRepository;

	@Override
	public ResponseEntity<Employee> createEmployee(Employee employee) {
		if (employee.getName().isEmpty() || employee.getName().length() == 0) {
			throw new BusinessException("600", "No name value provided for the emplyee Name!!");
		}
		try {
			return ResponseEntity.ok(employeeRepository.save(employee));
		}

		catch (IllegalArgumentException e) {
			throw new BusinessException("601", "No value provided for the emplyee!!");
		}

	}

	@Override
	public ResponseEntity<String> createEmployee(List<Employee> employeeList) {
		try {
			SessionFactory sessionFactory = SessionFactoryHelper.getSessionFactory();
			Session session = sessionFactory.openSession();
			session.beginTransaction();

			session.save(employeeList);
			session.getTransaction().commit();
			session.close();
			return ResponseEntity.ok("Employee created Successfully!!!");
		}catch (Exception e){
			log.error(e.getMessage());
			e.printStackTrace();
			return ResponseEntity.badRequest().build();
		}
	}

	@Override
	public ResponseEntity<String> deleteEmployee() {
		try {
			employeeRepository.deleteAll();
			return ResponseEntity.ok("Employee Deleted Successfully!!!");
		}catch (Exception e){
			log.error(e.getMessage());
			e.printStackTrace();
			return ResponseEntity.badRequest().build();
		}
	}

	@Override
	public ResponseEntity<List<Employee>> getAllEmployee() {
		return ResponseEntity.ok(employeeRepository.findAll());
	}
}
