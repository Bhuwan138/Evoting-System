<%@page import="com.evoting.evotingsystem.Entity.Position"%>
<%@page import="java.util.List"%>
<%@page import="com.evoting.evotingsystem.Entity.Candidate"%>
<%@page import="com.evoting.evotingsystem.DAO.CandidateDAO"%>
<%@page import="com.evoting.evotingsystem.Handler.FactoryHandler"%>
<%@page import="com.evoting.evotingsystem.DAO.UserDAO"%>
<%@page import="com.evoting.evotingsystem.Entity.UserDetails"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>User Portal</title>
    <%@include file="Components/Common_HTML_CSS_JS_LINKS.jsp" %>
  </head>
  <body>
    <div class="page-wrapper chiller-theme toggled">
      <%@include file="User_Navbar.jsp" %>
      <%        
          CandidateDAO cd = new CandidateDAO(FactoryHandler.getFactory());
        List<Candidate> candidates = cd.getAllCandidatesByCity(user.getCity());
        List<String> positions = cd.getAllDistinctPositions();
      %>
      <main class="page-content">
        <div class="container text-white mt-3">

<!--          <div class="row">
            <div class="col">
              <div class="top d-flex mt-3">
                <form class="flex-grow-1" role="search">
                  <input
                    class="form-control me-2"
                    type="search"
                    placeholder="Search"
                    aria-label="Search"
                    />
                </form>
                <div class="ms-3">Time- 08:10:12</div>
              </div>
            </div>
          </div>

          <div class="row mb-3">
            <div class="col">
              <div class="top d-flex justify-content-between mt-3">
                <div class="city-dropdown">
                  <select class="form-select">
                    <option value="city1">City 1</option>
                    <option value="city2">City 2</option>
                    <option value="city3">City 3</option>
                     Add more options as needed 
                  </select>
                </div>
                <div>
                  <button class="btn btn-primary">My City</button>
                </div>
              </div>
            </div>
          </div>

          <div class="row">
            <div class="col">
              <table class="table">
                <thead>
                  <tr>
                    <th>Column 1</th>
                    <th>Column 2</th>
                    <th>Column 3</th>
                    <th>Column 4</th>
                  </tr>
                </thead>
                <tbody>
                  <tr>
                    <td>Row 1, Column 1</td>
                    <td>Row 1, Column 2</td>
                    <td>Row 1, Column 3</td>
                    <td>Row 1, Column 4</td>
                  </tr>
                  <tr>
                    <td>Row 2, Column 1</td>
                    <td>Row 2, Column 2</td>
                    <td>Row 2, Column 3</td>
                    <td>Row 2, Column 4</td>
                  </tr>
                  <tr>
                    <td>Row 3, Column 1</td>
                    <td>Row 3, Column 2</td>
                    <td>Row 3, Column 3</td>
                    <td>Row 3, Column 4</td>
                  </tr>
                </tbody>
              </table>
            </div>
          </div>-->

          <div class="row">
            <div class="col">
              <div class="d-flex justify-content-between align-items-center mt-3">
                <a href="User_result.jsp">
                  <div class="btn btn-outline-info text-white pointer-arrow">View Result</div>
                </a>
                <div class="btn btn-outline-warning text-white pointer-arrow" data-bs-toggle="modal" data-bs-target="#voteModal">
                  Vote Here
                </div>
                <div>
                  <a href="User_AddCandidate.jsp">
                    <button class="btn btn-success pointer-arrow">
                      Become a Candidate
                    </button>
                  </a>
                </div>
              </div>
            </div>
          </div>

          <!-- Add this code within your HTML body -->
          <div class="modal fade" id="voteModal" tabindex="-1" aria-labelledby="voteModalLabel" aria-hidden="true">
            <div class="modal-dialog modal-dialog-centered">
              <div class="modal-content">
                <div class="modal-header">
                  <h5 class="modal-title text-info" id="voteModalLabel">Voting for <%= user.getCity()%>'s Candidates</h5>
                  <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                </div>
                <div class="modal-body">
                  <div class="mb-3 col-9">
                    <label for="positionSelect" class="form-label">Select Position:</label>
                    <select class="form-control" id="positionSelect">
                      <% for (String p : positions) {%>
                      <option><%= p%></option>
                      <%}%>
                    </select>
                  </div>
                  <ul class="list-group" id="candidateList">
                    <!-- Candidate list items will be added here dynamically -->
                  </ul>
                </div>
              </div>
            </div>
          </div>

          <%@include file="footer.jsp" %>
        </div>
      </main>
    </div>

    <script src="JavaScript/UserPortal.js" ></script>

    <script>      
      const candidatesData = [
      <% for (Candidate c : candidates) {%>
        {
          ctznNo: "<%= c.getUserDetails().getCtznNo() %>",
          name: "<%= c.getUserDetails().getUserName()%>",
          party: "<%= c.getParty()%>",
          symbol: "<%= c.getSymbol()%>",
          position: "<%= c.getPosition()%>" // Add position to the data
        },
      <% }%>
      ];
      
      let ctznNo = "<%= user.getCtznNo() %>";

    </script>

  </body>
</html>
