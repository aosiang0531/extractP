$(function() {

	const tepURL = new URLSearchParams(window.location.search);
	const Id = tepURL.get("orderId");

	const tbody = document.querySelector("tbody");
	const pay = document.querySelector("#pay");
	const info_url = "orderDetail/" + Id + "/info";
	const pay_url = "orderDetail/" + Id + "/total";
	const all_url = "orderDetail/" + Id + "/all";
	const sum_url = "orderInfo/order/" + Id;
	const add_url = "orderDetail";


	let order_detail_ids = [];
	let beforetotal = 0;

	//==========訂單明細=========//
	fetch(info_url)
		.then(resp => resp.json())
		.then(data => {
			for (let order_detail of data) {
				const image = "data:image/png;base64," + order_detail.product_image;
				const order_detail_id = order_detail.order_detail_id;
				tbody.innerHTML += `
					<tr class="text-center" name="${order_detail_id}">
							<td class="image-prod" name="product"><img class="img" src="${image}"></td>

							<td class="product-name"><h3>${order_detail.product_name}</h3></td>

							<td class="price" name="price">$${order_detail.order_product_price}</td>

							<td class="quantity">
								<input type="button" class="decrease" value="-">
								<input type="number" class="quantity input-number" style="width:50px" name="quantity" value="${order_detail.order_detail_quantity}">
								<input type="button" class="increase" value="+">
							</td>

							<td class="sub-total" name="subtotal">$${order_detail.subtotal}</td>
							<td class="product-remove"><a href="#"><span class="icon-close"></span></a></td>
							<td></td>
					</tr>
					`;
				beforetotal += order_detail.subtotal;
				order_detail_ids.push(order_detail_id);

			}

			//==========付款金額=========//
			fetch(pay_url)
				.then(resp => resp.json())
				.then(payment => {
					html = `
				                <h3 style="color:black;">付款詳情</h3>
				                <p class="d-flex">
				                      <span style="color:black;">商品總金額</span>
				                      <span style="color:#c49b63;" name="beforetotal" >$${payment.total}</span>
				                </p>
				                   <p class="d-flex" >
				                  <span style="color:black;">運費</span>
				                  <span style="color:#c49b63;" name="shippingfee">$60</span>
				                </p>
				                <hr>
				                <p class="d-flex total-price">
				                    <span style="color:black;">總付款金額</span>
				                    <span style="color:#c49b63;" name="total">$${payment.total + 60}</span>
				                </p>
				                <p class="text-center"><a href="#" class="btn btn-primary py-3 px-4" id="checkout">前往付款</a></p>
				                <p class="text-center"><a href="#" class="btn btn-primary py-3 px-4" id="save">儲存訂單</a></p>
				                `;
					pay.innerHTML = html;

				});

			//==========加價購=========//


			//=========== 點選前往付款 =========== //
			$(document).on("click", "#checkout", function() {
				let elements = Array.from(document.querySelectorAll("input[name='quantity']"));
				const total = document.querySelector("[name='total']").textContent.replace('$', '');

				let datalist = [];
				for (var i = 0; i < elements.length; i++) {
					let data = JSON.stringify({
						"id": order_detail_ids[i],
						"quantity": elements[i].value
					});
					datalist.push(data);
				}

				//存入更新後的數量
				fetch(all_url, {
					method: 'PUT',
					body: '[' + datalist + ']',
					headers: {
						'Content-Type': 'application/json; charset=utf-8',
					},
				})
					.then(resp => resp.json())
					.then(data => {
						console.log(data);
						console.log("success-quantity");
					})

				//存入更新後的總金額
				$.ajax({
					url: sum_url,
					type: "PUT",
					data: JSON.stringify({
						"payment_amount": total,
					}),
					dataType: "json",
					contentType: "application/json; charset=utf-8",
					success: function(data) {
						console.log(data);
						console.log("success-total");
					},
					error: function(xhr) {
						console.log("error-total");
						console.log(xhr);
					}
				});


				window.location.href = 'checkout.html?orderId=' + Id;


			});

			//============= 儲存訂單 ============= //
			$(document).on("click", "#save", function() {
				let elements = Array.from(document.querySelectorAll("input[name='quantity']"));
				const total = document.querySelector("[name='total']").textContent.replace('$', '');

				let datalist = [];
				for (var i = 0; i < elements.length; i++) {
					let data = JSON.stringify({
						"id": order_detail_ids[i],
						"quantity": elements[i].value
					});
					datalist.push(data);
				}

				//存入更新後的數量
				fetch(all_url, {
					method: 'PUT',
					body: '[' + datalist + ']',
					headers: {
						'Content-Type': 'application/json; charset=utf-8',
					},
				})
					.then(resp => resp.json())
					.then(data => {
						console.log(data);
						console.log("success-quantity");
					})

				//存入更新後的總金額
				$.ajax({
					url: sum_url,
					type: "PUT",
					data: JSON.stringify({
						"payment_amount": total,
					}),
					dataType: "json",
					contentType: "application/json; charset=utf-8",
					success: function(data) {
						console.log(data);
						console.log("success-total");
					},
					error: function(xhr) {
						console.log("error-total");
						console.log(xhr);
					}
				});


				window.location.href = 'orderhistory.html?memberId=' + 1;	//TODO


			});


		});

	//=========== 刪除購物車的一筆訂單明細 =============//
	$(document).on("click", ".product-remove", function() {
		const detailId = $(this).closest(".text-center").attr("name");
		const delete_url = "orderDetail/" + detailId;

		let r = confirm("確認刪除?");
		if (r) {
			$(this).closest(".text-center").remove();

			data = { "id": detailId };

			fetch(delete_url, {
				method: "DELETE",
				headers: {
					'Content-Type': 'application/json; charset=utf-8',
				},
				body: JSON.stringify(data)
			})
				.then(resp => resp.json())
				.then(data => {
					console.log(data);
					console.log("success-delete");
				})
				.catch(error => {
					console.log("Error:", error);
				});

			location.reload();

		}


	});

	//========== 數量變動 =============//
	//數量增加
	$(document).on("click", ".increase", function() {
		var num = $(this).siblings(".input-number").val();
		num++;
		$(this).siblings(".input-number").val(num);
		$(this).siblings(".input-number").attr("value", num);
		var price = $(this).parents(".quantity").siblings(".price").text().substr(1);
		var sum = (num * price);
		$(this).parent().siblings(".sub-total").text('$' + sum);

		updateTotal();
	});

	//數量減少
	$(document).on("click", ".decrease", function() {
		var num = $(this).siblings(".input-number").val();
		num--;
		$(this).siblings(".input-number").val(num);
		$(this).siblings(".input-number").attr("value", num);
		var price = $(this).parents(".quantity").siblings(".price").text().substr(1);
		var sum = (num * price);
		$(this).parent().siblings(".sub-total").text('$' + sum);

		updateTotal();
	});



	//=========== 商品總金額 =============//
	const updateTotal = () => {
		const subTotalElements = document.querySelectorAll(".sub-total");
		let total = 0;
		subTotalElements.forEach(element => {
			const subTotal = parseFloat(element.textContent.substr(1));
			total += subTotal;
		});

		const beforeTotalElement = document.querySelector("[name='beforetotal']");
		const totalElement = document.querySelector("[name='total']");

		beforeTotalElement.textContent = `$${total}`;
		totalElement.textContent = `$${(total + 60)}`;
	};


})