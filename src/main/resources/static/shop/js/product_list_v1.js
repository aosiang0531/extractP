var allProducts = [];


//商品加入購物車
function addToCart(productId, price) {
	var memberId = 1;

	var newDetail = JSON.stringify({
		"product_id": productId,
		//加入購物車時應該還不需更新價格，結帳時才要，「專題先加」
		"price": price,
		"quantity": 1, //從商品列表加入，預設數量為1
		//"order_id": cartId //orderId由detailController的方法由會員id自動取得
	});
	// console.log(newDetail);

	fetch(`/orderDetail/${memberId}/unplaced`, {
		method: "PUT", headers: { 'Content-Type': 'application/json' },
		body: newDetail
	})
		.then((resp) => resp.json())
		.then((body) => {
			console.log(body);
			alert("成功加入購物車");

		});
}

//取得會員購物車
//function loadCart(memberId) {
//	//假會員編號：1，記得改！！
//	var memberId = 3;
//	const url = `/orderInfo/${memberId}/unplaced`;
//	fetch(url, { method: 'POST' })
//		.then((res) => res.json())
//		.then((cart) => {
//			console.log("購物車編號：" + cart.id);
//			// 取得購物車編號
//			sessionStorage.setItem('cartId', cart.id);
//		});
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
	// 從controller叫出所有商品的json
	const url = "/shop/product/onSale";
	fetch(url)
		.then((res) => res.json())
		.then((productList) => {
			allProducts = productList;
			//            console.log(productList);
			renderProductList(allProducts);
		});
}

// 根據分類編號過濾商品列表並渲染到頁面上
function filterProductsByCategory(categoryId) {
	console.log("分類編號:" + categoryId);

	var filteredProducts = allProducts.filter(function (product) {
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
        <a href="index.html">咖啡商城</a> /
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

$(document).ready(function () {
	// 網頁載入時加載所有商品列表
	loadAllProducts();

	// 載入會員購物車ID
	//	loadCart();

	// 清空麵包屑區域的內容
	$("#breadcrumb").empty();

	// 生成麵包屑的 HTML 代碼
	var breadcrumbHtml = `
        <a href="product_list.html">咖啡商城</a> /
        <span>所有商品</span>
        `;

	// 插入麵包屑的 HTML 到麵包屑區域
	$("#breadcrumb").html(breadcrumbHtml);

	// 重新載入所有商品列表
	$("#allProductsBtn").click(function () {
		loadAllProducts();
		$("#breadcrumb").empty();
		// 生成麵包屑的 HTML 代碼
		var breadcrumbHtml = `
		    <a href="product_list.html">咖啡商城</a> /
		    <span>所有商品</span>
		    `;

		// 插入麵包屑的 HTML 到麵包屑區域
		$("#breadcrumb").html(breadcrumbHtml);
	});

	// 點擊「分類1」按鈕時過濾顯示分類1的商品
	$("#category1Btn").click(function () {
		filterProductsByCategory(1);
	});

	// 點擊「分類2」按鈕時過濾顯示分類2的商品
	$("#category2Btn").click(function () {
		filterProductsByCategory(2);
	});

	// 點擊「分類3」按鈕時過濾顯示分類3的商品
	$("#category3Btn").click(function () {
		filterProductsByCategory(3);
	});

	// 點擊「搜尋」按鈕時顯示輸入的關鍵字商品
	$("#searchIcon").click(function () {
		var keyword = $("#searchInput").val().trim();
		console.log(keyword);
		$("#searchInput").val("");
		if (keyword != "") {
			searchBykeyword(keyword);
		} else {
			alert("請輸入關鍵字");
		}

	});

	//搜尋框按enter等同按下搜尋icon
	$("#searchInput").keydown(function (event) {
		if (event.keyCode === 13) { // 13 代表 Enter 鍵的鍵碼
			$("#searchIcon").click(); // 點擊 id 為 searchIcon 的元素
		}
	});


	//排序
	$("#sortOptions").change(function () {
		var selectedOption = $(this).val();
		if (selectedOption === "lowToHigh") {
			// 價格由低到高排序
			allProducts.sort(function (a, b) {
				return a.price - b.price;
			});
		} else if (selectedOption === "highToLow") {
			// 價格由高到低排序
			allProducts.sort(function (a, b) {
				return b.price - a.price;
			});
		} else {
			//預設順序
			allProducts.sort(function (a, b) {
				return a.id - b.id;
			});
		}
		renderProductList(allProducts);
	});


	//分頁

});