/* 登入資訊 */
const token = localStorage.getItem("jwt");
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

		GET1();
		GET2();
		GET3();
		DELETE();

	})
	.catch(error => {
		console.error(error);
	});



/* 左邊頁籤 */
window.addEventListener('load', function() {
	var b1 = document.querySelector('#b1');
	b1.classList.add('active');

	var a1 = document.querySelector('#a1');
	a1.classList.add('active');

	var element = document.querySelector('[aria-selected="false"]');
	element.setAttribute('aria-selected', 'true');

});

/* 點擊頁籤 */
$(document).ready(function() {
	$('#a2').click(function(e) {
		e.stopPropagation();
		window.location.href = 'member_articlecollection.html'; // 替換為個人頁面的URL
	});
});



/* GET 會員資料 */
function GET1() {
	$(document).ready(function() {
		const file = document.querySelector("#file");
		const img = document.querySelector("#img");

		//	var memberId = 1; 

		var url = "member/{id}".replace("{id}", memberId);

		// 發送AJAX請求獲取會員資料
		$.ajax({
			url: url,
			type: "GET",
			success: function(response) {

				// 顯示會員圖片
				//			img.src = "data:image/jpeg;base64," + response.image;
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

/* GET 會員文章 */
function GET2() {
	$(document).ready(function() {
		const article = document.querySelector("#article");
		const img = document.querySelector("#articleimg");


		//	var memberId = 1; 
		var url = "article/memberId/{id}".replace("{id}", memberId);

		// 發送AJAX請求獲取文章資料
		$.ajax({
			url: url,
			type: "GET",
			success: function(response) {
				var articleTotal = response.length;
				console.log(articleTotal);

				for (var i = 0; i < articleTotal; i++) {
					console.log(i);
					const html = ` <div id="article" class="bg-white card mb-4 order-list shadow-sm">
			<div class="gold-members p-4">
                          <a href="#"> </a>
                          <div class="media">
                          	
                              <img
                                id="articleimg"
                                class="mr-4"
                                src="data:image/jpeg;base64,${response[i].image}"
                                alt="文章圖片"
                              />
                            
                            <div class="media-body">
                              
                                <span id="articleposttime" class="float-right text-info"
                                  >${response[i].created_date}
                                  <i class="icofont-check-circled text-success"></i
                                ></span>
                              </a>
                              <h4 id="articletitle" class="mb-2">
                               
                                <a href="#" class="text-black"
                                  >${response[i].articleTitle}</a
                                >
                              </h4>

                              <span id="articlecontent"><p class="text-dark">
                                ${response[i].articleContent}
                              </p></span>
                              <hr />
                              <div class="float-right">
                                <a id="editarticle${response[i].id}" class="btn btn-sm btn-outline-primary" href="#"
                                  ><i class="icofont-headphone-alt"></i> 編輯文章 </a
                                >
                                <a id="deletearticle${response[i].id}" class="btn btn-sm btn-primary" href="#"
                                  ><i class="icofont-refresh"></i> 刪除文章 </a
                                >
                              </div>
                             <div class="outer">
                             	 <div class="mb-0 text-black text-primary pt-2">
                                <span><i class="fa-solid fa-thumbs-up" style="color: #ef8d8a;"></i></i></span>
                                <span id="articlethunmbnumber">${response[i].thunmb_number}</span>
                              </div>
                              <div class="mb-0 text-black text-primary pt-2">
                                <span><i class="fa-solid fa-message" style="color: #ef8d8a;"></i></i></span>
                                <span id="articlecommentnumber">${response[i].comment_number}</span>
                              </div>
                              <div class="mb-0 text-black text-primary pt-2">
                                <span><i class="fa-solid fa-heart" style="color: #ef8d8a;"></i></span>
                                <span id="memberarticlefavnumber" >${response[i].member_article_fav_number}</span>
                              </div>
                             </div>
                            </div>
                          </div>
                        </div>
		    </div>` ;
					article.innerHTML += html;
				}
			},
			error: function(error) {
				console.log("獲取會員文章資料失敗");
			}
		});
	});
}

/* 編輯文章 */
function GET3() {
	$(document).ready(function() {

		$(document).on('click', '[id^="editarticle"]', function(e) {
			e.preventDefault();

			// 獲取文章ID
			var articleId = $(this).attr('id').replace('editarticle', '');

			// 使用Ajax傳遞ID參數並跳轉到另一個畫面
			$.ajax({
				url: 'article',
				type: 'GET',
				data: { id: articleId },  // 傳遞文章ID作為參數
				success: function(response) {
					// 跳轉到編輯文章畫面
					window.location.href = 'postArticle.html?id=' + articleId;
				},
				error: function(error) {
					console.log('獲取文章編輯畫面失敗');
				}
			});
		});
	});
}

/* 刪除文章 */
function DELETE() {
	$(document).ready(function() {
		$(document).on('click', '[id^="deletearticle"]', function(e) {
			e.preventDefault();

			// 獲取文章ID
			var articleId = $(this).attr('id').replace('deletearticle', '');
			var url = "article/{articleId}".replace("{articleId}", articleId);


			$.ajax({
				type: "DELETE",
				url: url,
				contentType: "application/json",
				success: function(response) {
					console.log(response);
					alert("文章刪除成功");
					window.location.href = 'member_myarticle.html';
				},
				error: function(error) {
					console.log(error);
				}
			});
		});
	});
}

