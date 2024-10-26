<%@page import="com.evoting.evotingsystem.Entity.UserDetails"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <meta name="viewport" content="width=device-width, initial-scale=1" />
    <meta
      name="description"
      content="Responsive sidebar template with sliding effect and dropdown menu based on bootstrap 3"
    />
    <title>Admin</title>

    <%@include file="Components/Common_HTML_CSS_JS_LINKS.jsp" %>

    <!--admin portal link-->
    <link rel="stylesheet" href="CSS/AdminPortal.css" />
  </head>

  <body>
    <div class="page-wrapper chiller-theme toggled">
      <%@include file="AdminNavbar.jsp" %>
      <main class="page-content">
        <div class="container-fluid">
          <!--FIRST ROW-->
          <div class="row">
            <div class="col bg-light-half d-flex justify-content-around py-4">
              <div class="row">
                <div
                  class="col-5 bg-success d-flex justify-content-center align-items-center"
                >
                  <i
                    class="bi bi-flag-fill display-3 text-center text-light"
                  ></i>
                </div>
                <div class="col-7 bg-light">
                  <div class="post-name h4 fs-bold">Total Users</div>
                  <div class="post-name h5 fs-bold"><%= totalUser %></div>
                </div>
              </div>

              <div class="row">
                <div
                  class="col-5 bg-success d-flex justify-content-center align-items-center"
                >
                  <i
                    class="bi bi-people-fill display-3 text-center text-light"
                  ></i>
                </div>
                <div class="col-7 bg-light">
                  <div class="post-name h4 fs-bold">Total Contestants</div>
                  <div class="post-name h5 fs-bold"><%= totalCandidate %></div>
                </div>
              </div>

              <div class="row">
                <div
                  class="col-5 bg-success d-flex justify-content-center align-items-center"
                >
                  <i
                    class="bi bi-ticket-detailed-fill display-3 text-center text-light"
                  ></i>
                </div>
                <div class="col-7 bg-light">
                  <div class="post-name h4 fs-bold">Total Groups</div>
                  <div class="post-name h5 fs-bold"><%= totalGroup %></div>
                </div>
              </div>
            </div>

           

          <!--row 2-->
<!--          <div class="row my-2">
            <div class="col bg-light-half">
              <h3><i class="bi bi-person-add"></i> Contestants</h3>
              <hr />
              <form class="d-flex" role="search">
                <input
                  class="form-control me-2"
                  type="search"
                  placeholder="Search"
                  aria-label="Search"
                />
                <button class="btn btn-outline-success" type="submit">
                  Search
                </button>
              </form>

              contestent table
              <table class="table table-hover mt-4">
                <thead>
                  <tr>
                    <th scope="col">Candidate ID</th>
                    <th scope="col">Candidate Name</th>
                    <th scope="col">City</th>
                    <th scope="col">Party Name</th>
                    <th scope="col">Party Symbol</th>
                  </tr>
                </thead>
                <tbody>
                  <tr>
                    <th scope="row">1</th>
                    <td>Bhuwan</td>
                    <td>Mahendranagar</td>
                    <td>Sotantra</td>
                    <td></td>
                  </tr>
                  <tr>
                    <th scope="row">2</th>
                    <td>Jacob</td>
                    <td>Thornton</td>
                    <td>@fat</td>
                  </tr>
                  <tr>
                    <th scope="row">3</th>
                    <td colspan="2">Larry the Bird</td>
                    <td>@twitter</td>
                  </tr>
                </tbody>
              </table>
            </div>
            

          <%@include file="footer.jsp" %>
        </div>-->
      </main>
    </div>
  </body>
</html>
