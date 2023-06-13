/* 登入資訊 */
let token = localStorage.getItem("jwt");
const Url = `/auth?token=${encodeURIComponent(token)}`;
var memberId;
var memberName;
var memberImage;

fetch(Url)
	.then(response => response.json())
	.then(data => {
		memberId = data.id
		memberName = data.name;
		memberImage = data.image;

		GET();

	})
	.catch(error => {
		console.error(error);
	});


/* 點擊跳轉 */
$(document).ready(function() {
	$('#logoutButton').click(function(e) {
		e.stopPropagation();
		e.preventDefault();
		window.location.href = 'member_login.html';
	});

	$('#editinfo').click(function(e) {
		e.stopPropagation();
		e.preventDefault();
		window.location.href = 'member_editinfo.html';
	});

	$('#editpassword').click(function(e) {
		e.stopPropagation();
		e.preventDefault();
		window.location.href = 'member_editpassword.html';
	});

	$('#managearticle').click(function(e) {
		e.stopPropagation();
		e.preventDefault();
		window.location.href = 'member_myarticle.html';
	});

	//			$('#manageshop').click(function(e) {
	//				e.stopPropagation();
	//				e.preventDefault(); 
	//				window.location.href = 'member_myarticle.html';
	//			});
	//			
	$('#manageorder').click(function(e) {
		e.stopPropagation();
		e.preventDefault();
		window.location.href = 'orderhistory.html?memberId=' + memberId;
	});
	//				
	//			$('#managead').click(function(e) {
	//				e.stopPropagation();
	//				e.preventDefault(); 
	//				window.location.href = 'member_myarticle.html'; 
	//			});

});

/* 登出*/
function GET() {
	$(document).ready(function() {

		e.stopPropagation();
		e.preventDefault();

		$.ajax({
			url: "auth/logout",
			type: "GET",
			success: function(response) {
				console.log("會員登出成功");
			},
			error: function(error) {
				console.log("會員登出失敗");
			}
		});
	});

}

/* GET會員 */
function GET() {
	$(document).ready(function() {
		const img = document.querySelector("#img");

		//	var memberId = 1;
		var url = "member/{id}".replace("{id}", memberId);

		$.ajax({
			url: url,
			type: "GET",
			success: function(response) {

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


