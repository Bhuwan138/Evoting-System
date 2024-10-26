<%@page import="com.evoting.evotingsystem.Entity.UserDetails"%>
<%@page import="com.evoting.evotingsystem.Handler.FactoryHandler"%>
<%@page import="com.evoting.evotingsystem.DAO.UserDAO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Edit User</title>
    <%@include file="Components/Common_HTML_CSS_JS_LINKS.jsp" %>
  </head>
  <body>
    <div class="page-wrapper chiller-theme toggled">
      <%@include file="AdminNavbar.jsp" %>
      <main class="page-content">
        <div class="container text-light">    <%
          UserDAO userDao = new UserDAO(FactoryHandler.getFactory());
          UserDetails user = userDao.getUserByCtznNo(request.getParameter("userId"));
          %>
          <h2>Edit User Details</h2>
          <hr />
          <form method="post" action="UpdateUserServlet">
            <input type="hidden" name="ctznNo" value="<%= user.getCtznNo()%>">
            <div class="form-group">
              <label for="userName">User Name</label>
              <input type="text" class="form-control" id="userName" name="userName" value="<%= user.getUserName()%>">
            </div>
            <div class="form-group">
              <label for="address">Address</label>
              <input type="text" class="form-control" id="address" name="address" value="<%= user.getAddress()%>">
            </div>
            <div class="form-group">
              <label for="city">City</label>
              <input type="text" class="form-control" id="city" name="city" value="<%= user.getCity()%>">
            </div>
            <div class="form-group">
              <label for="email">Email</label>
              <input type="email" class="form-control" id="email" name="email" value="<%= user.getEmail()%>">
            </div>
            <div class="form-group">
              <label for="mobileNo">Mobile No</label>
              <input type="text" class="form-control" id="mobileNo" name="mobileNo" value="<%= user.getMobileNo()%>">
            </div>
            <button type="submit" class="btn btn-primary mt-3">Update</button>
          </form>
        </div>
      </main>
    </div>
  </body>
</html>
