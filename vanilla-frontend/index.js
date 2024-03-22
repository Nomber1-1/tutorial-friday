function addRow() {
    const name = document.getElementById("name-input").value;
    const date = document.getElementById("date-input").value;
    const limit = document.getElementById("limit-input").value;
    const tr = document.createElement("tr");
    tr.innerHTML = `<td>${name}</td>\n<td>${date}</td>\n<td>${limit}</td>`;
    const tableBody = document.getElementById("event-table-body");
    tableBody.appendChild(tr);
}

window.addEventListener("DOMContentLoaded", function () {
    const button = document.getElementById("create-btn");
    button.addEventListener("click", addRow);
});
