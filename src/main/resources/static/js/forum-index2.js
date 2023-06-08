$(window).on("load", function(){

var itemsPerPage = 5; // 每頁顯示的商品數量
var currentPage = 1; // 當前頁碼
var artList = [];
// 生成分頁按鈕
function renderPagination() {
	const totalPages = Math.ceil(artList.length / itemsPerPage);
	$("#pagination-ul").empty(); // 清空分頁按鈕區域

	for (let i = 1; i <= totalPages; i++) {
		//		const pageButton = `<button class="page-btn">${i}</button>`;
		const pageButton = `<li class="page-item">
				                <a
				                  class="page-link rounded-0 mr-3 shadow-sm border-top-0 border-left-0 text-dark"
				                  href="#"
				                  >${i}</a
				                >
				              </li>`;
		$("#pagination-ul").append(pageButton);
	}

	// 顯示當前頁碼的按鈕為 disabled
	$(".page-item").eq(currentPage - 1).addClass("disabled");

	// 設置分頁按鈕點擊事件
	$(".page-link").click(function() {
		const newPage = parseInt($(this).text());
		currentPage = newPage;
		
		articlePop(getCurrentPageProducts());
		renderPagination();
	});
}

// 根據當前頁碼返回對應的商品陣列
function getCurrentPageProducts() {
	const startIndex = (currentPage - 1) * itemsPerPage;
	const endIndex = startIndex + itemsPerPage;
	return artList.slice(startIndex, endIndex);
}


// 熱門文章
const popurl = 'article/popPage';
fetch(popurl)
	.then(resp => resp.json())
	.then(artLists => {
		artList = artLists.content;
		articlePop(getCurrentPageProducts());
		renderPagination();
	})

function articlePop(artList) {
$(".inner-main-body").empty(); // 清空文章列表
	for (var i = (artList.length - 1); i > -1; i--) {
		var artId = artList[i].article_id;
		var grpName = artList[i].article_group_name;
		var img = artList[i].article_image ? "data:image/png;base64," + artList[i].article_image : "./images/logo.png";
		var title = artList[i].article_title;
		var content = artList[i].article_content.replace(/<[^>]+>/g, '').slice(0, 55);
		var author = artList[i].member_name;
		var thunmb = artList[i].article_thunmb_number;
		var comment = artList[i].article_comment_number;
		var fav = artList[i].member_article_fav_number;
		var time = artList[i].article_created_date;
		var options = {
			timeZone: 'Asia/Taipei',
			hour12: false,
			year: 'numeric',
			month: '2-digit',
			day: '2-digit',
			hour: '2-digit',
			minute: '2-digit',
			second: '2-digit'
		};
		var formattedTime = new Date(time).toLocaleString('zh-TW', options);
		let popArticle = `
				<div class="card mb-2"
					onclick="window.location.href = 'articleAppear2.html?article=' + ${artId};">
					<div class="card-body p-2 p-sm-3">
						<div class="media forum-item">
							<div class="article-group">${grpName}</div>
							<img src="${img}"
								 class="img-thumbnail" />
							<div class="media-body">
								<h6>
									<p data-toggle="collapse"
										data-target=".forum-content" class="text-body">${title}</p>
								</h6>
								<p class="text-secondary">${content}......</p>
								<div class="text-muted small text-center align-self-center class=row">
									<span class="d-none d-sm-inline-block"> 
										<i class="fa-regular fa-thumbs-up fa-xl"></i> 
										${thunmb}
									</span> 
									<span> 
										<i class="fa-solid fa-comment-dots fa-xl"></i>
										${comment}
									</span> 
									<span> 
										<i class="fa-solid fa-heart fa-xl"></i> 
										${fav}
									</span> 
									<span> ${author}</span> 
									<span> ${formattedTime}</span>
								</div>
							</div>
						</div>
					</div>
				</div>`;
		$(".inner-main-body").prepend(popArticle);

	}
}


// 最新文章
const latesturl = 'article/latest';
fetch(latesturl)
	.then(resp => resp.json())
	.then(artList => {
		//		console.log(artList);
		for (var i = (artList.length - 1); i > -1; i--) {
			var artId = artList[i].article_id;
			var grpName = artList[i].article_group_name;
			var img = artList[i].article_image ? "data:image/png;base64," + artList[i].article_image : "./images/logo.png";
			var title = artList[i].article_title;
			var content = artList[i].article_content.replace(/<[^>]+>/g, '').slice(0, 55);
			var author = artList[i].member_name;
			var thunmb = artList[i].article_thunmb_number;
			var comment = artList[i].article_comment_number;
			var fav = artList[i].member_article_fav_number;
			var time = artList[i].article_created_date;
			var options = {
				timeZone: 'Asia/Taipei',
				hour12: false,
				year: 'numeric',
				month: '2-digit',
				day: '2-digit',
				hour: '2-digit',
				minute: '2-digit',
				second: '2-digit'
			};
			var formattedTime = new Date(time).toLocaleString('zh-TW', options);
			let latestArticle = `
				<div class="card mb-2"
					onclick="window.location.href = 'articleAppear2.html?article=' + ${artId};">
					<div class="card-body p-2 p-sm-3">
						<div class="media forum-item">
							<div class="article-group">${grpName}</div>
							<img src="${img}"
								 class="img-thumbnail" />
							<div class="media-body">
								<h6>
									<p data-toggle="collapse"
										data-target=".forum-content" class="text-body">${title}</p>
								</h6>
								<p class="text-secondary">${content}......</p>
								<div class="text-muted small text-center align-self-center class=row">
									<span class="d-none d-sm-inline-block"> 
										<i class="fa-regular fa-thumbs-up fa-xl"></i> 
										${thunmb}
									</span> 
									<span> 
										<i class="fa-solid fa-comment-dots fa-xl"></i>
										${comment}
									</span> 
									<span> 
										<i class="fa-solid fa-heart fa-xl"></i> 
										${fav}
									</span> 
									<span> ${author}</span> 
									<span> ${formattedTime}</span>
								</div>
							</div>
						</div>
					</div>
				</div>`;
			$(".tab2").prepend(latestArticle);
		}
	})

});