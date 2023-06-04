// 自動調整textArea高度
var textarea = document.querySelector('#product_description');
textarea.addEventListener('input', function () {
    console.log("輸入框");
    this.style.height = 'auto';
    this.style.height = this.scrollHeight + 'px';
});


(() => {
    // ============用到的元素=============
    const submit = document.querySelector('#submit');
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


    // reset按鈕
    the_form.addEventListener("reset", function () {

        //清除預覽圖片
        // var hasImg = preview_area.getElementsByTagName("img")[0];
        if (img) {
            //若存在就將它移除
            img.src = "";
        }
    });

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
        if (isNaN(productStock) || productStock <= 0 || productStock >= 100) {
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

        msg.textContent = '';

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

        fetch('/shop/product', {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: productJson
        })
            .then(function (resp) {
                return resp.json();
            })
            .then(function (body) {
                var successful = body.successful;
                if (successful) {
                    submit.disabled = true;
                    msg.className = 'info';
                    msg.textContent = '產品新增成功';
                } else {
                    msg.className = 'error';
                    msg.textContent = '產品新增失敗';
                }
            })
            .catch(function (error) {
                console.error(error);
                msg.className = 'error';
                msg.textContent = '發生錯誤，請稍後再試';
            });
    }

    image.addEventListener('change', function () {
        var file = image.files[0];
        if (file) {
            img.src = URL.createObjectURL(file);
        }
    });


})();