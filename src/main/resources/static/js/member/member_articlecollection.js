/* 左邊頁籤 */
window.addEventListener('load', function() {
	var b2 = document.querySelector('#b2');
	b2.classList.add('active');

	var a2 = document.querySelector('#a2');
	a2.classList.add('active');

	var element = document.querySelector('[aria-selected="false"]');
	element.setAttribute('aria-selected', 'true');

});

/* 點擊頁籤 */
$(document).ready(function() {
	$('#a1').click(function(e) {
		e.stopPropagation();
		window.location.href = 'member_myarticle.html'; // 替換為個人頁面的URL
	});
});



/* GET 會員資料 */
$(document).ready(function() {
	const file = document.querySelector("#file");
	const img = document.querySelector("#img");


	// 頁面加載時執行的操作
	var memberId = 2; // 請替換為真實的會員ID

	// 構建後端端點URL並替換會員ID
	var url = "member/{id}".replace("{id}", memberId);

	// 發送AJAX請求獲取會員資料
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
				// 如果没有會員圖片，顯示默認圖片
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


/* GET 會員文章 */
$(document).ready(function() {
	const article = document.querySelector("#article");
	const img = document.querySelector("#articleimg");

	// 頁面加載時執行的操作
	var memberId = 2; // 請替換為真實的會員ID

	// 構建後端端點URL並替換會員ID
	var url = "member_article_fav/memberFav/{id}".replace("{id}", memberId);


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
                          	<input id="checkbox${response[i].id}" type="checkbox" class="checkbox">
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

//取消選取
$('#cancel').click(function(e) {
	e.stopPropagation();
	e.preventDefault();

	// 獲取已勾選 checkbox 的 id
	var checkboxIds = [];
	$('.checkbox:checked').each(function() {
		console.log[checkboxIds];
		checkboxIds.push(this.id);
	});

	// 將勾選的 checkbox 改為未勾選
	for (var i = 0; i < checkboxIds.length; i++) {
		$('#' + checkboxIds[i]).prop('checked', false);
	}
});

//移除收藏
//$('#remove').click(function(e) {
//  e.stopPropagation();
//  e.preventDefault();

//  // 獲取已勾選 checkbox 的 id
//  var checkboxIds = [];
//  $('.checkbox:checked').each(function() {
//    checkboxIds.push(this.id);
//  });
//
//  for (var i = 0; i < checkboxIds.length; i++) {
//	  
//		// 發送AJAX DELETE請求
//		$.ajax({
//			type: "DELETE",
//			url: 'member_article_fav', 
//			contentType: "application/json",
//			success: function(response) {
//				console.log(response);
//				alert("文章移除收藏成功");
//				window.location.href = 'member_articlecollection.html';
//			},
//			error: function(error) {
//				console.log(error);
//			}
//		});
//
//  }
//});

// 獲取移除按鈕元素
$('#remove').click(function(e) {
	e.stopPropagation();
	e.preventDefault();
	//  // 獲取所有被選取的checkbox元素
	//  var checkboxes = document.querySelectorAll("input[type='checkbox']:checked");
	//  // 儲存文章ID的陣列
	//  var articleIds = [];
	//
	//  // 將選取的checkbox的value（文章ID）加入到文章ID陣列中
	//  checkboxes.forEach(function(checkbox) {
	//    articleIds.push(checkbox.value);
	//  });

	// 獲取已勾選 checkbox 的 id
	var articleId = [];

	$('.checkbox:checked').each(function() {
		var checkboxIds = $(this).attr('id').replace('checkbox', '');
		articleId.push(checkboxIds);
	});

	var articleTotal = articleId.length;

	for (var k = 0; k < articleTotal; k++) {

		var data = {
			"member_id": 2,
			"article_id": articleId[k]
		}

		console.log(data);

		// 使用Ajax的DELETE方法刪除文章
		if (articleId.length > 0) {
			$.ajax({
				url: 'member_article_fav',
				type: "DELETE",
				data: JSON.stringify(data),
				contentType: "application/json",
				success: function(response) {
					// 刪除成功後的處理邏輯
					alert("文章移除收藏成功");
					window.location.href = 'member_articlecollection.html';
				},
				error: function(error) {
					// 刪除失敗後的處理邏輯
				}
			});
		}


	}
});


