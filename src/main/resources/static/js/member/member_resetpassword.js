/*GET會員*/
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



/* PUT更改密碼 */
		// 更新會員資料
		$('#update').on('click', function(e) {
			e.stopPropagation();

			var newPassword = $("input[name='newPassword']").val();
			var checkPassword = $("input[name='checkPassword']").val();

			if (checkPassword != newPassword) {
				msg.textContent = '密碼與確認密碼需一致';
				return;
			}
			var memberId = 17;
			var url = "member/{id}".replace("{id}", memberId);

			// 構建要發送的數據
		    var data = {
		      password: newPassword

		    };

		    // 發送AJAX請求
		    $.ajax({
		      url: url,
		      type: "PUT",
		      data: JSON.stringify(data),
		      contentType: "application/json",
		      success: function(response) {
		        // 更新成功後的處理邏輯
		        console.log("更新成功");
		      },
		      error: function(error) {
		        // 更新失敗後的處理邏輯
		        console.log("更新失敗");
		      }
		    });
		});
