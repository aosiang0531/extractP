$(document).ready(function () {
  $("#registerButton").click(function () {
    // 獲取輸入值
    const email = $("#email").val();
    const password = $("#password").val();
    const name = $("#name").val();
    const phone = $("#phone").val();

    $(".error-message").text("");

    const emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
    const passwordRegex = /^(?=.*[A-Za-z])(?=.*\d)[A-Za-z\d]{6,12}$/;
    const phoneRegex = /^09\d{8}$/; 

    let hasError = false;
    if (!emailRegex.test(email)) {
      $("#emailError").text("請輸入有效的Email");
      hasError = true;
    }
    if (!passwordRegex.test(password)) {
      $("#passwordError").text("密碼應為6-12位的英文和數字組合");
      hasError = true;
    }
    if (name.trim() === "") {
      $("#nameError").text("管理員名稱不能為空");
      hasError = true;
    }
    if (hasError) {
      return;
    }
    if (phone.trim() === "") {
      $("#phoneError").text("管理員電話不能為空");
      hasError = true;
    }
    if (!phoneRegex.test(phone)) {
      $("#phoneError").text("請輸入有效的號碼");
      hasError = true;
    }
    
    if (hasError) {
      return;
    }

    // 建立要發送的請求數據物件
    const requestData = {
      email: email,
      password: password,
      name: name,
      role: "ADMIN",
      identity: "管理員",
      is_suspended: "1",
      phone: phone
      
    };

    $.ajax({
      url: "auth/register", // 請替換為後端接口的實際路徑
      type: "POST",
      data: JSON.stringify(requestData),
      contentType: "application/json",
      success: function (response) {
        if (response == "") {
			
          $("#emailError").text("帳號重複");
        } else {
          console.log("註冊成功");
          alert("管理員新增成功");
        }
      },
      error: function (error) {
        $("#emailError").text("註冊失敗：" + error.responseText);
        console.error("註冊失敗:", error);
      },
    });
  });
});
