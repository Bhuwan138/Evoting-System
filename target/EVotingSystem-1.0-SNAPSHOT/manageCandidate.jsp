<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Manage Candidate</title>
    <link rel="stylesheet" href="CSS/manageTable.css" />
    <%@include file="Components/Common_HTML_CSS_JS_LINKS.jsp" %>
  </head>
  <body>
    <div class="page-wrapper chiller-theme toggled">
      <%@include file="AdminNavbar.jsp" %>
      <main class="page-content">
        <div class="container text-light">
          <h1>Manage Candidate</h1>
          <hr>
          <form class="d-flex" role="search">
            <input
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
                  <th scope="col" class="col-3">#</th>
                  <th scope="col" class="col-3">First</th>
                  <th scope="col" class="col-3">Last</th>
                  <th scope="col" class="col-3">Handle</th>
                </tr>
              </thead>
              <tbody>
                <tr>
                  <th scope="row" class="col-3">1</th>
                  <td class="col-3">Mark</td>
                  <td class="col-3">Otto</td>
                  <td class="col-3">@mdo</td>
                </tr>
                <tr>
                  <th scope="row" class="col-3">2</th>
                  <td class="col-3">Jacob</td>
                  <td class="col-3">Thornton</td>
                  <td class="col-3">@fat</td>
                </tr>

              </tbody>
            </table>
          </div>
        </div>
      </main>
    </div>
  </body>
</html>
