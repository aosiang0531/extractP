$(function() {

	const tepURL = new URLSearchParams(window.location.search);
	let memberId = tepURL.get("memberId"); //TODO
	memberId = 1; //TODO

	const allorder = document.querySelector("#allorder");
	const unPlaced = document.querySelector("#unPlaced");
	const unpaid = document.querySelector("#unpaid");
	const process = document.querySelector("#process");
	const delivery = document.querySelector("#delivery");
	const complete = document.querySelector("#complete");
	const startDateInput = document.querySelector("#startDate");
	const endDateInput = document.querySelector("#endDate");
	const allorder_url = "orderInfo/" + memberId + "/member";
	const unPlaced_url = "orderInfo/" + memberId + "/orderStatus/未成立";
	const unpaid_url = "orderInfo/" + memberId + "/paymentStatus/待付款";
	const process_url = "orderInfo/" + memberId + "/shippingStatus/待出貨";
	const delivery_url = "orderInfo/" + memberId + "/shippingStatus/已出貨";
	const complete_url = "orderInfo/" + memberId + "/shippingStatus/已完成";
	const date_url = "orderInfo/pickDate";

	//====================各訂單的頁籤分類====================//	
	//全部頁籤			
	fetch(allorder_url)
		.then(resp => resp.json())
		.then(data => {
			orderlist(allorder, data);
		});

	//未成立頁籤			
	fetch(unPlaced_url)
		.then(resp => resp.json())
		.then(data => {
			orderlist(unPlaced, data);
		});

	//待付款頁籤		
	fetch(unpaid_url)
		.then(resp => resp.json())
		.then(data => {
			orderlist(unpaid, data);
		});

	//待出貨頁籤				
	fetch(process_url)
		.then(resp => resp.json())
		.then(data => {
			orderlist(process, data);
		});

	//待收貨頁籤		
	fetch(delivery_url)
		.then(resp => resp.json())
		.then(data => {
			orderlist(delivery, data);
		});

	//已完成頁籤		
	fetch(complete_url)
		.then(resp => resp.json())
		.then(data => {
			orderlist(complete, data);
		});


	//====================訂單詳情內容====================//

	// 關閉 訂單內容
	$("button.btn_modal_close, div.overlay").on("click", function(e) {
		const overlay = document.querySelector('div[name="overlaycontent"]');
		overlay.innerHTML = "";

		$("div.overlay").hide();
	});


	//未成立訂單 -> 購物車頁面
	$(document).on('click', 'button[name="unpaidbtn"]', function() {
		var order_id = $(this).closest('tr').find('td[name="order_id"]').text();
		window.location.href = 'cart.html?orderId=' + order_id;
	})

	//燈箱 訂單詳情
	$(document).on('click', 'button[name="contentbtn"]', function() {
		$("div.overlay").show();

		const overlay = document.querySelector('div[name="overlaycontent"]');
		const order_id = $(this).closest('tr').find('td[name="order_id"]').text();
		var url = "orderDetail/" + order_id + "/info";

		fetch(url)
			.then(resp => resp.json())
			.then(data => {
				for (let order of data) {
					overlay.innerHTML += `
                    <div class="row">
                    	<label name="proud_name" class="col-md-4">${order.product_name}</label>
                        <label name="order_detail_quantity" class="col-md-2">${order.order_detail_quantity}</label>
                        <label name="subtotal" class="col-md-2">$${order.subtotal}</label>
                    </div>
				`;
				}


			});
	})

	//====================全部訂單的日期查詢====================//
	startDateInput.addEventListener("change", function() {
		const startDate = startDateInput.value;
		startDateInput.setAttribute("value", startDate);
		endDateInput.setAttribute("min", startDate);
	});

	endDateInput.addEventListener("change", function() {
		const endDate = endDateInput.value;
		endDateInput.setAttribute("value", endDate);
	});

	let isAppended = false;
	$(document).on('click', '.custom-button', function() {
		let data = JSON.stringify({
			"member_id": memberId,
			"date1": startDateInput.value,
			"date2": endDateInput.value
		});

		fetch(date_url, {
			method: 'POST',
			body: data,
			headers: {
				'Content-Type': 'application/json; charset=utf-8',
			},
		})
			.then(resp => resp.json())
			.then(data => {
				allorder.innerHTML = "";

				orderlist(allorder, data);

				if (!isAppended) {
					let clean = `<span class="clean-button">清除搜尋</span>`;
					$('[name="datePick"]').append(clean);
					isAppended = true;	// 設置為已經添加過清除搜尋

				}

			});



	});

	$(document).on('click', '.clean-button', function() {

		fetch(allorder_url)
			.then(resp => resp.json())
			.then(data => {
				startDateInput.value = null;
				endDateInput.value = null;
				orderlist(allorder, data);
			});

	});


	//====================頁籤功能====================//

	$("div.overlay > article").on("click", function(e) {
		e.stopPropagation();
	});

	$("a.tab").on("click", function(e) {
		e.preventDefault();

		/* 將頁籤列表移除所有 -on，再將指定的加上 -on */
		$(this).closest("ul").find("a.tab").removeClass("-on");
		$(this).addClass("-on");

		/* 找到對應的頁籤內容，加上 -on 來顯示 */
		$("div.tab").removeClass("-on");
		$("div.tab." + $(this).attr("data-target")).addClass("-on");
	});


	//依tab顯示對應狀態的訂單
	function orderlist(tabName, data) {
		tabName.innerHTML = "";
		for (let order of data) {
			const time = (new Date(order.created_date)).toISOString().slice(0, 19).replace(/-/g, "/").replace("T", " ");

			if (order.status == "未成立") {
				html = `
	                    <tr class="text-center">	
		                    <td name="order_id"><h6>${order.id}</h6></td>
		
		                    <td name="order_date"><h6>${time}</h6></td>
		
		                    <td name="total" class="total-price"><h6>$${order.payment_amount}</h6></td>
		                    
							<td name="order_status" class="orderstatus"><h6>${order.status}</h6></td>
							
		                    <td name="order_payment_status" class="paymentstatus"><h6>${order.payment_status}</h6></td>
		
		                    <td name="order_shipping_status" class="shippingstatus"><h6>${order.shipping_status}</h6></td>
		    
		                    <td class="order-content">
	                        <button type="button"
	                            class="btn_modal btn btn-primary py-3 px-4" name="unpaidbtn">繼續購物</button>
	                   		</td>
	                   		
	                   		<td class="order-content">
		                        <button type="button"
		                            class="btn_modal btn btn-primary py-3 px-4 " name="contentbtn">訂單詳情</button>
		                    </td>
                   		</tr>
					`;

			} else {
				html = `
		            	<tr class="text-center">	
			             	<td name="order_id"><h6>${order.id}</h6></td>
			
			                <td name="order_date"><h6>${time}</h6></td>
			
			                <td name="total" class="total-price"><h6>$${order.payment_amount}</h6></td>
							
							<td name="order_status" class="orderstatus"><h6>${order.status}</h6></td>
							
			                <td name="order_payment_status" class="paymentstatus"><h6>${order.payment_status}</h6></td>
			
			                <td name="order_shipping_status" class="shippingstatus"><h6>${order.shipping_status}</h6></td>
							
							<th>&nbsp;
							
			                <td class="order-content">
			                	<button type="button"
			                            class="btn_modal btn btn-primary py-3 px-4 " name="contentbtn">訂單詳情</button>
			               	</td>
	                   	</tr>
						`;
			}
			tabName.innerHTML += html;
		}
	}






});
