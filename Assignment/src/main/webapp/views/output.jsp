<%@page import="org.canara.models.Employee"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>EmployeeList</title>
		<style type="text/css">
			html,body{
				height: 100%;
				margin: 0;
				display: flex;
				justify-content: center;
				align-items: center;
				background-color: #A0DEFF;
			}
			.main{
				background-color: white;
				margin-left: auto;
				margin-right: auto;
				padding: 20px;
				border-radius: 10px;
				align-items: center;
				display: flex;
				flex-direction: column;
			}
			
			h1{
				display: flex;
				justify-content: center;
				color: #793FDF;
			}	
			table{
				border-collapse: collapse;
			}
			tr, th, td{
				border: 2px solid #793FDF;
				padding: 5px;
				align-items: center;
				text-align: center;
			}			
		</style>
	</head>
	<body>
		<div class="main">
			<h1>List Of Employees</h1>
		<table border="1">
            <thead>
                <tr>
                    <th>First Name</th>
                    <th>Last Name</th>
                    <th>Phone Number</th>
                </tr>
            </thead>
            <tbody>
                <%
                    ArrayList<Employee> employeeList = (ArrayList<Employee>) request.getAttribute("employeeList");
                    if (employeeList != null) {
                        for (Employee emp : employeeList) {
                %>
                <tr>
                    <td><%= emp.getFirstName() %></td>
                    <td><%= emp.getLastName() %></td>
                    <td><%= emp.getPhoneNo() %></td>
                </tr>
                <%
                        }
                    } else {
                %>
                <tr>
                    <td colspan="3">No employees found</td>
                </tr>
                <%
                    }
                %>
            </tbody>
        </table>
		</div>
	</body>
</html>