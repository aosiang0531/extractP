CREATE DATABASE IF NOT EXISTS extractp;

USE extractp;

set foreign_key_checks = 0;

DROP TABLE IF EXISTS sample;
DROP TABLE IF EXISTS ADMIN;
DROP TABLE IF EXISTS MEMBER;
DROP TABLE IF EXISTS `token`;
DROP TABLE IF EXISTS `article`;
DROP TABLE IF EXISTS `article_comment`;
DROP TABLE IF EXISTS `article_comment_report`;
DROP TABLE IF EXISTS `article_group`;
DROP TABLE IF EXISTS `article_report`;
DROP TABLE IF EXISTS `article_template`;
DROP TABLE IF EXISTS `article_thunmb`;
DROP TABLE IF EXISTS `member_article_fav`;
DROP TABLE IF EXISTS PRODUCT_CATEGORY;
DROP TABLE IF EXISTS PRODUCT;
DROP TABLE IF EXISTS ORDER_INFO;
DROP TABLE IF EXISTS ORDER_DETAIL;
DROP TABLE IF EXISTS class_and_event;
DROP TABLE IF EXISTS member_class_signed;
DROP TABLE IF EXISTS ad;
DROP TABLE IF EXISTS store;
DROP TABLE IF EXISTS store_report;

CREATE TABLE `sample` (
  `sample_id` int NOT NULL AUTO_INCREMENT,
  `sample_text` varchar(45) DEFAULT NULL,
  `sample_title` varchar(45) DEFAULT NULL,
  `sample_created_date` datetime DEFAULT NULL,
  `sample_last_modified_date` datetime DEFAULT NULL,
  `sample_created_by` varchar(45) DEFAULT NULL,
  `sample_last_modified_by` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`sample_id`)
)COMMENT='sample';

CREATE TABLE ADMIN (
	admin_id		INT AUTO_INCREMENT NOT NULL COMMENT '管理員編號',
    admin_email     VARCHAR(30) COMMENT '管理員信箱',
    admin_password 	VARCHAR(30) COMMENT '管理員密碼',
	admin_name	 	VARCHAR(30) COMMENT '管理員姓名',
	CONSTRAINT ADMIN_PRIMARY_KEY PRIMARY KEY (admin_id)
)COMMENT='管理員';

CREATE TABLE MEMBER (
	member_id					INT AUTO_INCREMENT NOT NULL COMMENT '會員編號',
	member_email				VARCHAR(30) COMMENT '會員信箱',
    member_name    	 			VARCHAR(30) COMMENT '會員名稱',
    member_password 			VARCHAR(200) COMMENT '會員密碼',
	member_role 				ENUM('USER','ADMIN') COMMENT 'Role',
	member_phone	 			VARCHAR(20) COMMENT '會員電話',
    member_identity 			VARCHAR(10) COMMENT '會員身分',
    member_is_suspended 		VARCHAR(10) COMMENT '是否停權',
    member_image 				LONGBLOB 	COMMENT '會員照片',
    member_created_time 		DATETIME 	COMMENT '會員註冊時間',
    member_created_user 		VARCHAR(30) COMMENT '最後修改人員',
    member_last_modified_time 	DATETIME 	COMMENT '最後修改時間',
	CONSTRAINT MEMBER_PRIMARY_KEY PRIMARY KEY (member_id)
)COMMENT='會員';

CREATE TABLE `token` (
  `id` int NOT NULL AUTO_INCREMENT,
  `token` varchar(200) DEFAULT NULL,
  `token_type` ENUM('BEARER'),
  `revoked` BOOLEAN DEFAULT NULL,
  `expired` BOOLEAN DEFAULT NULL,
  `member_id` int,
  PRIMARY KEY (`id`),
  UNIQUE KEY `token_UNIQUE` (`token`),
  FOREIGN KEY (member_id) REFERENCES member(member_id) 
)COMMENT='token';

CREATE TABLE `article` (
  `article_id` int NOT NULL AUTO_INCREMENT COMMENT '文章編號',
  `member_id` int DEFAULT NULL,
  `article_group_id` int DEFAULT NULL COMMENT '文章分類編號',
  `article_title` varchar(30) DEFAULT NULL COMMENT '文章標題',
  `article_content` varchar(16000) DEFAULT NULL COMMENT '文章內容',
  `article_image` longblob COMMENT '文章圖片',
  `article_created_date` datetime DEFAULT NULL COMMENT '發文時間',
  `article_thunmb_number` int DEFAULT '0' COMMENT '按讚數',
  `article_comment_number` int DEFAULT '0' COMMENT '留言數',
  `member_article_fav_number` int DEFAULT '0' COMMENT '收藏數',
  `article_last_modified_by` varchar(30) DEFAULT NULL COMMENT '最後更新人員',
  `article_last_modified_date` datetime DEFAULT NULL COMMENT '最後更新時間',
  `article_is_hidden` bit(1) DEFAULT b'0' COMMENT '是否隱藏',
  `article_is_top` bit(1) DEFAULT b'0' COMMENT '是否置頂',
  PRIMARY KEY (`article_id`),
  KEY `FK__idx` (`article_group_id`),
  CONSTRAINT `FK_article_article_group_id` FOREIGN KEY (`article_group_id`) REFERENCES `article_group` (`article_group_id`) ON DELETE CASCADE ON UPDATE CASCADE
) AUTO_INCREMENT=1 COMMENT='文章';

CREATE TABLE `article_comment` (
  `article_comment_id` int NOT NULL AUTO_INCREMENT COMMENT '留言編號',
  `member_id` int DEFAULT NULL COMMENT '留言作者',
  `article_id` int DEFAULT NULL COMMENT '文章編號',
  `article_comment_content` varchar(50) DEFAULT NULL COMMENT '留言內容',
  `article_comment_created_date` datetime DEFAULT NULL COMMENT '上傳留言時間',
  `article_comment_is_hidden` bit(1) DEFAULT b'0' COMMENT '是否隱藏',
  PRIMARY KEY (`article_comment_id`),
  KEY `FK_article_comment_article_id_idx` (`article_id`),
  CONSTRAINT `FK_article_comment_article_id` FOREIGN KEY (`article_id`) REFERENCES `article` (`article_id`) ON DELETE CASCADE ON UPDATE CASCADE
) AUTO_INCREMENT=1 COMMENT='文章留言';

CREATE TABLE `article_comment_report` (
  `article_comment_report_id` int NOT NULL AUTO_INCREMENT COMMENT '留言檢舉編號',
  `member_id` int DEFAULT NULL COMMENT '檢舉者',
  `article_comment_id` int DEFAULT NULL COMMENT '留言編號',
  `article_comment_report_content` varchar(20) DEFAULT NULL COMMENT '檢舉原因',
  `article_comment_report_created_date` datetime DEFAULT NULL COMMENT '檢舉時間',
  `article_comment_report_status` varchar(10) DEFAULT '0' COMMENT '檢舉狀態',
  `article_comment_report_last_modified_date` datetime DEFAULT NULL COMMENT '最後更新時間',
  `article_comment_report_last_modified_by` varchar(30) DEFAULT NULL COMMENT '最後更新人員',
  PRIMARY KEY (`article_comment_report_id`),
  KEY `FK_article_comment_report_article_comment_id_idx` (`article_comment_id`),
  CONSTRAINT `FK_article_comment_report_article_comment_id` FOREIGN KEY (`article_comment_id`) REFERENCES `article_comment` (`article_comment_id`) ON DELETE CASCADE ON UPDATE CASCADE
) AUTO_INCREMENT=1 COMMENT='留言檢舉';

CREATE TABLE `article_group` (
  `article_group_id` int NOT NULL AUTO_INCREMENT COMMENT '文章分類編號',
  `article_template_id` int DEFAULT NULL COMMENT '板塊編號',
  `article_group_name` varchar(45) DEFAULT NULL COMMENT '文章分類名稱',
  `article_group_is_hidden` bit(1) DEFAULT b'0' COMMENT '是否隱藏',
  `article_group_created_date` datetime DEFAULT NULL COMMENT '上傳時間',
  `article_group_last_modified_date` datetime DEFAULT NULL COMMENT '最後更新時間',
  `article_group_created_by` varchar(30) DEFAULT NULL COMMENT '上傳人員',
  `article_group_last_modified_by` varchar(30) DEFAULT NULL COMMENT '最後更新人員',
  PRIMARY KEY (`article_group_id`),
  KEY `FK_article_group__idx` (`article_template_id`),
  CONSTRAINT `FK_article_group_article_template_id` FOREIGN KEY (`article_template_id`) REFERENCES `article_template` (`article_template_id`) ON DELETE CASCADE ON UPDATE CASCADE
) AUTO_INCREMENT=1 COMMENT='文章分類';

CREATE TABLE `article_report` (
  `article_report_id` int NOT NULL AUTO_INCREMENT COMMENT '文章檢舉編號',
  `member_id` int DEFAULT NULL COMMENT '檢舉者',
  `article_id` int DEFAULT NULL COMMENT '文章編號',
  `article_report_content` varchar(20) DEFAULT NULL COMMENT '檢舉原因',
  `article_report_created_date` datetime DEFAULT NULL COMMENT '檢舉時間',
  `article_report_status` varchar(10) DEFAULT '0' COMMENT '檢舉狀態',
  `article_report_last_modified_date` datetime DEFAULT NULL COMMENT '最後更新時間',
  `article_report_last_modified_by` varchar(30) DEFAULT NULL COMMENT '最後更新人員',
  PRIMARY KEY (`article_report_id`),
  KEY `FK_artiicle_report_article_id_idx` (`article_id`),
  CONSTRAINT `FK_artiicle_report_article_id` FOREIGN KEY (`article_id`) REFERENCES `article` (`article_id`) ON DELETE CASCADE ON UPDATE CASCADE
) AUTO_INCREMENT=1 COMMENT='文章檢舉';

CREATE TABLE `article_template` (
  `article_template_id` int NOT NULL AUTO_INCREMENT COMMENT '板塊編號',
  `article_template_name` varchar(45) DEFAULT NULL COMMENT '板塊名稱',
  `article_template_description` varchar(400) DEFAULT NULL COMMENT '板塊描述',
  `article_template_created_date` datetime DEFAULT NULL COMMENT ' 上傳時間',
  `article_template_last_modified_date` datetime DEFAULT NULL COMMENT '最後更新時間',
  `article_template_created_by` varchar(30) DEFAULT NULL COMMENT '上傳人員',
  `article_template_last_modified_by` varchar(30) DEFAULT NULL COMMENT '最後更新人員',
  PRIMARY KEY (`article_template_id`)
) AUTO_INCREMENT=1 COMMENT='文章版塊';

CREATE TABLE `article_thunmb` (
  `member_id` int NOT NULL COMMENT '按讚者',
  `article_id` int NOT NULL COMMENT '文章編號',
  `article_thunmb_created_date` datetime DEFAULT NULL COMMENT '按讚時間',
  PRIMARY KEY (`member_id`,`article_id`),
  KEY `FK_article_thunmb_article_id_idx` (`article_id`),
  CONSTRAINT `FK_article_thunmb_article_id` FOREIGN KEY (`article_id`) REFERENCES `article` (`article_id`) ON DELETE CASCADE ON UPDATE CASCADE
) COMMENT='文章按讚';

CREATE TABLE `member_article_fav` (
  `member_id` int NOT NULL,
  `article_id` int NOT NULL COMMENT '文章編號',
  `member_article_fav_created_date` datetime DEFAULT NULL COMMENT '收藏時間',
  PRIMARY KEY (`member_id`,`article_id`),
  KEY `FK_member_article_fav_article_id_idx` (`article_id`),
  CONSTRAINT `FK_member_article_fav_article_id` FOREIGN KEY (`article_id`) REFERENCES `article` (`article_id`) ON DELETE CASCADE ON UPDATE CASCADE
) COMMENT=' 收藏文章';

CREATE TABLE PRODUCT_CATEGORY (
	category_id INT COMMENT "分類編號",
	category_name VARCHAR(10) NOT NULL COMMENT "分類名稱",
    CONSTRAINT PK_PRODUCT_CATEGORY_ID PRIMARY KEY (category_id)
)COMMENT "商品分類";

CREATE TABLE PRODUCT(
	product_id INT AUTO_INCREMENT COMMENT "商品編號",
    category_id INT COMMENT "商品分類",
    product_name VARCHAR(30) NOT NULL COMMENT "商品名稱",
    product_image longblob COMMENT "商品圖片",
    product_spec VARCHAR(30) COMMENT "商品規格",
    product_description VARCHAR(300) COMMENT "商品描述",
    product_price INT NOT NULL COMMENT "價格",
    product_stock INT NOT NULL COMMENT "庫存",
    product_sold_count INT COMMENT "已售數量",
    product_status VARCHAR(10) NOT NULL DEFAULT '上架中' COMMENT "上架狀態",
    product_created_date DATETIME DEFAULT CURRENT_TIMESTAMP NOT NULL COMMENT "上傳時間",
    product_last_modified_date DATETIME DEFAULT CURRENT_TIMESTAMP NOT NULL COMMENT "最後修改時間",
    product_created_by INT NOT NULL DEFAULT 91 COMMENT "上傳人員",  -- 應該要FK到管理員
    product_last_modified_by INT NOT NULL DEFAULT 91 COMMENT "最後修改人員", -- 應該要FK到管理員
    CONSTRAINT PK_PRODUCT_ID PRIMARY KEY (product_id),
    CONSTRAINT FK_PRODUCT_PRODUCT_CATEGORY FOREIGN KEY (category_id) REFERENCES PRODUCT_CATEGORY (category_id)
) COMMENT "商品";

CREATE TABLE ORDER_INFO (
	order_id INT AUTO_INCREMENT NOT NULL COMMENT '訂單編號',
	member_id INT COMMENT "會員", 
	order_status VARCHAR(10) NOT NULL COMMENT "訂單狀態",	
    order_created_date DATETIME COMMENT "下單時間",	
	order_shipping_method VARCHAR(10) COMMENT "寄件方式",	
	order_payment_amount INT COMMENT "付款金額",	
	order_payment_duedate DATETIME COMMENT "付款期限",	
	order_payment_method VARCHAR(10) COMMENT "付款方式",	
	order_shipping_status VARCHAR(10) COMMENT "物流狀態",	
	order_payment_status VARCHAR(10) COMMENT "付款狀態",	
	order_shipping_address VARCHAR(50) COMMENT "寄件地址",
	order_contact_number VARCHAR(10) COMMENT "聯絡電話",	
	order_shipping_name VARCHAR(10) COMMENT "收件人",
	order_last_modified_date DATETIME DEFAULT CURRENT_TIMESTAMP NOT NULL COMMENT "最後修改時間",
	CONSTRAINT PK_ORDER_INFO_ORDER_ID PRIMARY KEY (order_id)
) COMMENT "訂單";

CREATE TABLE ORDER_DETAIL (
	order_detail_id INT AUTO_INCREMENT NOT NULL COMMENT "訂單明細編號",
    order_id INT COMMENT "訂單編號",
    product_id INT COMMENT "商品編號",
    order_detail_quantity INT COMMENT "購買數量",
    order_product_price double COMMENT "購買價格",
    CONSTRAINT PK_ORDER_DETAIL PRIMARY KEY (order_detail_id),
    CONSTRAINT FK_ORDER_DETAIL_ORDER_ID FOREIGN KEY(order_id) REFERENCES ORDER_INFO (order_id),
	CONSTRAINT FK_ORDER_DETAIL_PRODUCT_ID FOREIGN KEY(product_id) REFERENCES PRODUCT (product_id)
) COMMENT "訂單明細";

Create table `class_and_event`(
  class_id  INT AUTO_INCREMENT PRIMARY KEY,
  class_type VARCHAR(10) comment '類型',
  member_id INT comment '課程/活動發起人',
  class_name VARCHAR(30) comment '課程/活動名稱',
  class_description VARCHAR(100) comment '課程/活動簡介',
  class_date DATE comment '課程/活動日期',
  class_period_of_time VARCHAR(10) comment '時段',
  class_start_time VARCHAR(30) comment '課程/活動開始時間',
  class_end_time VARCHAR(30) comment '課程/活動結束時間',
  class_location VARCHAR(30) comment '課程/活動地點',
  class_member_count INT comment '目前報名人數',
  class_member_max INT comment '人數上限',
  class_member_min INT comment '最低人數',
  class_start_date DATE comment '報名開始日期',
  class_end_date DATE comment '報名結束日期',
  class_image LONGBLOB comment '課程/活動圖片',
  class_status VARCHAR(10) comment '狀態',
  class_created_time DATETIME comment '上傳時間',
  class_last_modified_time DATETIME comment '最後更新時間',
  class_last_modified_member VARCHAR(30) comment '最後更新人員'
);

Create table `member_class_signed`(
  `member_id` VARCHAR(30) comment'報名人',
  `class_id` int comment'課程/活動編號',
  `member_class_signed_status`  VARCHAR(10) comment '課程/活動狀態',
  `member_class_signed_created_time`  DATETIME comment '課程/活動報名時間',
  `member_class_signed_last_modified_member`  VARCHAR(30) comment'最後更新時間',
  `member_class_signed_last_modified_time` DATETIME comment'最後更新人員'
) COMMENT "已報名課程";

CREATE TABLE ad (
  ad_id INT AUTO_INCREMENT COMMENT '廣告編號',
  member_id int COMMENT '會員編號' ,
  ad_start_date DATETIME COMMENT '開始日期',
  ad_end_date DATETIME COMMENT '結束日期',
  ad_spend INT COMMENT '廣告花費',
  ad_field VARCHAR(6) COMMENT '廣告投放欄位版面',
  ad_title VARCHAR(15) COMMENT '廣告標題',
  ad_deliver_status VARCHAR(3) COMMENT '廣告投放狀態',
  ad_review_status VARCHAR(3) COMMENT '廣告審核狀態',
  ad_image LONGBLOB COMMENT '圖片上傳',
  ad_admin_description VARCHAR(200) COMMENT '管理員說明',
  ad_name VARCHAR(10) COMMENT '廣告名稱',
  ad_created_time DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '上傳時間',
  ad_last_modified_time DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '最後更新時間',
  ad_last_modified_member VARCHAR(30) COMMENT '最後更新人員',
  ad_url VARCHAR(200) COMMENT '廣告頁面連結',
  PRIMARY KEY (ad_id),
  FOREIGN KEY (member_id) REFERENCES member(member_id)
) COMMENT "廣告";

CREATE TABLE store (
  store_id INT AUTO_INCREMENT COMMENT '店家編號', 
  store_tax VARCHAR(30) COMMENT '推薦人',
  member_id INT COMMENT '會員編號',
  store_name VARCHAR(30) COMMENT '店名',
  store_info VARCHAR(100) COMMENT '簡介',
  store_address VARCHAR(100) COMMENT '地址',
  store_time VARCHAR(50) COMMENT '營業時間',
  store_phone VARCHAR(30) COMMENT '電話',
  store_order LONGBLOB COMMENT '菜單',
  store_website  VARCHAR(100) COMMENT '網站',
  store_created_time DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '上傳時間',
  store_last_modified_time DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '最後更新時間',
  store_last_modified_member VARCHAR(30) COMMENT '最後更新人員',
  PRIMARY KEY (store_id),
  FOREIGN KEY (member_id) REFERENCES member(member_id) 
) COMMENT "商家";

CREATE TABLE store_report (
  store_report_id INT AUTO_INCREMENT PRIMARY KEY COMMENT '商家檢舉編號',
  member_id int COMMENT '檢舉者',
  store_tax VARCHAR(30) COMMENT '統編',
  store_report_content VARCHAR(20) COMMENT '檢舉原因',
  store_report_created_time DATETIME COMMENT '檢舉時間',
  store_report_status VARCHAR(5) COMMENT '檢舉狀態',
  store_report_last_modified_time DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '最後更新時間',
  store_report_last_modified_member VARCHAR(30) COMMENT '最後更新人員'
) COMMENT "商家檢舉";



INSERT INTO ADMIN (admin_email,admin_password,admin_name) VALUES ('admin1@gmail.com','testpwd','史建仁');
INSERT INTO ADMIN (admin_email,admin_password,admin_name) VALUES ('admin2@gmail.com','testpwd','史珍香');
INSERT INTO ADMIN (admin_email,admin_password,admin_name) VALUES ('admin3@gmail.com','testpwd','馬陵淑');

INSERT INTO MEMBER ( 
member_email,
member_name,
member_password,
member_phone,
member_identity,
member_is_suspended,
member_image,
member_created_time,
member_created_user,
member_last_modified_time
) VALUES ('test111@gmail.com','小王','test123','0911239222','商家',0,'','2023-05-19 11:31:39','史建仁','2023-05-19 11:32:39');
INSERT INTO MEMBER (
member_email,
member_name,
member_password,
member_phone,
member_identity,
member_is_suspended,
member_image,
member_created_time,
member_created_user,
member_last_modified_time
) VALUES ('test222@gmail.com','小張','test123','0913989999','一般會員',1,'','2023-05-19 11:35:39','史珍香','2023-05-19 11:40:39');
INSERT INTO MEMBER (
member_email,
member_name,
member_password,
member_phone,
member_identity,
member_is_suspended,
member_image,
member_created_time,
member_created_user,
member_last_modified_time
) VALUES ('test333@gmail.com','大寶','test123','0911239222','一般會員',0,'','2023-05-20 11:31:39','馬陵淑','2023-05-20 11:32:39');

INSERT INTO `extractp`.`article`
(`member_id`,
`article_group_id`,
`article_title`,
`article_content`,
`article_created_date`,
`article_thunmb_number`,
`article_comment_number`,
`member_article_fav_number`,
`article_last_modified_by`,
`article_last_modified_date`,
`article_is_hidden`,
`article_is_top`)
VALUES
(2,
1,
'咖啡豆產地',
'<p class="MsoNormal" style="text-indent:24.0pt"><a name="_Hlk135826782"><span style="mso-bidi-font-size:12.0pt;font-family:&quot;新細明體&quot;,serif;mso-ascii-theme-font:
 minor-fareast;mso-fareast-theme-font:minor-fareast;mso-hansi-theme-font:minor-fareast;
 mso-bidi-font-family:&quot;Segoe UI&quot;;color:black;mso-themecolor:text1;mso-font-kerning:
 0pt">咖啡是全球最受歡迎的飲品之一，其深沉的風味和豐富的香氣吸引了數百萬人喝咖啡。要了解咖啡，必須從咖啡豆種類入手。在本文中，我們將介紹一些最受歡迎的咖啡豆種類。<span lang="EN-US"><o:p></o:p></span></span></a></p><p class="MsoListParagraph" style="margin-left:8.5pt;mso-para-margin-left:0gd;
 text-indent:-8.5pt;mso-list:l0 level1 lfo1"><!--[if !supportLists]--><span lang="EN-US" style="mso-bidi-font-size:12.0pt;font-family:&quot;新細明體&quot;,serif;
 mso-ascii-theme-font:minor-fareast;mso-fareast-theme-font:minor-fareast;
 mso-hansi-theme-font:minor-fareast;mso-bidi-font-family:新細明體;mso-bidi-theme-font:
 minor-fareast;color:black;mso-themecolor:text1;mso-font-kerning:0pt">1.</span><!--[endif]--><span style="mso-bidi-font-size:
 12.0pt;font-family:&quot;新細明體&quot;,serif;mso-ascii-theme-font:minor-fareast;mso-fareast-theme-font:
 minor-fareast;mso-hansi-theme-font:minor-fareast;mso-bidi-font-family:&quot;Segoe UI&quot;;
 color:black;mso-themecolor:text1;mso-font-kerning:0pt">阿拉比卡種咖啡豆<span lang="EN-US"><o:p></o:p></span></span></p><p class="MsoListParagraph" style="margin-left:8.5pt;mso-para-margin-left:0gd;
 text-indent:24.0pt;mso-char-indent-count:2.0"><span style="mso-bidi-font-size:12.0pt;font-family:&quot;新細明體&quot;,serif;mso-ascii-theme-font:
 minor-fareast;mso-fareast-theme-font:minor-fareast;mso-hansi-theme-font:minor-fareast;
 mso-bidi-font-family:&quot;Segoe UI&quot;;color:black;mso-themecolor:text1;mso-font-kerning:
 0pt">咖啡中最受歡迎的品種之一，其在全球范圍內的生產量最大。通常具有較高的品質和價值，其風味和香氣非常豐富，而咖啡因含量較低。主要在中南美洲和非洲種植，如哥倫比亞、巴西、衣索比亞等地。<span lang="EN-US"><o:p></o:p></span></span></p><p class="MsoListParagraph" style="margin-left:8.5pt;mso-para-margin-left:0gd;
 text-indent:-8.5pt;mso-list:l0 level1 lfo1"><!--[if !supportLists]--><span lang="EN-US" style="mso-bidi-font-size:12.0pt;font-family:&quot;新細明體&quot;,serif;
 mso-ascii-theme-font:minor-fareast;mso-fareast-theme-font:minor-fareast;
 mso-hansi-theme-font:minor-fareast;mso-bidi-font-family:新細明體;mso-bidi-theme-font:
 minor-fareast;color:black;mso-themecolor:text1;mso-font-kerning:0pt">2.</span><!--[endif]--><span style="mso-bidi-font-size:
 12.0pt;font-family:&quot;新細明體&quot;,serif;mso-ascii-theme-font:minor-fareast;mso-fareast-theme-font:
 minor-fareast;mso-hansi-theme-font:minor-fareast;mso-bidi-font-family:&quot;Segoe UI&quot;;
 color:black;mso-themecolor:text1;mso-font-kerning:0pt">羅布斯塔種咖啡<span lang="EN-US"><o:p></o:p></span></span></p><p class="MsoListParagraph" style="margin-left:8.5pt;mso-para-margin-left:0gd;
 text-indent:24.0pt;mso-char-indent-count:2.0"><span style="mso-bidi-font-size:12.0pt;font-family:&quot;新細明體&quot;,serif;mso-ascii-theme-font:
 minor-fareast;mso-fareast-theme-font:minor-fareast;mso-hansi-theme-font:minor-fareast;
 mso-bidi-font-family:&quot;Segoe UI&quot;;color:black;mso-themecolor:text1;mso-font-kerning:
 0pt">另一種非常受歡迎的咖啡豆品種，通常價格比阿拉比卡豆便宜。咖啡豆味道較苦，咖啡因含量較高，通常用來製作濃縮咖啡。主要在巴西、印度尼西亞、越南和中美洲種植。<span lang="EN-US"><o:p></o:p></span></span></p><p class="MsoListParagraph" style="margin-left:8.5pt;mso-para-margin-left:0gd;
 text-indent:-8.5pt;mso-list:l0 level1 lfo1"><!--[if !supportLists]--><span lang="EN-US" style="mso-bidi-font-size:12.0pt;font-family:&quot;新細明體&quot;,serif;
 mso-ascii-theme-font:minor-fareast;mso-fareast-theme-font:minor-fareast;
 mso-hansi-theme-font:minor-fareast;mso-bidi-font-family:新細明體;mso-bidi-theme-font:
 minor-fareast;color:black;mso-themecolor:text1;mso-font-kerning:0pt">3.</span><!--[endif]--><span style="mso-bidi-font-size:
 12.0pt;font-family:&quot;新細明體&quot;,serif;mso-ascii-theme-font:minor-fareast;mso-fareast-theme-font:
 minor-fareast;mso-hansi-theme-font:minor-fareast;mso-bidi-font-family:&quot;Segoe UI&quot;;
 color:black;mso-themecolor:text1;mso-font-kerning:0pt">印度孟買莓咖啡豆<span lang="EN-US"><o:p></o:p></span></span></p><p class="MsoListParagraph" style="margin-left:8.5pt;mso-para-margin-left:0gd;
 text-indent:24.0pt;mso-char-indent-count:2.0"><span style="mso-bidi-font-size:12.0pt;font-family:&quot;新細明體&quot;,serif;mso-ascii-theme-font:
 minor-fareast;mso-fareast-theme-font:minor-fareast;mso-hansi-theme-font:minor-fareast;
 mso-bidi-font-family:&quot;Segoe UI&quot;;color:black;mso-themecolor:text1;mso-font-kerning:
 0pt">相對較少人知道的咖啡豆品種，其特別之處在於其豐富的風味和酸度。主要在印度西南部的喀拉拉邦種植，其品質和風味非常獨特，是咖啡愛好者的最愛。<span lang="EN-US"><o:p></o:p></span></span></p><p class="MsoNormal"><span lang="EN-US" style="mso-bidi-font-size:12.0pt;font-family:&quot;新細明體&quot;,serif;mso-ascii-theme-font:
 minor-fareast;mso-fareast-theme-font:minor-fareast;mso-hansi-theme-font:minor-fareast;
 mso-bidi-font-family:&quot;Segoe UI&quot;;color:black;mso-themecolor:text1;mso-font-kerning:
 0pt">4.</span><span style="mso-bidi-font-size:12.0pt;font-family:&quot;新細明體&quot;,serif;mso-ascii-theme-font:
 minor-fareast;mso-fareast-theme-font:minor-fareast;mso-hansi-theme-font:minor-fareast;
 mso-bidi-font-family:&quot;Segoe UI&quot;;color:black;mso-themecolor:text1;mso-font-kerning:
 0pt">肯尼亞<span lang="EN-US">AA</span>咖啡豆<span lang="EN-US"><o:p></o:p></span></span></p><p class="MsoListParagraph" style="margin-left:8.5pt;mso-para-margin-left:0gd"><span lang="EN-US" style="mso-bidi-font-size:
 12.0pt;font-family:&quot;新細明體&quot;,serif;mso-ascii-theme-font:minor-fareast;mso-fareast-theme-font:
 minor-fareast;mso-hansi-theme-font:minor-fareast;mso-bidi-font-family:&quot;Segoe UI&quot;;
 color:black;mso-themecolor:text1;mso-font-kerning:0pt">&nbsp;&nbsp; </span><span style="mso-bidi-font-size:12.0pt;font-family:&quot;新細明體&quot;,serif;
 mso-ascii-theme-font:minor-fareast;mso-fareast-theme-font:minor-fareast;
 mso-hansi-theme-font:minor-fareast;mso-bidi-font-family:&quot;Segoe UI&quot;;color:black;
 mso-themecolor:text1;mso-font-kerning:0pt">高品質的咖啡豆品種，以其濃郁的香氣和複雜的風味而聞名。主要在肯尼亞高地種植，是該國最重要的出口產品之一。這種咖啡豆的特點是豆子較小，但相對密度較大，產量較低，所以價格相對較高。<span lang="EN-US"><o:p></o:p></span></span></p><p class="MsoNormal"><span lang="EN-US" style="mso-bidi-font-size:12.0pt;font-family:&quot;新細明體&quot;,serif;mso-ascii-theme-font:
 minor-fareast;mso-fareast-theme-font:minor-fareast;mso-hansi-theme-font:minor-fareast;
 mso-bidi-font-family:&quot;Segoe UI&quot;;color:black;mso-themecolor:text1;mso-font-kerning:
 0pt">5.</span><span style="mso-bidi-font-size:12.0pt;font-family:&quot;新細明體&quot;,serif;mso-ascii-theme-font:
 minor-fareast;mso-fareast-theme-font:minor-fareast;mso-hansi-theme-font:minor-fareast;
 mso-bidi-font-family:&quot;Segoe UI&quot;;color:black;mso-themecolor:text1;mso-font-kerning:
 0pt">哥倫比亞<span lang="EN-US">Supremo<o:p></o:p></span></span></p><p class="MsoListParagraph" style="margin-left:8.5pt;mso-para-margin-left:0gd"><span lang="EN-US" style="mso-bidi-font-size:
 12.0pt;font-family:&quot;新細明體&quot;,serif;mso-ascii-theme-font:minor-fareast;mso-fareast-theme-font:
 minor-fareast;mso-hansi-theme-font:minor-fareast;mso-bidi-font-family:&quot;Segoe UI&quot;;
 color:black;mso-themecolor:text1;mso-font-kerning:0pt">&nbsp;&nbsp;&nbsp; </span><span style="mso-bidi-font-size:12.0pt;font-family:&quot;新細明體&quot;,serif;
 mso-ascii-theme-font:minor-fareast;mso-fareast-theme-font:minor-fareast;
 mso-hansi-theme-font:minor-fareast;mso-bidi-font-family:&quot;Segoe UI&quot;;color:black;
 mso-themecolor:text1;mso-font-kerning:0pt">高品質的咖啡豆，以其均衡的風味和低酸度而聞名。主要在哥倫比亞種植，這裡的氣候和土壤適合種植優質的阿拉比卡咖啡豆。特點是豆子較大，產量較高，價格相對較實惠。<span lang="EN-US"><o:p></o:p></span></span></p><p class="MsoNormal"><span lang="EN-US" style="mso-bidi-font-size:12.0pt;font-family:&quot;新細明體&quot;,serif;mso-ascii-theme-font:
 minor-fareast;mso-fareast-theme-font:minor-fareast;mso-hansi-theme-font:minor-fareast;
 mso-bidi-font-family:&quot;Segoe UI&quot;;color:black;mso-themecolor:text1;mso-font-kerning:
 0pt">6.</span><span style="mso-bidi-font-size:12.0pt;font-family:&quot;新細明體&quot;,serif;mso-ascii-theme-font:
 minor-fareast;mso-fareast-theme-font:minor-fareast;mso-hansi-theme-font:minor-fareast;
 mso-bidi-font-family:&quot;Segoe UI&quot;;color:black;mso-themecolor:text1;mso-font-kerning:
 0pt">藍山咖啡豆<span lang="EN-US"><o:p></o:p></span></span></p><p class="MsoListParagraph" style="margin-left:8.5pt;mso-para-margin-left:0gd"><span lang="EN-US" style="mso-bidi-font-size:
 12.0pt;font-family:&quot;新細明體&quot;,serif;mso-ascii-theme-font:minor-fareast;mso-fareast-theme-font:
 minor-fareast;mso-hansi-theme-font:minor-fareast;mso-bidi-font-family:&quot;Segoe UI&quot;;
 color:black;mso-themecolor:text1;mso-font-kerning:0pt">&nbsp;&nbsp;&nbsp; </span><span style="mso-bidi-font-size:12.0pt;font-family:&quot;新細明體&quot;,serif;
 mso-ascii-theme-font:minor-fareast;mso-fareast-theme-font:minor-fareast;
 mso-hansi-theme-font:minor-fareast;mso-bidi-font-family:&quot;Segoe UI&quot;;color:black;
 mso-themecolor:text1;mso-font-kerning:0pt">非常獨特的咖啡豆品種，產自牙買加的藍山地區。這種咖啡豆的特點是風味豐富、酸度低、香氣濃郁、口感滑順。由於其品質極高，價格也相對較高，因此被譽為世界上最好的咖啡之一。<span lang="EN-US"><o:p></o:p></span></span></p><p>
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 </p><p class="MsoListParagraph" style="margin-left:8.5pt;mso-para-margin-left:0gd"><span lang="EN-US" style="mso-bidi-font-size:
 12.0pt;font-family:&quot;新細明體&quot;,serif;mso-ascii-theme-font:minor-fareast;mso-fareast-theme-font:
 minor-fareast;mso-hansi-theme-font:minor-fareast;mso-bidi-font-family:&quot;Segoe UI&quot;;
 color:black;mso-themecolor:text1;mso-font-kerning:0pt">&nbsp;&nbsp;&nbsp; </span><span style="mso-bidi-font-size:12.0pt;font-family:&quot;新細明體&quot;,serif;
 mso-ascii-theme-font:minor-fareast;mso-fareast-theme-font:minor-fareast;
 mso-hansi-theme-font:minor-fareast;mso-bidi-font-family:&quot;Segoe UI&quot;;color:black;
 mso-themecolor:text1;mso-font-kerning:0pt">總結，這些咖啡豆種類都有自己獨特的特點和風味，適合不同口味和品味的咖啡愛好者。如果你想要品嘗不同的咖啡風味，可以嘗試不同的咖啡豆種類，慢慢品嚐其風味和特點，發現自己喜愛的口味。<span lang="EN-US"><o:p></o:p></span></span></p>',
NOW(),
0,
0,
0,
'Admin',
NOW(),
false,
false),
(
1,
8,
'星巴克記趣',
'<p class="MsoNormal" style="text-indent:24.0pt"><span style="font-family:&quot;新細明體&quot;,serif;
 mso-ascii-theme-font:minor-fareast;mso-fareast-theme-font:minor-fareast;
 mso-hansi-theme-font:minor-fareast;color:black;mso-themecolor:text1">作為全球最受歡迎的咖啡品牌之一，星巴克已經成為人們享受咖啡和美食的經典場所之一。在這裡，你可以品嚐到各種口味豐富的咖啡，同時也可以品嚐到多種美味的輕食和甜點。<span lang="EN-US"><o:p></o:p></span></span></p><p class="MsoNormal" style="text-indent:24.0pt"><span style="font-family:&quot;新細明體&quot;,serif;
 mso-ascii-theme-font:minor-fareast;mso-fareast-theme-font:minor-fareast;
 mso-hansi-theme-font:minor-fareast;color:black;mso-themecolor:text1">以下是我在星巴克享用美食的心得體驗：首先，我試了他們的著名手工調製咖啡，其中最受歡迎的是焦糖瑪奇朵。這種咖啡飲品混合了濃縮咖啡、牛奶和焦糖醬，味道非常濃郁、香甜。每一口都能讓你感受到咖啡和奶油的香氣，而焦糖醬的加入使得整個口感變得更加豐富和醇厚。<span lang="EN-US"><o:p></o:p></span></span></p><p class="MsoNormal" style="text-indent:24.0pt"><span style="font-family:&quot;新細明體&quot;,serif;
 mso-ascii-theme-font:minor-fareast;mso-fareast-theme-font:minor-fareast;
 mso-hansi-theme-font:minor-fareast;color:black;mso-themecolor:text1">另外，星巴克還提供了其他口味的手工調製咖啡，如拿鐵、卡布奇諾、摩卡等，每一款都有其獨特的風味和口感，值得一試。除了咖啡，星巴克還提供了各種美食和甜點，如香蕉核桃鬆餅、法式奶油鬆餅、巧克力布朗尼等。其中，我最喜歡的是法式奶油鬆餅，這種鬆餅非常鬆軟，口感綿密、柔軟，帶有濃郁的奶油香氣，非常美味。<span lang="EN-US"><o:p></o:p></span></span></p><p class="MsoNormal" style="text-indent:24.0pt"><span style="font-family:&quot;新細明體&quot;,serif;
 mso-ascii-theme-font:minor-fareast;mso-fareast-theme-font:minor-fareast;
 mso-hansi-theme-font:minor-fareast;color:black;mso-themecolor:text1">此外，星巴克還提供了多種口味的三明治和沙拉，如義式香腸蛋三明治、墨西哥辣味沙拉等，供應的食物種類非常豐富。最後，我必須提到星巴克的環境和氛圍。<span lang="EN-US"><o:p></o:p></span></span></p><p class="MsoNormal" style="text-indent:24.0pt"><span style="font-family:&quot;新細明體&quot;,serif;
 mso-ascii-theme-font:minor-fareast;mso-fareast-theme-font:minor-fareast;
 mso-hansi-theme-font:minor-fareast;color:black;mso-themecolor:text1">星巴克的店鋪氣氛獨特，帶有舒適和放鬆的感覺，讓你可以坐下來，享受一杯咖啡，閱讀書籍或工作。店內裝潢簡潔、現代，有許多舒適的座位、燈光和音樂，讓你感到放輕和舒適。在這裡，你可以和朋友聊天，也可以獨自享受寧靜的時光，這種寧靜的氛圍和環境，讓人感到非常放鬆和愉悅。<span lang="EN-US"><o:p></o:p></span></span></p><p>
 
 
 
 
 
 
 
 
 
 </p><p class="MsoNormal" style="text-indent:24.0pt"><span style="font-family:&quot;新細明體&quot;,serif;
 mso-ascii-theme-font:minor-fareast;mso-fareast-theme-font:minor-fareast;
 mso-hansi-theme-font:minor-fareast;color:black;mso-themecolor:text1">總體而言，我非常喜歡在星巴克享受美食和咖啡。他們的食物和飲品品質非常優秀，店鋪的環境和氛圍也非常舒適和放鬆。如果你正在尋找一個可以品嚐美味食物和享受輕松時光的地方，我強烈推薦你到星巴克來一趟。無論你是咖啡愛好者還是美食愛好者，星巴克都能滿足你的需求，讓你度過一個愉快的時光。<span lang="EN-US"><o:p></o:p></span></span></p>',
NOW(),
0,
0,
0,
'Admin',
NOW(),
false,
false),
(3,
2,
'保存和研磨咖啡豆的技巧',
'<p class="MsoNormal" style="text-indent:24.0pt"><span style="font-family:&quot;新細明體&quot;,serif;
 mso-ascii-theme-font:minor-fareast;mso-fareast-theme-font:minor-fareast;
 mso-hansi-theme-font:minor-fareast;color:black;mso-themecolor:text1">保存和研磨咖啡豆是保證咖啡風味和品質的重要環節。在這篇文章中，我們將分享一些保存和研磨咖啡豆的技巧，讓你能夠在家中輕鬆享受新鮮研磨的咖啡。<span lang="EN-US"><o:p></o:p></span></span></p><p class="MsoNormal" style="text-indent:24.0pt"><span style="font-family:&quot;新細明體&quot;,serif;
 mso-ascii-theme-font:minor-fareast;mso-fareast-theme-font:minor-fareast;
 mso-hansi-theme-font:minor-fareast;color:black;mso-themecolor:text1">首先，讓我們來談談咖啡豆的保存技巧。咖啡豆的新鮮度對咖啡的風味至關重要。為了保持咖啡豆的新鮮度，我們建議將咖啡豆存放在不透明、密封且不受陽光直射的容器中。避免將咖啡豆放在潮濕的地方，以免影響咖啡豆的品質。此外，咖啡豆最好在開封後的兩週內盡量使用完畢，以確保風味的最佳表現。<span lang="EN-US"><o:p></o:p></span></span></p><p class="MsoNormal" style="text-indent:24.0pt"><span style="font-family:&quot;新細明體&quot;,serif;
 mso-ascii-theme-font:minor-fareast;mso-fareast-theme-font:minor-fareast;
 mso-hansi-theme-font:minor-fareast;color:black;mso-themecolor:text1">接下來，讓我們討論咖啡豆的研磨技巧。研磨咖啡豆的適當程度能夠影響咖啡的風味和提取效果。對於不同的沖泡方式，需要適合的研磨度。例如，咖啡壺需要中度至粗研磨，而濾杯和濾紙需要中度研磨，而像是手沖和濃縮咖啡則需要較細的研磨度。請注意，研磨咖啡豆的時候，最好是在沖泡前研磨，以確保咖啡的新鮮度和風味。<span lang="EN-US"><o:p></o:p></span></span></p><p class="MsoNormal" style="text-indent:24.0pt"><span style="font-family:&quot;新細明體&quot;,serif;
 mso-ascii-theme-font:minor-fareast;mso-fareast-theme-font:minor-fareast;
 mso-hansi-theme-font:minor-fareast;color:black;mso-themecolor:text1">此外，研磨咖啡豆時，要注意控制研磨的時間和速度，避免產生過多的熱量和摩擦。過度研磨咖啡豆可能會導致苦澀的味道，而研磨不足則會造成咖啡的口感稀薄。研磨完成後，記得將研磨好的咖啡豆盛放在乾淨的容器中，避免受潮和暴露在空氣中。<span lang="EN-US"><o:p></o:p></span></span></p><p class="MsoNormal" style="text-indent:24.0pt"><span style="font-family:&quot;新細明體&quot;,serif;
 mso-ascii-theme-font:minor-fareast;mso-fareast-theme-font:minor-fareast;
 mso-hansi-theme-font:minor-fareast;color:black;mso-themecolor:text1">最後，建議在沖泡前量取適量的咖啡豆進行研磨，以確保每次沖泡都使用新鮮研磨的咖啡粉。這樣可以最大限度地保留咖啡豆的風味和香氣。<span lang="EN-US"><o:p></o:p></span></span></p><p>
 
 
 
 
 
 
 
 
 
 </p><p class="MsoNormal" style="text-indent:24.0pt"><span style="font-family:&quot;新細明體&quot;,serif;
 mso-ascii-theme-font:minor-fareast;mso-fareast-theme-font:minor-fareast;
 mso-hansi-theme-font:minor-fareast;color:black;mso-themecolor:text1">總結來說，保存和研磨咖啡豆是保證咖啡品質的關鍵步驟。透過適當的保存和研磨技巧，我們可以在家中輕鬆享受新鮮研磨的咖啡，品味到豐富的風味和香氣。希望這篇文章能夠幫助你更好地保存和研磨咖啡豆，提升你的咖啡體驗。如果你對其他主題還有需求，請隨時告訴我。<span lang="EN-US"><o:p></o:p></span></span></p>',
NOW(),
0,
0,
0,
'Admin',
NOW(),
false,
false),
(1,
8,
'咖啡與糕點的完美組合',
'<p class="MsoNormal" style="text-indent:24.0pt"><span style="font-family:&quot;新細明體&quot;,serif;
 mso-ascii-theme-font:minor-fareast;mso-fareast-theme-font:minor-fareast;
 mso-hansi-theme-font:minor-fareast;color:black;mso-themecolor:text1">咖啡與糕點，是一對完美組合，彷彿天生就為彼此而生。在這篇文章中，我們將介紹一些令人垂涎欲滴的咖啡與糕點搭配，讓你在品味咖啡的同時，也能享受到獨特的糕點美味。<span lang="EN-US"><o:p></o:p></span></span></p><p class="MsoNormal" style="text-indent:24.0pt"><span style="font-family:&quot;新細明體&quot;,serif;
 mso-ascii-theme-font:minor-fareast;mso-fareast-theme-font:minor-fareast;
 mso-hansi-theme-font:minor-fareast;color:black;mso-themecolor:text1">首先，讓我們來談談咖啡與巧克力蛋糕的組合。巧克力蛋糕的濃郁口感和咖啡的苦甜風味相得益彰。一口咖啡，搭配一小塊巧克力蛋糕，能夠帶來一種奢華的味覺享受。咖啡的微苦與巧克力的甜美交織在一起，讓人回味無窮。<span lang="EN-US"><o:p></o:p></span></span></p><p class="MsoNormal" style="text-indent:24.0pt"><span style="font-family:&quot;新細明體&quot;,serif;
 mso-ascii-theme-font:minor-fareast;mso-fareast-theme-font:minor-fareast;
 mso-hansi-theme-font:minor-fareast;color:black;mso-themecolor:text1">接下來，讓我們探討咖啡與提拉米蘇的絕佳組合。提拉米蘇是一道以咖啡浸泡的意大利傳統甜點，與咖啡相得益彰。咖啡的香氣與提拉米蘇的濃郁奶油和可可粉味道相互融合，營造出一種令人陶醉的味覺饗宴。一口咖啡，再配上一塊滑順的提拉米蘇，絕對是一種愉悅的享受。<span lang="EN-US"><o:p></o:p></span></span></p><p class="MsoNormal" style="text-indent:24.0pt"><span style="font-family:&quot;新細明體&quot;,serif;
 mso-ascii-theme-font:minor-fareast;mso-fareast-theme-font:minor-fareast;
 mso-hansi-theme-font:minor-fareast;color:black;mso-themecolor:text1">除了巧克力蛋糕和提拉米蘇，咖啡還可以與其他糕點相融合，例如水果塔、檸檬蛋糕、杏仁餅乾等。每一款糕點都有其獨特的風味和口感，與咖啡搭配後能夠帶來更豐富的層次感和口味變化。你可以根據自己的口味偏好，嘗試不同的咖啡與糕點組合，發掘屬於自己的完美搭配。<span lang="EN-US"><o:p></o:p></span></span></p><p>
 
 
 
 
 
 
 
 </p><p class="MsoNormal" style="text-indent:24.0pt"><span style="font-family:&quot;新細明體&quot;,serif;
 mso-ascii-theme-font:minor-fareast;mso-fareast-theme-font:minor-fareast;
 mso-hansi-theme-font:minor-fareast;color:black;mso-themecolor:text1">最後，無論是在家中還是咖啡廳，你都可以輕鬆品嚐到咖啡與糕點的完美組合。在沉澱的咖啡香氣中，一口口咬下濃醇的糕點，讓味蕾感受到無比的滿足和享受。<span lang="EN-US"><o:p></o:p></span></span></p>',
NOW(),
0,
0,
0,
'Admin',
NOW(),
false,
false),
(2,
5,
'探索完美磨豆機：品味咖啡的關鍵利器',
'<p class="MsoNormal" style="text-indent:24.0pt"><span style="font-family:&quot;新細明體&quot;,serif;
 mso-ascii-theme-font:minor-fareast;mso-fareast-theme-font:minor-fareast;
 mso-hansi-theme-font:minor-fareast;color:black;mso-themecolor:text1">磨豆機是咖啡愛好者不可或缺的工具之一，它扮演著研磨咖啡豆的重要角色。在這篇文章中，我們將深入探討磨豆機的功能、類型以及對咖啡品質的影響，幫助你更好地了解磨豆機的重要性。<span lang="EN-US"><o:p></o:p></span></span></p><p class="MsoNormal" style="text-indent:24.0pt"><span style="font-family:&quot;新細明體&quot;,serif;
 mso-ascii-theme-font:minor-fareast;mso-fareast-theme-font:minor-fareast;
 mso-hansi-theme-font:minor-fareast;color:black;mso-themecolor:text1">首先，讓我們來了解磨豆機的作用。磨豆機的主要功能是將咖啡豆研磨成合適的粒度，這是影響咖啡風味的關鍵因素之一。研磨咖啡豆的適當粒度能夠提取出咖啡豆的最佳風味，讓咖啡在沖煮過程中釋放出最豐富的香氣和口感。<span lang="EN-US"><o:p></o:p></span></span></p><p class="MsoNormal" style="text-indent:24.0pt"><span style="font-family:&quot;新細明體&quot;,serif;
 mso-ascii-theme-font:minor-fareast;mso-fareast-theme-font:minor-fareast;
 mso-hansi-theme-font:minor-fareast;color:black;mso-themecolor:text1">磨豆機的類型多種多樣，主要分為手動磨豆機和電動磨豆機兩種。手動磨豆機通常較小巧便攜，需要手動轉動磨盤進行研磨，適合喜歡親自參與咖啡沖煮過程的咖啡愛好者。電動磨豆機則具有更高的磨豆效率和便捷性，通常配備不同的磨豆設定，可以調整磨豆粒度，適合追求便利和效率的使用者。<span lang="EN-US"><o:p></o:p></span></span></p><p class="MsoNormal" style="text-indent:24.0pt"><span style="font-family:&quot;新細明體&quot;,serif;
 mso-ascii-theme-font:minor-fareast;mso-fareast-theme-font:minor-fareast;
 mso-hansi-theme-font:minor-fareast;color:black;mso-themecolor:text1">除了類型之外，磨豆機的品質和磨盤材質也對咖啡品質有重要影響。高品質的磨豆機通常擁有穩定的磨豆效果和可調節的磨豆設定，能夠確保研磨出一致且符合需求的粒度。而磨盤材質則會影響研磨過程中的熱量和靜電，進而影響咖啡豆的風味。一般來說，陶瓷磨盤較少產生熱量，有助於保護咖啡風味的完整性。<span lang="EN-US"><o:p></o:p></span></span></p><p class="MsoNormal"><span lang="EN-US" style="font-family:&quot;新細明體&quot;,serif;mso-ascii-theme-font:
 minor-fareast;mso-fareast-theme-font:minor-fareast;mso-hansi-theme-font:minor-fareast;
 color:black;mso-themecolor:text1">&nbsp;</span></p><p class="MsoNormal" style="text-indent:24.0pt"><span style="font-family:&quot;新細明體&quot;,serif;
 mso-ascii-theme-font:minor-fareast;mso-fareast-theme-font:minor-fareast;
 mso-hansi-theme-font:minor-fareast;color:black;mso-themecolor:text1">在選購磨豆機時，還需要考慮磨豆機的容量和清潔維護等因素。容量足夠的磨豆機可以一次性磨碎所需的咖啡豆量，節省時間和精力。而易於清潔的磨豆機能夠保持咖啡豆的新鮮度，避免殘留咖啡渣影響下次使用。<span lang="EN-US"><o:p></o:p></span></span></p><p class="MsoNormal"><span lang="EN-US" style="font-family:&quot;新細明體&quot;,serif;mso-ascii-theme-font:
 minor-fareast;mso-fareast-theme-font:minor-fareast;mso-hansi-theme-font:minor-fareast;
 color:black;mso-themecolor:text1">&nbsp;</span></p><p>
 
 
 
 
 
 
 
 
 
 
 
 
 
 </p><p class="MsoNormal" style="text-indent:24.0pt"><span style="font-family:&quot;新細明體&quot;,serif;
 mso-ascii-theme-font:minor-fareast;mso-fareast-theme-font:minor-fareast;
 mso-hansi-theme-font:minor-fareast;color:black;mso-themecolor:text1">總結來說，磨豆機是咖啡愛好者不可或缺的利器，能夠為你帶來更純粹、豐富的咖啡體驗。選擇合適的磨豆機能夠確保咖啡豆的最佳研磨粒度，讓咖啡的風味和口感達到最佳狀態。希望這篇文章能夠幫助你更好地了解磨豆機，為你的咖啡沖煮之旅增添更多樂趣和品質保證。<span lang="EN-US"><o:p></o:p></span></span></p>',
NOW(),
0,
0,
0,
'Admin',
NOW(),
false,
false),
(3,
7,
'品味咖啡壺的獨特魅力',
'<p class="MsoNormal" style="text-indent:24.0pt"><span style="font-family:&quot;新細明體&quot;,serif;
 mso-ascii-theme-font:minor-fareast;mso-fareast-theme-font:minor-fareast;
 mso-hansi-theme-font:minor-fareast;color:black;mso-themecolor:text1">咖啡壺是一種經典的咖啡沖泡工具，它不僅能夠幫助我們沖泡出香濃美味的咖啡，還有著獨特的魅力。在這篇文章中，我們將探索咖啡壺的各種種類、工藝和使用技巧，讓你更深入了解這個令人愛不釋手的咖啡器具。<span lang="EN-US"><o:p></o:p></span></span></p><p class="MsoNormal" style="text-indent:24.0pt"><span lang="EN-US" style="font-family:&quot;新細明體&quot;,serif;mso-ascii-theme-font:minor-fareast;mso-fareast-theme-font:
 minor-fareast;mso-hansi-theme-font:minor-fareast;color:black;mso-themecolor:
 text1">&nbsp;</span></p><p class="MsoNormal" style="text-indent:24.0pt"><span style="font-family:&quot;新細明體&quot;,serif;
 mso-ascii-theme-font:minor-fareast;mso-fareast-theme-font:minor-fareast;
 mso-hansi-theme-font:minor-fareast;color:black;mso-themecolor:text1">首先，我們來介紹一些常見的咖啡壺種類。法式壺（<span lang="EN-US">French Press</span>）是一款經典的咖啡壺，它具有簡單易用的特點，能夠沖泡出濃郁的咖啡口感。意式壺（<span lang="EN-US">Moka Pot</span>）則是一款源自意大利的咖啡壺，它通過壓力和熱水的結合，製作出濃縮咖啡。另外，還有滴漏壺（<span lang="EN-US">Drip Pot</span>）、壓濾壺（<span lang="EN-US">AeroPress</span>）等不同種類的咖啡壺，每種壺都有其獨特的沖泡方式和風味。<span lang="EN-US"><o:p></o:p></span></span></p><p class="MsoNormal" style="text-indent:24.0pt"><span lang="EN-US" style="font-family:&quot;新細明體&quot;,serif;mso-ascii-theme-font:minor-fareast;mso-fareast-theme-font:
 minor-fareast;mso-hansi-theme-font:minor-fareast;color:black;mso-themecolor:
 text1">&nbsp;</span></p><p class="MsoNormal" style="text-indent:24.0pt"><span style="font-family:&quot;新細明體&quot;,serif;
 mso-ascii-theme-font:minor-fareast;mso-fareast-theme-font:minor-fareast;
 mso-hansi-theme-font:minor-fareast;color:black;mso-themecolor:text1">接下來，我們將探討咖啡壺的工藝。咖啡壺的材質和製作工藝直接影響著咖啡的風味和品質。不鏽鋼咖啡壺具有良好的熱傳導性和耐用性，能夠保持咖啡的溫度和風味。玻璃咖啡壺則能讓我們清楚地觀察到咖啡的沖泡過程，同時也維護了咖啡的純淨口感。陶瓷咖啡壺則給予咖啡一種獨特的質感和口感，它能夠保持咖啡的溫度和濃度，帶來舒適的咖啡體驗。<span lang="EN-US"><o:p></o:p></span></span></p><p class="MsoNormal" style="text-indent:24.0pt"><span lang="EN-US" style="font-family:&quot;新細明體&quot;,serif;mso-ascii-theme-font:minor-fareast;mso-fareast-theme-font:
 minor-fareast;mso-hansi-theme-font:minor-fareast;color:black;mso-themecolor:
 text1">&nbsp;</span></p><p class="MsoNormal" style="text-indent:24.0pt"><span style="font-family:&quot;新細明體&quot;,serif;
 mso-ascii-theme-font:minor-fareast;mso-fareast-theme-font:minor-fareast;
 mso-hansi-theme-font:minor-fareast;color:black;mso-themecolor:text1">除了咖啡壺的種類和工藝，我們還將分享一些使用咖啡壺的技巧。首先是選擇適合的咖啡豆和研磨度，不同咖啡壺需要不同類型<span lang="EN-US"><o:p></o:p></span></span></p><p class="MsoNormal" style="text-indent:24.0pt"><span lang="EN-US" style="font-family:&quot;新細明體&quot;,serif;mso-ascii-theme-font:minor-fareast;mso-fareast-theme-font:
 minor-fareast;mso-hansi-theme-font:minor-fareast;color:black;mso-themecolor:
 text1">&nbsp;</span></p><p class="MsoNormal" style="text-indent:24.0pt"><span style="font-family:&quot;新細明體&quot;,serif;
 mso-ascii-theme-font:minor-fareast;mso-fareast-theme-font:minor-fareast;
 mso-hansi-theme-font:minor-fareast;color:black;mso-themecolor:text1">的咖啡豆和研磨度，適應性好的咖啡壺能夠適應不同類型的咖啡豆。其次是掌握沖泡的時間和比例，適當的沖泡時間和比例能夠提取出咖啡豆的最佳風味。最後是保養和清潔咖啡壺，定期清潔咖啡壺能夠保持其品質和耐用性。<span lang="EN-US"><o:p></o:p></span></span></p><p class="MsoNormal" style="text-indent:24.0pt"><span lang="EN-US" style="font-family:&quot;新細明體&quot;,serif;mso-ascii-theme-font:minor-fareast;mso-fareast-theme-font:
 minor-fareast;mso-hansi-theme-font:minor-fareast;color:black;mso-themecolor:
 text1">&nbsp;</span></p><p class="MsoNormal">
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 </p><p class="MsoNormal" style="text-indent:24.0pt"><span style="font-family:&quot;新細明體&quot;,serif;
 mso-ascii-theme-font:minor-fareast;mso-fareast-theme-font:minor-fareast;
 mso-hansi-theme-font:minor-fareast;color:black;mso-themecolor:text1">總結來說，咖啡壺是一個不可或缺的咖啡工具，它的種類、工藝和使用技巧都對咖啡的風味和品質有著重要的影響。透過選擇合適的咖啡壺、掌握沖泡技巧，我們可以在家中輕鬆沖泡出一杯香濃美味的咖啡，享受細緻的咖啡體驗。希望這篇文章能夠幫助你更深入了解咖啡壺的獨特魅力，並帶給你更愉悅的咖啡時光。<span lang="EN-US"><o:p></o:p></span></span></p>',
NOW(),
0,
0,
0,
'Admin',
NOW(),
false,
false);


INSERT INTO `extractp`.`article_comment`
(
`member_id`,
`article_id`,
`article_comment_content`,
`article_comment_created_date`,
`article_comment_is_hidden`)
VALUES
(
1,
1,
'寫真好，你問chatgpt的吧?!!!',
NOW(),
false),
(
2,
3,
'謝謝你的分享',
NOW(),
false),
(
3,
2,
'測試留言~~~',
NOW(),
false),
(
1,
4,
'喝咖啡，吃甜食，讓你胃食道逆流了嗎？',
NOW(),
false),
(
2,
5,
'磨豆機讚讚！',
NOW(),
false),
(
3,
6,
'求贊助一台QAQ',
NOW(),
false),
(
1,
4,
'五樓樓上是幾樓?!',
NOW(),
false);


INSERT INTO `extractp`.`article_comment_report`
(`member_id`,
`article_comment_id`,
`article_comment_report_content`,
`article_comment_report_created_date`,
`article_comment_report_status`,
`article_comment_report_last_modified_date`,
`article_comment_report_last_modified_by`)
VALUES
(1,
1,
'人身攻擊',
NOW(),
0,
NOW(),
'Admin'),
(2,
2,
'不雅文字',
NOW(),
0,
NOW(),
'Admin'),
(3,
3,
'歧視語言',
NOW(),
0,
NOW(),
'Admin'),
(1,
4,
'散播廣告',
NOW(),
0,
NOW(),
'Admin');


INSERT INTO `article_group`
(`article_template_id`,
`article_group_name`,
`article_group_created_date`,
`article_group_created_by`)
VALUES
(1,
'種類產地',
NOW(),
'Admin'),
(1,
'處理方式',
NOW(),
'Admin'),
(1,
'烘焙程度',
NOW(),
'Admin'),
(2,
'咖啡壺',
NOW(),
'Lara'),
(2,
'磨豆機',
NOW(),
'Admin'),
(2,
'周邊配件',
NOW(),
'Admin'),
(3,
'商品心得',
NOW(),
'Lara'),
(3,
'美食記',
NOW(),
'Admin'),
(3,
'課程分享',
NOW(),
'Admin'),
(3,
'活動分享',
NOW(),
'Admin'),
(4,
'咖啡壺',
NOW(),
'Admin'),
(4,
'磨豆機',
NOW(),
'Admin'),
(4,
'周邊配件',
NOW(),
'Admin');


INSERT INTO `extractp`.`article_report`
(`member_id`,
`article_id`,
`article_report_content`,
`article_report_created_date`,
`article_report_status`,
`article_report_last_modified_date`,
`article_report_last_modified_by`)
VALUES
(1,
2,
'歧視語言',
NOW(),
0,
NOW(),
'Admin'),
(2,
3,
'散播廣告',
NOW(),
0,
NOW(),
'Admin'),
(3,
4,
'不雅文字',
NOW(),
0,
NOW(),
'Admin'),
(1,
6,
'色情內容',
NOW(),
0,
NOW(),
'Admin');


INSERT INTO `article_template`
(`article_template_name`,
`article_template_description`,
`article_template_created_date`,
`article_template_created_by`
)
VALUES
('咖啡知識',
'1.禁止發表與本板主旨無關之內容。
2.不論是新手發問或是高手討論，請注意網路禮節。
3.禁止業配及廣告文，心得分享務必保持客觀中立。
4.轉錄或節錄文章者，請取得原作者同意，並註明出處。
5.禁止販售、揪團團購。
6.發文及發言是每個人的權利，但嚴禁人身攻擊或偏激留言。
7.文章內容請勿空泛或明顯無意義內容。',
NOW(),
'Admin'
),
('咖啡器具',
'1.禁止發表與本板主旨無關之內容。
2.不論是新手發問或是高手討論，請注意網路禮節。
3.禁止業配及廣告文，心得分享務必保持客觀中立。
4.轉錄或節錄文章者，請取得原作者同意，並註明出處。
5.禁止販售、揪團團購。
6.發文及發言是每個人的權利，但嚴禁人身攻擊或偏激留言。
7.文章內容請勿空泛或明顯無意義內容。',
NOW(),
'Admin'
),
('心得分享',
'1.禁止發表與本板主旨無關之內容。
2.不論是新手發問或是高手討論，請注意網路禮節。
3.禁止業配及廣告文，心得分享務必保持客觀中立。
4.轉錄或節錄文章者，請取得原作者同意，並註明出處。
5.禁止販售、揪團團購。
6.發文及發言是每個人的權利，但嚴禁人身攻擊或偏激留言。
7.文章內容請勿空泛或明顯無意義內容。',
NOW(),
'Admin'
),
('二手交易',
'1.禁止發表與本板主旨無關之內容。
2.不論是新手發問或是高手討論，請注意網路禮節。
3.禁止業配及廣告文，心得分享務必保持客觀中立。
4.轉錄或節錄文章者，請取得原作者同意，並註明出處。
5.禁止販售、揪團團購。
6.發文及發言是每個人的權利，但嚴禁人身攻擊或偏激留言。
7.文章內容請勿空泛或明顯無意義內容。
8.發文請依照格式。禁止發布與本論壇無關或非必要之宣傳。',
NOW(),
'Admin'
);


INSERT INTO `extractp`.`article_thunmb`
(`member_id`,
`article_id`,
`article_thunmb_created_date`)
VALUES
(1,
2,
NOW()),
(1,
3,
NOW()),
(1,
4,
NOW()),
(2,
2,
NOW()),
(3,
2,
NOW()),
(2,
4,
NOW()),
(3,
6,
NOW()),
(3,
3,
NOW()),
(2,
1,
NOW()),
(1,
5,
NOW()),
(3,
1,
NOW());


INSERT INTO `extractp`.`member_article_fav`
(`member_id`,
`article_id`,
`member_article_fav_created_date`)
VALUES
(1,
3,
NOW()),
(3,
1,
NOW()),
(1,
6,
NOW()),
(2,
2,
NOW()),
(3,
4,
NOW());

INSERT INTO PRODUCT_CATEGORY (category_id, category_name)VALUES(1,"咖啡器具");
INSERT INTO PRODUCT_CATEGORY (category_id, category_name)VALUES(2,"咖啡豆");
INSERT INTO PRODUCT_CATEGORY (category_id, category_name)VALUES(3,"濾掛咖啡");


INSERT INTO PRODUCT (
  category_id,  -- (1：咖啡器具、2.咖啡豆、3.濾掛咖啡)
  product_name, 
  product_image, 
  product_spec, 
  product_description, 
  product_price, 
  product_stock, 
  product_sold_count, 
  product_status, -- (上架中、已售完、已下架)
  product_created_date, 
  product_last_modified_date, 
  product_created_by,  -- (管理員編號，未關聯)
  product_last_modified_by -- (管理員編號，未關聯)
) VALUES
(
  1, 
  'French Press 咖啡壺', 
  NULL, 
  '容量：500ml', 
  '這是一款法式咖啡壺，適合泡沖煮方式，是手沖咖啡的好幫手', 
  400, 
  20, 
  5, 
  '上架中', 
  CURRENT_TIMESTAMP, 
  CURRENT_TIMESTAMP, 
  1,
  1
),
(
  1, 
  'Chemex 咖啡壺', 
  NULL, 
  '容量：700ml', 
  '這是一款玻璃濾壺，適合濾掛式泡法，是手沖咖啡的好幫手', 
  600, 
  15, 
  8, 
  '上架中', 
  CURRENT_TIMESTAMP, 
  CURRENT_TIMESTAMP, 
  1,
  1
),
(
  2, 
  '哥倫比亞單品咖啡豆', 
  NULL, 
  '規格：250g', 
  '這是一款單品咖啡豆，產地為哥倫比亞，口感濃郁醇厚，適合濾掛式泡法', 
  350, 
  50, 
  20, 
  '上架中', 
  CURRENT_TIMESTAMP, 
  CURRENT_TIMESTAMP, 
  1,
  1
),
(
  2, 
  '衣索比亞淺焙咖啡豆', 
  NULL, 
  '規格：500g', 
  '這是一款淺焙咖啡豆，產地為衣索比亞，帶有鮮花香氣和果味，適合手沖或濾掛式泡法', 
  500, 
  40, 
  18, 
  '上架中', 
  CURRENT_TIMESTAMP, 
  CURRENT_TIMESTAMP, 
  1,
  1
),
(
  2,
  '手沖咖啡濾紙',
  NULL,
  '尺寸：02',
  '這款手沖咖啡濾紙是專為手沖咖啡設計的，採用高品質的濾紙材料，能夠有效過濾咖啡渣，讓您品嚐到純正的手沖咖啡。它的尺寸剛好適合大部分手沖咖啡器具，非常方便使用。使用這款濾紙沖泡出來的咖啡濃度均勻，口感順滑，香氣十足。無論您是咖啡愛好者還是對手沖咖啡感興趣的初學者，這款濾紙都能夠滿足您的需求。讓我們一起來享受手沖咖啡的美妙吧！',
  100,
  50,
  10,
  '上架中',
  CURRENT_TIMESTAMP,
  CURRENT_TIMESTAMP,
  1,
  1
),
(
  3,
  '拋棄式咖啡杯',
  NULL,
  '容量：300ml',
  '這款拋棄式咖啡杯方便使用，適合外帶咖啡，非常方便又衛生。它的容量適中，可以滿足您的日常需求。無論您是在路上還是辦公室，都可以隨時隨地享受到美味的咖啡。這款咖啡杯採用環保材料製造，對環境友好，同時也非常堅固耐用。您可以放心使用，並且輕鬆丟棄。讓我們一起減少使用一次性塑膠杯，保護環境，同時享受美味的咖啡！',
  50,
  100,
  20,
  '上架中',
  CURRENT_TIMESTAMP,
  CURRENT_TIMESTAMP,
  1,
  1
),
(
  2,
  '喬治亞咖啡豆',
  NULL,
  '重量：200g',
  '這款喬治亞咖啡豆帶給您濃郁的口感和香氣，是咖啡愛好者的首選。它的重量為200克，足夠讓您品嚐到多次美味的咖啡。這些咖啡豆採用優質的種植和烘焙工藝製成，保證了咖啡的品質和口感。您可以根據自己的喜好選擇研磨度和沖泡方式，來沖泡出屬於自己的完美咖啡。不論您是喜歡濃縮咖啡還是拿鐵咖啡，這款喬治亞咖啡豆都能夠滿足您的需求。讓我們一起享受咖啡的魅力吧！',
  300,
  30,
  8,
  '上架中',
  CURRENT_TIMESTAMP,
  CURRENT_TIMESTAMP,
  1,
  1
),
(
  1,
  '蒸氣咖啡機',
  NULL,
  '型號：XYZ-123',
  '這款蒸氣咖啡機具有優秀的萃取能力和簡單易用的操作，讓您輕鬆沖泡出濃郁的咖啡。它的型號為XYZ-123，是一款性價比很高的咖啡機。這款咖啡機配備了先進的蒸氣噴射系統，可以製作出豐富的奶泡，讓您享受到咖啡店般的咖啡體驗。它還具有自動清潔功能，讓您省去清潔的麻煩。不論您是咖啡愛好者還是家庭使用者，這款蒸氣咖啡機都能夠滿足您的需求。讓我們一起來沖泡出完美的咖啡吧！',
  600,
  15,
  4,
  '上架中',
  CURRENT_TIMESTAMP,
  CURRENT_TIMESTAMP,
  1,
  1
),
(
  3,
  '濾掛咖啡包',
  NULL,
  '口味：原味',
  '這款濾掛咖啡包方便快捷，是懶人沖泡咖啡的好選擇。它採用高品質的咖啡豆製成，保證了咖啡的風味和品質。每個濾掛咖啡包都已經事先包好，只需將其掛在杯邊，倒入熱水，幾分鐘後就能夠品嚐到美味的咖啡了。這款咖啡包口味原味，適合喜歡純正咖啡味道的人。它的包裝簡單輕便，非常適合旅行和戶外活動使用。',
  200,
  40,
  12,
  '上架中',
  CURRENT_TIMESTAMP,
  CURRENT_TIMESTAMP,
  1,
  1
),
(
  2,
  '瑞士巧克力咖啡粉',
  NULL,
  '重量：250g',
  '這款瑞士巧克力咖啡粉結合了咖啡和巧克力的經典口味，讓您享受到獨特的味道體驗。它的重量為250克，足夠讓您沖泡出多杯美味的巧克力咖啡。這款咖啡粉採用優質的咖啡豆和頂級的巧克力原料，製成了一款口感豐富、香氣撲鼻的咖啡粉。您可以根據自己的喜好調整濃度和甜度，來沖泡出屬於自己的完美口味。不論是在家中還是辦公室，這款瑞士巧克力咖啡粉都能夠讓您享受到咖啡和巧克力的絕妙結合。讓我們一起品味這份獨特的享受吧！',
  350,
  25,
  6,
  '上架中',
  CURRENT_TIMESTAMP,
  CURRENT_TIMESTAMP,
  1,
  1
),
(
  1,
  '手沖壺套裝',
  NULL,
  '材質：玻璃',
  '這款手沖壺套裝包括了一個手沖壺和一個濾紙架，方便您沖泡出美味的手沖咖啡。手沖壺採用高品質的玻璃材質，清澈透明，讓您可以清楚地看到沖泡的過程。它的容量適中，可以滿足您的個人或小型聚會的需求。濾紙架則是專為手沖壺設計的，可以固定濾紙，讓您的沖泡過程更加便捷。這款手沖壺套裝不僅外觀優雅，而且操作簡單，適合咖啡愛好者和手沖咖啡初學者使用。讓我們一起享受手沖咖啡的細膩和美味吧！',
  250,
  35,
  9,
  '上架中',
  CURRENT_TIMESTAMP,
  CURRENT_TIMESTAMP,
  1,
  1
),
(
  3,
  '巴西特級咖啡粉',
  NULL,
  '重量：500g',
  '這款巴西特級咖啡粉帶給您濃郁的口感和香氣，是咖啡愛好者必備的選擇。它的重量為500克，足夠讓您品嚐到多次美味的咖啡。這款咖啡粉選用了巴西的頂級咖啡豆，經過精心烘焙而成，保證了咖啡的品質和口感。它的口味濃郁而平衡，帶有些許的巧克力和堅果香氣，讓您沉浸在咖啡的世界中。不論您是喜歡濃縮咖啡還是拿鐵咖啡，這款巴西特級咖啡粉都能夠滿足您的需求。讓我們一起享受咖啡帶來的愉悅吧！',
  450,
  40,
  10,
  '上架中',
  CURRENT_TIMESTAMP,
  CURRENT_TIMESTAMP,
  1,
  1
),
(
  1,
  '義式咖啡機',
  NULL,
  '型號：ABC-456',
  '這款義式咖啡機具有卓越的性能和優雅的外觀，是咖啡愛好者的最愛。它的型號為ABC-456，是一款高品質的咖啡機。這款咖啡機配備了先進的壓力萃取系統，可以製作出濃郁細膩的義式咖啡。它還具有多個調節選項，可以讓您根據個人口味調整咖啡的濃度和溫度。無論是喜歡濃縮咖啡還是拿鐵咖啡，這款義式咖啡機都能夠滿足您的需求。讓我們一起來沖泡出完美的義式咖啡吧！',
  800,
  10,
  3,
  '上架中',
  CURRENT_TIMESTAMP,
  CURRENT_TIMESTAMP,
 1,
  1
),
(
  2,
  '阿拉比卡咖啡豆',
  NULL,
  '重量：1kg',
  '這款阿拉比卡咖啡豆是咖啡愛好者的首選。它的重量為1千克，足夠讓您品嚐到多次美味的咖啡。這些咖啡豆採用了優質的阿拉比卡種植和精細烘焙工藝，保證了咖啡的品質和口感。它的口味柔和而芳香，帶有些許的果香和巧克力的味道，讓您愛不釋手。您可以根據自己的喜好選擇研磨度和沖泡方式，來沖泡出屬於自己的完美咖啡。無論是在家中還是咖啡館，這款阿拉比卡咖啡豆都能夠帶給您絕佳的咖啡體驗。讓我們一起享受這份獨特的享受吧！',
  600,
  20,
  5,
  '上架中',
  CURRENT_TIMESTAMP,
  CURRENT_TIMESTAMP,
  1,
  1
),
(
  3,
  '經典濾掛咖啡包',
  NULL,
  '口味：香濃',
  '這款經典濾掛咖啡包方便快捷，是懶人沖泡咖啡的好選擇。它採用高品質的咖啡豆製成，保證了咖啡的風味和品質。每個濾掛咖啡包都已經事先包好，只需將其掛在杯邊，倒入熱水，幾分鐘後就能夠品嚐到香濃美味的咖啡了。這款咖啡包口味香濃，適合喜歡濃郁口感的人。它的包裝簡單輕便，非常適合旅行和戶外活動使用。讓我們一起享受方便又美味的濾掛咖啡吧！',
  250,
  50,
  15,
  '已下架',
  CURRENT_TIMESTAMP,
  CURRENT_TIMESTAMP,
  1,
  1
),
(
  2,
  '摩卡咖啡豆',
  NULL,
  '重量：500g',
  '這款摩卡咖啡豆帶給您獨特的風味和口感，是咖啡愛好者必備的選擇。它的重量為500克，足夠讓您品嚐到多次美味的咖啡。這款咖啡豆採用了優質的摩卡種植和精細烘焙工藝，保證了咖啡的品質和口感。它的口味濃郁而獨特，帶有些許的巧克力和堅果香氣，讓您沉浸在咖啡的世界中。不論您是喜歡濃縮咖啡還是拿鐵咖啡，這款摩卡咖啡豆都能夠滿足您的需求。讓我們一起來品味這份獨特的享受吧！',
  350,
  30,
  8,
  '已下架',
  CURRENT_TIMESTAMP,
  CURRENT_TIMESTAMP,
  1,
  1
);


INSERT INTO `ORDER_INFO`
(
`member_id`, 
`order_status`, -- (未成立、已成立)
`order_created_date`,
`order_shipping_method`, -- (宅配、郵寄)
`order_payment_amount`,
`order_payment_duedate`,
`order_payment_method`, -- (信用卡、貨到付款、轉帳)
`order_shipping_status`, -- (待出貨、已出貨、已完成)
`order_payment_status`, -- (已付款、待付款)
`order_shipping_address`,
`order_contact_number`,
`order_shipping_name`)
VALUES
(
1, 
"已成立",
"2023-05-27 09:51:42",
"郵寄",
2350,
CURRENT_TIMESTAMP,
"轉帳",
"已出貨",
"已付款",
"彰化市",
"098800000",
"JOHN"
),
(
1, 
"已成立",
"2023-05-28 11:32:17",
"宅配",
6050,
CURRENT_TIMESTAMP,
"貨到付款",
"待出貨",
"待付款",
"新北市",
"091200000",
"MARY"
),
(
1, 
"已成立",
"2023-05-28 23:52:29",
"郵寄",
3100,
CURRENT_TIMESTAMP,
"轉帳",
"已出貨",
"已付款",
"500彰化市",
"098800000",
"JOHN"
),
(
1, 
"已成立",
"2023-05-29 09:51:42",
"宅配",
6450,
CURRENT_TIMESTAMP,
"信用卡",
"待出貨",
"已付款",
"台南市",
"091500000",
"SOPHIA"
),
(
1, 
"已成立",
"2023-05-30 16:23:47",
"宅配",
5900,
CURRENT_TIMESTAMP,
"信用卡",
"已完成",
"已付款",
"桃園市",
"092200000",
"PETER"
),
(
1, 
"已成立",
"2023-05-31 01:22:30",
"宅配",
1100,
CURRENT_TIMESTAMP,
"貨到付款",
"待出貨",
"待付款",
"新北市",
"091200000",
"MARY"
),
(
1, 
"已成立",
"2023-06-01 09:51:42",
"宅配",
3900,
CURRENT_TIMESTAMP,
"貨到付款",
"待出貨",
"待付款",
"新北市",
"091200000",
"MARY"
),
(
1, 
"已成立",
"2023-06-03 20:44:02",
"郵寄",
2350,
CURRENT_TIMESTAMP,
"轉帳",
"已出貨",
"已付款",
"彰化市",
"098800000",
"JOHN"
),
(
1, 
"已成立",
"2023-06-05 09:21:12",
"宅配",
5900,
CURRENT_TIMESTAMP,
"信用卡",
"待出貨",
"已付款",
"台南市",
"091500000",
"SOPHIA"
);


INSERT INTO `ORDER_DETAIL`
(
`order_id`,
`product_id`,
`order_detail_quantity`,
`order_product_price`)
VALUES
(
1,
1,
3,
400
),
(
1,
15,
5,
250
),
(
2,
4,
5,
500
),
(
2,
8,
1,
600
),
(
3,
6,
13,
450
),
(
3,
9,
4,
200
),
(
4,
1,
6,
400
),
(
4,
10,
10,
350
),
(
5,
2,
2,
600
),
(
5,
14,
2,
600
),
(
6,
9,
3,
200
),
(
6,
11,
2,
250
),
(
7,
16,
9,
350
),
(
7,
15,
3,
250
),
(
8,
4,
2,
500
),
(
8,
12,
3,
450
),
(
9,
10,
11,
250
),
(
9,
12,
7,
450
);

INSERT INTO `class_and_event` (`class_type`, `member_id`, `class_name`, `class_description`, `class_date`, `class_period_of_time`, `class_start_time`, `class_end_time`, `class_location`, `class_member_count`, `class_member_max`, `class_member_min`, `class_start_date`, `class_end_date`, `class_image`, `class_status`, `class_created_time`, `class_last_modified_time`, `class_last_modified_member`)
VALUES 
  ('課程', 1, '咖啡入門課程', '學習咖啡的基礎知識與煮法', '2023-07-05', '上午', '09:00', '11:00', '台北市中正區忠孝西路1號', 0, 20, 5, '2023-06-25', '2023-07-03', NULL, '報名中', NOW(), NOW(), 'admin'),
  ('課程', 1, '咖啡品鑑課程', '品嚐各種不同風味的咖啡豆', '2023-07-10', '下午', '14:00', '16:00', '新北市板橋區文化路2號', 0, 15, 3, '2023-06-30', '2023-07-08', NULL, '報名中', NOW(), NOW(), 'admin'),
  ('課程', 1, '手沖咖啡技巧班', '教授手沖咖啡的製作技巧與方法', '2023-07-15', '上午', '10:00', '12:00', '台中市西區民權路3號', 0, 10, 2, '2023-07-05', '2023-07-13', NULL, '報名中', NOW(), NOW(), 'admin'),
  ('活動', 1, '咖啡展覽活動', '欣賞咖啡藝術作品與交流咖啡文化', '2023-07-20', '下午', '15:00', '17:00', '高雄市前鎮區成功路100號', 0, 30, 10, '2023-07-10', '2023-07-18', NULL, '報名中', NOW(), NOW(), 'admin');

INSERT INTO `member_class_signed` (`member_id`, `class_id`, `member_class_signed_status`, `member_class_signed_created_time`, `member_class_signed_last_modified_member`, `member_class_signed_last_modified_time`)
VALUES 
  ('1', 1, '已報名', NOW(), 'admin', NOW()),
  ('1', 2, '已報名', NOW(), 'admin', NOW()),
  ('1', 3, '已報名', NOW(), 'admin', NOW()),
  ('1', 4, '已報名', NOW(), 'admin', NOW());

INSERT INTO store
(store_tax,
member_id,
store_name,
store_info,
store_address,
store_time,
store_phone,
store_website)
VALUES
('15493154',
1,
'Homeys cafe',
'有質感的咖啡廳',
'台北市大安區敦化南路一段236巷36號2樓',
'12:00~23:00',
'27111519',
'https://www.facebook.com/homeyscafe'),
('23493002',
1,
'九日咖啡',
'很棒的咖啡廳',
'台北市中山區復興北路88號',
'7:30~17:30',
'27523310',
'https://coffee-store-1846.business.site'),
('15482003',
1,
'公園咖啡',
'裝潢簡樸的咖啡廳，供應手沖單一產區咖啡、茶飲、甜點和輕食。',
'台北市中山區遼寧街146號',
'11:00~19:00',
'27198880',
'https://www.facebook.com/profile.php?id=100064500291897'),
('55263303',
1,
'夏洛特咖啡',
'很棒的咖啡廳，推薦',
'台北市大安區復興南路一段107巷5弄5號',
'12:00~23:00',
'27117470',
'https://charlottecafe.easy.co'),
('77881234',
1,
'Dreamers Coffee',
'巧克力奶昔好喝',
'台北市大安區復興南路一段135巷7號',
'07:00~21:30',
'27528229',
'https://www.facebook.com/DreamersCoffeeRoasters'),
('95130025',
1,
'Coffee Space 咖啡空間',
'很有特色的咖啡廳喔',
'台北市大安區復興南路一段122巷6-3號',
'12:00~20:00',
'23278136',
'https://coffeespacetw.business.site'),
('00985612',
1,
'Coffee Underwater',
'手沖很有味道的咖啡廳，很推薦大家來',
'台北市中山區龍江路106巷4號',
'09:30~19:00',
'25066520',
'https://www.coffeeunderwater.com'),
('08521549',
1,
'嗜黑精品咖啡專門店',
'很好喝的咖啡店。',
'台北市松山區八德路二段352號',
'08:00~16:00',
'87719990',
'https://www.facebook.com/swayblackcoffee'),
('51593549',
1,
'小屋咖啡',
'很棒的咖啡廳。',
'台北市松山區八德路二段437巷10弄20號',
'12:00~18:00',
'27719980',
'https://www.facebook.com/profile.php?id=100057134483041'),
('15932100',
2,
'Fika Cafe',
'非常舒適的咖啡店',
'台北市中山區伊通街33號',
'08:00~19:00',
'25070633',
'https://www.fikafikacafe.com'),
('15162100',
2,
'弄宅咖啡',
'入內要脫鞋喔',
'台北市中山區松江路150巷18-1號',
'13:00~21:00',
'25117003',
'https://www.facebook.com/alleyhousecoffee'),
('24162100',
2,
'倆男咖啡室',
'非常有特色的咖啡廳',
'台北市中山區松江路204巷33號1F',
'11:00~21:00',
'25630723',
'https://www.facebook.com/Twomandaily')