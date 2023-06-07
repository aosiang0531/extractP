/* 左邊頁籤 */
window.addEventListener('load', function() {
  var b2 = document.querySelector('#b2');
  b2.classList.add('active');
  
  var a2 = document.querySelector('#a2');
  a2.classList.add('active');

  var element = document.querySelector('[aria-selected="false"]');
  element.setAttribute('aria-selected', 'true');

});

/* 點擊頁籤 */
  $(document).ready(function() {
    $('#a1').click(function(e) {
      e.stopPropagation();
      window.location.href = 'member_myarticle.html'; // 替換為個人頁面的URL
    });
  });



/* GET 會員資料 */
  $(document).ready(function() {
    const file = document.querySelector("#file");
    const img = document.querySelector("#img");


  // 頁面加載時執行的操作
  var memberId = 17; // 請替換為真實的會員ID

  // 構建後端端點URL並替換會員ID
  var url = "member/{id}".replace("{id}", memberId);

  // 發送AJAX請求獲取會員資料
  $.ajax({
    url: url,
    type: "GET",
    success: function(response) {
      
      // 顯示會員圖片
      img.src = "data:image/jpeg;base64," + response.image;
      
       const html = `<h2 class="user-name">${response.name}</h2>`;
       document.querySelector("#name").innerHTML = html;
   
      //$("#name").innerText = response.name;
      
      
    },
    error: function(error) {
      console.log("獲取會員資料失敗");
    }
  });
});


  
