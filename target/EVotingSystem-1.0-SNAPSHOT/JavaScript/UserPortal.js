function createCandidateListItem(candidate) {
  const listItem = document.createElement("li");
  listItem.classList.add("list-group-item");
  const candidateInfo = `
         <div class="d-flex justify-content-between align-items-center">
         <div>
         <img src="Images/Candidate/${candidate.symbol}" alt="Candidate Symbol" width="50" class="mr-2">
      ${candidate.name} (${candidate.party})
         </div>
         <button class="btn btn-success" onclick="confirmVote('${candidate.name}','${candidate.ctznNo}')">Vote</button>
         </div>
         `;
  listItem.innerHTML = candidateInfo;
  return listItem;
}

function populateCandidateList(selectedPosition) {
  const candidateList = document.getElementById("candidateList");
  candidateList.innerHTML = ""; // Clear existing list

  candidatesData.forEach((candidate) => {
    if (selectedPosition === "All" || candidate.position === selectedPosition) {
      const listItem = createCandidateListItem(candidate);
      candidateList.appendChild(listItem);
    }
  });
}

function confirmVote(candidateName, candidateCtznNo) {
  const confirmation = confirm(`Are you sure you want to vote for ${candidateName}?`);
  if (confirmation) {
    const data = {
      candidateCtznNumber: candidateCtznNo,
      userCtznNumber: ctznNo
    };
    // Send the data to the servlet using fetch
    fetch("VoteCandidateServlet", {
      method: "POST",
      headers: {
        "Content-Type": "application/json"
      },
      body: JSON.stringify(data)
    })
            .then(response => response.json())
            .then(data => {
              console.log(data);
              alert(data.message);
            })
            .catch(error => {
              console.log(error);
              alert(data.message);
            });
  }
}

const positionSelect = document.getElementById("positionSelect");

positionSelect.addEventListener("change", function () {
  const selectedPosition = positionSelect.value;
  populateCandidateList(selectedPosition);
});

populateCandidateList("All");

