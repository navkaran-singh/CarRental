<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
  <head>
    <meta charset="UTF-8" />
    <title>Register</title>
    <link rel="stylesheet" href="/css/styles.css" />
  </head>
  <body>
    <div class="container">
      <div class="card">
        <div class="header">
          <div class="brand">Car Rental</div>
          <nav class="nav">
            <a href="/">Home</a>
            <a href="/login">Login</a>
          </nav>
        </div>
        <h1>Create account</h1>
        <p class="subtitle">It only takes a moment to get started.</p>
        <form class="form" method="post" action="/api/auth/register">
          <div class="field">
            <label class="label">Username</label>
            <input class="input" type="text" name="username" required />
          </div>
          <div class="field">
            <label class="label">Password</label>
            <input class="input" type="password" name="password" required />
          </div>
          <div class="actions">
            <button class="button" type="submit">Create account</button>
          </div>
        </form>
        <div class="spacer"></div>
        <p class="center">
          Already have an account? <a class="link" href="/login">Sign in</a>
        </p>
      </div>
    </div>
  </body>
</html>
