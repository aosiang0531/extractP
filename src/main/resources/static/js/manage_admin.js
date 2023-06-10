const adminButton = document.getElementById("adminButton");
adminButton.addEventListener("click", handleAdminClick);

function handleAdminClick() {
	fetch("admin")
		.then((response) => response.json())
		.then((data) => {
			const tableHead = document.getElementById("tableHead");
			tableHead.innerHTML = "";







			const theadRow = document.createElement("tr");
			theadRow.innerHTML = `
	        <th>管理員編號</th>
	        <th>管理員名稱</th>
	        <th>管理員信箱</th>
	        
	      `;
			tableHead.appendChild(theadRow);

			const tableBody = document.getElementById("memberTableBody");
			tableBody.innerHTML = "";

			data.forEach((admin) => {
				const row = document.createElement("tr");
				row.innerHTML = `
	          <td>${admin.id}</td>
	          <td>${admin.name}</td>
	          <td>${admin.email}</td>
	        
	        `;
				tableBody.appendChild(row);
			});
		})
		.catch((error) => {
			console.error("發生錯誤:", error);
		});
}
