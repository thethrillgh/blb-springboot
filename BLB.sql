--DROP DATABASE IF EXISTS BlbApp;
--CREATE DATABASE BlbApp;
--
--\c BlbApp postgres;
DROP DATABASE IF EXISTS testdb;
CREATE DATABASE testdb;

\c testdb postgres;

DROP TABLE IF EXISTS "bond" CASCADE;
CREATE TABLE "bond" (
  "bondid" BIGSERIAL PRIMARY KEY,
  "cusip" VARCHAR UNIQUE,
  "issuer" VARCHAR,
  "issuedate" DATE,
  "type" VARCHAR,
  "interestrate" DOUBLE PRECISION,
  "maturitydate" DATE,
  "quantity" INTEGER,
  "creditrating" VARCHAR,
  "callable" VARCHAR,
  "coupontype" VARCHAR,
  "bid" DOUBLE PRECISION,
  "ask" DOUBLE PRECISION,
  "yieldbid" DOUBLE PRECISION,
  "yieldask" DOUBLE PRECISION,
  "marketprice" DOUBLE PRECISION,
  "marketyield" DOUBLE PRECISION,
  "facevalue" DOUBLE PRECISION
);

DROP TABLE IF EXISTS "useraccount" CASCADE;
CREATE TABLE "useraccount"(
  "userid" BIGSERIAL PRIMARY KEY,
  "firstname" VARCHAR,
  "lastname" VARCHAR,
  "phonenum" VARCHAR,
  "acctemail" VARCHAR,
  "acctpass" VARCHAR,
  "acctssn" VARCHAR,
  "ssnlastfour" VARCHAR,
  "passsalt" VARCHAR,
  "streetaddress" VARCHAR,
  "city" VARCHAR,
  "state" VARCHAR,
  "postalcode" VARCHAR,
  "acctbalance" DOUBLE PRECISION
);

DROP TABLE IF EXISTS "bondhistory" CASCADE;
CREATE TABLE "bondhistory" (
	"id" BIGSERIAL PRIMARY KEY,
	"time" DATE,
	"bid" DOUBLE PRECISION,
	"ask" DOUBLE PRECISION,
	"yieldbid" DOUBLE PRECISION,
	"yieldask" DOUBLE PRECISION,
	"changeprice" DOUBLE PRECISION,
	"cusip" VARCHAR,
  	FOREIGN KEY ("cusip") REFERENCES bond("cusip")
);

DROP TABLE IF EXISTS "bankaccount" CASCADE;
CREATE TABLE "bankaccount" (
  "id" BIGSERIAL PRIMARY KEY,
  "acctnum" VARCHAR,
  "routingnum" VARCHAR,
  "accttype" VARCHAR,
  "userid" BIGSERIAL,
  FOREIGN KEY ("userid") REFERENCES useraccount("userid")
);

DROP TABLE IF EXISTS "bondorder" CASCADE;
CREATE TABLE "bondorder" (
  "id" BIGSERIAL PRIMARY KEY,
  "ordertimestamp" TIMESTAMP,
  "tradedate" VARCHAR NOT NULL,
  "settlementdate" VARCHAR NOT NULL,
  "principal" DOUBLE PRECISION,
  "accruedinterest" DOUBLE PRECISION,
  "total" DOUBLE PRECISION,
  "numbondspurchased" INTEGER,
  "transactiontype" VARCHAR,
  "userid" BIGSERIAL,
  "bondid" BIGSERIAL,
  FOREIGN KEY ("userid") REFERENCES useraccount("userid"),
  FOREIGN KEY ("bondid") REFERENCES bond("bondid")
);


INSERT INTO bond(cusip,issuer,issuedate,type,interestrate,maturitydate,quantity,creditrating,callable,coupontype,bid,ask,yieldbid,yieldask,marketprice,marketyield,facevalue)
VALUES
('912828X88','US TREASURY','2017-05-15','1MO',1,'2018-05-15',10000,'AA+',FALSE,'FIXED',100.778343,101.4596284,6.102501603,6.061524272,101.1189857,6.082012938,100),
('912854L23','US TREASURY','2017-08-01','6MO',1.02,'2018-08-01',10000,'AA-',FALSE,'FIXED',103.0374603,103.7512668,6.065755099,6.024022833,103.3943636,6.044888966,100),
('912876O33','US TREASURY','2017-07-18','1YR',1.23,'2018-07-18',1000,'AA+',FALSE,'FIXED',98.0253715,98.59991287,5.100720276,5.070998396,98.31264219,5.085859336,100),
('912828X72','US TREASURY','2017-06-06','2YR',1.55,'2018-06-06',500,'AA+',FALSE,'FIXED',100.5995133,101.5190776,1.540762922,1.526806622,101.0592955,1.533784772,100),
('912866JK8','US TREASURY','2017-04-30','3YR',1.56,'2022-04-30',1000,'AA',FALSE,'FIXED',101.5473057,102.307487,6.893338973,6.842118994,101.9273963,6.867728984,1000),
('912894V45','US TREASURY','2017-07-31','5YR',1.77,'2022-07-31',1000,'AA+',FALSE,'FIXED',100.2074844,100.8839301,6.112317892,6.071333656,100.5457073,6.091825774,1000),
('912810FH6','US TREASURY','2017-07-12','7YR',2.09,'2022-07-12',1000,'AA',FALSE,'FIXED',104.52056,105.0514133,8.821230958,8.776654888,104.7859867,8.798942923,1000),
('912812N28','US TREASURY','2017-07-20','10YR',4.58,'2027-07-20',1500,'AA+',FALSE,'FIXED',99.74278593,100.3369175,9.624756228,9.567764529,100.0398517,9.596260379,1000),
('912837TZ9','US TREASURY','2017-06-21','20YR',5.01,'2027-06-21',1500,'AA+',FALSE,'FIXED',98.31155121,99.54022118,10.07002725,9.945728352,98.9258862,10.0078778,1000),
('912834F90','US TREASURY','2017-06-22','30YR',5.49,'2027-06-22',1500,'AA+',FALSE,'FIXED',100.4391155,102.2220618,9.358903606,9.195666605,101.3305886,9.277285106,1000);

INSERT INTO useraccount(firstname,lastname,phonenum,acctemail,acctpass,acctssn,ssnlastfour,passsalt,streetaddress,city,state,postalcode,acctbalance)
VALUES
('Arthur','Doons','12024561111','morty@rick.com','camaro33','123456789','6789',NULL,'1 Main Street','New York','NY','10022',100000.00),
('Dirk','Sweetprince','18002453875','dirk@yahoo.com','securepassword','928648372','8372',NULL,'2 Dinkleburg Cir','Boston','MA','01002',75.09),
('Doug','Dimmadome','19982976649','dimsdale@gmail.com','TimmyTurner23','938777475','7475',NULL,'1 Dimmadome Rd','Dimsdale','ME','74837',10000000.45),
('Matt','Puck','15087749933','life@aol.com','Dwightshrute','837374938','4938',NULL,'14 Fizzy Ln','Currency','TX','19288',20),
('Ana','Vara Berra','18273648594','venezuela@bing.com','Salsadancing','111648342','8342',NULL,'2133 Jammin Dr','Hoboken','NJ','07030',47563.22),
('Dani','Cats','18004737737','dartmouth@gmail.com','dannypass','398573874','3874',NULL,'1 Webster Ave','Hanover','NH','83838',101010.4),
('Sammy','Groot','18009999999','groot@umass.com','Password1234','111223333','3333',NULL,'1 Friendship Ln','Amherst','MA','12345',0),
('Yusiff','Hamis','38484848848','something@gmail.com','password5','234534533','4533',NULL,'18 Main St','Danville','KY','28282',7.77);

INSERT INTO bankaccount(acctnum,routingnum,accttype)
VALUES
('123456789876','123456789','Savings'),
('394857209345','938398477','Checking'),
('483539845733','457542768','Checking'),
('435874773097','563701938','Checking'),
('989435739489','900745822','Checking'),
('758376245387','234508547','Checking'),
('587483937777','435202345','Savings'),
('458739274857','593048534','Savings');

--INSERT INTO bondorder(ordertimestamp,tradedate,settlementdate,principal,accruedinterest,total,numbondspurchased)
--VALUES
--('2017-04-12 4:05:06','2017-04-12','2017-04-18',100000,120.050,100120.050,100),
--('2017-06-23 15:12:34','2017-06-23','2017-06-26',5000,85.090,5085.090,50),
--('2017-07-07 9:17:56','2017-07-07','2017-07-20',1000000,2300.390,1002300.390,1000),
--('2017-05-02 11:23:08','2017-05-02','2017-05-18',10000,150.430,10150.430,100),
--('2017-04-27 21:40:30','2017-04-27','2017-05-02',500000,0.000,500000.000,500),
--('2017-06-18 17:45:23','2017-06-18','2017-06-22',100000,1020.220,101020.220,100),
--('2017-07-01 10:59:05','2017-07-01','2017-07-07',10000000,0.000,10000000.000,1000),
--('2017-08-02 5:30:43','2017-08-02','2017-08-10',50000,45.040,50045.040,50),
--('2017-07-24 13:29:40','2017-07-24','2017-07-31',100000,305.780,100305.780,1000);
