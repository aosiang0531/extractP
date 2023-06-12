/* 登入資訊 */
const resetURL = new URLSearchParams(window.location.search);
const memberId = resetURL.get("memberId");
GET();
PUT();

console.log(memberId);
/*GET會員*/
function GET() {
	$(document).ready(function() {
		const img = document.querySelector("#img");

		//	var memberId = 1; 
		//		var url = "member/{id}".replace("{id}", memberId);
		var url = "member/" + memberId;

		console.log(memberId)

		// 發送AJAX請求獲取會員資料
		$.ajax({
			url: url,
			type: "GET",
			
			success: function(response) {
				
				console.log(response);

				// 顯示會員圖片
				//img.src = "data:image/jpeg;base64," + response.image;
				if (response.image) {
					// 顯示會員圖片
					img.src = "data:image/jpeg;base64," + response.image;
				} else {
					// 如果没有會員圖片，顯示咖啡圖片
					img.src = "images/300.jpg";
				}

				const html = `<h2 class="user-name">${response.name}</h2>`;
				document.querySelector("#name").innerHTML = html;

				//$("#name").innerText = response.name;

			},
			error: function(error) {
				console.log("獲取會員資料失敗");
			}
		});
	});
}


/* PUT更改密碼 */
// 更新會員資料
function PUT() {
	$('#update').on('click', function(e) {
		e.stopPropagation();

		var newPassword = $("input[name='newPassword']").val();
		var checkPassword = $("input[name='checkPassword']").val();

		if (checkPassword != newPassword) {
			msg.textContent = '密碼與確認密碼需一致';
			return;
		}
		//	var memberId = 1;
		var url = "member/{id}".replace("{id}", memberId);

		// 要發送的數據
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
				alert("密碼重設成功");
				window.location.href = "member_login.html";
			},
			error: function(error) {
				// 更新失敗後的處理邏輯
				console.log("更新失敗");
			}
		});
	});
}