const tepURL = new URLSearchParams(window.location.search);
const artId = tepURL.get("articleID");




// summernote
$(document).ready(function() {
	$("#summernote").summernote({
		height: 600,
		disableUpload: true,

	});
	$('.note-icon-picture').parent().hide();
	$('.note-icon-video').parent().hide();

});
$('.edit').hide();

// 編輯文章
if (artId) {
	$('.post').hide();
	const artUrl = 'article/' + artId;
	fetch(artUrl)
		.then(resp => resp.json())
		.then(articleData => {
			$('.edit').show();
			$("input.post-title").val(articleData.articleTitle);
			$('#summernote').summernote('code', articleData.articleContent);
			$("#college-list").hide();
			$("#sector-list").hide();
			$('#img').attr('src', "data:image/png; base64," + articleData.image);
		})
	$('.edit').on("click", function() {
		// 更新送進資料庫
		let title_text = ($("input.post-title").val()).trim();
		let content_text = $("#summernote").val();
		if (title_text == "" || content_text == "") {
			alert("請輸入標題及內容");
			return;
		}

		var file = image.files[0];
		var imageBase64 = null;

		if (file) {
			var fileReader = new FileReader();
			fileReader.onload = function(event) {
				var tempImg = event.target.result;
				imageBase64 = tempImg.replace(/^data:image\/(png|jpeg|jpg|gif);base64,/, '');
				submitFormWithImage(imageBase64);
			};
			fileReader.readAsDataURL(file);
		} else {
			submitFormWithImage(null);
		}
		function submitFormWithImage(imageBase64) {
			var data = {
				"articleTitle": title_text,
				"articleContent": content_text,
				"image": imageBase64
			};
			var jsonData = JSON.stringify(data);
			const editArtUrl = 'article/' + artId;
			fetch(editArtUrl, {
				method: 'PUT',
				body: jsonData,
				headers: { 'Content-Type': 'application/json;charset=utf8' }
			})
				.then(resp => resp.json())
				.then(data => {
					console.log("data " + data);
				})
		}
		// 轉跳頁面
		var url = 'articleAppear2.html?article=' + artId;
		window.location.href = url;
	})
}

//   關聯的下拉選單
$(document).ready(function() {
	const tempUrl = 'article_template';
	fetch(tempUrl)
		.then(resp => resp.json())
		.then(tempList => {
			for (var i = 0; i < tempList.length; i++) {
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
		.then(grpList => {
			for (var i = 0; i < grpList.length; i++) {
				var id = grpList[i].id;
				var name = grpList[i].name;
				var tempId = grpList[i].article_template_id;
				if (tempId == 1) {
					let grpOp = `
					<option class="grp-op" value="${i}" grp-id="${id}" temp-id="${tempId}">${name}</option>
				`;
					$("#sector-list").append(grpOp);
				}
			}

		});
	// 下拉選單連動
	$("#college-list").on("change", function() {
		fetch(grpUrl)
			.then(resp => resp.json())
			.then(grpList => {
				var selectedTempId = $(this).find(":selected").attr("temp-id");
				$("#sector-list").empty();
				for (var i = 0; i < grpList.length; i++) {
					var id = grpList[i].id;
					var name = grpList[i].name;
					var tempId = grpList[i].article_template_id;
					if (selectedTempId == tempId) {
						let grpOp = `
						<option class="grp-op" value="${i}" grp-id="${id}" temp-id="${tempId}">${name}</option>
					`;
						$("#sector-list").append(grpOp);
					}
				}
			})
	});
	//	上傳圖片的預覽
	image.addEventListener('change', function() {
		var file = image.files[0];
		if (file) {
			img.src = URL.createObjectURL(file);
		}
	});

});

$(document).ready(function() {
	$(".post").on("click", function() {
		// 送進文章資料庫
		let title_text = ($("input.post-title").val()).trim();
		let grp_selected = $("#sector-list").find(":selected").attr("grp-id");
		let content_text = $("#summernote").val();
		if (title_text == "" || content_text == "") {
			alert("請輸入標題及內容");
			return;
		}

		var file = image.files[0];
		var imageBase64 = null;

		if (file) {
			var fileReader = new FileReader();
			fileReader.onload = function(event) {
				var tempImg = event.target.result;
				imageBase64 = tempImg.replace(/^data:image\/(png|jpeg|jpg|gif);base64,/, '');
				submitFormWithImage(imageBase64);
			};
			fileReader.readAsDataURL(file);
		} else {
			submitFormWithImage(null);
		}
		function submitFormWithImage(imageBase64) {
			var data = {
				"articleTitle": title_text,
				"group_id": grp_selected,
				"articleContent": content_text,
				"image": imageBase64,
				"memberId": 1
			};
			var jsonData = JSON.stringify(data);
			const artUrl = 'article';
			fetch(artUrl, {
				method: 'POST',
				body: jsonData,
				headers: { 'Content-Type': 'application/json;charset=utf8' }
			})
				.then(resp => resp.json())
				.then(data => {
					//					console.log("data " + data);
					// 轉跳頁面
					var url = 'articleAppear2.html?article=' + data.id;
					window.location.href = url;
				})
		}
	});
	$(".article_cancel").on("click", function() {
		window.location.href = 'index.html';
	})
});
