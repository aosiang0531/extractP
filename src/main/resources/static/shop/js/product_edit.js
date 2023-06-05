(() => {
    const submit = document.querySelector('#submit');
    const title = document.querySelector('#title')
    const idAtTitle = document.querySelector('#idAtTitle')
    const category_id = document.querySelector('#category_id');
    const product_name = document.querySelector('#product_name');
    const image = document.querySelector('#image');
    const product_spec = document.querySelector('#product_spec');
    const product_description = document.querySelector('#product_description');
    const product_price = document.querySelector('#product_price');
    const product_stock = document.querySelector('#product_stock');
    const msg = document.querySelector('#msg');
    const img = document.querySelector('#img');
    const the_form = document.querySelector('#the_form');


    //先將商品資料載入回來
    const urlParams = new URLSearchParams(window.location.search);
    const product_id = urlParams.get("id");
    console.log("產品編號：" + product_id);

    const url = `product/${product_id}`;

    fetch(url)
        .then((res) => res.json())
        .then((product) => {
            //獲得欲修改的產品資訊
            const singleProduct = product;
            console.log(singleProduct);


            if (singleProduct.id != undefined) {
                idAtTitle.innerHTML = singleProduct.id;
                category_id.value = singleProduct.categoryId;
                product_name.value = singleProduct.productName;
                product_spec.value = singleProduct.spec;
                product_price.value = singleProduct.price;
                product_stock.value = singleProduct.stock;
                product_description.value = singleProduct.description;
                img.src = "data:image/png;base64," + singleProduct.image;
            } else {
                title.innerHTML = "查無此商品！";
            }

        });



    // reset按鈕
    the_form.addEventListener("reset", function () {

        //清除預覽圖片
        // var hasImg = preview_area.getElementsByTagName("img")[0];
        if (img) {
            //若存在就將它移除
            preview_area.removeChild(img);
        }
    });


    //修改資料
    submit.addEventListener('click', () => {


        const errorMessages = [];
        //檢查輸入的內容
        if (category_id.value === "") {
            errorMessages.push('請選擇分類');
        }
        console.log("分類為" + category_id.value);
        if (product_name.value.length < 3 || product_name.value.length > 50) {
            errorMessages.push('產品名長度須介於3~50字元');
        }

        // 檢查商品規格是否為空
        if (!product_spec.value.trim()) {
            errorMessages.push('請輸入商品規格');
        }

        // 檢查商品價格是否有效
        const productPrice = parseFloat(product_price.value);
        if (isNaN(productPrice) || productPrice <= 0) {
            errorMessages.push('請輸入有效的商品價格(不得為空或0元)');
        }

        // 檢查商品庫存是否有效
        const productStock = parseInt(product_stock.value);
        if (isNaN(productStock) || productStock < 0 || productStock >= 100) {
            errorMessages.push('請輸入有效的商品庫存數量(0~100)');
        }

        // 檢查商品描述是否為空
        if (!product_description.value.trim()) {
            errorMessages.push('請輸入商品說明');
        }

        if (errorMessages.length > 0) {
            msg.innerHTML = errorMessages.join('<br>');
            return;
        }

        var file = image.files[0];
        var imageBase64 = null;

        if (file) {
            var fileReader = new FileReader();
            fileReader.onload = function (event) {
                var tempImg = event.target.result;
                imageBase64 = tempImg.replace(/^data:image\/(png|jpeg|jpg);base64,/, '');
                submitFormWithImage(imageBase64);
            };
            fileReader.readAsDataURL(file);
        } else {
            submitFormWithImage(null);
        }
    });

    function submitFormWithImage(imageData) {
        var productJson = JSON.stringify({
            categoryId: category_id.value,
            productName: product_name.value,
            description: product_description.value,
            spec: product_spec.value,
            image: imageData,
            stock: product_stock.value,
            price: product_price.value
        });

        fetch(`/shop/product/${product_id}`, {
            method: 'put',
            headers: { 'Content-Type': 'application/json' },
            body: productJson
        })
            .then((resp) => resp.json())
            .then((body) => {
                const { successful } = body;
                if (successful) {
                    submit.disabled = true;
                    msg.className = 'info';
                    msg.textContent = '產品修改成功';
                    alert("產品修改成功");
                    window.history.back();
                } else {
                    msg.className = 'error';
                    msg.textContent = '產品修改失敗';
                }
            });
    }


    image.addEventListener('change', function () {
        var file = image.files[0];
        if (file) {
            img.src = URL.createObjectURL(file);
        }
    });


})();