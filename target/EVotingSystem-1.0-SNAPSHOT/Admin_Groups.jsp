<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <title>Manage Groups</title>
    <%@include file="Components/Common_HTML_CSS_JS_LINKS.jsp" %>
    <style>
      .list-group-item {
        margin-bottom: 10px; /* Adjust the margin as needed */
      }
      .group-form {
        margin-top: 10px; /* Adjust the margin as needed */
      }
    </style>
  </head>
  <body>
    <div class="page-wrapper chiller-theme toggled">
      <%@include file="AdminNavbar.jsp" %>
      <main class="page-content">
        <div class="container-fluid text-white">
          <div class="row">
            <div class="col">
              <h1>Group</h1>
              <hr />
            </div>
          </div>
          <div class="row">
            <div class="col d-flex flex-row-reverse">
              <button class="btn btn-success float-right" id="addGroupBtn">
                Add New Group
              </button>
            </div>
          </div>
          <ul class="row mt-3" id="groupsContainer">
            <!-- Existing groups will be added here -->
          </ul>
        </div>
      </main>
    </div>

    <ul class="list-group row mt-3" id="groupsContainer">
      <!-- Existing groups will be added here -->
    </ul>

    <script src="JavaScript/Admin.js"></script>


  </body>
</html>
