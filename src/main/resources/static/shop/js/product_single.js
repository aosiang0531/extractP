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

const urlParams = new URLSearchParams(window.location.search);
const productImageElement = document.getElementById("productImage");
const imagePopupElement = document.getElementById("imagePopup");
const productNameElement = document.getElementById("productName");
const productPriceElement = document.getElementById("productPrice");
const productSpecElement = document.getElementById("productSpec");
const productStockElement = document.getElementById("productStock");
const productDescriptElement = document.getElementById("productDescript");
const product_id = urlParams.get("id");
const singleProductUrl = `product/${product_id}`;

console.log("產品編號：" + product_id);

let price = 0;
let quantity = 0;

//商品加入購物車
function addToCart() {

    var newDetail = JSON.stringify({
        "product_id": product_id,
        "price": price,
        "quantity": quantity, //從商品列表加入，預設數量為1

    });
    console.log("購物車裡的：" + newDetail);

    fetch(`/orderDetail/${memberId}/unplaced`, {
        method: "PUT", headers: { 'Content-Type': 'application/json' },
        body: newDetail
    })
        .then((resp) => resp.json())
        .then((body) => {
            console.log(body);
            alert("成功加入購物車");
            countCartItem();
        });
}

// 顯示購物車明細數量
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
//	}, 100); // 延遲處理
//}


// 載入商品資訊;
fetch(singleProductUrl)
    .then((res) => res.json())
    .then((product) => {
        //獲得單個產品資訊
        const singleProduct = product;
        console.log(singleProduct);
        price = singleProduct.price;
        productNameElement.innerText = singleProduct.productName;
        productPriceElement.innerText = "$" + singleProduct.price;
        productSpecElement.innerText = singleProduct.spec;
        productStockElement.innerText = singleProduct.stock;
        productDescriptElement.innerText = singleProduct.description;
        productImageElement.src =
            "data:image/png;base64," + singleProduct.image;
        imagePopupElement.href =
            "data:image/png;base64," + singleProduct.image;
        countCartItem();
    });

//var quantitiy = 0;
$(".quantity-right-plus").click(function (e) {
    // 移除按鈕預設行為
    e.preventDefault();
    // 取得數量
    quantity = parseInt($("#quantity").val());
    
    // 數量小於庫存時才能+1
    if(quantity < productStockElement.textContent){
	    $("#quantity").val(quantity + 1);
	    quantity = parseInt($("#quantity").val());
	    console.log(quantity);
    }
});

$(".quantity-left-minus").click(function (e) {
    e.preventDefault();
    quantity = parseInt($("#quantity").val());
    // 數量大於0時才能-1
    if (quantity > 1) {
        $("#quantity").val(quantity - 1);
        quantity = parseInt($("#quantity").val());
        console.log(quantity);
    }
});

$("#addToCart").click(function () {

    console.log("加入購物車");
    addToCart();

});