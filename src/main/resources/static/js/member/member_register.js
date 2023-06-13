/* 登入資訊 */
	let token = localStorage.getItem("jwt");
	const Url = `/auth?token=${encodeURIComponent(token)}`;
	var memberId;
	
	
	fetch(Url)
		.then(response => response.json())
		.then(data => {
			memberId = data.id;
			
			
			
		})
		.catch(error => {
			console.error(error);
		});


/* 驗證+POST新增 */

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
	
	function nameError(){
		 // 檢查名字是否為空
        if (document.getElementById('name').value.trim() === '') {
            var nameError = document.getElementById('nameError');
            nameError.textContent = '請輸入名字';
            console.log('請輸入名字');
            return;
        }else{
    		document.getElementById('nameError').textContent = '';
    		return true;
    	}
	}
	
	function phoneError(){
		// 檢查電話格式
        var phoneRegex = /^\d{10}$/;
        if (!phoneRegex.test(document.getElementById('phone').value)) {
            var phoneError = document.getElementById('phoneError');
            phoneError.textContent = '請輸入有效的手機號碼';
            console.log('請輸入有效的手機號碼');
            return;
        }else{
    		document.getElementById('phoneError').textContent = '';
    		return true;
    	}
	}

	$(document).ready(function() {
		
		
    $("#registerButton").click(function(e) {
       // e.preventDefault();
        

        var formData = {
            name: $("#name").val(),
            phone: $("#phone").val(),
            email: $("#email").val(),
            password: $("#password").val(),
            identity: $("input[name='memberidentity']:checked").val(),
            role: "USER"
        };
        
//        if (name === "" || phone === "" || email === "" || password === "") {
//          return false; // 阻止畫面跳轉
//          }
		const accLength = $("#email").val().length;
		if (accLength === 0 ) {
		return;
		}

		const pwdLength = $("#password").val().length;
		if (pwdLength === 0) {
		return;
		}

		const phoneLength = $("#phone").val().length;
		if (phoneLength === 0) {
		return;
		}
		
		const nameLength = $("#phone").val().length;
		if (nameLength === 0) {
		return;
		}
		
        $.ajax({
            type: "POST",
            url: "auth/register", 
            data: JSON.stringify(formData),
            contentType: "application/json",
            success: function(response) {
                console.log(response);
                window.location.href = "member_login.html";
            },
            error: function(error) {
                console.log(error);
            }
        });
    });
});


