<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Add new Contestent</title>
    <%@include file="Components/Common_HTML_CSS_JS_LINKS.jsp" %>
  </head>
  <body>
    <div class="page-wrapper chiller-theme toggled">
      <%@include file="User_Navbar.jsp" %>
      <%        List<String> cities = userDao.getAllCities();
      %>
      <main class="page-content">
        <div class="container text-white">
          <h1>Add New Contestant</h1>
          <hr>
          <form method="post" action="AddCandidateServlet" enctype="multipart/form-data">

            <div class="form-row">
              <!--              <div class="form-group col-md-6">
                              <label for="candidateId">Candidate ID</label>
                              <input type="text" class="form-control" id="candidateId" placeholder="Enter Candidate ID">
                            </div>-->
              <div class="form-group col-md-6">
                <label for="citizenship">Citizenship Number</label>
                <input type="text" name="citizenship" class="form-control" id="citizenship" value="<%= user.getCtznNo()%>" placeholder="Enter Citizenship Number">
              </div>
              <div class="form-group col-md-6">
                <label for="party">Party</label>
                <input type="text" name="party"  class="form-control" id="party" placeholder="Enter Party Name">
              </div>

            </div>

            <div class="form-row">
              <div class="form-group col-md-6">
                <label for="citySelect">City</label>
                <select class="form-control" name="city" id="citySelect">
                  <%
                    for (String city : cities) {
                  %>
                  <option value="<%= city%>"><%= city%> </option>
                  <%
                    }
                  %>
                </select>
              </div>
            </div>
            <div class="form-row">
              <div class="form-group col-md-6">
                <label class="form-label" for="symbol">Symbol</label>
                <input type="file" name="symbol" class="form-control" id="symbol" placeholder="Enter Party Symbol" />
              </div>
            </div>
            <div class="form-group col-md-6">
              <label for="groupSelect">Select Group:</label>
              <select class="form-control" name="group" id="groupSelect">
                <option value="group1">Select Group </option>
              </select>
            </div>

            <!-- Position Selection -->
            <div class="form-group col-md-6">
              <label for="positionSelect">Select Position:</label>
              <select class="form-control" name="position" id="positionSelect">
                <option value="position1">Select Position</option>
              </select>
            </div>


            <button type="submit" class="btn btn-success text-white mt-4">Add Contestant</button>
          </form>
        </div>
      </main>
    </div>

    <script>
      $(document).ready(function () {
        // Fetch the list of groups and positions when the page loads
        $.ajax({
          type: "GET",
          url: "AdminGroupGetData",
          dataType: "json",
          success: function (data) {
            let groupData = data.groups;
            const groupSelect = $("#groupSelect");
            const positionSelect = $("#positionSelect");
            groupSelect.empty();


            groupData.forEach((group) => {
              groupSelect.append(new Option(group.groupName, group.groupName));

            });

            groupSelect.on("change", () => {
              let selectedGroup = groupSelect.val();

              const selectedGroupData = groupData.find((group) => {
                return group.groupName === selectedGroup;
              });
              positionSelect.empty();

              if (selectedGroupData) {
                selectedGroupData.positions.forEach((position) => {
                  positionSelect.append(new Option(position, position));
                });
              }
            });
          },
          error: function () {
            console.error("Error fetching groups and positions.");
          }
        });
      });


    </script>
    <% String successMessage = (String) session.getAttribute("message"); %>
    <% if (successMessage != null && !successMessage.isEmpty()) {%>
    sweetAlert("Success!", "<%= successMessage%>", "success");
    <% session.removeAttribute("successMessage"); %>
    <% }%>
  </body>
</html>
