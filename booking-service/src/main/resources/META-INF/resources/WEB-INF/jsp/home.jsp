<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
  <head>
    <meta charset="UTF-8" />
    <title>Car Rental - Home</title>
    <link rel="stylesheet" href="/css/styles.css" />
  </head>
  <body>
    <div class="container">
      <div class="card">
        <div class="header">
          <div class="brand">Car Rental</div>
          <nav class="nav">
            <a href="/">Home</a>
            <a href="/register">Register</a>
            <a href="/login">Login</a>
          </nav>
        </div>
        <h1>Available Cars</h1>
        <p class="subtitle">Browse the latest available cars ready to book.</p>
        <table class="table">
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
                <td>
                  <c:choose>
                    <c:when test="${car.available}"
                      ><span class="badge green">Available</span></c:when
                    >
                    <c:otherwise
                      ><span class="badge red">Unavailable</span></c:otherwise
                    >
                  </c:choose>
                </td>
              </tr>
            </c:forEach>
          </tbody>
        </table>
      </div>
    </div>
  </body>
</html>
