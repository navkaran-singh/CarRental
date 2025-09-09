<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
  <head>
    <meta charset="UTF-8" />
    <title>Car Rental - Home</title>
  </head>
  <body>
    <h1>Available Cars</h1>
    <p>
      <a href="/register">Register</a> |
      <a href="/login">Login</a>
    </p>
    <table border="1" cellpadding="6" cellspacing="0">
      <thead>
        <tr>
          <th>ID</th>
          <th>Make</th>
          <th>Model</th>
          <th>Year</th>
          <th>License Plate</th>
          <th>Available</th>
        </tr>
      </thead>
      <tbody>
        <c:forEach var="car" items="${cars}">
          <tr>
            <td><c:out value="${car.id}" /></td>
            <td><c:out value="${car.make}" /></td>
            <td><c:out value="${car.model}" /></td>
            <td><c:out value="${car.year}" /></td>
            <td><c:out value="${car.licensePlate}" /></td>
            <td><c:out value="${car.available}" /></td>
          </tr>
        </c:forEach>
      </tbody>
    </table>
  </body>
</html>
