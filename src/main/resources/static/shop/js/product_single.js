const urlParams = new URLSearchParams(window.location.search);
const product_id = urlParams.get("id");
console.log("產品編號：" + product_id);
const productImageElement = document.getElementById("productImage");
const imagePopupElement = document.getElementById("imagePopup");
const productNameElement = document.getElementById("productName");
const productPriceElement = document.getElementById("productPrice");
const productSpecElement = document.getElementById("productSpec");
const productDescriptElement = document.getElementById("productDescript");
const url = `product/${product_id}`;
let price = 0;
let quantity = 0;

//商品加入購物車
function addToCart() {
    var memberId = 1;

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
        });
}


// 載入商品資訊;
fetch(url)
    .then((res) => res.json())
    .then((product) => {
        //獲得單個產品資訊
        const singleProduct = product;
        console.log(singleProduct);
        price = singleProduct.price;
        productNameElement.innerText = singleProduct.productName;
        productPriceElement.innerText = "$" + singleProduct.price;
        productSpecElement.innerText = singleProduct.spec;
        productDescriptElement.innerText = singleProduct.description;
        productImageElement.src =
            "data:image/png;base64," + singleProduct.image;
        imagePopupElement.href =
            "data:image/png;base64," + singleProduct.image;
    });

//var quantitiy = 0;
$(".quantity-right-plus").click(function (e) {
    // 移除按鈕預設行為
    e.preventDefault();
    // 取得數量
    quantity = parseInt($("#quantity").val());
    // 數量+1
    $("#quantity").val(quantity + 1);
    quantity = parseInt($("#quantity").val());
    console.log(quantity);
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