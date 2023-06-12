
// 第二步，加上 tab_active 函式
function tab_active() {
	let target_tab;
	switch (location.hash) {
		case "#tab1":
			target_tab = "tab1";
			break;
		case "#tab2":
			target_tab = "tab2";
			break;
		default:
			target_tab = "tab1";
	}

	var all_a_tab = document.querySelectorAll("a.tab");
	for (let i = 0; i < all_a_tab.length; i++) {
		all_a_tab[i].classList.remove("-on");
	}
	document
		.querySelector("a.tab[data-target=" + target_tab + "]")
		.classList.add("-on");

	var all_div = document.querySelectorAll("div.tab");
	for (let i = 0; i < all_div.length; i++) {
		all_div[i].classList.remove("-on");
	}
	document.querySelector("div.tab." + target_tab).classList.add("-on");
}

document.addEventListener("DOMContentLoaded", function() {
	//console.log(location.hash);
	// 第三步
	tab_active();

	var all_a_tab = document.querySelectorAll("a.tab");
	for (let i = 0; i < all_a_tab.length; i++) {
		all_a_tab[i].addEventListener("click", function(e) {
			e.preventDefault();

			// 將頁籤列表移除所有 -on，再將指定的加上 -on
			for (let i = 0; i < all_a_tab.length; i++) {
				all_a_tab[i].classList.remove("-on");
			}
			this.classList.add("-on");

			// 找到對應的頁籤內容，加上 -on 來顯示
			var all_div = document.querySelectorAll("div.tab");
			for (let i = 0; i < all_div.length; i++) {
				all_div[i].classList.remove("-on");
				if (
					all_div[i].classList.contains(this.getAttribute("data-target"))
				) {
					all_div[i].classList.add("-on");
				}
			}

			// 第一步
			history.pushState(
				null,
				null,
				"#" + this.getAttribute("data-target")
			);
		});
	}
});

window.addEventListener("popstate", function() {
	// 第二步，加上 tab_active 函式
	tab_active();
});

$(function(){
	// 搜尋文章
	$("#button-search").on("click", function() {
		let search_text = ($("input.form-control").val()).trim();
		if (search_text == "") {
			alert("請輸入關鍵字");
			return;
		}
		window.location.href = 'SearchResult.html?keyword=' + search_text;
	});

	// 整數1轉換成英文字串"One"
	function numberToWord(number) {
		switch (number) {
			case 1:
				return "One";
			case 2:
				return "Two";
			case 3:
				return "Three";
			case 4:
				return "Four";
		}
	}

	const token = localStorage.getItem("jwt");
	const url = `/auth?token=${encodeURIComponent(token)}`;
	var sender;
	var memberId;

	fetch(url)
		.then(response => response.json())
		.then(data => {
			sender = data.name;
			memberId = data.id;
	console.log("member:" + memberId);
		})
		.catch(error => {
			console.error(error);
		});

	// 發表文章
	$(".post-article").on("click", function() {

		if (!memberId || memberId == undefined) {
			alert("請登入會員");
			window.location.href = 'member_login.html';
		} else {
			window.location.href = 'postArticle.html';
		}
	})
	// 聊天室	
	$(".public-chat").on("click", function() {
		if (!memberId || memberId == undefined) {
			alert("請登入會員");
			window.location.href = 'member_login.html';
		} else {
			window.location.href = 'chat_public.html';
		}

	})

	// 文章板塊
	const artTempUrl = 'article_template';
	fetch(artTempUrl)
		.then(resp => resp.json())
		.then(artTemList => {
			//		 console.log(artTemList);
			for (var i = 0; i < artTemList.length; i++) {
				var nameValue = artTemList[i].name;
				var idValue = artTemList[i].id;
				var word = numberToWord(i + 1);
				var headingId = `flush-heading` + word;
				var collapseId = `flush-collapse` + word;
				let tem_html = ` 
			<div class="accordion-item">
               <h2 class="accordion-header" id="${headingId}">
                  <button
                    id="btn-tem"
                    class="accordion-button collapsed"
                    type="button"
                    data-bs-toggle="collapse"
                    data-bs-target="#${collapseId}"
                    aria-expanded="false"
                    aria-controls="${collapseId}"
                    data-id="${idValue}"
                   >
                    ${nameValue}
                   </button>
               </h2>
               <div
                id="${collapseId}"
                class="accordion-collapse collapse"
                aria-labelledby="${headingId}"
                data-bs-parent="#accordionFlushExample"
                >
                </div>
             </div>`;
				$("#accordionFlushExample").append(tem_html);

				// 添加按钮点击事件监听器
				$(`#btn-tem-${i}`).on("click", function() {
					$(this).toggleClass("collapsed");
				});
			}
		})

	// 文章分類
	const btnGrp = document.querySelector('#btn-grp');
	const url2 = 'article_group';
	fetch(url2)
		.then(resp => resp.json())
		.then(artGrpList => {
			//		 console.log(artGrpList);
			for (var i = 0; i < artGrpList.length; i++) {

				var groupId = artGrpList[i].id
				var nameValue2 = artGrpList[i].name;
				var idValue = artGrpList[i].article_template_id;
				let grp_html = `
				<button
					id = "btn-grp"
                   class="accordion-body"
                   data-tempid="${idValue}"
                   onclick="window.location.href = 'TemplateAppear.html?group=' + ${groupId} + '&temp=' + ${idValue};"
                 >
                   ${nameValue2}
                 </button>`;
				if (i < 3) {
					$('div[aria-labelledby="flush-headingOne"]').append(grp_html);
				} else if (i > 2 && i < 6) {
					$('div[aria-labelledby="flush-headingTwo"]').append(grp_html);
				} else if (i > 5 && i < 10) {
					$('div[aria-labelledby="flush-headingThree"]').append(grp_html);
				} else {
					$('div[aria-labelledby="flush-headingFour"]').append(grp_html);
				}
			}
		})
});