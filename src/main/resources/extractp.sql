CREATE DATABASE IF NOT EXISTS extractp;

USE extractp;

set foreign_key_checks=0;
DROP TABLE IF EXISTS sample;
DROP TABLE IF EXISTS ADMIN;
DROP TABLE IF EXISTS MEMBER;
DROP TABLE IF EXISTS article;
DROP TABLE IF EXISTS article_comment;
DROP TABLE IF EXISTS article_comment_report;
DROP TABLE IF EXISTS article_group;
DROP TABLE IF EXISTS article_tag;
DROP TABLE IF EXISTS article_hastag;
DROP TABLE IF EXISTS article_template;
DROP TABLE IF EXISTS article_thunmb;
DROP TABLE IF EXISTS artiicle_report;
DROP TABLE IF EXISTS member_article_fav;
DROP TABLE IF EXISTS PRODUCT_CATEGORY;
DROP TABLE IF EXISTS PRODUCT;
DROP TABLE IF EXISTS ORDER_INFO;
DROP TABLE IF EXISTS ORDER_DETAIL;
DROP TABLE IF EXISTS class;
DROP TABLE IF EXISTS member_class_signed;
DROP TABLE IF EXISTS event;
DROP TABLE IF EXISTS member_event_signed;
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
    member_password 			VARCHAR(30) COMMENT '會員密碼',
	member_phone	 			VARCHAR(20) COMMENT '會員電話',
    member_identity 			VARCHAR(10) COMMENT '會員身分',
    member_is_suspended 		BIT(1)		COMMENT '是否停權',
    member_image 				LONGBLOB 	COMMENT '會員照片',
    member_created_time 		DATETIME 	COMMENT '會員註冊時間',
    member_created_user 		VARCHAR(30) COMMENT '最後修改人員',
    member_last_modified_time 	DATETIME 	COMMENT '最後修改時間',
	CONSTRAINT MEMBER_PRIMARY_KEY PRIMARY KEY (member_id)
)COMMENT='會員';

CREATE TABLE `article_template` (
  `article_template_id` int NOT NULL AUTO_INCREMENT COMMENT '板塊編號',
  `article_template_name` varchar(45) DEFAULT NULL COMMENT '板塊名稱',
  `article_template_description` varchar(400) DEFAULT NULL COMMENT '板塊描述',
  `article_template_created_date` datetime DEFAULT NULL COMMENT ' 上傳時間',
  `article_template_last_modified_date` datetime DEFAULT NULL COMMENT '最後更新時間',
  `article_template_created_by` varchar(30) DEFAULT NULL COMMENT '上傳人員',
  `article_template_last_modified_by` varchar(30) DEFAULT NULL COMMENT '最後更新人員',
  PRIMARY KEY (`article_template_id`)
) COMMENT='文章版塊';

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
) COMMENT='文章分類';

CREATE TABLE `article` (
  `article_id` int NOT NULL AUTO_INCREMENT COMMENT '文章編號',
  `member_id` int DEFAULT NULL,
  `article_group_id` int DEFAULT NULL COMMENT '文章分類編號',
  `article_title` varchar(30) DEFAULT NULL COMMENT '文章標題',
  `article_content` varchar(1000) DEFAULT NULL COMMENT '文章內容',
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
) COMMENT='文章';

CREATE TABLE `article_comment` (
  `article_comment_id` int NOT NULL AUTO_INCREMENT COMMENT '留言編號',
  `member_id` int DEFAULT NULL COMMENT '留言作者',
  `article_id` int DEFAULT NULL COMMENT '文章編號',
  `article_comment_content` varchar(50) DEFAULT NULL COMMENT '留言內容',
  `article_comment_created_date` datetime DEFAULT NULL COMMENT '上傳留言時間',
  PRIMARY KEY (`article_comment_id`),
  KEY `FK_article_comment_article_id_idx` (`article_id`),
  CONSTRAINT `FK_article_comment_article_id` FOREIGN KEY (`article_id`) REFERENCES `article` (`article_id`) ON DELETE CASCADE ON UPDATE CASCADE
) COMMENT='文章留言';

CREATE TABLE `article_comment_report` (
  `article_comment_report_id` int NOT NULL AUTO_INCREMENT COMMENT '留言檢舉編號',
  `member_id` int DEFAULT NULL COMMENT '檢舉者',
  `article_comment_id` int DEFAULT NULL COMMENT '留言編號',
  `article_comment_report_content` varchar(20) DEFAULT NULL COMMENT '檢舉原因',
  `article_comment_report_created_date` datetime DEFAULT NULL COMMENT '檢舉時間',
  `article_comment_report_status` bit(1) DEFAULT NULL COMMENT '檢舉狀態',
  `article_comment_report_last_modified_date` datetime DEFAULT NULL COMMENT '最後更新時間',
  `article_comment_report_last_modified_by` varchar(30) DEFAULT NULL COMMENT '最後更新人員',
  PRIMARY KEY (`article_comment_report_id`),
  KEY `FK_article_comment_report_article_comment_id_idx` (`article_comment_id`),
  CONSTRAINT `FK_article_comment_report_article_comment_id` FOREIGN KEY (`article_comment_id`) REFERENCES `article_comment` (`article_comment_id`) ON DELETE CASCADE ON UPDATE CASCADE
) COMMENT='留言檢舉';

CREATE TABLE `article_tag` (
  `article_tag_id` int NOT NULL AUTO_INCREMENT COMMENT '標籤編號',
  `article_tag_name` varchar(20) DEFAULT NULL COMMENT '標籤名',
  `article_tag_created_date` datetime DEFAULT NULL COMMENT '上傳時間',
  `article_tag_created_by` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`article_tag_id`)
) COMMENT='文章標籤';

CREATE TABLE `article_hastag` (
  `article_id` int NOT NULL COMMENT '文章編號',
  `article_tag_id` int NOT NULL COMMENT '標籤編號',
  `article_hastag_created_date` datetime DEFAULT NULL COMMENT '上傳時間',
  `article_hastag_last_modified_date` datetime DEFAULT NULL COMMENT '最後更新時間',
  `article_hastag_created_by` varchar(30) DEFAULT NULL COMMENT '上傳人員',
  `article_hastag_last_modified_by` varchar(30) DEFAULT NULL COMMENT '最後更新人員',
  PRIMARY KEY (`article_id`,`article_tag_id`),
  KEY `FK_article_hastag_article_id_idx` (`article_id`),
  KEY `FK_article_hastag_article_tag_id_idx` (`article_tag_id`),
  CONSTRAINT `FK_article_hastag_article_id` FOREIGN KEY (`article_id`) REFERENCES `article` (`article_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FK_article_hastag_article_tag_id` FOREIGN KEY (`article_tag_id`) REFERENCES `article_tag` (`article_tag_id`) ON DELETE CASCADE ON UPDATE CASCADE
) COMMENT='文章內含標籤';

CREATE TABLE `article_thunmb` (
  `member_id` int NOT NULL COMMENT '按讚者',
  `article_id` int NOT NULL COMMENT '文章編號',
  `article_thunmb_created_date` datetime DEFAULT NULL COMMENT '按讚時間',
  PRIMARY KEY (`member_id`,`article_id`),
  KEY `FK_article_thunmb_article_id_idx` (`article_id`),
  CONSTRAINT `FK_article_thunmb_article_id` FOREIGN KEY (`article_id`) REFERENCES `article` (`article_id`) ON DELETE CASCADE ON UPDATE CASCADE
) COMMENT='文章按讚者';

CREATE TABLE `artiicle_report` (
  `artiicle_report_id` int NOT NULL AUTO_INCREMENT COMMENT '文章檢舉編號',
  `member_id` int DEFAULT NULL COMMENT '檢舉者',
  `article_id` int DEFAULT NULL COMMENT '文章編號',
  `artiicle_report_content` varchar(20) DEFAULT NULL COMMENT '檢舉原因',
  `artiicle_report_created_date` datetime DEFAULT NULL COMMENT '檢舉時間',
  `artiicle_report_status` bit(1) DEFAULT NULL COMMENT '檢舉狀態',
  `artiicle_report_last_modified_date` datetime DEFAULT NULL COMMENT '最後更新時間',
  `artiicle_report_last_modified_by` varchar(30) DEFAULT NULL COMMENT '最後更新人員',
  PRIMARY KEY (`artiicle_report_id`),
  KEY `FK_artiicle_report_article_id_idx` (`article_id`),
  CONSTRAINT `FK_artiicle_report_article_id` FOREIGN KEY (`article_id`) REFERENCES `article` (`article_id`) ON DELETE CASCADE ON UPDATE CASCADE
) COMMENT='文章檢舉';

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
	member_id INT COMMENT "會員", -- 應該要FK到會員
	order_status VARCHAR(10) NOT NULL COMMENT "訂單狀態",	
    order_created_time DATETIME COMMENT "下單時間",	
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

Create table `class`(
  class_id  INT AUTO_INCREMENT PRIMARY KEY,
  member_id INT comment '課程發起人',
  class_name VARCHAR(30) comment '課程名稱',
  class_description VARCHAR(100) comment '課程簡介',
  class_date DATE comment '上課日期',
  class_time VARCHAR(30) comment '上課時間',
  `class_period of time` VARCHAR(10) comment '時段',
  class_location VARCHAR(30) comment '上課地點',
  `class_member_count` INT comment '目前報名人數',
  class_member_max INT comment '課程人數上限',
  class_member_min INT comment '課程人數下限',
  class_start_date DATE comment '報名開始日期',
  class_end_date DATE comment '報名結束日期',
  class_image LONGBLOB comment '課程圖片',
  class_status VARCHAR(10) comment '課程狀態',
  class_created_time DATETIME comment '課程上傳時間',
  class_last_modified_time DATETIME comment '最後更新時間',
  class_last_modified_member VARCHAR(30) comment '最後更新人員'
) COMMENT "課程";

Create table `event`(
  event_id  INT AUTO_INCREMENT PRIMARY KEY,
  member_id INT comment '活動發起人',
  event_name VARCHAR(30) comment '活動名稱',
  event_description VARCHAR(100) comment '活動簡介',
  event_date DATE comment '活動日期',
  event_time VARCHAR(30) comment '活動時間',
  `event_period of time` VARCHAR(10) comment '時段',
  event_location VARCHAR(30) comment '上課地點',
  `event_member_count` INT comment '目前報名人數',
  event_member_max INT comment '活動人數上限',
  event_member_min INT comment '活動人數下限',
  event_start_date DATE comment '報名開始日期',
  event_end_date DATE comment '報名結束日期',
  event_image LONGBLOB comment '活動圖片',
  event_status VARCHAR(10) comment '活動狀態',
  event_created_time DATETIME comment '活動上傳時間',
  event_last_modified_time DATETIME comment '最後更新時間',
  event_last_modified_member VARCHAR(30) comment '最後更新人員'
) COMMENT "活動";

Create table `member_class_signed`(
`class_member_id` INT AUTO_INCREMENT PRIMARY KEY comment'已報名課程編號',
  `member_id` VARCHAR(30) comment'報名人',
  `class_id` int comment'課程編號',
  `member_class_signed_status`  VARCHAR(10) comment '課程狀態',
  `member_class_signed_created_time`  DATETIME comment '課程報名時間',
  `member_class_signed_last`  VARCHAR(30) comment'最後更新時間',
  `member_class_signed_last_modified_time` DATETIME comment'最後更新人員'
) COMMENT "已報名課程";

Create table `member_event_signed`(
`event_member_id` INT AUTO_INCREMENT PRIMARY KEY comment'已報名活動編號',
  `member_id` VARCHAR(30) comment'報名人',
  `event_id` int comment'活動編號',
  `member_event_signed_status`  VARCHAR(10) comment '活動狀態',
  `member_event_signed_created_time`  DATETIME comment '活動報名時間',
  `member_event_signed_last`  VARCHAR(30) comment'最後更新時間',
  `member_event_signed_last_modified_time` DATETIME comment'最後更新人員'
) COMMENT "已報名活動";

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
  store_tax VARCHAR(30) COMMENT '統編',
  member_id int COMMENT '推薦人',
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
  PRIMARY KEY (store_tax),
  FOREIGN KEY (member_id) REFERENCES member(member_id) 
) COMMENT "商家";

CREATE TABLE store_report (
  store_report_id INT AUTO_INCREMENT COMMENT '商家檢舉編號',
  member_id int COMMENT '檢舉者',
  store_tax VARCHAR(30) COMMENT '統編',
  store_report_content VARCHAR(20) COMMENT '檢舉原因',
  store_report_created_time DATETIME COMMENT '檢舉時間',
  store_report_status VARCHAR(5) COMMENT '檢舉狀態',
  store_report_last_modified_time DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '最後更新時間',
  store_report_last_modified_member VARCHAR(30) COMMENT '最後更新人員',
  PRIMARY KEY (store_report_id),
  FOREIGN KEY (member_id) REFERENCES member(member_id),
  FOREIGN KEY (store_tax) REFERENCES store(store_tax)
) COMMENT "商家檢舉";


INSERT INTO ADMIN (admin_email,admin_password,admin_name) VALUES ('1qaz2wsx@gmail.com','testpwd','史建仁');
INSERT INTO ADMIN (admin_email,admin_password,admin_name) VALUES ('zaq1xsw2@gmail.com','testpwd','史珍香');
INSERT INTO ADMIN (admin_email,admin_password,admin_name) VALUES ('qwe123@gmail.com','testpwd','馬陵淑');

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
) VALUES ('test111@gmail.com','王大哥','test123','0911239222','商家',0,'','2023-05-19 11:31:39','史建仁','2023-05-19 11:32:39');
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
) VALUES ('test222@gmail.com','張小張','test123','0913989999','一般會員',1,'','2023-05-19 11:35:39','史珍香','2023-05-19 11:40:39');
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
) VALUES ('test333@gmail.com','吳十五','test123','0911239222','一般會員',0,'','2023-05-20 11:31:39','馬陵淑','2023-05-20 11:32:39');

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
'Lara'
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
'Lara'
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
'Lara'
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
'Lara'
);

-- 新增文章類別
INSERT INTO `article_group`
(`article_template_id`,
`article_group_name`,
`article_group_created_date`,
`article_group_created_by`)
VALUES
(1,
'種類產地',
NOW(),
'Lara'),
(1,
'處理方式',
NOW(),
'Lara'),
(1,
'烘焙程度',
NOW(),
'Lara'),
(2,
'咖啡壺',
NOW(),
'Lara'),
(2,
'磨豆機',
NOW(),
'Lara'),
(2,
'周邊配件',
NOW(),
'Lara'),
(3,
'商品心得',
NOW(),
'Lara'),
(3,
'美食記',
NOW(),
'Lara'),
(3,
'課程分享',
NOW(),
'Lara'),
(3,
'活動分享',
NOW(),
'Lara'),
(4,
'咖啡壺',
NOW(),
'Lara'),
(4,
'磨豆機',
NOW(),
'Lara'),
(4,
'周邊配件',
NOW(),
'Lara');