var allProducts = [];

// 獲取已上架商品列表並渲染到頁面上
function loadAllProducts() {
    // 從controller叫出所有商品的json
    const url = "/shop/product/onSale";
    fetch(url)
        .then((res) => res.json())
        .then((productList) => {
            allProducts = productList;
            console.log(productList);
            renderProductList(allProducts);
        });
}

// 根據分類編號過濾商品列表並渲染到頁面上
function filterProductsByCategory(category_id) {
    console.log("分類編號:" + category_id);
    var filteredProducts = allProducts.filter(function (product) {
        return product.category_id === category_id;
    });

    var selectedOption = $("#sortOptions").val();

    if (selectedOption === "lowToHigh") {
        // 根据價格由低到高排序
        filteredProducts.sort(function (a, b) {
            return a.price - b.price;
        });
    } else if (selectedOption === "highToLow") {
        // 根据價格由高到低排序
        filteredProducts.sort(function (a, b) {
            return b.price - a.price;
        });
    }

    renderProductList(filteredProducts);

    var categoryName;
    switch (category_id) {
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
        <a>${categoryName}</a> /
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
                  <li><a class="btn btn-primary text-white mt-2" href="../shop/product-single.html?id=${product.id}">
                    <i class="icon icon-eye"></i></a></li>
                  <li><a class="btn btn-primary text-white mt-2" href="/hello2.html?id=3">
                    <i class="icon icon-cart-plus"></i></a></li>
                </ul>
              </div>
            </div>
            <div class="card-body">
              <a href="${product.id}.html" class="h5 text-decoration-none">${product.name}</a>
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

    // 清空麵包屑區域的內容
    $("#breadcrumb").empty();

    // 生成麵包屑的 HTML 代碼
    var breadcrumbHtml = `
        <a href="shop/shop.html">咖啡商城</a> /
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
    <a href="shop/shop.html">咖啡商城</a> /
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

    //
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