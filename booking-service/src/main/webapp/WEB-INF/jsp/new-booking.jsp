<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
  <head>
    <meta charset="UTF-8" />
    <title>New Booking</title>
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

        <h1>Create a Booking</h1>
        <div class="spacer"></div>

        <form method="post" action="/book" class="form">
          <div class="field">
            <label class="label">Car</label>
            <select name="carId" class="input" required>
              <c:forEach var="car" items="${cars}">
                <option value="${car.id}">
                  ${car.make} ${car.model} (${car.licensePlate})
                </option>
              </c:forEach>
            </select>
          </div>

          <div class="field">
            <label class="label">Start date</label>
            <input class="input" type="date" name="startDate" required />
          </div>

          <div class="field">
            <label class="label">End date</label>
            <input class="input" type="date" name="endDate" required />
          </div>

          <div class="actions">
            <button class="button" type="submit">Book car</button>
          </div>
        </form>
      </div>
    </div>
  </body>
</html>
