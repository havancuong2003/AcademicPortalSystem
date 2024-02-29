<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="javax.servlet.http.*" %>
<!DOCTYPE html>
<html>
<head>
    <title>Dropdown List Retention Example</title>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script>
        $(document).ready(function() {
            $('#dropdown').change(function() {
                // Automatically submit the form when the select element changes
                $('#myForm').submit();
            });
        });
    </script>
</head>
<body>
    <h2>Dropdown List Retention Example</h2>
    
    <form id="myForm" action="drop" method="post">
        <label for="dropdown">Select an option:</label>
        <select id="dropdown" name="dropdown">
            <option value="option1" <c:if test="${sessionScope.selectedOption eq 'option1'}">selected</c:if>>Option 1</option>
            <option value="option2" <c:if test="${sessionScope.selectedOption eq 'option2'}">selected</c:if>>Option 2</option>
            <option value="option3" <c:if test="${sessionScope.selectedOption eq 'option3'}">selected</c:if>>Option 3</option>
        </select>
    </form>
</body>
</html>
