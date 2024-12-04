package com.prowings.jdbc.demo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class JdbcDemo {

	public static void main(String[] args) {

		System.out.println("main started!!");
		Connection connection = null;
		List<Student> stdList = new ArrayList<>();
		try {
			// step-1 - Register the driver class. (Note-In latest JDK versions, this step
			// is optional)
			Class.forName("com.mysql.cj.jdbc.Driver");
			// step-2 - Create connection object
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/my-first-db",
					"prowingsuser", "prowingsuser");
			// step-3 - create Statement/PreparedStatement object
			Statement statement = connection.createStatement();
			// step-4 - Execute the query
			ResultSet rs = statement.executeQuery("SELECT * FROM student");

			while (rs.next()) {
				int id = rs.getInt(1);
				String name = rs.getString(2);
				String address = rs.getString(3);
				float marks = rs.getFloat(4);
				System.out.println("Fetched row from DB : " + "id = " + id + " name = " + name + " address = " + address
						+ " marks = " + marks);
				
				Student std = new Student(id, name, address, marks);
				stdList.add(std);
				
			}
		} catch (ClassNotFoundException e) {
			System.out.println("111Some error occurred during connecting to DB!!" + e.getMessage());
			e.printStackTrace();
		} catch (SQLException e) {
			System.out.println("222Some error occurred during connecting to DB!!" + e.getMessage());
			e.printStackTrace();
		} catch (Exception e) {
			System.out.println("Some error occurred!!" + e.getMessage());
		}
		finally {
			
			//step-5 - Close the connection
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		System.out.println("Students from DB : "+ stdList);
		
		System.out.println("main ended!!");
	}

}
