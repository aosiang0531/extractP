-- 新增產品分類
INSERT INTO PRODUCT_CATEGORY (category_id, category_name)VALUES(1,"咖啡器具");
INSERT INTO PRODUCT_CATEGORY (category_id, category_name)VALUES(2,"咖啡豆");
INSERT INTO PRODUCT_CATEGORY (category_id, category_name)VALUES(3,"濾掛咖啡");

-- 新增產品
INSERT INTO PRODUCT (
  category_id, 
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
  91, 
  91
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
  91, 
  91
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
  91, 
  92
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
  92, 
  92
);

INSERT INTO `ORDER_INFO`
(
`member_id`, -- (會員id，未關聯)
`order_status`, -- (未成立、已成立)
`order_created_date`,
`order_shipping_method`, -- (宅配、郵寄、超商)
`order_payment_amount`,
`order_payment_duedate`,
`order_payment_method`, -- (信用卡、貨到付款、轉帳)
`order_shipping_status`, -- (待出貨、已出貨、運送中、已完成)
`order_payment_status`, -- (已付款、待付款)
`order_shipping_address`,
`order_contact_number`,
`order_shipping_name`)
VALUES
(
1,
"未成立",
CURRENT_TIMESTAMP,
null,
null,
CURRENT_TIMESTAMP,
null,
null,
null,
null,
null,
null),
(
2,
"已成立",
CURRENT_TIMESTAMP,
"超商",
1200,
CURRENT_TIMESTAMP,
"貨到付款",
"待出貨",
"待付款",
"220新北市",
"091200000",
"MARY"
),
(
1,
"已成立",
CURRENT_TIMESTAMP,
"郵寄",
2500,
CURRENT_TIMESTAMP,
"轉帳",
"已出貨",
"已付款",
"500彰化市",
"098800000",
"JOHN"
),
(
3,
"未成立",
CURRENT_TIMESTAMP,
"宅配",
3500,
CURRENT_TIMESTAMP,
"信用卡",
"待出貨",
"待付款",
"750台南市",
"091500000",
"SOPHIA"
),
(
3,
"已成立",
CURRENT_TIMESTAMP,
"宅配",
1500,
CURRENT_TIMESTAMP,
"信用卡",
"已完成",
"已付款",
"302桃園市",
"092200000",
"PETER"
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
2,
6,
600),
(
1,
3,
7,
350
);



