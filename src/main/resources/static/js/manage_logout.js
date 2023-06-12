$(document).ready(function () {
  $("#logoutLink").click(function (event) {
    event.preventDefault(); // 防止點擊連結後立即跳轉

    fetch("auth/logout", {
      method: "GET",
    })
      .then(function (response) {
        if (response.ok) {
          // 登出成功，執行相應的操作
          console.log("登出成功");
          // 例如重新導向到登入頁面
          window.location.href = "admin_login.html";
        } else {
          // 登出失敗，處理錯誤
          console.error("登出失敗");
        }
      })
      .catch(function (error) {
        console.error("登出發生錯誤:", error);
      });
  });
});
