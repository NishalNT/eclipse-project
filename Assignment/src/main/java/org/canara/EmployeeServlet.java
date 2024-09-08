package org.canara;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import org.canara.models.Employee;
import org.canara.service.DbConnection;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class EmployeeServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Connection connection = DbConnection.getDbConnection();
		ResultSet resultSet = null;
		ArrayList<Employee> listOfEmployee = new ArrayList<>();
		String getEmployeeList = "SELECT * FROM EMPLOYEE_NAME EN JOIN EMPLOYEE_PHONE EP WHERE EN.EMP_ID = EP.PHONE_ID;";
		
		try (PreparedStatement getEmployeeListStatement = connection.prepareStatement(getEmployeeList)) {
			resultSet = getEmployeeListStatement.executeQuery();
			System.out.println("Select Query Created");
			
			while (resultSet.next()) {
				Employee emp = new Employee(resultSet.getNString("FIRST_NAME"),
								   resultSet.getNString("LAST_NAME"),
								   resultSet.getNString("PHONE_NO")
						);
				System.out.println("\nFetched: " + emp);
				listOfEmployee.add(emp);
				System.out.println("Added to ArrayList");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (resultSet != null) resultSet.close();
				connection.setAutoCommit(true);
			} catch (Exception closeEx) {
				closeEx.printStackTrace();
			}
		}
		
		System.out.println("Array List is: " + listOfEmployee);
		req.setAttribute("employeeList", listOfEmployee);
		RequestDispatcher dispatcher = req.getRequestDispatcher("views/output.jsp");
		dispatcher.forward(req, resp);
		System.out.println("Dispatched to output");
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Employee emp = new Employee(req.getParameter("firstName"), req.getParameter("lastName"), req.getParameter("phoneNo"));
		Connection connection = DbConnection.getDbConnection();
		
		String insertQuery = "INSERT INTO EMPLOYEE_NAME(FIRST_NAME, LAST_NAME) VALUES(?, ?);";
		String insertQuery1 = "INSERT INTO EMPLOYEE_PHONE(PHONE_NO) VALUES(?);";
		
		try (PreparedStatement preparedStatement = connection.prepareStatement(insertQuery);
			 PreparedStatement preparedStatement1 = connection.prepareStatement(insertQuery1)) {
			preparedStatement.setString(1, emp.getFirstName());
			preparedStatement.setString(2, emp.getLastName());
			preparedStatement.executeUpdate();
			System.out.println("Name Entered");
			
			preparedStatement1.setString(1, emp.getPhoneNo());
			preparedStatement1.executeUpdate();
			System.out.println("Phone No. Entered");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		// Redirect to the GET method to avoid duplicate submissions
		resp.sendRedirect(req.getContextPath() + "/saveEmployeeDetails");
	}
}
