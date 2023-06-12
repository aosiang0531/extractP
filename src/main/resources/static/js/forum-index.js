$(function(){

	var itemsPerPage = 3; // 每頁顯示的商品數量
	var currentPage = 1; // 當前頁碼
	var artList = [];
	var currentPage2 = 1; // 第二個頁籤的當前頁碼
	var artList2 = []; // 第二個頁籤的商品列表


	// 生成分頁按鈕
	function renderPagination() {
		const totalPages = Math.ceil(artList.length / itemsPerPage);
		$("#pagination-ul").empty(); // 清空分頁按鈕區域
		for (let i = 1; i <= totalPages; i++) {
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
			articleLatest(getCurrentPageProducts());
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
		.then(popLists => {
			artList = popLists.content;
			articlePop(getCurrentPageProducts());
			renderPagination();
		})

	function articlePop(artList) {
		$(".inner-main-body").empty(); // 清空文章列表
		//		"article_is_top": false,
		for (var i = 0; i < artList.length; i++) {
			var artId = artList[i].article_id;
			var grpName = artList[i].article_group_name;
			var img = artList[i].article_image ? "data:image/png;base64," + artList[i].article_image : "./images/logo.png";
			var title = artList[i].article_title;
			var content = artList[i].article_content.replace(/<[^>]+>/g, '').slice(0, 30);
			var author = artList[i].member_name;
			var thunmb = artList[i].article_thunmb_number;
			var comment = artList[i].article_comment_number;
			var fav = artList[i].member_article_fav_number;
			var top = artList[i].article_is_top;
			//			var time = artList[i].article_created_date;
			const time = (new Date(artList[i].article_created_date)).toISOString().slice(0, 19).replace(/-/g, "/").replace("T", " ");
			let popArticle = `
				<div class="card mb-2" is_top="${top}" 
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
									<span> ${time}</span>
								</div>
							</div>
						</div>
					</div>
				</div>`;
			$(".inner-main-body").append(popArticle);
		}
	}

	// 生成分頁按鈕
	function renderPagination2() {
		const totalPages = Math.ceil(artList2.length / itemsPerPage);
		$("#pagination-ul-2").empty(); // 清空分頁按鈕區域
		for (let i = 1; i <= totalPages; i++) {
			const pageButton = `<li class="page-item">
				                <a
				                  class="page-link rounded-0 mr-3 shadow-sm border-top-0 border-left-0 text-dark"
				                  href="#"
				                  >${i}</a
				                >
				              </li>`;
			$("#pagination-ul-2").append(pageButton);
		}

		// 顯示當前頁碼的按鈕為 disabled
		$(".page-item").eq(currentPage2 - 1).addClass("disabled");

		// 設置分頁按鈕點擊事件
		$(".page-link").click(function() {
			const newPage = parseInt($(this).text());
			currentPage2 = newPage;
			articleLatest(getCurrentPageProducts2());
			renderPagination2();

//			$(".tab").removeClass("-on");
//			$(".tab2").addClass("-on");
//			$("a[data-target='tab2']").addClass("-on");
		});
	}

	// 根據當前頁碼返回對應的商品陣列
	function getCurrentPageProducts2() {
		const startIndex = (currentPage2 - 1) * itemsPerPage;
		const endIndex = startIndex + itemsPerPage;
		return artList2.slice(startIndex, endIndex);
	}


	// 最新文章
	const latesturl = 'article/latestPage';
	fetch(latesturl)
		.then(resp => resp.json())
		.then(latestList => {
			artList2 = latestList.content;
			articleLatest(getCurrentPageProducts2());
			renderPagination2();

		})
	function articleLatest(artList2) {
		$(".inner-main-body2").empty(); // 清空文章列表
		for (var i = 0; i < artList2.length; i++) {
			var artId = artList2[i].article_id;
			var grpName = artList2[i].article_group_name;
			var img = artList2[i].article_image ? "data:image/png;base64," + artList2[i].article_image : "./images/logo.png";
			var title = artList2[i].article_title;
			var content = artList2[i].article_content.replace(/<[^>]+>/g, '').slice(0, 30);
			var author = artList2[i].member_name;
			var thunmb = artList2[i].article_thunmb_number;
			var comment = artList2[i].article_comment_number;
			var fav = artList2[i].member_article_fav_number;
			//			var time = artList2[i].article_created_date;
			const time = (new Date(artList2[i].article_created_date)).toISOString().slice(0, 19).replace(/-/g, "/").replace("T", " ");

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
									<span> ${time}</span>
								</div>
							</div>
						</div>
					</div>
				</div>`;
			$(".inner-main-body2").append(latestArticle);
		}
	}

});