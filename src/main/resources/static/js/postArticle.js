// summernote
$(document).ready(function () {
    $("#summernote").summernote({
      height: 600
    });
  });

//   關聯的下拉選單
$(document).ready(function() {
	
	const tempUrl = 'article_template';
	fetch(tempUrl)
		.then(resp => resp.json())
		.then(tempList => {
			for (var i = 0 ; i < tempList.length; i++){
				var id = tempList[i].id;
				var name = tempList[i].name;
				let tempOp = `
					<option value="${i}" temp-id="${id}">${name}</option>
				`;
				$("#college-list").append(tempOp);
			}
		});

	const grpUrl = 'article_group';
	fetch(grpUrl)
		.then(resp => resp.json())
		.then(grpList =>　{
			for(var i = 0; i < grpList.length; i ++) {
				var id = grpList[i].id;
				var name = grpList[i].name;
				var tempId = grpList[i].article_template_id;
				if(tempId == 1){
				let grpOp = `
					<option class="grp-op" value="${i}" grp-id="${id}" temp-id="${tempId}">${name}</option>
				`;
				$("#sector-list").append(grpOp);}
			}
		});
		
	// 下拉選單連動
	$("#college-list").on("change", function() {		
		fetch(grpUrl)
			.then(resp => resp.json())
			.then(grpList =>　{
				var selectedTempId = $(this).find(":selected").attr("temp-id");
				$("#sector-list").empty();
				for(var i = 0; i < grpList.length; i ++) {
					var id = grpList[i].id;
					var name = grpList[i].name;
					var tempId = grpList[i].article_template_id;
					if(selectedTempId == tempId){				
					let grpOp = `
						<option class="grp-op" value="${i}" grp-id="${id}" temp-id="${tempId}">${name}</option>
					`;
					$("#sector-list").append(grpOp);
					}
				}
			})
	});
 });
 $(document).ready(function() {
//	  var images = [];
//		 $("#summernote").summernote({
//		  callbacks: {
//		    onImageUpload: function(files) {
//		      for (var i = 0; i < files.length; i++) {
//		        var file = files[i];
//		        // 將文件上傳到伺服器並處理
//		        // 獲取處理後的圖片 URL
//		        		 console.log("file:"+ file);
//		        var imageUrl = URL.createObjectURL(file.files[0]);
//		         console.log("imageUrl:"+ imageUrl);
//		        images.push(imageUrl);
//		      }
//		    }
//		  }
//		 });
	

	 $(".post").on("click", function(){
		 
	// 送進文章資料庫
		 let title_text = ($("input.post-title").val()).trim();
		 let grp_selected = $("#sector-list").find(":selected").attr("grp-id");
		 let content_text = $("#summernote").val();
		 if(title_text == "" || content_text == ""){
			 alert("請輸入標題及內容");
			 return;
		 }
		 var data = {
			 "title":title_text,
			 "group_id":grp_selected,
			 "content":content_text,
			 "image":null,
			 "memberId":1
		 };	 
		var jsonData = JSON.stringify(data);		
		const artUrl = 'article';	 
	  	fetch(artUrl, {
			  	method:'POST',
			  	body:jsonData,
			  	headers:{'Content-Type': 'application/json;charset=utf8'}
			  	})
			  	.then(resp => resp.json())
			  	.then(data => {
					  // 轉跳頁面
					  var url = 'articleAppear2.html?article=' + data.id;
					  window.location.href = url;
				  })
	 })
    });