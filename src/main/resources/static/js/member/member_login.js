/* 檢查格式+POST 查詢 */
	function emailError(){
		console.log('emailError');
		// 檢查email格式
	var emailRegex = /^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\.[A-Za-z]{2,}$/;
	if (!emailRegex.test(document.getElementById('email').value)) {
		var emailError = document
				.getElementById('emailError');
		emailError.textContent = '請輸入有效的電子郵件地址';
		console.log('請輸入有效的電子郵件地址');
		return false;
		
	}else{
		document.getElementById('emailError').textContent = '';
		return true;
	}
	}
	
	function passwordError(){
		// 檢查密碼格式
        var passwordRegex = /^(?=.*\d)(?=.*[a-z])(?=.*[A-Z])[0-9a-zA-Z]{8,}$/;
        if (!passwordRegex.test(document.getElementById('password').value)) {
            var passwordError = document.getElementById('passwordError');
            passwordError.textContent = '密碼必須包含至少一個數字、一個小寫字母、一個大寫字母，並且長度至少為8個字元';
            console.log('密碼必須包含至少一個數字、一個小寫字母、一個大寫字母，並且長度至少為8個字元');
            return;
        }else{
    		document.getElementById('passwordError').textContent = '';
    		return true;
    	}
	}
	
		// 當按下登入按鈕時觸發事件
		$('#loginButton').click(function(e) {
    	e.preventDefault(); // 防止表單提交

		// 取得輸入的郵件和密碼
		var email = $('#email').val();
    	var password = $('#password').val();

    	const accLength = $("#email").val().length;
		if (accLength === 0 ) {
		return;
		}

		const pwdLength = $("#password").val().length;
		if (pwdLength === 0) {
		return;
		}

		fetch('member/login', {
                 method: 'POST',
                 headers: {
                     'Content-Type': 'application/json'
                 },
                 body: JSON.stringify({
                     email: email,
                     password: password
                 })
             })
             .then(response => {
                 if (response.ok) {
                  response.json().then(data =>{
                   
                     sessionStorage.setItem('memberId', data.id);
                     sessionStorage.setItem('memberEmail', data.email);
                     sessionStorage.setItem('memberName', data.name);
                     
                     
                     // 登入成功，重定向到指定頁面
                     window.location.href = 'member_personalpage.html';
                  })
                     
                 } else {
                     // 處理錯誤訊息
                    //  if (response.status === 401) {
                    //      document.getElementById('passwordError').textContent = '帳號或密碼錯誤';
                    //  } else {
                    //      document.getElementById('passwordError').textContent = '發生錯誤';
                    //  }
					console.log("錯誤")
                 }
             })
			
             .catch(error => {
                 console.error('發生錯誤:', error);
                 alert('發生錯誤');
             });

			});
