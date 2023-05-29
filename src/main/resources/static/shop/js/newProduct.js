(() => {
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

    var the_form = document.querySelector('#the_form');
    // var textarea = document.querySelector('textarea');

    // ============其他=============
    // 自動調整textArea高度
    // textarea.addEventListener('input', function () {
    //     this.style.height = 'auto';
    //     this.style.height = this.scrollHeight + 'px';
    // });

    // reset按鈕
    the_form.addEventListener("reset", function () {

        //清除預覽圖片
        // var hasImg = preview_area.getElementsByTagName("img")[0];
        if (img) {
            //若存在就將它移除
            preview_area.removeChild(img);
        }
    });

    submit.addEventListener('click', () => {
        const productNameLength = product_name.value.length;
        if (productNameLength < 3 || productNameLength > 50) {
            msg.textContent = '產品名長度須介於3~50字元';
            return;
        }

        msg.textContent = '';
        const fileReader = new FileReader();
        fileReader.addEventListener('load', e => {

            // 將傳入的圖片檔案轉成base64
            const imageBase64 = btoa(e.target.result);

            var jsonn = JSON.stringify({
                category_id: category_id.value,
                name: product_name.value,
                description: product_description.value,
                spec: product_spec.value,
                image: imageBase64,
                stock: product_stock.value,
                price: product_price.value
            })
            // console.log(jsonn);

            fetch('http://localhost:8080/product', {
                method: 'POST',
                headers: { 'Content-Type': 'application/json' },
                body: jsonn

            })
                .then(resp => resp.json())
                .then(body => {
                    const { successful } = body;
                    if (successful) {
                        // for (let input of inputs) {
                        //     submit.disabled = true;
                        // }
                        submit.disabled = true;
                        msg.className = 'info';
                        msg.textContent = '產品新增成功';
                    } else {
                        msg.className = 'error';
                        msg.textContent = '產品新增失敗';
                    }
                });
        });
        fileReader.readAsBinaryString(image.files[0]);
    });


    image.addEventListener('change', () => {
        const file = image.files[0];
        // console.log(file);
        if (file) {
            img.src = URL.createObjectURL(file);

        }
    });
})();