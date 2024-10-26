
<%@page import="com.evoting.evotingsystem.DAO.UserDAO"%>
<%@page import="com.evoting.evotingsystem.Handler.FactoryHandler"%>
<%@page import="com.evoting.evotingsystem.Entity.UserDetails"%>
<%
  String ctznNo = (String)session.getAttribute("userId");
  UserDAO userDao = new UserDAO(FactoryHandler.getFactory());
  UserDetails user = userDao.getUserByCtznNo(ctznNo);
%>
<link rel="stylesheet" href="CSS/AdminNavbar.css" />
<script src="JavaScript/AdminNavbar.js"></script>
<link
  href="https://use.fontawesome.com/releases/v5.0.6/css/all.css"
  rel="stylesheet"
  />

<a id="show-sidebar" class="btn btn-sm btn-dark" href="#">
  <i class="fas fa-bars"></i>
</a>
<nav id="sidebar" class="sidebar-wrapper">
  <div class="sidebar-content">
    <div class="sidebar-brand">
      <a href="#">E-Voting System</a>
      <div id="close-sidebar">
        <i class="fas fa-times"></i>
      </div>
    </div>
    <div class="sidebar-header">
      <div class="user-pic">
        <img
          class="img-fluid rounded-circle"
          src="https://raw.githubusercontent.com/azouaoui-med/pro-sidebar-template/gh-pages/src/img/user.jpg"
          alt="User picture"
          />
      </div>
      <div class="user-info">
        <span class="user-name">
          
          <strong><%= user.getUserName() %></strong>
        </span>
        <span class="user-role">User</span>
        <span class="user-status">
          <i class="fa fa-circle"></i>
          <span>Online</span>
        </span>
      </div>
    </div>
    <!-- sidebar-header  -->

    <div class="sidebar-menu">
      <ul>
        <li class="nav-item header-menu">
          <span>General</span>
        </li>
        <li class="nav-item">
          <a href="UserPortal.jsp" class="nav-link" role="button">
            <i class="fa fa-tachometer-alt"></i>
            <span>Dashboard</span>
            <span class="badge bg-warning">New</span>
          </a>
        </li>
        <li class="nav-item">
          <a href="User_AddCandidate.jsp" class="nav-link" role="button">
            <i class="fa fa-shopping-cart"></i>
            <span>Become a Candidate</span>
          </a>         
        </li>



        <li class="nav-item header-menu">
          <span>Extra</span>
        </li>

        <li class="nav-item">
          <a href="User_result.jsp" class="nav-link">
            <i class="fa fa-calendar"></i>
            <span>View Result</span>
          </a>
        </li>

      </ul>
    </div>
    <!-- sidebar-menu  -->
  </div>
  <!-- sidebar-content  -->
  <div class="sidebar-footer">
    <a href="#">
      <i class="fa fa-bell"></i>
      <span class="badge bg-warning notification">3</span>
    </a>
    <a href="#">
      <i class="fa fa-envelope"></i>
      <span class="badge bg-success notification">7</span>
    </a>
    <a href="#">
      <i class="fa fa-cog"></i>
      <span class="badge-sonar"></span>
    </a>
    <a href="login.jsp">
      <i class="fa fa-power-off"></i>
    </a>
  </div>
</nav>
<!-- sidebar-wrapper  -->




