$(document).ready(function () {
  $("#registerButton").click(function () {
    // 獲取輸入值
    const email = $("#email").val();
    const password = $("#password").val();
    const name = $("#name").val();

    $(".error-message").text("");

    // 正则表达式验证
    const emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
    const passwordRegex = /^[a-zA-Z0-9]{6,12}$/;

    let hasError = false;
    if (!emailRegex.test(email)) {
      $("#emailError").text("請輸入有效的Email");
      hasError = true;
    }
    if (!passwordRegex.test(password)) {
      $("#passwordError").text("密碼應為6-12位的英文或數字");
      hasError = true;
    }
    if (name.trim() === "") {
      $("#nameError").text("管理員名稱不能為空");
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
    };

    $.ajax({
      url: "admin/register", // 請替換為後端接口的實際路徑
      type: "POST",
      data: JSON.stringify(requestData),
      contentType: "application/json",
      success: function (response) {
        if (response.email == null) {
          $("#emailError").text("帳號重複");
        } else {
          console.log("註冊成功");
          alert("管理員新增成功");
          // 執行頁面跳轉
//          window.location.href = "admin_login.html"; // 替換為要跳轉的頁面 URL
        }
      },
      error: function (error) {
        $("#emailError").text("註冊失敗：" + error.responseText);
        console.error("註冊失敗:", error);
      },
    });
  });
});
