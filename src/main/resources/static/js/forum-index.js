$(window).on("load", function(){
// 熱門文章
const popurl = 'article/pop';
fetch(popurl)
	.then(resp => resp.json())
	.then(artList => {
		for (var i = (artList.length -1); i > -1 ; i--) {
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
	})


// 最新文章
const latesturl = 'article/latest';
fetch(latesturl)
	.then(resp => resp.json())
	.then(artList => {
//		console.log(artList);
		for (var i = (artList.length -1); i > -1 ; i--) {
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