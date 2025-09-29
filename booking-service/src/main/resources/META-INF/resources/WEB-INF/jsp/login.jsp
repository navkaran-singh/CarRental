<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
  <head>
    <meta charset="UTF-8" />
    <title>Login</title>
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
          </nav>
        </div>
        <h1>Login</h1>
        <p class="subtitle">
          Welcome back. Enter your credentials to continue.
        </p>
        <form class="form" method="post" action="/login">
          <div class="field">
            <label class="label">Username</label>
            <input class="input" type="text" name="username" required />
          </div>
          <div class="field">
            <label class="label">Password</label>
            <input class="input" type="password" name="password" required />
          </div>
          <div class="actions">
            <button class="button" type="submit">Sign in</button>
          </div>
        </form>
        <div class="spacer"></div>
        <p class="center">
          No account? <a class="link" href="/register">Create one</a>
        </p>
      </div>
    </div>
  </body>
</html>
