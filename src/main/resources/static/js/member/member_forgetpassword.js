/* 發信 */
		$(document).ready(function() {

		  $("#reset").click(function(e) {
			e.stopPropagation();

			const accLength = $("#email").val().length;
			if (accLength < 5 || accLength > 50) {
				msg.textContent = '此email無效。請確認格式為：example@email.com';
				return;
			}

			var formData = {
				recipient: $("#email").val(),
				msgBody: "請點選以下連結重設密碼。\n\n http://localhost:8080/member_resetpassword.html",
				subject: "萃·練 重設密碼"
			};
		

			$.ajax({
			  type: "POST",
			  url: "member/sendMail",
			  data: JSON.stringify(formData),
			  contentType: "application/json",
			  success: function(response) {
				console.log(response);
				alert("已送出重設密碼信");
				window.location.href="member_login.html";
			  },
			  error: function(error) {

				console.log(error);
			  }
			});
		  });
		});
