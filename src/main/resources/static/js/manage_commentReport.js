const commentButton = document.getElementById("commentButton");
commentButton.addEventListener("click", handleCommentClick);


function handleCommentClick() {
	fetch("article_comment_report") //"article_comment_report/reportStatus/0"
		.then((response) => response.json())
		.then((data) => {
			const tableHead = document.getElementById("tableHead");
			tableHead.innerHTML = "";


			const tableRow = document.createElement("tr");

			// Column for "待審核" button
			const reviewPendingButtonColumn = document.createElement("th");
			const reviewPendingButton = document.createElement("button");
			reviewPendingButton.textContent = "待審核";
			reviewPendingButton.addEventListener("click", () => {
				handleReviewButtonClick(0); // Set reviewStatus to 0 for pending
			});
			reviewPendingButtonColumn.appendChild(reviewPendingButton);
			tableRow.appendChild(reviewPendingButtonColumn);

			// Column for "未通過" button
			const reviewRejectedButtonColumn = document.createElement("th");
			const reviewRejectedButton = document.createElement("button");
			reviewRejectedButton.textContent = "未通過";
			reviewRejectedButton.addEventListener("click", () => {
				handleReviewButtonClick(2); // Set reviewStatus to 2 for rejected
			});
			reviewRejectedButtonColumn.appendChild(reviewRejectedButton);
			tableRow.appendChild(reviewRejectedButtonColumn);

			// Column for "通過" button
			const reviewApprovedButtonColumn = document.createElement("th");
			const reviewApprovedButton = document.createElement("button");
			reviewApprovedButton.textContent = "通過";
			reviewApprovedButton.addEventListener("click", () => {
				handleReviewButtonClick(1); // Set reviewStatus to 1 for approved
			});
			reviewApprovedButtonColumn.appendChild(reviewApprovedButton);
			tableRow.appendChild(reviewApprovedButtonColumn);

			tableHead.appendChild(tableRow);




			const theadRow = document.createElement("tr");
			theadRow.innerHTML = `
          <th>留言檢舉編號</th>
          <th>留言檢舉會員</th>
          <th>被檢舉留言編號</th>
          <th>留言檢舉內容</th>
          <th>留言檢舉時間</th>
          <th>留言檢舉狀態</th>
          <th>檢舉審核</th>
          <th>詳細內容</th>
        `;
			tableHead.appendChild(theadRow);

			const tableBody = document.getElementById("memberTableBody");
			tableBody.innerHTML = "";

			data.forEach((articleComment) => {

				var status;
				if (articleComment.articleCommentReportStatus == 0) {
					status = "待審核";
				} else if (articleComment.articleCommentReportStatus == 1) {
					status = "通過";
				} else if (articleComment.articleCommentReportStatus == 2) {
					status = "未通過";
				}


				const row = document.createElement("tr");
				row.innerHTML = `
            <td>${articleComment.id}</td>
            <td>${articleComment.member_id}</td>
            <td>${articleComment.article_comment_id}</td>
            <td>${articleComment.content}</td>
            <td>${new Date(articleComment.created_date)
						.toISOString()
						.slice(0, 19)
						.replace(/-/g, "/")
						.replace("T", " ")}</td>
            <td>${status}</td>
<td><button onclick="editComment(${articleComment.id})"><i class="fa-solid fa-pen-to-square fa-beat"></i></button></td>         
<td><button onclick="showCommentDetails(${articleComment.article_comment_id})"><i class="fa-solid fa-eye fa-spin"></i></button></td>
 `;

				tableBody.appendChild(row);
			});
		})
		.catch((error) => {
			console.error("發生錯誤:", error);

		});
}


function handleReviewButtonClick(reportStatus) {
	fetch(`article_comment_report/reportStatus/${reportStatus}`)
		.then((response) => response.json())
		.then((data) => {
			const tableBody = document.getElementById("memberTableBody");
			tableBody.innerHTML = "";

			data.forEach((articleComment) => {
				var status;
				if (articleComment.articleCommentReportStatus == 0) {
					status = "待審核";
				} else if (articleComment.articleCommentReportStatus == 1) {
					status = "通過";
				} else if (articleComment.articleCommentReportStatus == 2) {
					status = "未通過";
				}

				const row = document.createElement("tr");
				row.innerHTML = `
          <td>${articleComment.id}</td>
          <td>${articleComment.member_id}</td>
          <td>${articleComment.article_comment_id}</td>
          <td>${articleComment.content}</td>
          <td>${new Date(articleComment.created_date)
						.toISOString()
						.slice(0, 19)
						.replace(/-/g, "/")
						.replace("T", " ")}</td>
          <td>${status}</td>
          <td><button onclick="editComment(${articleComment.id})"><i class="fas fa-edit"></i></button></td>
          <td><button onclick="showCommentDetails(${articleComment.article_comment_id})"><i class="fa-solid fa-eye"></i></button></td>
        `;

				tableBody.appendChild(row);
			});
		})
		.catch((error) => {
			console.error("發生錯誤:", error);
		});
}


function editComment(commentId) {
	const modal = document.getElementById("editCommentModal");
	const commentIdField = document.getElementById("commentId");
	//	const commentStatusField = document.getElementById("commentStatus");

	// 填充欄位值
	commentIdField.value = commentId;
	//	commentStatusField.value = ""; // 清空欄位值，以便重新選擇

	// 顯示互動視窗
	const bootstrapModal = new bootstrap.Modal(modal);
	bootstrapModal.show();
}

function saveCommentChanges() {
	const commentIdField = document.getElementById("commentId");
	const commentStatusField = document.getElementById("commentStatus");

	const updatedCommentId = commentIdField.value;
	const updatedIsApproved = commentStatusField.value === "true";

	console.log(updatedIsApproved);
	const data = {
		article_comment_report_id: parseInt(updatedCommentId),
		is_approved: updatedIsApproved,
	};

	fetch("article_comment_report/review", {
		method: "PUT",
		headers: {
			"Content-Type": "application/json",
		},
		body: JSON.stringify(data),
	})
		.then((response) => response.json())
		.then((result) => {
			console.log(result);
			if (result.result === 1) {
				const tableBody = document.getElementById("memberTableBody");
				const rows = tableBody.getElementsByTagName("tr");

				for (let i = 0; i < rows.length; i++) {
					const row = rows[i];
					const idCell = row.querySelector("td:first-child");
					const statusCell = row.querySelector("td:nth-child(6)");

					if (idCell.textContent === updatedCommentId.toString()) {
						statusCell.textContent = updatedIsApproved ? "通過" : "未通過";
						console.log(statusCell.textContent);
						break;
					}
				}
			}

			handleCommentClick();
		})
		.catch((error) => {
			console.error("發生錯誤:", error);
		});

	const modal = document.getElementById("editCommentModal");
	const bootstrapModal = bootstrap.Modal.getInstance(modal);
	bootstrapModal.hide();
}


function showCommentDetails(commentId) {
	fetch(`article_comment/${commentId}`)
    .then((response) => response.json())
    .then((comment) => {
      const modal = document.getElementById("commentDetailsModal");
      const commentIdField = document.getElementById("commentIdField");
      const commentContentField = document.getElementById("commentContentField");

      commentIdField.textContent = comment.id;
      commentContentField.textContent = comment.content;
console.log(comment.id)
      // Show the modal
      const bootstrapModal = new bootstrap.Modal(modal);
      bootstrapModal.show();
    })
    .catch((error) => {
      console.error("發生錯誤:", error);
    });
}







