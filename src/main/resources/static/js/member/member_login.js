/* 登入資訊 */
	const token = localStorage.getItem("jwt");
	const Url = `/auth?token=${encodeURIComponent(token)}`;
	var memberId;
	
	fetch(Url)
		.then(response => response.json())
		.then(data => {
			memberId = data.id
			
		})
		.catch(error => {
			console.error(error);
		});


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
	

		$('#loginButton').click(function(e) {
    	e.preventDefault(); 

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

		fetch('auth/authenticate', {
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
                   console.log(data);
                   localStorage.setItem("jwt", data.access_token);
//                     sessionStorage.setItem('memberId', data.id);
//                     sessionStorage.setItem('memberEmail', data.email);
//                     sessionStorage.setItem('memberName', data.name);
                     
                    
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
