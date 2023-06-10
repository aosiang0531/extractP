/* 發信 */
		$(document).ready(function() {
		  // 按鈕點擊事件監聽
		  $("#reset").click(function(e) {
			e.stopPropagation();

			const accLength = $("#email").val().length;
			if (accLength < 5 || accLength > 50) {
				msg.textContent = '此email無效。請確認格式為：example@email.com';
				return;
			}

			
			// 獲取表單數據
			var formData = {
				recipient: $("#email").val(),
				msgBody: "請點選以下連結重設密碼。\n\n http://localhost:8080/member_resetpassword.html",
				subject: "萃·練 重設密碼"
			};
		
			// 發送AJAX POST請求
			$.ajax({
			  type: "POST",
			  url: "member/sendMail", // 替換為後端接口的實際URL
			  data: JSON.stringify(formData),
			  contentType: "application/json",
			  success: function(response) {
				// 處理後端的回應
				console.log(response);
				// 執行必要的操作或顯示成功訊息
				alert("已送出重設密碼信");
				window.location.href="member_login.html";
			  },
			  error: function(error) {
				// 處理錯誤
				console.log(error);
				// 顯示錯誤訊息或執行必要的操作
			  }
			});
		  });
		});
