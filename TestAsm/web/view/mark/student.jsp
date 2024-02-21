<%-- 
    Document   : student
    Created on : Feb 18, 2024, 8:07:54 AM
    Author     : -MSI-
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <style>
         table {
             width: 100%;
             border-collapse: collapse;
         }
         table, th, td {
             border: 1px solid black;
             padding: 8px;
             text-align: left;
         }
        </style>
    </head>

<body>

<table>
    <thead>
        <tr>
            <th>GRADE CATEGORY</th>
            <th>GRADE ITEM</th>
            <th>WEIGHT</th>
            <th>VALUE</th>
            <th>COMMENT</th>
        </tr>
    </thead>
    <tbody>
        <tr>
            <td rowspan="1">Active Learning</td>
            <td>Active learning</td>
            <td>10.0 %</td>
            <td>9.8</td>
            <td></td>
        </tr>
      
        <tr>
            <td rowspan="2">Exercise</td>
            <td>Exercise 1</td>
            <td>5.0 %</td>
            <td>8.5</td>
            <td></td>
        </tr>
        <tr>
            <td>Exercise 2</td>
            <td>5.0 %</td>
            <td>7.5</td>
            <td></td>
        </tr>
      
        <tr>
            <td rowspan="1">Presentation</td>
            <td>Presentation</td>
            <td>10.0 %</td>
            <td>8.5</td>
            <td></td>
        </tr>
       
        <tr>
            <td rowspan="1">Project</td>
            <td>Project</td>
            <td>30.0 %</td>
            <td>7</td>
            <td></td>
        </tr>
    
        <tr>
            <td>Final Exam</td>
            <td>Final Exam</td>
            <td>40.0 %</td>
            <td></td>
            <td></td>
        </tr>
        <tr>
            <td>Final Exam Resit</td>
            <td>Final Exam Resit</td>
            <td>40.0 %</td>
            <td></td>
            <td></td>
        </tr>
        <tr>
            <td rowspan="2">COURSE TOTAL</td>
            <td>AVERAGE</td>
            <td>0.0</td>
            <td></td>
            <td></td>
        </tr>
        <tr>
            <td>STATUS</td>
            <td></td>
            <td></td>
            <td>NOT PASSED</td>
        </tr>
    </tbody>
</table>

</body>
</html>
