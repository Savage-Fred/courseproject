CREATE DATABASE grocery_store;

CREATE TABLE store (
	store_id 		SERIAL PRIMARY KEY NOT NULL,
	store_name 		VARCHAR(30),
	store_address	VARCHAR(60)
);

CREATE TABLE users (
	user_id 	SERIAL PRIMARY KEY NOT NULL,
	username 	VARCHAR(20),
	fullname	VARCHAR(45),
	start_date 	DATE, 
	password 	VARCHAR(30),
	is_manager 	BOOLEAN,
	user_pic_path TEXT,
	store_id 	INT REFERENCES store(store_id)
);

CREATE TABLE managers (
	manager_id 	SERIAL PRIMARY KEY NOT NULL,
	user_id 	INT REFERENCES users(user_id)
);

CREATE TABLE cashiers (
	cashier_id 	SERIAL PRIMARY KEY NOT NULL, 
	user_id 	INT REFERENCES users(user_id),
	manager 	INT REFERENCES managers(manager_id)
);

CREATE TABLE products (
	product_id		SERIAL  	PRIMARY KEY NOT NULL,
	product_name 	TEXT 		NOT NULL,
	description		TEXT,
	expiration_date	DATE,
	quantity 		INT 			CHECK(quantity > 0),
	unit_price 		DECIMAL(13,2),	
	tax_rate		NUMERIC(5,5) 	DEFAULT 0.08000 					-- a number between 0.00000 and 0.99999 corresponding to a tax rate of 0 to 99.999%
);

CREATE TABLE customers (
	cust_id 	SERIAL PRIMARY KEY NOT NULL,
	cust_name 	VARCHAR(45),
	lotalty 	BOOLEAN
);

CREATE TABLE orders (
	order_id 			SERIAL 	PRIMARY KEY	NOT NULL,
	order_date			DATE,
	order_tax			DECIMAL(13,2),
	order_time			TIME(2),
	sub_total 			DECIMAL(13,2),
	total_price			DECIMAL(13,2),
	customer_id 		INT REFERENCES customers(cust_id)
);

-- table used for the products bought in an order
CREATE TABLE products_in_order (
	pio_id		SERIAL PRIMARY KEY NOT NULL,
	order_id 	INT REFERENCES orders(order_id),
	product_id	INT REFERENCES products(product_id),
	quantity 	INT CHECK(quantity > 0)
);

CREATE TABLE business_reports (
	report_id 	SERIAL PRIMARY KEY NOT NULL,
	report_date DATE NOT NULL,
	revenue 	DECIMAL(13,2),
	profit 		DECIMAL(13,2) CHECK (profit < revenue),
	store_id 	INT REFERENCES store(store_id)
);

CREATE TABLE orders_in_business_report (
	obp_id 		SERIAL PRIMARY KEY NOT NULL,
	order_id 	INT REFERENCES orders(order_id),
	report_id 	INT REFERENCES business_reports(report_id)
);



-- SAMPLE DATA 

INSERT INTO store (store_name, store_address) VALUES
	('Big Bob\'s Groceries', '1000 Magnolia, Auburn, AL');

INSERT INTO products (product_name, description, expiration_date, quantity, unit_price, tax_rate) VALUES
	('Apple', 	'Red Delicious', 		'2017-10-13', 50, 1.75, 	0.06000),
	('Banana', 	'Contains 10 Bananas', 	'2017-10-15', 30, 2.00, 	0.06000),
	('Eggs', 	'One Dozen Eggs', 		'2017-10-17', 40, 2.75, 	0.08000),
	('Milk', 	'Whole Milk', 			'2017-10-05', 70, 2.50, 	0.08000),
	('Bread', 	'Whole Grain', 			'2017-06-10', 45, 3.00, 	0.06000),
	('Cheese', 	'Shredded Cheddar', 	'2017-11-27', 60, 4.00, 	0.08000),
	('Steak', 	'Two Pack T-Bones', 	'2017-10-04', 35, 10.00,	0.05000);

INSERT INTO customers (cust_name, lotalty) VALUES 
	('Tom Woods', 		FALSE),		
	('Nathan Ricards', 	TRUE),
	('Henry White', 	TRUE),
	('Alisha Davidson', TRUE),
	('Leah Watson', 	FALSE),
	('Liana Williamson',TRUE),
	('Camila Morales', 	FALSE),
	('Jaime Taylor', 	TRUE),
	('Jordan Lucas', 	FALSE),
	('Taylor Chase', 	TRUE);

INSERT INTO orders (order_date, order_time, total_price, customer_id, sub_total, order_tax) VALUES
	('2017-9-20', '08:00:00', 	13.25, 1, 12.00, 1.25),
	('2017-9-20', '10:00:00', 	31.75, 2, 28.75, 3.00),
	('2017-9-21', '15:00:00', 	14.50, 3, 12.00, 2.50),
	('2017-9-22', '11:00:00', 	21.50, 4, 19.00, 2.50),
	('2017-9-23', '14:30:00', 	21.00, 5, 19.00, 2.00),
	('2017-8-20', '08:00:00', 	16.25, 6, 15.00, 1.25),
	('2017-5-02', '10:00:00', 	38.75, 7, 35.00, 3.75),
	('2017-3-11', '15:00:00', 	51.50, 3, 45.00, 6.50),
	('2017-2-22', '11:00:00', 	12.50, 1, 10.25, 2.25),
	('2017-1-12', '14:30:00', 	01.00, 2, 0.92, 0.08);

INSERT INTO products_in_order (order_id, product_id, quantity) VALUES
	(1, 1, 3),
	(1, 3, 2),
	(1, 4, 1),
	(2, 3, 1),
	(2, 4, 2),
	(2, 6, 1),
	(2, 7, 2),
	(3, 2, 2),
	(3, 4, 1),
	(3, 6, 2),
	(4, 1, 2),
	(4, 4, 2),
	(4, 5, 1),
	(4, 7, 1),
	(5, 2, 1),
	(5, 4, 2),
	(5, 6, 1),
	(6, 1, 3),
	(6, 2, 1),
	(6, 3, 3),
	(6, 4, 1),
	(6, 5, 3),
	(7, 1, 1),
	(7, 1, 3),
	(7, 2, 1),
	(7, 3, 3),
	(7, 4, 1),
	(8, 5, 2),
	(8, 2, 3),
	(8, 3, 4),
	(8, 4, 1),
	(8, 5, 3),
	(9, 1, 2),
	(9, 2, 3),
	(9, 3, 1),
	(9, 4, 5),
	(9, 5, 3),
	(10, 1, 2),
	(10, 2, 2),
	(10, 3, 2),
	(10, 4, 2),
	(10, 5, 2);	

INSERT INTO users (username, fullname, start_date, password, is_manager, user_pic_path, store_id) VALUES
	('aaa002',	'Albert Alfredo', 	'2016-1-01',	'password1', 		TRUE, 	'userpics/aaa002.png', 1),
	('abc001',	'Annie Carter', 	'2017-03-22',	'passwordisTaco', 	FALSE, 	'userpics/abc001.jpg', 1),
	('mjj023', 	'Michael Jordan', 	'2013-07-13', 	'thegoat',			TRUE,	'userpics/mjj023.png', 1),
	('vej034', 	'Bo Jacson', 		'2015-8-26', 	'BoOverTheTop', 	FALSE,	'userpics/vej034.jpg', 1),
	('cwb034', 	'Charles Barkley',	'2016-04-13',	'RoundMound',		FALSE, 	'userpics/cwb034.jpg', 1),
	('tdc057', 	'Tim Cook', 		'2017-01-20',	'plaintextpw?', 	TRUE, 	'userpics/tdc057.png', 1);

INSERT INTO managers(user_id) VALUES
	(1),(3),(6);

INSERT INTO cashiers (user_id, manager) VALUES 
	(2, 1),
	(4, 2),
	(5, 6);

INSERT INTO business_reports (report_date, revenue, profit, store_id) VALUES
	('2017-6-20',	1000, 500, 	1),
	('2017-7-20',	5000, 3000, 1),
	('2017-8-21',	2431, 1034, 1),
	('2017-9-22',	1341, 675, 	1),
	('2017-10-22',	8914, 7513, 1);

INSERT INTO orders_in_business_report (report_id, order_id) VALUES
	(1,1),
	(1,2),
	(1,3),
	(1,4),
	(1,5),
	(2,6),
	(2,7),
	(2,8),
	(2,9),
	(2,10),
	(3,2),
	(3,4),
	(3,6),
	(3,8),
	(3,1),
	(4,1),
	(4,2),
	(4,3),
	(4,4),
	(4,5),
	(5,6),
	(5,7),
	(5,8),
	(5,9),
	(5,10);









