$(window).on("load", function(){
	const tepURL = new URLSearchParams(window.location.search);
	const tempId = tepURL.get("temp");

//  熱門文章
	const temPopUrl = 'article/temPop/' + tempId;
	fetch(temPopUrl)
	.then(resp => resp.json())
	.then(temPopList => {
		for(var i = (temPopList.length-1); i > -1; i--){
			var artId = temPopList[i].article_id;
			var grpName = temPopList[i].article_group_name;
			var img = temPopList[i].article_image ? "data:image/png;base64," + temPopList[i].article_image : "./images/logo.png";
			var title = temPopList[i].article_title;
			var content = temPopList[i].article_content.replace(/<[^>]+>/g, '').slice(0, 55);
			var author = temPopList[i].member_name;
			var thunmb = temPopList[i].article_thunmb_number;
			var comment = temPopList[i].article_comment_number;
			var fav = temPopList[i].member_article_fav_number;
			var time = temPopList[i].article_created_date;
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
	});
	
	
// 最新文章
	const temLatestUrl = 'article/temLatest/' + tempId;
	fetch(temLatestUrl)
		.then(resp => resp.json())
		.then(tempLatestList => {
		for (var i = (tempLatestList.length -1); i > -1 ; i--) {
			var artId = tempLatestList[i].article_id;
			var grpName = tempLatestList[i].article_group_name;
			var img = tempLatestList[i].article_image ? "data:image/png;base64," + tempLatestList[i].article_image : "./images/logo.png";
			var title = tempLatestList[i].article_title;
			var content = tempLatestList[i].article_content.replace(/<[^>]+>/g, '').slice(0, 55);
			var author = tempLatestList[i].member_name;
			var thunmb = tempLatestList[i].article_thunmb_number;
			var comment = tempLatestList[i].article_comment_number;
			var fav = tempLatestList[i].member_article_fav_number;
			var time = tempLatestList[i].article_created_date;
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
	});
	
// 規則
	const tempRuleUrl = 'article_template/' + tempId;
//	console.log(tempId);
	fetch(tempRuleUrl)
		.then(resp => resp.json())
		.then(ruleList => {
			var tempRule = ruleList.description;
//			console.log(tempRule);			
			let temRuleHtml = `
				     <div class="card mb-2">
                      <div class="card-body p-2 p-sm-3">
                        <div class="media forum-item rules">
                          <p style="white-space: pre-line;">${tempRule}
                        </div>
                      </div>
                    </div>
			`;
			$(".tab3").append(temRuleHtml);			
		})
	
});