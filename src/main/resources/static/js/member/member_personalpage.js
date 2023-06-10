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
		window.location.href = 'orderhistory.html?memberId=' + '1'; 
	});
	//				
	//			$('#managead').click(function(e) {
	//				e.stopPropagation();
	//				e.preventDefault(); 
	//				window.location.href = 'member_myarticle.html'; 
	//			});

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




