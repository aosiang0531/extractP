CREATE DATABASE IF NOT EXISTS extractp;

USE extractp;

set foreign_key_checks = 0;

DROP TABLE IF EXISTS sample;
DROP TABLE IF EXISTS ADMIN;
DROP TABLE IF EXISTS MEMBER;
DROP TABLE IF EXISTS `token`;
DROP TABLE IF EXISTS `chat_room`;
DROP TABLE IF EXISTS `chat_message`;
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

CREATE TABLE `chat_room` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `name` varchar(200) DEFAULT NULL,
  `status` int,
  `messages` BOOLEAN DEFAULT NULL,
  PRIMARY KEY (`id`) 
)COMMENT='token';

CREATE TABLE `chat_message` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `content` varchar(500) NOT NULL,
  `type` int NOT NULL,
  `is_read` BOOLEAN NOT NULL,
  `sender` varchar(30) NOT NULL,
  `chat_room_id` BIGINT,
  PRIMARY KEY (`id`),
  FOREIGN KEY (chat_room_id) REFERENCES chat_room(id) 
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