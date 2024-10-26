<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>JSP Page</title>
    <%@include file="Components/Common_HTML_CSS_JS_LINKS.jsp" %>
    
    <!--links-->
    <link href="CSS/registration.css" rel="stylesheet">
    
    <!--scripts-->
    <script src="JavaScript/registration.js"></script>
  </head>
  <body>
    <div class="container register">
      <div class="row">
        <div class="col-md-3 register-left">
          <img src="https://image.ibb.co/n7oTvU/logo_white.png" alt=""/>
          <h3>Welcome To</h3>
          <h2>E-Voting System</h2>
        </div>
        <div class="col-md-9 register-right">
          <ul class="nav nav-tabs nav-justified" id="myTab" role="tablist">
            <li class="nav-item">
              <a class="nav-link active" data-toggle="tab" role="tab" aria-controls="home"  href="#" onclick="redirectRegistration()">sign up</a></li>
            <li class="nav-item">
              <a class="nav-link" data-toggle="tab" role="tab" aria-controls="profile" href="login.jsp" onclick="redirectUser()">login</a></li>
          </ul>
          <div class="tab-content" id="myTabContent">
            <div class="tab-pane fade show active" id="home" role="tabpanel" aria-labelledby="home-tab">
              <h3 class="register-heading">Register Here</h3>
              <div class="row register-form">
                <div class="col-md-6">
                  <div class="form-group">
                    <input type="text" class="form-control" id="username" placeholder="Your Name *" value="" />
                  </div>
                  <div class="form-group">
                    <input type="text" class="form-control" id="city" placeholder="City Name *" value="" />
                  </div>
                  <div class="form-group">
                    <input type="password" id="password" class="form-control" placeholder="Password *" value="" />
                  </div>
                  <div class="form-group">
                    <input type="password" id="cpassword" class="form-control"  placeholder="Confirm Password *" value="" />
                  </div>

                </div>
                <div class="col-md-6">
                  <div class="form-group">
                    <input type="email" id="email" class="form-control" placeholder="Your Email *" value="" />
                  </div>
                  <div class="form-group">
                    <input type="text" id="mobile" name="txtEmpPhone" class="form-control" placeholder="Your Phone *" value="" />
                  </div>
                  <div class="form-group">
                    <input type="text" id="ctzn" name="txtCtzn" class="form-control" placeholder="Your Citizenship No *" value="" />

                  </div>
                  <div class="form-group">
                    <input type="text" id="address" class="form-control" placeholder="Enter Your Location *" value="" />
                  </div>
                  <input type="button" onclick="addUser()" class="btn btnRegister"  value="Register Here"/>

                </div>
                <span id="result"></span>
              </div>

            </div>

          </div>
        </div>
      </div>
    </div>
  </body>
</html>
