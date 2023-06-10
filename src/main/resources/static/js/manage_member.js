const memberButton = document.getElementById('memberButton');
memberButton.addEventListener('click', handleMemberClick);


function handleMemberClick() {
	fetch('member')
		.then(response => response.json())
		.then(data => {
			const tableHead = document.getElementById('tableHead');
			tableHead.innerHTML = '';


			const theadRow = document.createElement('tr');
			theadRow.innerHTML = `
	        <th>會員編號</th>
	        <th>會員名稱</th>
	        <th>會員信箱</th>
	        <th>會員電話</th>
	        <th>會員身分</th>
	        <th>會員圖片</th>
	        <th>停權狀態</th>
	        <th>狀態更新</th>
	      `;
			tableHead.appendChild(theadRow);

			const tableBody = document.getElementById('memberTableBody');
			tableBody.innerHTML = '';




			data.forEach(member => {
				var status;
				if (member.is_suspended == 0) {
					status = "停用";
				} else if (member.is_suspended == 1) {
					status = "啟用";
				}

				const row = document.createElement('tr');
				row.innerHTML = `
	          <td>${member.id}</td>
	          <td>${member.name}</td>
	          <td>${member.email}</td>
	          <td>${member.phone}</td>
	          <td>${member.identity}</td>
	          <td><img src="${member.image}" style="width: 200px; height: auto;"></td>
	          <td>${status}</td>
	          <td><button onclick="editMember(${member.id})"><i class="fas fa-edit"></i></button></td>
	        `;
				tableBody.appendChild(row);
			});
		})
		.catch(error => {
			console.error('發生錯誤:', error);
		})

}

function editMember(memberId) {
	console.log(memberId);
	const memberIdInput = document.getElementById('memberId');
	memberIdInput.value = memberId;

	const editMemberModal = new bootstrap.Modal(document.getElementById('editMemberModal'));
	editMemberModal.show();

}
function saveMemberChanges() {
	const memberId = document.getElementById('memberId').value;
	const isSuspended = document.getElementById('isSuspended').value;

	const data = {
		is_suspended: isSuspended
	};

	fetch(`member/${memberId}`, {
		method: 'PUT',
		headers: {
			'Content-Type': 'application/json'
		},
		body: JSON.stringify(data)
	})
		.then(response => {
			if (response.ok) {
				// 更新成功，你可以在此處進行任何相關處理

				// 隱藏 modal
				const modal = document.getElementById('editMemberModal');
				const bootstrapModal = new bootstrap.Modal(modal);
				bootstrapModal.hide();
		handleMemberClick()
			} else {
				throw new Error('更新失敗');
			}
		})
		.catch(error => {
			alert('更新失敗：' + error.message);
		});
}