<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
  <head>
    <meta charset="UTF-8" />
    <title>Add Car</title>
    <link rel="stylesheet" href="/css/styles.css" />
    <meta name="viewport" content="width=device-width, initial-scale=1" />
  </head>
  <body>
    <div class="container">
      <div class="card">
        <div class="header">
          <div class="brand">Car Rental</div>
          <div class="nav">
            <a href="/">Home</a>
          </div>
        </div>

        <h1>Add a Car</h1>
        <div class="spacer"></div>

        <c:if test="${not empty error}">
          <div class="badge red">${error}</div>
          <div class="spacer"></div>
        </c:if>

        <form method="post" action="/cars" class="form">
          <div class="field">
            <label class="label">Make</label>
            <input class="input" type="text" name="make" required />
          </div>
          <div class="field">
            <label class="label">Model</label>
            <input class="input" type="text" name="model" required />
          </div>
          <div class="field">
            <label class="label">Year</label>
            <input
              class="input"
              type="number"
              name="year"
              min="1900"
              max="2100"
              required
            />
          </div>
          <div class="field">
            <label class="label">License Plate</label>
            <input class="input" type="text" name="licensePlate" required />
          </div>
          <div class="actions">
            <button class="button" type="submit">Add Car</button>
          </div>
        </form>
      </div>
    </div>
  </body>
</html>
