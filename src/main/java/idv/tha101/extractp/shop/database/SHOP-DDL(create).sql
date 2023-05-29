-- DROP DATABASE extractp; -- 	刪除資料庫
CREATE DATABASE IF NOT EXISTS extractp;

USE extractp;

DROP TABLE IF EXISTS PRODUCT_CATEGORY;
DROP TABLE IF EXISTS PRODUCT;
DROP TABLE IF EXISTS ORDER_INFO;
DROP TABLE IF EXISTS ORDER_DETAIL;

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

