
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Add New User</title>
    <%@include file="Components/Common_HTML_CSS_JS_LINKS.jsp" %>
  </head>
  <body>
    <div class="page-wrapper chiller-theme toggled">
      <%@include file="AdminNavbar.jsp" %>
      <main class="page-content">
        <div class="container text-white">
          <h1>Add New User</h1>
          <hr>
          <form method="post" action="NewUserServlet" onsubmit="return validateAndSubmitForm();">

            <div class="form-row">
              <div class="form-group col-md-6">
                <label for="fullName">Full Name</label>
                <input type="text" class="form-control" name="fullname" id="fullName" placeholder="Enter Full Name">
              </div>
              <div class="form-group col-md-6">
                <label for="ctznno">Citizenship Number</label>
                <input type="text" class="form-control" name="ctznno" id="ctznno" placeholder="Enter citizenship number">
              </div>
              <div class="form-group col-md-6">
                <label for="inputEmail4">Email</label>
                <input type="email" class="form-control" name="email" id="email" placeholder="Email">
              </div>
              <div class="form-group col-md-6">
                <label for="inputPassword4">Password</label>
                <input type="password" class="form-control" name="password" id="inputPassword4" placeholder="Password">
              </div>
              <div class="form-group col-md-6">
                <label for="inputrePassword4">Re-Password</label>
                <input type="password" class="form-control" name="repassword" id="inputrePassword4" placeholder="Re-Password">
              </div>
            </div>
            <div class="form-group col-md-6">
              <label for="inputAddress">Address</label>
              <input type="text" class="form-control" name="address" id="inputAddress" placeholder="1234 Main St">
            </div>         
            <div class="form-row">
              <div class="form-group col-md-6">
                <label for="inputCity">City</label>
                <input type="text" class="form-control" name="city" id="inputCity" placeholder="Enter city name">
              </div>
            </div>
            <div class="form-group col-md-6">
              <label for="phone">Phone</label>
              <input type="number" class="form-control" name="phone" id="phone" placeholder="Enter Phone Number">
            </div> 

            <button type="submit" class="btn btn-success mt-4">Sign in</button>
          </form>
        </div>
      </main>
    </div>

    <script src="JavaScript/newUser.js"></script>
    <script>
            // Function to display SweetAlert success message
            function showSuccessMessage(message) {
              sweetAlert('Success', message, 'success');
            }

// Function to display SweetAlert error message
            function showErrorMessage(message) {
              sweetAlert('Error', message, 'error');
            }

// Check if there are success or error messages from the server and display them
            const successMessage = `<%=request.getAttribute("successMessage")%>`;
            const errorMessage = `<%=request.getAttribute("errorMessage")%>`;

            if (successMessage !== "null") {
              showSuccessMessage(successMessage);
            }else if (errorMessage !== "null") {
              showErrorMessage(errorMessage);
            }else{
              
            }
    </script>
  </body>
</html>
