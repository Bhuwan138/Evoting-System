<%@page import="java.util.Iterator"%>
<%@page import="java.util.Map"%>
<%@page import="com.evoting.evotingsystem.Entity.Candidate"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Election Result</title>
    <%@include file="Components/Common_HTML_CSS_JS_LINKS.jsp" %>
  </head>
  <body>
    <div class="page-wrapper chiller-theme toggled">
      <%@include file="User_Navbar.jsp" %>



      <main class="page-content">
        <div class="container text-white mt-3">

          <h2>Result Page</h2>
          <hr />
          <table id="votingTable" class="table table-striped">
            <thead>
              <tr class="green-row">
                <th>Candidate Name</th>
                <th>City</th>
                <th>Party</th>
                <th>Position</th>
                <th>Total Vote</th>
                <th>Vote Percentage</th>
              </tr>
            </thead>

            <tbody>
              <tr>
                <td> Bhuwan </td>
                <td>City 1</td>
                <td>
                  <img src="Images/Candidate/" alt="party image" width="50" class="mx-3" />
                  Party 1</td>
                <td>Position 1</td>
                <td>550</td>
                <td>90%</td>
              </tr>
            </tbody>

          </table>
        </div>
      </main>
    </div>

    <script>
      $(document).ready(function () {
        $.ajax({
          type: "GET",
          url: "ElectionResultControllerServlet", 
          dataType: "json",
          success: function (data) {
            var tableBody = $("#votingTable tbody");
            tableBody.empty(); 

            $.each(data, function (index, voting) {
              // Append a new row to the table
              tableBody.append(
                      "<tr>" +
                      "<td>" + voting.candidateName + "</td>" +
                      "<td>" + voting.city + "</td>" +
                      "<td>" +
                      "<img src='Images/Candidate/" + voting.partySymbol + "' alt='party image' width='50' class='mx-3 rounded-circle' />" +
                      voting.partyName +
                      "</td>" +
                      "<td>" + voting.position + "</td>" +
                      "<td>" + voting.totalVote + "</td>" +
                      "<td>" + voting.votePercentage + "%</td>" +
                      "</tr>"
                      );
            });
          },
          error: function () {
            console.error("Error fetching voting details.");
          }
        });
      });
    </script>


  </body>
</html>
