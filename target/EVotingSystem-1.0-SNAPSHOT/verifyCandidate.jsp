<%@page import="java.util.List"%>
<%@page import="com.evoting.evotingsystem.Entity.Candidate"%>
<%@page import="com.evoting.evotingsystem.Entity.Candidate"%>
<%@page import="com.evoting.evotingsystem.DAO.CandidateDAO"%>
<%@page import="com.evoting.evotingsystem.Handler.FactoryHandler"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
  CandidateDAO cd = new CandidateDAO(FactoryHandler.getFactory());
  List<Candidate> candidates = cd.getAllNonCandidates();
  List<Candidate> ar_candidate = cd.getAllCandidatesAcceptedRejected();
  
%>
<!DOCTYPE html>
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Verify Candidate</title>
    <%@include file="Components/Common_HTML_CSS_JS_LINKS.jsp" %>
  </head>
  <body>
    <div class="page-wrapper chiller-theme toggled">
      <%@include file="AdminNavbar.jsp" %>
      <main class="page-content">
        <div class="container text-white">
          <h1>Verify Candidate</h1>
          <hr>
          <table class="table table-bordered table-striped">
            <thead>
              <tr>
                <th>Citizenship Number</th>
                <th>Name</th>
                <th>City</th>
                <th>Party </th>
                <th>Action </th>
              </tr>
            </thead>
            <tbody>
              <%
                for (Candidate candidate : candidates) {
              %>
              <tr>
                <td><%= candidate.getUserDetails().getCtznNo()%></td>
                <td><%= candidate.getUserDetails().getUserName()%></td>
                <td><%= candidate.getCity()%></td>
                <td><img class="rounded-circle" src="Images/Candidate/<%= candidate.getSymbol()%>" alt="Party Symbol" class="mr-2" width="50"> <%= candidate.getParty()%></td>
                <td>
                  <i class="fas fa-check h2 btn btn-outline-success" onclick="acceptCandidate(<%= candidate.getCandidateId()%>)"></i>
                  <i class="fas fa-times h2 btn btn-outline-danger" onclick="declineCandidate(<%= candidate.getCandidateId()%>)"></i>
                </td>
              </tr>
              <%}%>
            </tbody>
          </table>

          <div class="row">
            <div class="col">
              <h2>Accepted and Rejected Candidates</h2>
              <table class="table table-bordered table-striped">
                <thead>
                  <tr>
                    <th>Citizenship Number</th>
                    <th>Name</th>
                    <th>City</th>
                    <th>Party</th>
                  </tr>
                </thead>
                <tbody>
                  <%
                    for (Candidate candidate : ar_candidate) {
                  %>
                  <tr class="<%= candidate.isIsCandidate() == 1 ? "table-success":"table-danger" %>">
                    <td><%= candidate.getUserDetails().getCtznNo()%></td>
                    <td><%= candidate.getUserDetails().getUserName()%></td>
                    <td><%= candidate.getCity()%></td>
                    <td>
                      <img class="rounded-circle" src="Images/Candidate/<%= candidate.getSymbol()%>" alt="Party Symbol" class="mr-2" width="50">
                      <%= candidate.getParty()%>
                    </td>
                    
                  </tr>
                  <%
                    }
                  %>
                </tbody>
              </table>
            </div>
          </div>
        </div>
      </main>
    </div>

    <script>
      function acceptCandidate(candidateId) {
        updateCandidateStatus(candidateId, 1); // 1 for accepted
      }

      function declineCandidate(candidateId) {
        updateCandidateStatus(candidateId, -1); // -1 for declined
      }

      function updateCandidateStatus(candidateId, status) {
        $.ajax({
          type: "POST",
          url: "UpdateCandidateStatusServlet",
          data: {
            candidateId: candidateId,
            status: status
          },
          success: function (response) {
            if (response === "success") {
              // Update the UI or show a success message
              alert("Candidate status updated successfully.");
            } else {
              // Handle the case where the status was not updated
              alert("Candidate status could not be updated.");
            }
          },
          error: function () {
            // Handle errors here
            alert("Error occurred while updating candidate status.");
          }
        });
      }
    </script>



  </body>
</html>
