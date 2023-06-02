//刪除商品
function deleteProduct(productId) {
	if (confirm("確定刪除？")) {
		fetch(`/shop/product/${productId}`, {
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
		fetch(`/shop/product/${productId}/status/${status}`, {
			method: "POST",
		})
			.then((response) => {
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
	const url = "product";
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
				if (product.productStatus == "上架中") {
					status = "available";
				} else if (product.productStatus == "已下架") {
					status = "unAvailable";
				}

				// 生成各別商品HTML
				var productHtml = `
                <tr data-status=${status}>
                    <td><img src="${imgUrl}" alt="Product Image" style="width:100px;"></td>
                    <td>${product.name}</td>
                    <td>${product.id}</td>
                    <td>${product.spec}</td>
                    <td>$${product.price}</td>
                    <td>${product.stock}</td>
                    <td>${product.sold_count}</td>
                    <td>
                        <div class="d-flex">
                            <a class="btn btn-primary mr-2" href="../shop/edit_product.html?id=${product.id}">編輯</a>

                            <form id="changeStateForm" method="POST">
							 <button id="changeStateButton" type="button" class="btn btn-warning mr-2 ${status}" onclick="changeStatus(${product.id},'${status}')">
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

				if (status === "available") {
					//添加上架中商品到專屬頁籤
					$("#availableProductList").append(productHtml);
				} else if (status === "unAvailable") {
					//添加已下架商品到專屬頁籤
					$("#unAvailableProductList").append(productHtml);
				}
				//添加到全部商品列表區域
				$("#allProductList").append(productHtml);

			}// 讀取所有商品end
		});


})();