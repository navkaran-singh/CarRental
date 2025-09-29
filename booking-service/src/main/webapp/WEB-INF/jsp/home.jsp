<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> <%@ taglib
prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
  <head>
    <meta charset="UTF-8" />
    <title>Car Rental - Home</title>
    <link rel="stylesheet" href="/css/styles.css" />
    <meta name="viewport" content="width=device-width, initial-scale=1" />
    <style>
      .table-wrap {
        overflow-x: auto;
        -webkit-overflow-scrolling: touch;
      }
      .action-cell {
        min-width: 360px;
      }
    </style>
  </head>
  <body>
    <div class="container">
      <div class="card">
        <div class="header">
          <div class="brand">Car Rental</div>
          <div class="nav">
            <a href="/">Home</a>
            <a href="/book/new">New Booking</a>
            <a href="/register">Register</a>
            <a href="/login">Login</a>
          </div>
        </div>

        <h1>Available Cars</h1>
        <div class="subtitle">
          Browse the latest available cars ready to book.
        </div>

        <c:if test="${not empty error}">
          <div class="badge red">${error}</div>
          <div class="spacer"></div>
        </c:if>
        <c:if test="${not empty success}">
          <div class="badge green">${success}</div>
          <div class="spacer"></div>
        </c:if>

        <div class="table-wrap">
          <table class="table">
            <thead>
              <tr>
                <th>Make</th>
                <th>Model</th>
                <th>Year</th>
                <th>License Plate</th>
                <th>Status</th>
                <th class="action-cell">Action</th>
              </tr>
            </thead>
            <tbody>
              <c:forEach var="car" items="${cars}">
                <tr>
                  <td><c:out value="${car.make}" /></td>
                  <td><c:out value="${car.model}" /></td>
                  <td><c:out value="${car.year}" /></td>
                  <td><c:out value="${car.licensePlate}" /></td>
                  <td>
                    <c:set var="bookedNow" value="${isBookedToday[car.id]}" />
                    <c:set
                      var="unavailable"
                      value="${isUnavailable[car.id] || !car.available || bookedNow}"
                    />
                    <c:choose>
                      <c:when test="${unavailable}">
                        <span class="badge red">Unavailable</span>
                        <c:choose>
                          <c:when
                            test="${not empty justBookedCarId && justBookedCarId == car.id}"
                          >
                            <span style="margin-left: 8px; color: #9ca3af">
                              Available from
                              <strong>${justBookedEndDate}</strong>
                            </span>
                          </c:when>
                          <c:when
                            test="${not empty availableFromDisplay[car.id]}"
                          >
                            <span style="margin-left: 8px; color: #9ca3af">
                              Available from
                              <strong>${availableFromDisplay[car.id]}</strong>
                            </span>
                          </c:when>
                          <c:otherwise>
                            <c:if test="${not empty nextAvailable[car.id]}">
                              <span style="margin-left: 8px; color: #9ca3af">
                                Available from
                                <strong>${nextAvailable[car.id]}</strong>
                              </span>
                            </c:if>
                          </c:otherwise>
                        </c:choose>
                      </c:when>
                      <c:otherwise>
                        <span class="badge green">Available</span>
                      </c:otherwise>
                    </c:choose>
                  </td>
                  <td>
                    <c:choose>
                      <c:when
                        test="${isUnavailable[car.id] || !car.available || isBookedToday[car.id]}"
                      >
                        <button class="button" disabled>Book</button>
                      </c:when>
                      <c:otherwise>
                        <form
                          method="post"
                          action="/book"
                          style="
                            display: flex;
                            gap: 12px;
                            align-items: center;
                            flex-wrap: wrap;
                          "
                        >
                          <input type="hidden" name="carId" value="${car.id}" />
                          <input
                            class="input"
                            type="date"
                            name="startDate"
                            placeholder="Start date"
                            required
                          />
                          <input
                            class="input"
                            type="date"
                            name="endDate"
                            placeholder="End date"
                            required
                          />
                          <button class="button" type="submit">Book</button>
                        </form>
                      </c:otherwise>
                    </c:choose>
                  </td>
                </tr>
              </c:forEach>
            </tbody>
          </table>
        </div>
      </div>
    </div>
  </body>
</html>
