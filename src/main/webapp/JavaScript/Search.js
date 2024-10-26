function updateTableWithJsonData(jsonData) {
  var $tableBody = $("#searchUser"); // Update this with the appropriate table body ID
  console.log("table body");
  console.log($tableBody);
  console.log(jsonData);
  // Clear the existing table rows
  $tableBody;

  // Parse JSON data
//  var users = JSON.parse(jsonData);
  var users = jsonData;

  // Loop through users and append rows to the table
  users.forEach(function (user) {
    var newRow = `
          <tr class="user-row">
            <td scope="row" class="col-2">${user.ctznNo}</td>
            <td class="col-3">${user.userName}</td>
            <td class="col-2">${user.city}</td>
            <td class="col-3">${user.address}</td>
            <td class="col-2 d-flex">
              <a href="editUser.jsp?userId=${user.ctznNo}" class="btn btn-outline-success pointer-arrow">
                <i class="bi bi-pencil-square"></i>
              </a>
              <form method="post" action="DeleteUserServlet">
                <input type="hidden" name="userId" value="${user.ctznNo}">
                <button type="submit" class="btn btn-outline-danger ms-2 pointer-arrow">
                  <i class="bi bi-trash-fill"></i>
                </button>
              </form>
            </td>
          </tr>
        `;

    $tableBody.append(newRow);
  });
}

