document.addEventListener("DOMContentLoaded", function() {
	var form = document.getElementById('messageid');
	var inputField = form.querySelector('.inputvalue');
	var submitButton = form.querySelector('.submit');
	
	const token = localStorage.getItem("jwt");
	const url = `/auth?token=${encodeURIComponent(token)}`;
	var sender;
	
	fetch(url)
		.then(response => response.json())
		.then(data => {
			sender = data.name;
		})
		.catch(error => {
			console.error(error);
		});
		
	fetch("/chat/rooms/1/messages")
		.then(response => response.json())
		.then(data => {
			console.log(data);
		})
		.catch(error => {
			console.error(error);
		});

	submitButton.addEventListener('click', function(event) {
		event.preventDefault(); // 阻止表单的默认提交行为

		var inputValue = inputField.value.trim();
		if (inputValue !== '') {
			// 执行发送消息的逻辑，可以调用相应的WebSocket发送消息的方法
			// 例如：sendMessage(inputValue);
			sendMessage(inputValue);
			//			socket.send(inputValue);
			// 清空输入框
			inputField.value = '';
		}
	});

	var socket = new WebSockets();
	socket.connect('/ws/chat', () => {
		getMessage();
	});

	function getMessage() {
		socket.subscribe("/sub/chat/rooms/1", (response) => {
			response = JSON.parse(response.body);
			console.log(response);
		})
	}

	socket.subscribe("/sub/chat/rooms/1", (response) => {
		response = JSON.parse(response.body);
		let html = `
		<div> ${response.sender} : ${response.content}</div>
		`;
		$(".talkstartes").append(html);
		console.log(response);
	})

	function sendMessage(inputValue) {

		const message = {
			content: inputValue,
			sender: sender,
			message_type: 'NONE',
			chat_room_id: 1
		};

		socket.send('/pub/chat/message', message);
	}

});

