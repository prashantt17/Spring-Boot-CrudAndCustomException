package com.prashant.springbootcrud.Service;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.prashant.springbootcrud.ExceptionHandeling.BusinessException;
import com.prashant.springbootcrud.Model.Employee;
import com.prashant.springbootcrud.Repositories.EmployeeRepository;
import com.prashant.springbootcrud.Util.SessionFactoryHelper;

@Service
public class EmployeeService implements EmployeeServiceInterface {

	@Autowired
	EmployeeRepository employeeRepository;

	@Override
	public Employee createEmployee(Employee employee) {
		if (employee.getName().isEmpty() || employee.getName().length() == 0) {
			throw new BusinessException("600", "No name value provided for the emplyee Name!!");
		}
		try {
			return employeeRepository.save(employee);
		}

		catch (IllegalArgumentException e) {
			throw new BusinessException("601", "No value provided for the emplyee!!");
		}

	}

	@Override
	public void createEmployee(List<Employee> employeeList) {

		SessionFactory sessionFactory = SessionFactoryHelper.getSessionFactory();
		Session session = sessionFactory.openSession();
		session.beginTransaction();

		session.save(employeeList);
		session.getTransaction().commit();
		session.close();

	}

	@Override
	public void deleteEmployee() {

		employeeRepository.deleteAll();
	}

	@Override
	public List<Employee> getAllEmployee() {
		return employeeRepository.findAll();
	}
}
