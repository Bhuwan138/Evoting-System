function createNewGroup(groupNames) {
  // Create a new list item for the group
  let groupName = groupNames.replace(/\s/g, "");
  const listItem = document.createElement("li");
  listItem.classList.add(
          "list-group-item",
          "d-flex",
          "justify-content-between",
          "align-items-center"
          );

  // Create a button for the group
  const button = document.createElement("button");
  button.classList.add("btn", "btn-warning", "toggle-button");
  button.type = "button";
  console.log("Creating grp :" + groupName);
  button.id = groupName; 
  button.innerText = groupName;

  // Create a Bootstrap icon (plus button)
  const plusIcon = document.createElement("span");
  plusIcon.classList.add(
          "bi",
          "bi-plus-circle",
          "ms-2",
          "toggle-form-button"
          );
  plusIcon.style.cursor = "pointer";

  // Create a form for entering position names
  const form = document.createElement("form");
  form.classList.add("group-form");
  form.style.display = "none"; // Initially hide the form
  form.innerHTML = `
                <div class="input-group">
                    <input type="text" class="form-control" placeholder="Enter position name">
                    <button type="submit" class="btn btn-primary">Add</button>
                </div>
            `;

  // Create a list for displaying position names
  const positionList = document.createElement("ul");
  positionList.classList.add("list-group", "mt-2");

  // Append the button, plus icon, form, and position list to the list item
  listItem.appendChild(button);
  listItem.appendChild(plusIcon);
  listItem.appendChild(form);
  listItem.appendChild(positionList);

  // Event listener for toggling the form when the plus icon is clicked
  plusIcon.addEventListener("click", () => {
    form.style.display = form.style.display === "none" ? "block" : "none";
  });

  // Event listener for form submission
  form.addEventListener("submit", (e) => {
    e.preventDefault();
    const positionInput = form.querySelector("input");
    const positionName = positionInput.value.trim();
    if (positionName !== "") {
      // Create a new position list item
      const positionItem = document.createElement("li");
      positionItem.classList.add("list-group-item");
      positionItem.textContent = positionName;

      // Append the position item to the position list
      positionList.appendChild(positionItem);

      // Clear the input field
      positionInput.value = "";

      // Create an object to represent the group data
      const groupData = {
        groupName: groupName,
        positions: Array.from(positionList.children).map(
                (item) => item.textContent
        ),
      };

      // Send the group data to the servlet using JSON
      sendGroupDataToServlet(groupData);
    }
  }
  );
  // Event listener for collapsing other groups when one is opened
  button.addEventListener("click", () => {
    button.classList.toggle("active");
    const allButtons = document.querySelectorAll(".btn.btn-warning");
    allButtons.forEach((btn) => {
      if (btn !== button) {
        const targetId = btn.id;
        console.log(targetId);
        const target = document.querySelector(`#${targetId}`);
        target.classList.remove("show");
      }
    });
  });

  // Append the new group (list item) to the list container
  const listContainer = document.getElementById("groupsContainer");
  listContainer.appendChild(listItem);
}


function fetchAndDisplayGroups() {
  fetch("AdminGroupGetData", {
    method: "GET",
    headers: {
      "Content-Type": "application/json",
    },
  })
          .then((response) => response.json())
          .then((data) => {
            let datas = JSON.stringify(data);
            let totalData = JSON.parse(datas).groups;
            console.log(totalData);
            const groupsContainer = document.getElementById("groupsContainer");
            groupsContainer.innerHTML = ""; // Clear existing content


            totalData.forEach((group) => {
              let names = group.groupName;
              let name = names.replace(/\s/g, "");
              createNewGroup(name);
              let el = document.querySelector(`#${name}`);
              let parentEl = el.parentElement;
              const childElement = parentEl.querySelector("ul.list-group");
              const positionList = childElement;
              console.log("Position List : " + positionList);
              group.positions.forEach((position) => {
                console.log("Position" + position);
                const positionItem = document.createElement("li");
                positionItem.classList.add("list-group-item");
                positionItem.textContent = position;
                positionList.appendChild(positionItem);
              });

            });

          })
          .catch((error) => {
            console.error("Error:", error);
          });
}

function sendGroupDataToServlet(groupData) {
  fetch("AdminGroupServlet", {
    method: "POST",
    headers: {
      "Content-Type": "application/json",
    },
    body: JSON.stringify(groupData),
  })
          .then((response) => response.json())
          .then((data) => {
            // Handle the response from the servlet (e.g., success or error message)
            console.log(data);
          })
          .catch((error) => {
            console.error("Error:", error);
          });
}

// Event listener for the "Add New Group" button
document.getElementById("addGroupBtn").addEventListener("click", () => {
  // Prompt the user for a group name
  const groupName = prompt("Enter a group name:");
  if (groupName !== null && groupName.trim() !== "") {
    // Create a new group (list item) with the entered name
    createNewGroup(groupName);
  }
});

window.addEventListener("load", fetchAndDisplayGroups);


