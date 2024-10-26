<%@page import="com.evoting.evotingsystem.DAO.CandidateGroupPositionDAO"%>
<%@page import="com.evoting.evotingsystem.DAO.CandidateDAO"%>
<%@page import="com.evoting.evotingsystem.DAO.UserDAO"%>
<%@page import="com.evoting.evotingsystem.Handler.FactoryHandler"%>
<link rel="stylesheet" href="CSS/AdminNavbar.css" />
<script src="JavaScript/AdminNavbar.js"></script>
<link
  href="https://use.fontawesome.com/releases/v5.0.6/css/all.css"
  rel="stylesheet"
  />

<%
  UserDAO usersDao = new UserDAO(FactoryHandler.getFactory());
  CandidateDAO candidateDao = new CandidateDAO(FactoryHandler.getFactory());
  CandidateGroupPositionDAO candidateGroupDao = new CandidateGroupPositionDAO(FactoryHandler.getFactory());
  long totalUser = usersDao.countTotalRecords("UserDetails");
  long totalCandidate = candidateDao.countTotalRecords("Candidate");
  long totalGroup = candidateGroupDao.countTotalRecords("Group");
%>

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
          <strong> Bhuwan Raj Pandey</strong>
        </span>
        <span class="user-role">Administrator</span>
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
          <a href="AdminPortal.jsp" class="nav-link" role="button">
            <i class="fa fa-tachometer-alt"></i>
            <span>Dashboard</span>
            <span class="badge bg-warning">New</span>
          </a>
        </li>
        <li class="nav-item sidebar-dropdown">
          <a href="#" class="nav-link" role="button">
            <i class="fa fa-shopping-cart"></i>
            <span>Candidate</span>
            <span class="badge bg-danger">3</span>
          </a>
          <div class="sidebar-submenu">
            <a href="verifyCandidate.jsp" class="dropdown-item">Verify Candidate</a>
            <a href="manageCandidate.jsp" class="dropdown-item">Manage All</a>
          </div>
        </li>
        <li class="nav-item sidebar-dropdown">
          <a href="#" class="nav-link" role="button">
            <i class="far fa-gem"></i>
            <span>Users</span>
          </a>
          <div class="sidebar-submenu">
            <a href="newUser.jsp" class="dropdown-item">Add New</a>
            <a href="manageUsers.jsp" class="dropdown-item ">Manage All</a>
          </div>
        </li>
        <li class="nav-item ">
          <a href="Admin_Groups.jsp" class="nav-link" role="button">
            <i class="far fa-gem"></i>
            <span>Groups</span>
          </a>
        </li>
       

        <li class="nav-item header-menu">
          <span>Extra</span>
        </li>

        <li class="nav-item">
          <a href="Admin_Result.jsp" class="nav-link">
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




