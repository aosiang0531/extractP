var allProducts = [];
var itemsPerPage = 8; // 每頁顯示的商品數量
var currentPage = 1; // 當前頁碼
//var countCartItem;
//const cartItemNumber = document.querySelector('#cartItemNumber');
var memberId;
var memberImage;

//JWT
fetch(url)
	.then(response => response.json())
	.then(data => {
//		sender = data.name;
		memberId = data.id;
		memberImage = "data:image/png;base64," + data.image;
		if (token === null) {
				userLink.href = "member_login.html";
				userIcon.style.display = "none"; // 隱藏圖片
			} else {
				userLink.href = "member_personalpage.html";
				noneUser.style.display = "none";
				userIcon.src = memberImage; // 指定圖片的路徑
				userIcon.style.display = "inline-block"; // 顯示圖片
			}
	})
	.catch(error => {
		console.error(error);
	});

//商品加入購物車
function addToCart(productId, price) {

	var newDetail = JSON.stringify({
		"product_id": productId,
		"price": price,
		"quantity": 1, //從商品列表加入時，預設數量為1
	});

	fetch(`/orderDetail/${memberId}/unplaced`, {
		method: "PUT", headers: { 'Content-Type': 'application/json' },
		body: newDetail
	})
		.then((resp) => resp.json())
		.then((body) => {
			//			console.log(body);
			alert("成功加入購物車");
//			countCartItem();

		});
}

//// 顯示購物車明細數量
//function countCartItem() {
//	setTimeout(() => {
//		fetch(`/orderDetail/${memberId}/countItems`)
//			.then((resp) => {
//				if (resp.ok) {
//					return resp.text();
//				}
//			})
//			.then((count) => {
//				// 更新數字
//				cartItemNumber.innerHTML = count;
//			})
//			.catch(error => {
//				// 發生錯誤時的處理邏輯
//				console.log('發生錯誤:', error);
//			});
//	}, 100); // 非同步延遲處理，使它可以在網頁上不刷新即顯示
//}

//以關鍵字搜尋商品
function searchBykeyword(keyword) {
	const url = `/shop/product/search/${keyword}`;
	fetch(url)
		.then((res) => res.json())
		.then((searchedProduct) => {
			if (searchedProduct.length != 0) {

				var breadcrumbHtml = `
		    <a href="product_list.html">咖啡商城</a> /
		    <span>搜尋結果</span>
		    `;

				// 插入麵包屑的 HTML 到麵包屑區域
				$("#breadcrumb").html(breadcrumbHtml)
				renderProductList(searchedProduct);

			} else {
				alert("查無商品");
			}
		});
}


// 獲取已上架商品列表並渲染到頁面上
function loadAllProducts() {
	const url = "/shop/product/onSalePaged";
	fetch(url)
		.then((res) => res.json())
		.then((productList) => {
			console.log("AAA" + productList);
			allProducts = productList.content;
			console.log(allProducts);
			renderProductList(getCurrentPageProducts());
			renderPagination();
		});

	$("#breadcrumb").empty();
	// 生成麵包屑的 HTML 代碼
	var breadcrumbHtml = `
		    <a href="product_list.html">咖啡商城</a> /
		    <span>所有商品</span>
		    `;

	// 插入麵包屑的 HTML 到麵包屑區域
	$("#breadcrumb").html(breadcrumbHtml);
}

// 根據當前頁碼返回對應的商品陣列
function getCurrentPageProducts() {
	const startIndex = (currentPage - 1) * itemsPerPage;
	const endIndex = startIndex + itemsPerPage;
	return allProducts.slice(startIndex, endIndex);
}

// 根據分類編號過濾商品列表並渲染到頁面上
function filterProductsByCategory(categoryId) {
	console.log("分類編號:" + categoryId);

	var filteredProducts = allProducts.filter(function(product) {
		return product.categoryId === categoryId;
	});


	renderProductList(filteredProducts);

	var categoryName;
	switch (categoryId) {
		case 1:
			categoryName = "咖啡器具";
			break;
		case 2:
			categoryName = "咖啡豆";
			break;
		case 3:
			categoryName = "濾掛咖啡";
			break;
	}
	// 清空麵包屑區域的內容
	$("#breadcrumb").empty();

	// 生成麵包屑的 HTML 代碼
	var breadcrumbHtml = `
        <a href="product_list.html">咖啡商城</a> /
        <a>${categoryName}</a> 
        `;

	// 插入麵包屑的 HTML 到麵包屑區域
	$("#breadcrumb").html(breadcrumbHtml);
}

// 更新商品列表的頁面顯示
function renderProductList(products) {
	$("#list").empty(); // 清空商品列表區域

	for (let product of products) {
		// 讀出商品圖片
		var imgUrl = "data:image/png;base64," + product.image;

		// 生成商品HTML並添加到商品列表區域
		var productHtml = `
        <div class="col-md-3">
          <div class="card mb-4 product-wap rounded-0">
            <div class="card rounded-0">
              <img class="card-img rounded-0 img-fluid" src=${imgUrl}>
              <div
                class="card-img-overlay rounded-0 product-overlay d-flex align-items-center justify-content-center">
                <ul class="list-unstyled">
                 	<li><a class="btn btn-primary text-white mt-2" href="../shop/product_single.html?id=${product.id}">
                    <i class="icon icon-eye"></i></a></li>
					<li>
						<a class="btn btn-primary text-white mt-2" onclick="addToCart(${product.id}, ${product.price})">
					 		<i class="icon icon-cart-plus"></i>
						</a>
					</li>
                </ul>
              </div>
            </div>
            <div class="card-body">
              <a href="${product.id}.html" class="h5 text-decoration-none">${product.productName}</a>
              <ul class="w-100 list-unstyled d-flex justify-content-between mb-0">
                <li>${product.spec}</li>
              </ul>
              <ul class="w-100 list-unstyled d-flex justify-content-between mb-0">
                <p class="text-center mb-0">$${product.price}</p>
              </ul>
            </div>
          </div>
        </div>
      `;
		$("#list").append(productHtml);
	}
}

// 生成分頁按鈕
function renderPagination() {
	const totalPages = Math.ceil(allProducts.length / itemsPerPage);
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
		renderProductList(getCurrentPageProducts());
		renderPagination();
	});
}

//點擊分頁按鈕後，捲動到最上方
$(document).on('click', '.page-item', function() {
	//    console.log("HEY");
	$("html, body").animate({ scrollTop: 0 }, "fast");
});


function searchItem() {
	var keyword = $("#searchInput").val().trim();
	console.log(keyword);
	$("#searchInput").val("");
	if (keyword != "") {
		searchBykeyword(keyword);
	} else {
		alert("請輸入關鍵字");
	}
}

//網頁載入時執行
$(document).ready(function() {
	// 網頁載入時加載所有商品列表
	loadAllProducts();

	// 載入購物車清單數量
//	countCartItem();

	// 重新載入所有商品列表
	$("#allProductsBtn").click(function() {
		loadAllProducts();
	});

	// 點擊「分類1」按鈕時過濾顯示分類1的商品
	$("#category1Btn").click(function() {
		filterProductsByCategory(1);
	});

	// 點擊「分類2」按鈕時過濾顯示分類2的商品
	$("#category2Btn").click(function() {
		filterProductsByCategory(2);
	});

	// 點擊「分類3」按鈕時過濾顯示分類3的商品
	$("#category3Btn").click(function() {
		filterProductsByCategory(3);
	});

	// 點擊「搜尋」按鈕時顯示輸入的關鍵字商品
	$("#searchIcon").click(function() {
		searchItem();
	});

	//搜尋框按enter等同按下搜尋icon
	$("#searchInput").keydown(function(event) {
		// 13代表Enter鍵的鍵碼
		if (event.keyCode === 13) {
			$("#searchIcon").click();
		}
	});

	//排序
	$("#sortOptions").change(function() {
		var selectedOption = $(this).val();
		if (selectedOption === "lowToHigh") {
			// 價格由低到高排序
			allProducts.sort(function(a, b) {
				return a.price - b.price;
			});
		} else if (selectedOption === "highToLow") {
			// 價格由高到低排序
			allProducts.sort(function(a, b) {
				return b.price - a.price;
			});
		} else {
			//預設順序
			allProducts.sort(function(a, b) {
				return a.id - b.id;
			});
		}
		renderProductList(allProducts);
	});

});