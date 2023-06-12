	// 顯示購物車明細數量
	//	var memberId = 1; //假會員編號
	const cartItemNumber = document.querySelector('#cartItemNumber');
	function countCartItem() {
		setTimeout(() => {
			fetch(`/orderDetail/${memberId}/countItems`)
				.then((resp) => {
					if (resp.ok) {
						return resp.text();
					}
				})
				.then((count) => {
					// 更新數字
					if (count != undefined || count != null) {
						sessionStorage.setItem('cartItemNumber', count);
						cartItemNumber.innerHTML = count;
						console.log("更新購物車數字:" + count + "項商品")
					} else {
						cartItemNumber.innerHTML = 0;
						console.log("未登入購物車商品數顯示0")
					}
				})
				.catch(error => {
					// 發生錯誤時的處理邏輯
					console.log('發生錯誤:', error);
				});
		}, 200); // 非同步延遲處理，使它可以在網頁上不刷新即顯示
	}

	//JWT
	const token = localStorage.getItem("jwt");
	const userLink = document.getElementById("userLink");
	const url = `/auth?token=${encodeURIComponent(token)}`;
	const noneUser = document.getElementById("noneUser");
	const userIcon = document.getElementById("userIcon");
	var memberId;
	var memberImage;
	//	var sender;

	fetch(url)
		.then(response => response.json())
		.then(data => {
			//			sender = data.name;
			memberId = data.id;
			memberImage = "data:image/png;base64," + data.image;

			if (token === null) {
				userLink.href = "member_login.html";
				userIcon.style.display = "none"; // 隱藏圖片
			} else {
				userLink.href = "member_personalpage.html";
				noneUser.style.display = "none";
				userIcon.src = memberImage; // 顯示會員大頭照
				userIcon.style.display = "inline-block";
			}
			
			countCartItem();
		})
		.catch(error => {
			console.error(error);
		});
//刪除商品
function deleteProduct(productId) {
	if (confirm("確定刪除？")) {
		fetch(`shop/product/${productId}`, {
			method: "DELETE",
		})
			.then((response) => {
				if (response.ok) {
					console.log("刪除成功");
					//成功後刷新頁面
					alert("刪除成功！");
					location.reload();
				} else {
					console.error("刪除失敗");
					//
				}
			})
			.catch((error) => {
				console.error("發生錯誤", error);
				//
			});
	}
}

//商品上下架狀態
function changeStatus(productId, status) {
	if (confirm("確定更改上下架狀態？")) {
		fetch(`shop/product/${productId}/status/${status}`, {
			method: "POST",
		})
			.then((response) => {
				console.log(response);
				if (response.ok) {
					console.log("成功");
					//成功後刷新頁面
					alert("修改上下架成功！");
					location.reload();
				} else {
					console.error("失敗");
					//
				}
			})
			.catch((error) => {
				console.error("發生錯誤", error);
				//
			});
	}
}





(() => {


	// 獲取所有商品列表
	var allProducts = [];

	// 從controller叫出所有商品的json
	const url = "shop/product";
	fetch(url)
		.then((res) => res.json())
		.then((productList) => {
			console.log(productList);

			// 清空商品列表區域
			$("#list").empty();

			//迴圈讀出所有商品
			for (let product of productList) {

				// 讀出商品圖片
				var imgUrl = "data:image/png;base64," + product.image;
				//讀出商品上架狀態
				var status = "";
				if (product.stock == 0) {
					status = "soldOut";
				} else if (product.productStatus == "上架中") {
					status = "available";
				} else if (product.productStatus == "已下架") {
					status = "unAvailable";
				}

				// 生成各別商品HTML
				var productHtml = `
                <tr data-status=${status}>
                    <td><img src="${imgUrl}" alt="Product Image" style="width:100px;"></td>
                    <td>${product.productName}</td>
                    <td>${product.id}</td>
                    <td>${product.spec}</td>
                    <td>$${product.price}</td>
                    <td>${product.stock}</td>
                    <td>${product.sold_count}</td>
                    <td>
                        <div class="d-flex">
                            <a class="btn btn-primary mr-2" href="/product_edit.html?id=${product.id}">編輯</a>

                            <form id="changeStateForm" method="POST">
							 <button id="changeStateButton" type="button" class="btn mr-2 ${status}" onclick="changeStatus(${product.id},'${status}')">
								  ${product.productStatus === '上架中' ? '下架' : '上架'}
								</button>

							</form>
                            <form id="deleteForm" method="POST">
							  <button id="availabilityButton" type="button" class="btn btn-danger" onclick="deleteProduct(${product.id})">删除</button>
							</form>
                        </div>
                    </td>

                </tr>                
                `;

				if (status === "soldOut" || status === "unAvailable soldOut") {
					//添加上架中商品到專屬頁籤
					$("#soldOut").append(productHtml);
				} else if (status === "available") {
					//添加上架中商品到專屬頁籤
					$("#availableProductList").append(productHtml);
				} else if (status === "unAvailable" || status === "unAvailable soldOut") {
					//添加已下架商品到專屬頁籤
					$("#unAvailableProductList").append(productHtml);
				}
				//添加到全部商品列表區域
				$("#allProductList").append(productHtml);

			}// 讀取所有商品end
		});


})();