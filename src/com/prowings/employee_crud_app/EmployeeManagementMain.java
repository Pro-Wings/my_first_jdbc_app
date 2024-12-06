package com.prowings.employee_crud_app;

public class EmployeeManagementMain {
	
	public static void main(String[] args) {
		
		EmployeeRepository repository = new EmployeeRepository();
		
		Employee emp1 = new Employee("Ram", "IT", 98000);
		Employee emp2 = new Employee("Sham", "HR", 32000);
		Employee emp3 = new Employee("Sachin", "IT", 76000);
		
		repository.addNewEmployee(emp1);
		
	}

}
