--- address
insert into address(id, street, number) values (100001, 'Heidelberglaan' , 15);
insert into address(id, street, number) values (100002, 'Bolognalaan', 101);
--- customer
insert into customers(id, name, picture, addressid) values (100001, 'Gideon de Bruijn', 'https://i.imgur.com/9CjofNK.png', 100001);
insert into customers(id, name, picture, addressid) values (100002, 'Rens Dollee', 'https://i.imgur.com/AVRXRQ1.png', 100002);
--- account
insert into accounts(id, opendate, isactive, addressid, customerid) values (100001, current_date, 1, 100001, 100001);
insert into accounts(id, opendate, isactive, addressid, customerid) values (100002, current_date, 1, 100002, 100002);
--- categories
insert into categories(id, name, description, picture) values (100001, 'PCs', 'computer', 'https://i.imgur.com/yOWCPdq.jpg');
insert into categories(id, name, description, picture) values (100002, 'Laptops', 'carriable computer', 'https://i.imgur.com/MSXA26w.jpg');
insert into categories(id, name, description, picture) values (100003, 'Bags', 'laptop bags', 'https://i.imgur.com/c3RJzkH.jpg');
insert into categories(id, name, description, picture) values (100004, 'Chargers', 'laptop chargers', 'https://i.imgur.com/4CqYRwB.png');
insert into categories(id, name, description, picture) values (100005, 'Mice', 'Mouse for computer', 'https://i.imgur.com/zKEpfs7.jpg');
insert into categories(id, name, description) values (100006, 'New', 'newly added products');
--- products
insert into products(id, name, price, description, picture) values (100001, 'fortnite gaming pc', 1500, 'gaming pc for maximale fortnite performance', 'https://i.imgur.com/yOWCPdq.jpg');
insert into products(id,name, price, description, picture) values (100002, 'msi gaming pc', 1200, 'high performance gaming from MSI', 'https://i.imgur.com/9ZTCOQ7.jpg');
insert into products(id, name, price, description, picture) values (100003, 'gaming laptop', 1200, 'laptop voor gaming', 'https://i.imgur.com/Z9cudZS.jpg');
insert into products(id, name, price, description, picture) values (100004, 'old laptop', 50, 'ouderwetse laptop', 'https://i.imgur.com/cxahDY6.jpg');
insert into products(id, name, price, description, picture) values (100005, 'default laptop bag', 10, 'standard laptop bag for all your needs', 'https://i.imgur.com/z7GYPmT.jpg');
insert into products(id, name, price, description, picture) values (100006, 'default laptop charger', 15, 'used laptop charger, still works well','https://i.imgur.com/p46U0rv.jpg');
insert into products(id, name, price, description, picture) values (100007, 'gaming mouse', 40, 'gaming mouse for high performance', 'https://i.imgur.com/PHyqE8W.jpg');
--- category_product
insert into category_product(categoryid, productid) values (100001,100001);
insert into category_product(categoryid, productid) values (100001,100002);
insert into category_product(categoryid, productid) values (100002,100003);
insert into category_product(categoryid, productid) values (100002,100004);
insert into category_product(categoryid, productid) values (100003,100005);
insert into category_product(categoryid, productid) values (100004,100006);
insert into category_product(categoryid, productid) values (100005,100007);
-- users
insert into users values ('user123', 'geheim', 'USER', 100001);
insert into users values ('admin', 'admin', 'ADMIN', null);
-- orders
insert into orders(id, addressid, accountid) values (100001, 100001, 100001);
-- orderlines
insert into orderlines(id, quantity, price, orderid, productid) values (100001, 1, 20.00, 100001, 100001);