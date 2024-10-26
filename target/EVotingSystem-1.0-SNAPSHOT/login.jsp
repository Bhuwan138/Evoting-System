<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
  <head>
    <title>Login Page</title>
    <meta charset="UTF-8">
    <%@include file="Components/Common_HTML_CSS_JS_LINKS.jsp" %>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <!--links--> 
    <link href="CSS/login.css" rel="stylesheet">
    
    <!--scripts--> 
    <script type="text/javascript" src="JavaScript/login.js"></script>


  </head>
  <body>
    <div id="login">
      <div class="container">
        <div id="login-row" class="row justify-content-center align-items-center">
          <div id="login-column" class="col-md-6">
            <div id="login-box" class="col-md-12">
              <form id="login-form" class="form" action="" method="post">
                <h3 class="text-center text-info">Login</h3>
                <div class="mb-3">
                  <label for="username" class="form-label text-info">Username:</label>
                  <input type="text" name="username" id="userName" class="form-control">
                </div>
                <div class="mb-3">
                  <label for="password" class="form-label text-info">Password:</label>
                  <input type="password" name="password" id="password" class="form-control">
                </div>
                <div class="mb-3">
                  <button type="button" name="submit" class="btn btn-info btn-md" onclick="connectUser()" id="btnlogin">Submit</button>
                </div>
                <div id="register-link" class="text-end mt-1">
                  <a href="registration.jsp" class="text-info">Register here</a><br>
                  <span id="result"></span>
                </div>
              </form>
            </div>
          </div>
        </div>
      </div>
    </div>

  </body>
</html>
