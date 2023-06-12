const articleButton = document.getElementById("articleButton");
articleButton.addEventListener("click", handleArticleClick);

function handleArticleClick() {


	fetch("article_report") //"article_report/reportStatus/0"
		.then((response) => response.json())
		.then((data) => {
			const tableHead = document.getElementById("tableHead");
			tableHead.innerHTML = "";



			const tableRow = document.createElement("tr");

			//						 Column for "未審核" button
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

			tableHead.appendChild(tableRow);


			// Column for "通過" button
			const reviewApprovedButtonColumn = document.createElement("th");
			const reviewApprovedButton = document.createElement("button");
			reviewApprovedButton.textContent = "通過";
			reviewApprovedButton.addEventListener("click", () => {
				handleReviewButtonClick(1); // Set reviewStatus to 1 for approved
			});
			reviewApprovedButtonColumn.appendChild(reviewApprovedButton);
			tableRow.appendChild(reviewApprovedButtonColumn);


			const theadRow = document.createElement("tr");
			theadRow.innerHTML = `
	        <th>文章檢舉編號</th>
	        <th>文章檢舉會員</th>
	        <th>被檢舉文章編號</th>
	        <th>文章檢舉內容</th>
	        <th>文章檢舉時間</th>
	        <th>文章檢舉狀態</th>
	        <th>檢舉審核</th>
	        <th>詳細內容</th>
	      `;
			tableHead.appendChild(theadRow);


			const tableBody = document.getElementById("memberTableBody");
			tableBody.innerHTML = "";





			data.forEach((article) => {

				var status;
				if (article.articleReportStatus == 0) {
					status = "待審核";
				} else if (article.articleReportStatus == 1) {
					status = "通過";
				} else if (article.articleReportStatus == 2) {
					status = "未通過";
				}

				const row = document.createElement("tr");
				row.innerHTML = `
	          <td>${article.id}</td>
	          <td>${article.member_id}</td>
	          <td>${article.article_id}</td>
	          <td>${article.content}</td>
	          <td>${new Date(article.created_date)
						.toISOString()
						.slice(0, 19)
						.replace(/-/g, "/")
						.replace("T", " ")}</td>
	          <td>${status}</td>
	          <td><button onclick="editArticle(${article.id
					})"><i class="fa-solid fa-pen-to-square fa-beat"></i></button></td>
<td><button onclick="showArticleDetails(${article.article_id})"><i class="fa-solid fa-eye fa-spin"></i></button></td>					
					
	        `;
				tableBody.appendChild(row);
			});
		})
		.catch((error) => {
			console.error("發生錯誤:", error);
		});

}


function handleReviewButtonClick(reviewStatus) {
	fetch(`article_report/reportStatus/${reviewStatus}`)
		.then((response) => response.json())
		.then((data) => {
			const tableBody = document.getElementById("memberTableBody");
			tableBody.innerHTML = "";

			data.forEach((article) => {
				var status;
				if (article.articleReportStatus == 0) {
					status = "待審核";
				} else if (article.articleReportStatus == 1) {
					status = "通過";
				} else if (article.articleReportStatus == 2) {
					status = "未通過";
				}
				const row = document.createElement("tr");
				row.innerHTML = `
          <td>${article.id}</td>
          <td>${article.member_id}</td>
          <td>${article.article_id}</td>
          <td>${article.content}</td>
          <td>${new Date(article.created_date)
						.toISOString()
						.slice(0, 19)
						.replace(/-/g, "/")
						.replace("T", " ")}</td>
          <td>${status}</td>
          <td><button onclick="editArticle(${article.id})"><i class="fas fa-edit"></i></button></td>
          <td><button onclick="showArticleDetails(${article.article_id})"><i class="fa-solid fa-eye"></i></button></td>
        `;
				tableBody.appendChild(row);
			});

		})
		.catch((error) => {
			console.error("發生錯誤:", error);
		});
}



function editArticle(articleId) {
	console.log(articleId);
	const modal = document.getElementById("editArticleModal");
	const articleIdField = document.getElementById("articleId");
	//	const articleStatusField = document.getElementById("articleStatus");

	articleIdField.value = articleId;
	//	articleStatusField.value = "";

	const bootstrapModal = new bootstrap.Modal(modal);
	bootstrapModal.show();
}

function saveArticleChanges() {
	const articleIdField = document.getElementById("articleId");
	const articleStatusField = document.getElementById("articleStatus");

	const updatedArticleId = articleIdField.value;
	const updatedIsApproved = articleStatusField.value === "true";

	const data = {
		article_report_id: updatedArticleId,
		is_approved: updatedIsApproved.toString(),
	};

	fetch("article_report/review", {
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

					if (idCell.textContent === updatedArticleId) {
						statusCell.textContent = updatedIsApproved ? "通過" : "未通過";
						break;
					}
				}
			}
			handleArticleClick();
		})

	// 隱藏互動視窗
	const modal = document.getElementById("editArticleModal");
	const bootstrapModal = bootstrap.Modal.getInstance(modal);
	bootstrapModal.hide();
}




function showArticleDetails(articleId) {
	const url = "article/" + articleId;

	fetch(url)
		.then((response) => response.json())
		.then((data) => {

			const modal = document.getElementById("articleDetailsModal");
			const articleIdField = document.getElementById("articleId_forView");
			const articleTitleField = document.getElementById("articleTitle");
			const articleContentField = document.getElementById("articleContent");
			//      const articleImageField = document.getElementById("articleImage");

			articleIdField.textContent = data.id;
			articleTitleField.textContent = data.articleTitle;
			articleContentField.textContent = data.articleContent;

			console.log(data.id);

			const bootstrapModal = new bootstrap.Modal(modal);
			bootstrapModal.show();
		})
		.catch((error) => {
			console.error("發生錯誤:", error);
		});
}
