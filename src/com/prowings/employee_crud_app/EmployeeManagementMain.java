package com.prowings.employee_crud_app;

import java.util.List;

public class EmployeeManagementMain {
	
	public static void main(String[] args) {
		
		EmployeeRepository repository = new EmployeeRepository();
		
		Employee emp1 = new Employee("Ram", "IT", 98000);
		Employee emp2 = new Employee("Sham", "HR", 32000);
		Employee emp3 = new Employee("Sachin", "IT", 76000);
		
//		repository.addNewEmployee(emp1);
//		repository.addNewEmployee(emp2);
//		repository.addNewEmployee(emp3);
		
		Employee fetchedEmployee = repository.getEmployeeById(1);
		System.out.println("Fetched Employee : "+fetchedEmployee);
		
		List<Employee> allEmployeeList = repository.getAllEmployees();
		System.out.println("All Employees : "+ allEmployeeList);
		
		repository.deleteEmployeeById(1);	
		
		//update employee
		
		Employee updatedEmployee = new Employee("Sachin08", "IT", 98000);
		
		System.out.println("Updated Employee record : "+repository.updateEmployee(3, updatedEmployee));
	}

}
