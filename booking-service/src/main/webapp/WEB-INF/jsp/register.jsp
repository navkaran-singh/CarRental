<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
  <head>
    <meta charset="UTF-8" />
    <title>Register</title>
  </head>
  <body>
    <h1>Register</h1>
    <form method="post" action="/api/auth/register">
      <div>
        <label>Username:</label>
        <input type="text" name="username" required />
      </div>
      <div>
        <label>Password:</label>
        <input type="password" name="password" required />
      </div>
      <div>
        <button type="submit">Register</button>
      </div>
    </form>
    <p><a href="/">Back to Home</a></p>
  </body>
</html>
