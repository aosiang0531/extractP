const orderButton = document.getElementById('orderButton');
orderButton.addEventListener('click', handleOrderClick);


function handleOrderClick() {
	fetch('orderInfo/orderStatus')
		.then(response => response.json())
		.then(data => {
			const tableHead = document.getElementById('tableHead');
			tableHead.innerHTML = '';



			const theadRow = document.createElement('tr');
			theadRow.innerHTML = `
	        <th>訂單編號</th>
	        <th>下單會員</th>
	        <th>訂單日期</th>
	        <th>訂單狀態</th>
	        <th>付款狀態</th>
	        <th>物流狀態</th>
	        <th>訂單出貨</th>
		      `;
			tableHead.appendChild(theadRow);

			const tableBody = document.getElementById('memberTableBody');
			tableBody.innerHTML = '';

			data.forEach(order => {
				
				
				
				const row = document.createElement('tr');
				row.innerHTML = `
	          <td>${order.id}</td>
	          <td>${order.member_id}</td>
	          <td>${new Date(order.created_date).toISOString().slice(0, 19).replace(/-/g, "/").replace("T", " ")}</td>
	          <td>${order.status}</td>
	          <td>${order.payment_status}</td>
	          <td>${order.shipping_status}</td>
	          <td><button onclick="editOrder(${order.id})" data-order-id="${order.id}"><i class="fa-solid fa-truck-fast"></i></button></td>
	        `;
				tableBody.appendChild(row);
			});
		})
		.catch(error => {
			console.error('發生錯誤:', error);
		});
}

function editOrder(orderId) {
	document.getElementById('confirmButton').onclick = function() {
		confirmOrderShipment(orderId);
	};
	const modal = new bootstrap.Modal(document.getElementById('editOrderModal'));
	modal.show();

}

function confirmOrderShipment(orderId) {

	const data = {
		order_id: orderId,
		shipping_status: "已出貨"
	};

	fetch('orderInfo/' + orderId, {
		method: 'PUT',
		headers: {
			'Content-Type': 'application/json'
		},
		body: JSON.stringify(data)
	})
		.then(response => response.json())
		.then(result => {

			console.log(result);
		})
		.catch(error => {
			console.error('發生錯誤:', error);
		})
		.finally(() => {
			const modal = new bootstrap.Modal(document.getElementById('editOrderModal'));
			modal.hide();
			handleOrderClick();
		});
}
