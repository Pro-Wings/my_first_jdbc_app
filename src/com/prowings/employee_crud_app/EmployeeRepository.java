package com.prowings.employee_crud_app;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
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
		String getEmployeeByIdQuery = "SELECT * FROM employee";
		List<Employee> allEmployees = new ArrayList<>();
		try {
			Statement statement = connection.createStatement();
			ResultSet rs = statement.executeQuery(getEmployeeByIdQuery);
			
			while(rs.next())
			{
				Employee fetchedEmployee = new Employee();
				fetchedEmployee.setId(rs.getInt(1));
				fetchedEmployee.setName(rs.getString(2));
				fetchedEmployee.setDepartment(rs.getString(3));
				fetchedEmployee.setSalary(rs.getInt(4));
				allEmployees.add(fetchedEmployee);
			}
			System.out.println("Employee record fetched successfully!!!");
			
		} catch (SQLException e) {
			System.out.println("Some error while executing PreparedStmt for getEmployeeById!!" + e.getMessage());
		}
		
		return allEmployees;
	}

	public Employee getEmployeeById(int id) {
		
		String getEmployeeByIdQuery = "SELECT * FROM employee WHERE id =";
		Employee fetchedEmployee = new Employee();
		try {
			Statement statement = connection.createStatement();
			ResultSet rs = statement.executeQuery(getEmployeeByIdQuery+id);
			
			while(rs.next())
			{
				fetchedEmployee.setId(rs.getInt(1));
				fetchedEmployee.setName(rs.getString(2));
				fetchedEmployee.setDepartment(rs.getString(3));
				fetchedEmployee.setSalary(rs.getInt(4));

			}
			System.out.println("Employee record fetched successfully!!!");
			
		} catch (SQLException e) {
			System.out.println("Some error while executing PreparedStmt for getEmployeeById!!" + e.getMessage());
		}
		
		return fetchedEmployee;
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

		String updateEmployeeQuery = "UPDATE employee SET name = ?, department = ?, salary = ? WHERE id = ?";
	    Employee updatedRecord = null;
	    Employee updatedEmployeeFromDB = null;
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(updateEmployeeQuery);
			
			preparedStatement.setString(1, updatedEmployee.getName());
			preparedStatement.setString(2, updatedEmployee.getDepartment());
			preparedStatement.setInt(3, updatedEmployee.getSalary());
			preparedStatement.setInt(4, id);
			
			int res = preparedStatement.executeUpdate();
			
			if (res>0) {
				System.out.println("Employee record updated successfully!!");
			}
			else
				System.out.println("Employee record not updated!!");
			
			updatedEmployeeFromDB = getEmployeeById(id);

		} catch (SQLException e) {
			System.out.println("Some error while deleting employee!!" + e.getMessage());
		}
		return updatedEmployeeFromDB;

	}

	// CRUD - U - update
	public boolean deleteEmployeeById(int id) {
		
		String deleteEmployeeQuery = "DELETE FROM employee WHERE id = ?";
		boolean result = false;
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(deleteEmployeeQuery);
			preparedStatement.setInt(1, id);
			
			int res = preparedStatement.executeUpdate();
			
			if (res>0) {
				System.out.println("Employee record deleted from DB successfully!!");
				result = true;
			}
			else
				System.out.println("Employee record not deleted from DB!!");

		} catch (SQLException e) {
			System.out.println("Some error while deleting employee!!" + e.getMessage());
		}
		return result;

	}

}
