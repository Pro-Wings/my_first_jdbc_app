package com.prowings.employee_crud_app;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class EmployeeRepository {

	private static final String dbUrl = "jdbc:mysql://localhost:3306/my-first-db";
	private static final String dbUname = "prowingsuser";
	private static final String dbUpwd = "prowingsuser";
	Connection connection = null;

	{
		System.out.println("inside instance block of EmployeeRepository!!");
		try {
			connection = DriverManager.getConnection(dbUrl, dbUname, dbUpwd);
			System.out.println("Connection to DB established successfully!!");

		} catch (SQLException e) {
			System.out.println("Some error occurred during connecting to DB!!" + e.getMessage());
			e.printStackTrace();
		} catch (Exception e) {
			System.out.println("Some error occurred!!" + e.getMessage());
		} 
	}

	// CRUD - R - read
	public List<Employee> getAllEmployees() {
		return null;
	}

	public Employee getEmployeeById(int id) {
		return null;
	}

	// CRUD - C - create
	public boolean addNewEmployee(Employee employee) {

		String insertEmployeeQuery = "INSERT INTO employee (name, department, salary) VALUES (?, ?, ?)";
		boolean result = false;
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(insertEmployeeQuery);

			preparedStatement.setString(1, employee.getName());
			preparedStatement.setString(2, employee.getDepartment());
			preparedStatement.setInt(3, employee.getSalary());

			int res = preparedStatement.executeUpdate();
			
			if (res>0) {
				System.out.println("Employee record added to DB successfully!!");
				result = true;
			}
			else
				System.out.println("Employee record not added to DB!!");

		} catch (SQLException e) {
			System.out.println("Some error while executing PreparedStmt for insertEmployee!!" + e.getMessage());
		}
		return result;

	}

	// CRUD - U - update
	public Employee updateEmployee(int id, Employee updatedEmployee) {
		return null;
	}

	// CRUD - U - update
	public boolean deleteEmployeeById(int id) {
		return true;
	}

}
