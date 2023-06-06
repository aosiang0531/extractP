$(function(){

	const tepURL = new URLSearchParams(window.location.search);
	const keyword = tepURL.get("keyword");
//	console.log(keyword);
	
	const searchTitleUrl = 'article/searchTitle?keyword=' + keyword;
	fetch(searchTitleUrl)
		.then(resp => resp.json())
		.then(titleData => {
			console.log(titleData);
		for(var i = 0; i < titleData.length; i++){
			var id = titleData[i].id;
			var title = titleData[i].articleTitle;
			var content = titleData[i].articleContent.replace(/<[^>]+>/g, '').slice(0, 55);
			var img =titleData[i].image ? "data:image/png;base64," + titleData[i].image : "./images/logo.png";
			var time = titleData[i].created_date;
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
			var thumb = titleData[i].thunmb_number;
			var comment = titleData[i].comment_number;
			var fav = titleData[i].member_article_fav_number;
			let resultHtml = `
				 <div class="card mb-2"
					onclick="window.location.href = 'articleAppear2.html?article=' + ${id};">
					<div class="card-body p-2 p-sm-3">
						<div class="media forum-item">
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
										${thumb}
									</span> 
									<span> 
										<i class="fa-solid fa-comment-dots fa-xl"></i>
										${comment}
									</span> 
									<span> 
										<i class="fa-solid fa-heart fa-xl"></i> 
										${fav}
									</span> 
									<span> ${formattedTime}</span>
								</div>
							</div>
						</div>
					</div>
				</div>
			`;
			$(".titleResult").after(resultHtml);
		}

			
			
	})

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
})