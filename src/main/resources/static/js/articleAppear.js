
//收藏功能 點愛心亮燈
$(".like").click(function() {
	$(this).toggleClass('cs');
})


//收藏功能 點讚亮燈
$(".thumbs-up").click(function() {
	$(this).toggleClass('cs');
})


//聊聊
$(function() {
	var INDEX = 0;
	$("#chat-submit").click(function(e) {
		e.preventDefault();
		var msg = $("#chat-input").val();
		if (msg.trim() == '') {
			return false;
		}
		generate_message(msg, 'self');
		var buttons = [
			{
				name: 'Existing User',
				value: 'existing'
			},
			{
				name: 'New User',
				value: 'new'
			}
		];
		setTimeout(function() {
			generate_message(msg, 'user');
		}, 1000)

	})

	function generate_message(msg, type) {
		INDEX++;
		var str = "";
		str += "<div id='cm-msg-" + INDEX + "' class=\"chat-msg " + type + "\">";
		str += "          <div class=\"cm-msg-text\">";
		str += msg;
		str += "          <\/div>";
		str += "        <\/div>";
		$(".chat-logs").append(str);
		$("#cm-msg-" + INDEX).hide().fadeIn(100);
		if (type == 'self') {
			$("#chat-input").val('');
		}
		$(".chat-logs").stop().animate({ scrollTop: $(".chat-logs")[0].scrollHeight }, 1000);
	}

	function generate_button_message(msg, buttons) {
		/* Buttons should be object array 
		  [
			{
			  name: 'Existing User',
			  value: 'existing'
			},
			{
			  name: 'New User',
			  value: 'new'
			}
		  ]
		*/
		INDEX++;
		var btn_obj = buttons.map(function(button) {
			return "              <li class=\"button\"><a href=\"javascript:;\" class=\"btn btn-primary chat-btn\" chat-value=\"" + button.value + "\">" + button.name + "<\/a><\/li>";
		}).join('');
		var str = "";
		str += "<div id='cm-msg-" + INDEX + "' class=\"chat-msg user\">";
		str += "          <div class=\"cm-msg-text\">";
		str += msg;
		str += "          <\/div>";
		str += "          <div class=\"cm-msg-button\">";
		str += "            <ul>";
		str += btn_obj;
		str += "            <\/ul>";
		str += "          <\/div>";
		str += "        <\/div>";
		$(".chat-logs").append(str);
		$("#cm-msg-" + INDEX).hide().fadeIn(300);
		$(".chat-logs").stop().animate({ scrollTop: $(".chat-logs")[0].scrollHeight }, 1000);
		$("#chat-input").attr("disabled", true);
	}

	$(document).delegate(".chat-btn", "click", function() {
		var value = $(this).attr("chat-value");
		var name = $(this).html();
		$("#chat-input").attr("disabled", false);
		generate_message(name, 'self');
	})

	$("#chatroom").click(function() {
		$(".chat-box").toggle('scale');
	})

	$(".chat-box-toggle").click(function() {
		$(".chat-box").toggle('scale');
	})

})

//  抓到文章id  
const tepURL = new URLSearchParams(window.location.search);
const artId = tepURL.get("article");
var commentNum;

// 文章顯示
const articleUrl = "article/detailsById/" + artId;
fetch(articleUrl)
	.then(resp => resp.json())
	.then(artList => {
		console.log("A" + artList);
		var title = artList[0].article_title;
		var author = artList[0].member_created_user;
		var content = artList[0].article_content;
		var thumb = artList[0].article_thunmb_number;
		commentNum = artList[0].article_comment_number;
		var fav = artList[0].member_article_fav_number;
		var img = "data:image/png; base64," + artList[0].article_image;
		var time = artList[0].article_created_date;
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

		let articleHeader = `
			            <div class="fw-bolder mb-1" style="
                  			color: brown !important;
                  			font-size: 30px !important;
                  			font-weight: bold;
                  			text-align: center !important;
                		">
              			${title}
            			</div>
            			<!-- 發文時間作者 -->
            			<div class="text-muted fst-italic mb-2">
              				<span>${formattedTime}</span>
              				<span>${author}</span>
            			</div>
			`;
		$("#art-header").append(articleHeader);

		let artImg = `
				  <img class="img-fluid rounded" src="${img}" 
				  style="
                  border-radius: 3% !important;
                  max-width: 100% !important;
                  aspect-ratio: 16/9 !important;
                " />			
			`;
		$("#art-img").append(artImg);


		let articleContent = `
				<p class="fs-5 mb-4" style="white-space: pre-line;">
				${content}
	            </p>
	            <!-- 文章標籤/按讚/留言/收藏-->
	            <div>
	              <a class="badge bg-secondary text-decoration-none link-light" href="#!">Web Design</a>
	              <a class="badge bg-secondary text-decoration-none link-light" href="#!">Freebies</a>
	            </div>
	            <div class="text-muted small text-center align-self-center class=row" style="display: inline-block;">
	              <span id="thumbs" class="d-none d-sm-inline-block mr-2" style="color: brown;">
	                <i class="fa-regular fa-thumbs-up fa-xl thumbs-up"></i>
	                ${thumb}</span>
	              <span style="color: brown;">
	                <i class="fa-solid fa-comment-dots fa-xl mr-2"></i>
	                ${commentNum}</span>
	              <span id="favorite" style="color: brown;">
	                <i class="fa-solid fa-heart fa-xl like mr-2"></i>
	                ${fav}</span>
	            </div>
	            <a href="#" class="btn_modal btn_report article-repo" type="button">檢舉</a>
			`;
		$("#art-content").append(articleContent);
	})

//  顯示留言
const commentUrl = 'article_comment/findByArticleId/' + artId;
fetch(commentUrl)
	.then(resp => resp.json())
	.then(commentList => {
		for (var i = 0; i < commentList.length; i++) {
			var commentId = commentList[i].article_comment_id
			var creator = commentList[i].member_created_user;
			var content = commentList[i].article_comment_content;
			var time = (new Date(commentList[i].article_comment_created_date)).toISOString().slice(0, 19).replace(/-/g, "/").replace("T", " ");
			let commHtml = `
				      <li class="media" commid="${commentId}">
                        <a href="#" class="pull-left">
                          <img src="https://bootdey.com/img/Content/user_1.jpg" alt="" class="img-circle" />
                        </a>
                        <div class="media-body">
                          <span class="text-muted pull-right">
                            <small class="text-muted">${time}</small>
                          </span>
                          <strong class="text-success">${creator}</strong>
                          <p>${content}</p>
                          <a href="#" class="btn_modal btn_report comm-repo" type="button">檢舉</a>
                        </div>
                      </li>
				`;
			$(".media-list").append(commHtml);
		}
	})

//新增評論
$(".btn-info").click(function() {
	// 抓到留言內容	
	let article_comment_content = ($("#writecomment").val()).trim();
	var data = {
		"content": article_comment_content,
		"member_id": 1,
		"article_id": artId
	}
	var jsonData = JSON.stringify(data);
	if (article_comment_content != "") {
		// 留言存進資料庫
		const postCommUrl = 'article_comment';
		fetch(postCommUrl, {
			method: 'POST',
			body: jsonData,
			headers: { 'Content-Type': 'application/json;charset=utf8' }
		})
			.then(resp => resp.json())
			.then(commData => {
				var time = (new Date(commData.created_date)).toISOString().slice(0, 19).replace(/-/g, "/").replace("T", " ");
				$(".media-list").append(`
			                    <li class="media">
			                      <a href="#" class="pull-left">
			                        <img src="https://bootdey.com/img/Content/user_1.jpg" alt="" class="img-circle" />
			                      </a>
			                      <div class="media-body">
			                        <span class="text-muted pull-right">
			                          <small class="text-muted">${time}</small>
			                        </span>
			                        <strong class="text-success">${commData.memberid}</strong>
			                        <p>
			                          ${article_comment_content}
			                        </p>
			                        <a href="#" class="btn_modal btn_report comm-repo" type="button">檢舉</a>
			                      </div>
			                    </li>
			    	`);
				$("#writecomment").val("");
			// 新增留言數字
//				var addCommNum = commentNum + 1;
//				console.log("addNumber:" + addCommNum);
				const articlePut = 'article/' + artId;
//				fetch(articlePut, {
//					method:'PUT',
//					body:''
//				})
					
			})
	} else {
		alert("評論內容請勿空白");
	}
});

// 檢舉
// 開啟 文章檢舉 Modal 彈跳視窗
$(document).on("click", ".article-repo", function() {
	$("div.article-report").fadeIn();
});

// 開啟 留言檢舉 Modal 彈跳視窗
var commId;
$(document).on("click", ".comm-repo", function() {
	commId = $(this).closest("li").attr("commid");
	$("div.comment-report").fadeIn();
});

// 關閉 檢舉 Modal 取消按鈕
$(document).on("click", "button.btn_modal_cancel, div.overlay", function(e) {
	$("div.overlay").fadeOut();
});


// 確定 文章檢舉 Modal 完成按鈕
$(document).on("click", "button.btn_modal_complete_art, div.overlay", function(e) {
	let selectedLabel = $("input[name='option']:checked").closest('label').text();
	//      console.log("label:" + selectedLabel);
	var data = {
		"content": selectedLabel,
		"member_id": 1,
		"article_id": artId
	}
	var jsonData = JSON.stringify(data);
	let r = confirm("確認檢舉？");
	if (r) {
		if (selectedLabel == "") {
			alert("請選取檢舉原因");
			return;
		}
		const artReportUrl = 'article_report';
		fetch(artReportUrl, {
			method: 'POST',
			body: jsonData,
			headers: { 'Content-Type': 'application/json;charset=utf8' }
			})
			.then(resp => resp.json())
			.then(artReportData => {
				$("div.overlay").fadeOut();
			})
	}
});

// 確定 留言檢舉 Modal 完成按鈕
$(document).on("click", "button.btn_modal_complete_comm, div.overlay", function(e) {
	let selectedLabel = $("input[name='option']:checked").closest('label').text();
		
	var data = {
		"content": selectedLabel,
		"member_id": 1,
		"article_comment_id": commId
	}
	var jsonData = JSON.stringify(data);
	let r = confirm("確認檢舉？");
		if (r) {
			if (selectedLabel == "") {
				alert("請選取檢舉原因");
				return;
			}
			const commReportUrl = 'article_comment_report';
			fetch(commReportUrl, {
				method:'POST',
				body:jsonData,
				headers: { 'Content-Type': 'application/json;charset=utf8' }
				})
				.then(resp => resp.json())
				.then(commReportData => {
					$("div.overlay").fadeOut();
				})
		}
});

$(document).on("click", "div.overlay > article", function(e) {
	e.stopPropagation();
});





























