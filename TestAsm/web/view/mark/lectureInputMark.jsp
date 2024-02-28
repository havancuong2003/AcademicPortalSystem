<%-- 
    Document   : lecture
    Created on : Feb 28, 2024, 1:47:39 PM
    Author     : -MSI-
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Grade Entry - Lecture</title>
        <style>
            /* CSS styles here */
            body {
                font-family: Arial, sans-serif;
            }
            table {
                width: 100%;
                border-collapse: collapse;
                margin-bottom: 20px;
            }
            table th, table td {
                border: 1px solid #ddd;
                padding: 8px;
                text-align: left;
            }
            table th {
                background-color: #f2f2f2;
            }
            table tr:nth-child(even) {
                background-color: #f2f2f2;
            }
            form {
                margin-bottom: 20px;
            }
            form label {
                display: block;
                margin-bottom: 5px;
            }
            form input[type="number"] {
                width: 80px;
                padding: 5px;
                border: 1px solid #ddd;
                border-radius: 4px;
            }
            form button {
                background-color: #4CAF50;
                color: white;
                border: none;
                padding: 10px 20px;
                text-align: center;
                text-decoration: none;
                display: inline-block;
                border-radius: 4px;
                cursor: pointer;
            }
            form button:hover {
                background-color: #45a049;
            }
        </style>
    </head>
    <body>
        <div class="container">

            <h1>Grade Entry</h1>
            <h2>Grade Item: Assignment 1</h2>
            <form action="#" method="post">
                <table>
                    <thead>
                        <tr>
                            <th>Student ID</th>
                            <th>Student Name</th>
                            <th>Grade</th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr>
                            <td>1</td>
                            <td>Student 1</td>
                            <td><input type="number" name="grade1" min="0" max="100" required></td>
                        </tr>
                        <tr>
                            <td>2</td>
                            <td>Student 2</td>
                            <td><input type="number" name="grade2" min="0" max="100" required></td>
                        </tr>
                        <tr>
                            <td>3</td>
                            <td>Student 3</td>
                            <td><input type="number" name="grade3" min="0" max="100" required></td>
                        </tr>
                        <!-- Add more rows for additional students as needed -->
                    </tbody>
                </table>
                <button type="submit">Submit Grades</button>
            </form>
        </div>
    </body>
</html>

