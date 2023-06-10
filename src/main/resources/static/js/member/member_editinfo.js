
/* 檢查格式+GET會員 */

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
    const file = document.querySelector("#file");
    const img = document.querySelector("#img");


  var memberId = 1; 
  var url = "member/{id}".replace("{id}", memberId);

  $.ajax({
    url: url,
    type: "GET",
    success: function(response) {
      // 將會員資料顯示在input框框中
      $("input[name='memberName']").val(response.name);
      $("input[name='memberPhone']").val(response.phone);
      $("input[name='memberEmail']").val(response.email);
      
      // 顯示會員圖片
      //img.src = "data:image/jpeg;base64," + response.image;
      if (response.image) {
				// 顯示會員圖片
				img.src = "data:image/jpeg;base64," + response.image;
			} else {
				// 如果没有會員圖片，顯示咖啡圖片
				img.src = "images/300.jpg";
			}
      
       const html = `<h2 class="user-name">${response.name}</h2>`;
       document.querySelector("#name").innerHTML = html;
   
      //$("#name").innerText = response.name;
      
      
    },
    error: function(error) {
      console.log("獲取會員資料失敗");
    }
  });
});


/*PUT更新*/

  $(document).ready(function() {

  $("#update").click(function(e) {
    e.stopPropagation();
    console.log("HI");


    var memberId = 1; 
    var url = "member/{id}".replace("{id}", memberId);


    // 獲取表單元素
    var memberName = $("input[name='memberName']").val();
    var memberPhone = $("input[name='memberPhone']").val();
    var memberEmail = $("input[name='memberEmail']").val();

    // 要發送的數據
    var data = {
      name: memberName,
      phone: memberPhone,
      email: memberEmail,

    };
    
//    const accLength = $("#email").val().length;
//	if (accLength === 0 ) {
//	return;
//	}


//	const phoneLength = $("#phone").val().length;
//	if (phoneLength === 0) {
//	return;
//	}
//	
//	const nameLength = $("#name").val().length;
//	if (nameLength === 0) {
//	return;
//	}


    $.ajax({
      url: url,
      type: "PUT",
      data: JSON.stringify(data),
      contentType: "application/json",
      success: function(response) {
        // 更新成功後的處理邏輯
        console.log("更新成功");
        window.location.href = 'member_editinfo.html';
      },
      error: function(error) {
        // 更新失敗後的處理邏輯
        console.log("更新失敗");
      }
    });
    
  });


  });
  



/*PUT更新會員圖片*/

  const file = document.querySelector('#file');
  const img = document.querySelector('#img');

  file.addEventListener('change',() => {
    img.src = URL.createObjectURL(file.files[0]);
  });
  
  var memberId = 1; 
  var url = "member/{id}".replace("{id}", memberId);
  
  document.querySelector('#imgupdate').addEventListener('click', (e) =>{
    const fr = new FileReader();
    fr.addEventListener('load', e => {
      fetch( url , {
        method: 'PUT',
        headers: { 'Content-Type': 'application/json' 
        },
        body: JSON.stringify({
        	image: btoa(e.target.result)
      })
    })
   		.then(response => {
        if (response.ok) {
          // 上傳成功後跳轉頁面
          window.location.href = 'member_editinfo.html';
        } else {
          // 上傳失敗時的處理程式碼
          console.log('上傳失敗');
        }
      })
      .catch(error => {
        // 錯誤處理程式碼
        console.log('發生錯誤', error);
      });
  });
  fr.readAsBinaryString(file.files[0]);
}); 
  
/*取消按鈕跳轉*/
  $(document).ready(function() {
    $('#cancel').click(function(e) {
      e.stopPropagation();
      window.location.href = 'member_personalpage.html'; 
    });
  });

