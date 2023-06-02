
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

// 文章板塊
const url = 'article_template';
fetch(url)
	.then(resp => resp.json())
	.then(artTemList => {
//		 console.log(artTemList);
		for (var i = 0; i < artTemList.length; i++) {
			var nameValue = artTemList[i].name;
			var idValue = artTemList[i].id;
//			console.log(idValue);
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
		}
//		$("#btn-tem").on("click", function(){
//			console.log("A");
//		let temp = `
//            <!-- 文章顯示 -->
//            <div class="article-appear">
//              <div class="tab_container">
//                <!-- 頁籤 -->
//                <div class="tab_list_block">
//                  <ul class="tab_list">
//                    <li>
//                      <a href="#" data-target="tab1" class="tab -on"
//                        >熱門文章</a
//                      >
//                    </li>
//                    <li>
//                      <a href="#" data-target="tab2" class="tab">最新文章</a>
//                    </li>
//                    <li>
//                      <a href="#" data-target="tab3" class="tab">板塊規則</a>
//                    </li>
//                  </ul>
//                </div>
//                <div class="tab_contents">
//                  <!-- 熱門文章 -->
//                  <div class="tab tab1 -on">
//                    <div
//                      class="inner-main-body p-2 p-sm-3 collapse forum-content show"
//                    >
//                      <!-- 分頁 -->
//                      <ul
//                        class="pagination pagination-sm pagination-circle justify-content-center mb-0"
//                      >
//                        <li class="page-item disabled">
//                          <span class="page-link has-icon"
//                            ><i class="fa-solid fa-chevron-left fa-xl"></i
//                          ></span>
//                        </li>
//                        <li class="page-item">
//                          <a class="page-link" href="javascript:void(0)">1</a>
//                        </li>
//                        <li class="page-item active">
//                          <span class="page-link">2</span>
//                        </li>
//                        <li class="page-item">
//                          <a class="page-link" href="javascript:void(0)">3</a>
//                        </li>
//                        <li class="page-item">
//                          <a
//                            class="page-link has-icon"
//                            href="javascript:void(0)"
//                            ><i class="fa-solid fa-chevron-right fa-xl"></i
//                          ></a>
//                        </li>
//                      </ul>
//                    </div>
//                  </div>
//                  <!-- 最新文章 -->
//                  <div class="tab tab2">
//                    <ul
//                      class="pagination pagination-sm pagination-circle justify-content-center mb-0"
//                    >
//                      <li class="page-item disabled">
//                        <span class="page-link has-icon"
//                          ><i class="fa-solid fa-chevron-left fa-xl"></i
//                        ></span>
//                      </li>
//                      <li class="page-item">
//                        <a class="page-link" href="javascript:void(0)">1</a>
//                      </li>
//                      <li class="page-item active">
//                        <span class="page-link">2</span>
//                      </li>
//                      <li class="page-item">
//                        <a class="page-link" href="javascript:void(0)">3</a>
//                      </li>
//                      <li class="page-item">
//                        <a class="page-link has-icon" href="javascript:void(0)"
//                          ><i class="fa-solid fa-chevron-right fa-xl"></i
//                        ></a>
//                      </li>
//                    </ul>
//                  </div>
//                  <!-- 板塊規則 -->
//                  <div class="tab tab3">
//                  </div>
//                </div>
//              </div>
//            </div>`;
//            $(".col-sm-10").empty();
//            $(".col-sm-10").append(temp);
//		})
	})
	

	

// 文章分類
const btnGrp = document.querySelector('#btn-grp');
const url2 = 'article_group';
fetch(url2)
	.then(resp => resp.json())
	.then(artGrpList => {
//		 console.log(artGrpList);
		for (var i = 0; i < artGrpList.length; i++) {
			var nameValue2 = artGrpList[i].name;
			var idValue = artGrpList[i].article_template_id;
//			 console.log(idValue);
			let grp_html = `
				<button
					id = "btn-grp"
                   class="accordion-body"
                   data-tempid="${idValue}"
                   onclick="window.location.href = 'TemplateAppear.html?temp=' + ${idValue};"
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

	
