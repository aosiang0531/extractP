/* 點擊跳轉 */
		$(document).ready(function() {
			$('#logoutButton').click(function(e) {
				e.stopPropagation();
				e.preventDefault(); // 防止表單提交
				window.location.href = 'member_login.html'; // 替換為個人頁面的URL
			});
			
			$('#editinfo').click(function(e) {
				e.stopPropagation();
				e.preventDefault(); // 防止表單提交
				window.location.href = 'member_editinfo.html'; // 替換為個人頁面的URL
			});

			$('#editpassword').click(function(e) {
				e.stopPropagation();
				e.preventDefault(); // 防止表單提交
				window.location.href = 'member_editpassword.html'; // 替換為個人頁面的URL
			});
			
			$('#managearticle').click(function(e) {
				e.stopPropagation();
				e.preventDefault(); // 防止表單提交
				window.location.href = 'member_myarticle.html'; // 替換為個人頁面的URL
			});
			
//			$('#manageshop').click(function(e) {
//				e.stopPropagation();
//				e.preventDefault(); // 防止表單提交
//				window.location.href = 'member_myarticle.html'; // 替換為個人頁面的URL
//			});
//			
//			$('#manageorder').click(function(e) {
//				e.stopPropagation();
//				e.preventDefault(); // 防止表單提交
//				window.location.href = 'member_myarticle.html'; // 替換為個人頁面的URL
//			});
//			
//			$('#managead').click(function(e) {
//				e.stopPropagation();
//				e.preventDefault(); // 防止表單提交
//				window.location.href = 'member_myarticle.html'; // 替換為個人頁面的URL
//			});

		});




/* GET會員 */
		$(document).ready(function() {
			const img = document.querySelector("#img");

			// 頁面加載時執行的操作
			var memberId = 17; // 請替換為真實的會員ID

			// 構建後端端點URL並替換會員ID
			var url = "member/{id}".replace("{id}", memberId);

			// 發送AJAX請求獲取會員資料
			$.ajax({
				url : url,
				type : "GET",
				success : function(response) {

					// 顯示會員圖片
					img.src = "data:image/jpeg;base64," + response.image;

					const html = `<h2 class="user-name">${response.name}</h2>`;
					document.querySelector("#name").innerHTML = html;

					//$("#name").innerText = response.name;

				},
				error : function(error) {
					console.log("獲取會員資料失敗");
				}
			});
		});




