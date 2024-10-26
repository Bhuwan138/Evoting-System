
<%@page import="java.util.List"%>
<%@page import="com.evoting.evotingsystem.Entity.UserDetails"%>
<%@page import="com.evoting.evotingsystem.Handler.FactoryHandler"%>
<%@page import="com.evoting.evotingsystem.DAO.UserDAO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%
  UserDAO userDao = new UserDAO(FactoryHandler.getFactory());
  List<UserDetails> users = userDao.getUserDetails();


%>

<!DOCTYPE html>
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Manage Users</title>
    <%@include file="Components/Common_HTML_CSS_JS_LINKS.jsp" %>
    <link rel="stylesheet" href="CSS/manageTable.css" />



  </head>
  <body>
    <div class="page-wrapper chiller-theme toggled">
      <%@include file="AdminNavbar.jsp" %>
      <main class="page-content">
        <div class="container text-light">

          <% if (request.getAttribute("successMessage") != null) {%>
          <div class="alert alert-success">
            <%= request.getAttribute("successMessage")%>
          </div>
          <% } %>

          <% if (request.getAttribute("successUser") != null) {%>
          <div class="alert alert-success">
            <%= request.getAttribute("successUser")%>
          </div>
          <% } %>
          <% if (request.getAttribute("errorUser") != null) {%>
          <div class="alert alert-success">
            <%= request.getAttribute("errorUser")%>
          </div>
          <% } %>

          <% if (request.getAttribute("errorMessage") != null) {%>
          <div class="alert alert-danger">
            <%= request.getAttribute("errorMessage")%>
          </div>
          <% } %>

          <script>
            setTimeout(function () {
              var successMessage = document.getElementById('successMessage');
              if (successMessage) {
                successMessage.style.display = 'none';
              }

              var errorMessage = document.getElementById('errorMessage');
              if (errorMessage) {
                errorMessage.style.display = 'none';
              }
            }, 3000);
          </script>





          <h1>Users</h1>
          <hr>
          <form class="d-flex" role="search">
            <input
              id="searchInput"
              class="form-control me-2"
              type="search"
              placeholder="Search"
              aria-label="Search"
              />

            <button class="btn btn-success" type="submit">
              Search
            </button>
          </form>


          <!--table-->
          <div class="table-responsive mt-4">
            <table class="table table-fixed">
              <thead>
                <tr>
                  <th scope="col" class="col-2">Citizenship Number</th>
                  <th scope="col" class="col-3">Name</th>
                  <th scope="col" class="col-2">City</th>
                  <th scope="col" class="col-3">Address</th>
                  <th scope="col" class="col-2">Actions</th>
                </tr>
              </thead>
              <tbody id="searchUser">
                <%
                  for (UserDetails user : users) {
                %>
                <tr  class="user-row">
                  <td scope="row" class="col-2 "><%= user.getCtznNo()%></td>
                  <td class="col-3"><%= user.getUserName()%></td>
                  <td class="col-2"><%= user.getCity()%></td>
                  <td class="col-3"><%= user.getAddress()%></td>
                  <td class="col-2 d-flex">
                    <!--                    <button type="button" class="btn btn-outline-success pointer-arrow btnEditUser">
                                          <i class="bi bi-pencil-square"></i>
                                        </button>-->
                    <a href="editUser.jsp?userId=<%= user.getCtznNo()%>" class="btn btn-outline-success pointer-arrow">
                      <i class="bi bi-pencil-square"></i>
                    </a>

                    <form method="post" action="DeleteUserServlet">
                      <input type="hidden" name="userId" value="<%= user.getCtznNo()%>">
                      <button type="submit" class="btn btn-outline-danger ms-2 pointer-arrow">
                        <i class="bi bi-trash-fill"></i>
                      </button>
                    </form> 
                  </td>
                </tr>
                <%
                  }
                %>
              </tbody>
            </table>
          </div>



        </div>
      </main>
    </div>
    
    <script>
      $(document).ready(function () {
        $('.btnEditUser').on('click', () => {
          $('#updateUser').modal('show');
          $tr = $(this).closest('tr');
          var data = $tr.children("td").map(() => {
            return $(this).text();
          }).get();
          console.log(data);
          $('#ctznNo').val(data[0]);
          $('#userName').val(data[1]);
          $('#address').val(data[2]);
          $('#city').val(data[3]);
          $('#email').val(data[4]);
          $('#mobileNo').val(data[5]);
        });
      });
    </script>

    <script>
      let jsonDataSearch;
      $(document).ready(function () {
        // Function to filter rows based on search input
        function filterRows(searchText) {
          var $rows = $(".user-row"); // Select all rows with the "user-row" class

          $rows.each(function () {
            var rowText = $(this).find(".user-name").text().toLowerCase(); // Change to the appropriate class
            var showRow = rowText.includes(searchText.toLowerCase()); // Use includes() to check for a partial match
            $(this).toggle(showRow);
          });
        }

        // Function to update the table with JSON data
        

        // Trigger filtering and AJAX request as you type into the search input
        $("#searchInput").on("input", function () {
          var searchText = $(this).val(); // Get the search input value
          filterRows(searchText); // Call the filterRows function with the search text

          // Send AJAX request to fetch search results from the servlet
          $.ajax({
            type: "GET",
            url: "SearchUserServlet",
            data: {searchInput: searchText},
            success: function (data) {
              jsonDataSearch = data;
              // Update the table with the JSON data
              updateTableWithJsonData(jsonDataSearch);
            },
            error: function () {
              // Handle errors if needed
            }
          });
        });
      });
    </script>

 <script src="JavaScript/Search.js"></script>


  </body>
</html>
