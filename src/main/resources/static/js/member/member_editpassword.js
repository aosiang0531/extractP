/* 取消按鈕跳轉 */
$(document).ready(function() {
	$('#cancel').click(function(e) {
		e.stopPropagation();
		e.preventDefault();
		window.location.href = 'member_personalpage.html'; 
	});

});


/* GET會員 */
$(document).ready(function() {
	const img = document.querySelector("#img");

	var memberId = 1; 
	var url = "member/{id}".replace("{id}", memberId);

	$.ajax({
		url: url,
		type: "GET",
		success: function(response) {

			// 顯示會員圖片
			// img.src = "data:image/jpeg;base64," + response.image;
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


/* PUT更新密碼 */
function passwordError(){
		// 檢查密碼格式
        var passwordRegex = /^(?=.*\d)(?=.*[a-z])(?=.*[A-Z])[0-9a-zA-Z]{8,}$/;
        if (!passwordRegex.test(document.getElementById('newPassword').value)) {
            var passwordError = document.getElementById('passwordError');
            passwordError.textContent = '密碼必須包含至少一個數字、一個小寫字母、一個大寫字母，並且長度至少為8個字元';
            console.log('密碼必須包含至少一個數字、一個小寫字母、一個大寫字母，並且長度至少為8個字元');
            return;
        }else{
    		document.getElementById('passwordError').textContent = '';
    		return true;
    	}
	}

// 更新會員資料
$('#update').on('click', function(e) {
	e.stopPropagation();

	var newPassword = $("input[name='newPassword']").val();
	var checkPassword = $("input[name='checkPassword']").val();

	if (checkPassword != newPassword) {
		msg.textContent = '密碼與確認密碼需一致';
		return;
	}
	
	var memberId = 1;
	var url = "member/{id}".replace("{id}", memberId);

	// 要發送的數據
	var data = {
		password: newPassword

	};

	$.ajax({
		url: url,
		type: "PUT",
		data: JSON.stringify(data),
		contentType: "application/json",
		success: function(response) {
			// 更新成功後的處理邏輯
			console.log("更新成功");
			window.location.href = 'member_personalpage.html';
		},
		error: function(error) {
			// 更新失敗後的處理邏輯
			console.log("更新失敗");
		}
	});
});
