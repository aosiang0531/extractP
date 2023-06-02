$(function() {

	const tepURL = new URLSearchParams(window.location.search);
	const Id = tepURL.get("memberId");
	memberId = 1; //TODO

	const allorder = document.querySelector("#allorder");
	const placed = document.querySelector("#orderplaced");
	const unpaid = document.querySelector("#orderunpaid");
	const process = document.querySelector("#orderprocess");
	const delivery = document.querySelector("#orderdelivery");
	const complete = document.querySelector("#ordercomplete");
	const allorder_url = "orderInfo/" + memberId + "/member";
	const placed_url = "orderInfo/" + memberId + "/orderstatus/未成立";
	const unpaid_url = "orderInfo/" + memberId + "/paymentstatus/待付款";
	const process_url = "orderInfo/" + memberId + "/shippingstatus/待出貨";
	const delivery_url1 = "orderInfo/" + memberId + "/shippingstatus/已出貨";
	const delivery_url2 = "orderInfo/" + memberId + "/shippingstatus/運送中";
	const complete_url = "orderInfo/" + memberId + "/shippingstatus/已完成";

	//====================各訂單的頁籤分類====================//	
	//	全部頁籤			
	fetch(allorder_url)
		.then(resp => resp.json())
		.then(data => {
			for (let order of data) {
				const time = (new Date(order.created_date)).toISOString().slice(0, 19).replace(/-/g, "/").replace("T", " ");

				if (order.status == "未成立") {
					allorder.innerHTML += `
	                    <tr class="text-center">	
		                    <td name="order_id"><h6>${order.id}</h6></td>
		
		                    <td name="order_date"><h6>${time}</h6></td>
		
		                    <td name="total" class="total-price"><h6>$${order.payment_amount}</h6></td>
		
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
					allorder.innerHTML += `
		            	<tr class="text-center">	
			             	<td name="order_id"><h6>${order.id}</h6></td>
			
			                <td name="order_date"><h6>${time}</h6></td>
			
			                <td name="total" class="total-price"><h6>$${order.payment_amount}</h6></td>
			
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


			}
		});

	//未成立頁籤			
	fetch(placed_url)
		.then(resp => resp.json())
		.then(data => {
			for (let order of data) {
				const time = (new Date(order.created_date)).toISOString().slice(0, 19).replace(/-/g, "/").replace("T", " ");

				placed.innerHTML += `
	                <tr class="text-center">
	
	                	<td name="order_id"><h6>${order.id}</h6></td>
	
	                    <td name="order_date"><h6>${time}</h6></td>
	
	                    <td name="total" class="total-price"><h6>$${order.payment_amount}</h6></td>
	
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
			}
		});

	//待付款頁籤		
	fetch(unpaid_url)
		.then(resp => resp.json())
		.then(data => {
			for (let order of data) {
				const time = (new Date(order.created_date)).toISOString().slice(0, 19).replace(/-/g, "/").replace("T", " ");

				if (order.payment_method != "貨到付款") {
					unpaid.innerHTML += `
	            	<tr class="text-center">
	
	                	<td name="order_id"><h6>${order.id}</h6></td>
	
	                    <td name="order_date"><h6>${time}</h6></td>
	
	                    <td name="total" class="total-price"><h6>$${order.payment_amount}</h6></td>
	
	                    <td name="order_payment_status" class="paymentstatus"><h6>${order.payment_status}</h6></td>
	
	                    <td name="order_shipping_status" class="shippingstatus"><h6>${order.shipping_status}</h6></td>
	                    
	                    <td class="order-content">
			             	<button type="button"
			                 	class="btn_modal btn btn-primary py-3 px-4 " name="contentbtn">訂單詳情</button>
			            </td>
	                    
	                </tr>
					`;
				}

			}
		});

	//待出貨頁籤				
	fetch(process_url)
		.then(resp => resp.json())
		.then(data => {
			for (let order of data) {
				const time = (new Date(order.created_date)).toISOString().slice(0, 19).replace(/-/g, "/").replace("T", " ");
				process.innerHTML += `
	            	<tr class="text-center">
	
	                    <td name="order_id"><h6>${order.id}</h6></td>
	
	                    <td name="order_date"><h6>${time}</h6></td>
	
	                    <td name="total" class="total-price"><h6>$${order.payment_amount}</h6></td>
	
	                    <td name="order_payment_status" class="paymentstatus"><h6>${order.payment_status}</h6></td>
	
	                    <td name="order_shipping_status" class="shippingstatus"><h6>${order.shipping_status}</h6></td>
	
	                    <td class="order-content">
	                        <button type="button"
	                            class="btn_modal btn btn-primary py-3 px-4" name="contentbtn">訂單詳情</button>
	                    </td>
	                </tr>
					`;
			}
		});

	//處理中頁籤		
	fetch(delivery_url1)
		.then(resp => resp.json())
		.then(data => {
			for (let order of data) {
				const time = (new Date(order.created_date)).toISOString().slice(0, 19).replace(/-/g, "/").replace("T", " ");

				delivery.innerHTML += `
	            	<tr class="text-center">
	
	                    <td name="order_id"><h6>${order.id}</h6></td>
	
	                    <td name="order_date"><h6>${time}</h6></td>
	
	                    <td name="total" class="total-price"><h6>$${order.payment_amount}</h6></td>
	
	                    <td name="order_payment_status" class="paymentstatus"><h6>${order.payment_status}</h6></td>
	
	                    <td name="order_shipping_status" class="shippingstatus"><h6>${order.shipping_status}</h6></td>
	
	                    <td class="order-content">
	                        <button type="button"
	                            class="btn_modal btn btn-primary py-3 px-4" name="contentbtn">訂單詳情</button>
	                    </td>
	                </tr>
					`;
			}

			fetch(delivery_url2)
				.then(resp => resp.json())
				.then(data => {
					for (let order of data) {
						const time = (new Date(order.created_date)).toISOString().slice(0, 19).replace(/-/g, "/").replace("T", " ");

						delivery.innerHTML += `
		                    	<tr class="text-center">
		
		                    		<td name="order_id"><h6>${order.id}</h6></td>
		
		                   			<td name="order_date"><h6>${time}</h6></td>
		
		                    		<td name="total" class="total-price"><h6>$${order.payment_amount}</h6></td>
		
		                    		<td name="order_payment_status" class="paymentstatus"><h6>${order.payment_status}</h6></td>
		
		                    		<td name="order_shipping_status" class="shippingstatus"><h6>${order.shipping_status}</h6></td>
		
		                    		<td class="order-content">
		                        		<button type="button"
		                            		class="btn_modal btn btn-primary py-3 px-4" name="contentbtn">訂單詳情</button>
		                    		</td>
		                		</tr>
							`;
					}
				});
		});

	//已完成頁籤		
	fetch(complete_url)
		.then(resp => resp.json())
		.then(data => {
			for (let order of data) {
				const time = (new Date(order.created_date)).toISOString().slice(0, 19).replace(/-/g, "/").replace("T", " ");

				complete.innerHTML += `
	                    <tr class="text-center">
	
	                    <td name="order_id"><h6>${order.id}</h6></td>
	
	                    <td name="order_date"><h6>${time}</h6></td>
	
	                    <td name="total" class="total-price"><h6>$${order.payment_amount}</h6></td>
	
	                    <td name="order_payment_status" class="paymentstatus"><h6>${order.payment_status}</h6></td>
	
	                    <td name="order_shipping_status" class="shippingstatus"><h6>${order.shipping_status}</h6></td>
	
	                    <td class="order-content">
	                        <button type="button"
	                            class="btn_modal btn btn-primary py-3 px-4" name="contentbtn">訂單詳情</button>
	                    </td>
	                </tr>
					`;
			}
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
});
