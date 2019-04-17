CREATE TABLE Customers (ID SERIAL NOT NULL, name varchar(255), picture varchar(255), addressID int4 NOT NULL, PRIMARY KEY (ID));
CREATE TABLE Address (ID SERIAL NOT NULL, street varchar(255), number int4, PRIMARY KEY (ID));
CREATE TABLE Accounts (ID SERIAL NOT NULL, openDate date, isActive int4, addressID int4 NOT NULL, CustomerID int4 NOT NULL, PRIMARY KEY (ID));
CREATE TABLE Orders (ID SERIAL NOT NULL, addressID int4 NOT NULL, Accountid int4 NOT NULL, PRIMARY KEY (ID));
CREATE TABLE Orderlines (id SERIAL NOT NULL, quantity int4, price int4, orderId int4 NOT NULL, Productid int4 NOT NULL, PRIMARY KEY (id));
CREATE TABLE Products (id SERIAL NOT NULL, name varchar(255), price int4, description varchar(255), picture varchar(255), PRIMARY KEY (id));
CREATE TABLE Categories (id SERIAL NOT NULL, name varchar(255), description varchar(255), picture varchar(255), PRIMARY KEY (id));
CREATE TABLE Category_Product (categoryid int4 NOT NULL, Productid int4 NOT NULL, PRIMARY KEY (categoryid, Productid));
CREATE TABLE Advertisements (id SERIAL NOT NULL, fromDate date, untilDate date, advertisementsprice int4, advertisementText varchar(255), Productid int4 NOT NULL, PRIMARY KEY (id));
ALTER TABLE Customers ADD CONSTRAINT FKCustomer437585 FOREIGN KEY (addressID) REFERENCES address (ID);
ALTER TABLE Accounts ADD CONSTRAINT FKAccount757974 FOREIGN KEY (addressID) REFERENCES address (ID);
ALTER TABLE Accounts ADD CONSTRAINT FKAccount136579 FOREIGN KEY (CustomerID) REFERENCES Customers (ID);
ALTER TABLE Orders ADD CONSTRAINT FKorder81686 FOREIGN KEY (addressID) REFERENCES address (ID);
ALTER TABLE Orders ADD CONSTRAINT FKorder443310 FOREIGN KEY (Accountid) REFERENCES Accounts (id);
ALTER TABLE orderlines ADD CONSTRAINT FKorder590082 FOREIGN KEY (orderId) REFERENCES orders (ID);
ALTER TABLE orderlines ADD CONSTRAINT FKorder857868 FOREIGN KEY (Productid) REFERENCES Products (id);
ALTER TABLE category_Product ADD CONSTRAINT FKcategory_422784 FOREIGN KEY (categoryid) REFERENCES categories (id);
ALTER TABLE category_Product ADD CONSTRAINT FKcategory_717187 FOREIGN KEY (Productid) REFERENCES Products (id);
ALTER TABLE advertisements ADD CONSTRAINT FKadvertisement498694 FOREIGN KEY (Productid) REFERENCES Products (id);

CREATE TABLE Users ( 
	username varchar(255) NOT NULL, 
	password varchar(255) NOT NULL, 
	role varchar(255) NOT NULL,
	customer integer,
	PRIMARY KEY (username)
	);

ALTER TABLE Users ADD CONSTRAINT FKuser491232 FOREIGN KEY (customer) REFERENCES Customers (id);
