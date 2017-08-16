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
  "transactionamt" DOUBLE PRECISION,
  "accruedinterest" DOUBLE PRECISION,
  "numbondspurchased" INTEGER,
  "userid" BIGSERIAL,
  "bondid" BIGSERIAL,
  FOREIGN KEY ("userid") REFERENCES useraccount("userid"),
  FOREIGN KEY ("bondid") REFERENCES bond("bondid")
);


INSERT INTO bond(cusip,issuer,issuedate,type,interestrate,maturitydate,quantity,creditrating,callable,coupontype,bid,ask,yieldbid,yieldask,marketprice,marketyield,facevalue)
VALUES
('912828X88','US TREASURY','2017-05-15','1YR',6.15,'2018-05-15',10000,'AA+',FALSE,'FIXED',100.778343,101.4596284,6.102501603,6.061524272,101.1189857,6.082012938,100),
('912854L23','US TREASURY','2017-08-01','1YR',6.25,'2018-08-01',10000,'AA-',FALSE,'FIXED',103.0374603,103.7512668,6.065755099,6.024022833,103.3943636,6.044888966,100),
('912876O33','US TREASURY','2017-07-18','1YR',5,'2018-07-18',1000,'AA+',FALSE,'FIXED',98.0253715,98.59991287,5.100720276,5.070998396,98.31264219,5.085859336,100),
('912828X72','US TREASURY','2017-06-06','1YR',1.55,'2018-06-06',500,'AA+',FALSE,'FIXED',100.5995133,101.5190776,1.540762922,1.526806622,101.0592955,1.533784772,100),
('912866JK8','US TREASURY','2017-04-30','5YR',7,'2022-04-30',1000,'AA',FALSE,'FIXED',101.5473057,102.307487,6.893338973,6.842118994,101.9273963,6.867728984,1000),
('912894V45','US TREASURY','2017-07-31','5YR',6.125,'2022-07-31',1000,'AA+',FALSE,'FIXED',100.2074844,100.8839301,6.112317892,6.071333656,100.5457073,6.091825774,1000),
('912810FALSEH6','US TREASURY','2017-07-12','5YR',9.22,'2022-07-12',1000,'AA',FALSE,'FIXED',104.52056,105.0514133,8.821230958,8.776654888,104.7859867,8.798942923,1000),
('912846GG7','US TREASURY','2017-07-31','5YR',8.6,'2022-07-31',1000,'AA-',FALSE,'FIXED',102.4987675,103.3836407,8.390344789,8.318530806,102.9412041,8.354437798,1000),
('912812N28','US TREASURY','2017-07-20','10YR',9.6,'2027-07-20',1500,'AA+',FALSE,'FIXED',99.74278593,100.3369175,9.624756228,9.567764529,100.0398517,9.596260379,1000),
('912837TZ9','US TREASURY','2017-06-21','10YR',9.9,'2027-06-21',1500,'AA+',FALSE,'FIXED',98.31155121,99.54022118,10.07002725,9.945728352,98.9258862,10.0078778,1000),
('912834FALSE90','US TREASURY','2017-06-22','10YR',9.4,'2027-06-22',1500,'AA+',FALSE,'FIXED',100.4391155,102.2220618,9.358903606,9.195666605,101.3305886,9.277285106,1000),
('912867JJ9','US TREASURY','2017-06-20','10YR',6.65,'2027-06-20',1000,'AA+',FALSE,'FIXED',100.0655333,101.7988689,6.645644892,6.532489086,100.9322011,6.589066989,1000),
('912822LI2','US TREASURY','2017-06-26','15YR',6.535,'2032-06-26',1000,'AA+',FALSE,'FIXED',101.5376459,102.8691088,6.436036549,6.352733172,102.2033774,6.394384861,10000),
('912840KK0','US TREASURY','2017-07-31','15YR',6.25,'2032-07-31',1000,'AA+',FALSE,'FIXED',101.150497,102.3246133,6.178911808,6.108012332,101.7375551,6.14346207,10000),
('912845ZX9','US TREASURY','2017-07-18','15YR',4.15,'2032-07-18',100,'AA+',FALSE,'FIXED',99.73833339,101.206408,4.160887654,4.100530869,100.4723707,4.130709261,10000),
('912877H12','US TREASURY','2017-07-27','15YR',6.1,'2032-07-27',1000,'AA+',FALSE,'FIXED',101.7210972,103.5285652,5.996789423,5.892093635,102.6248312,5.944441529,10000),
('912882R19','US TREASURY','2017-07-31','30YR',7.93,'2047-07-31',1000,'AA+',FALSE,'FIXED',100.4903063,102.1788953,7.891308418,7.760898153,101.3346008,7.826103286,10000),
('912834ZV8','US TREASURY','2017-04-27','30YR',8.28,'2047-04-27',500,'AA+',FALSE,'FIXED',98.89792714,100.8252333,8.372268499,8.212229945,99.86158021,8.292249222,10000),
('912876B00','US TREASURY','2017-04-10','30YR',8.43,'2047-04-10',10000,'AA-',FALSE,'FIXED',100.1168588,101.5648883,8.420160306,8.300112508,100.8408735,8.360136407,10000),
('912888F88','US TREASURY','2017-07-17','30YR',3.875,'2047-07-17',10000,'AA+',FALSE,'FIXED',99.19046513,99.71578142,3.906625496,3.886044862,99.45312328,3.896335179,10000);

INSERT INTO useraccount(firstname,lastname,phonenum,acctemail,acctpass,acctssn,ssnlastfour,passsalt,streetaddress,city,state,postalcode,acctbalance)
VALUES
('Donald','Trump','12024561111','yourefired@putin.com','IvankasoHawt666','123456789','6789',NULL,'1600 Pensylvania Ave','Washington','DC','20500',0.1),
('Dirk','Sweetprince','18002453875','dirk.mcdirkface@yahoo.com','iLikeSalmon1994','928648372','8372',NULL,'2 Dinkleburg Cir','Boston','MA','01002',75.09),
('Doug','Dimmadome','19982976649','ownrdimsdaledimdom@cn.com','TimmyTurner23','938777475','7475',NULL,'1 Dimmadome Rd','Dimsdale','ME','74837',10000000.45),
('Matt','Puck','15087749933','floozies.currencies.life@aol.com','BigBoobzlol69','837374938','4938',NULL,'14 Floozy Ln','Currency','TX','19288',20),
('Ana','Vara Berra','18273648594','imfromvenezuela@bing.com','SalsaSalsaJAJA2','111648342','8342',NULL,'2133 Jaja Dr','Hoboken','NJ','07030',47563.22),
('Dani','Cats','18004737737','Iwenttodartmouth@dc.com','IwenttoanIVY333','398573874','3874',NULL,'1 Webster Ave','Hanover','NH','83838',101010.4),
('Sammy','Groot','18009999999','Iwishiwenttodartmouth@umass.com','Password1234','111223333','3333',NULL,'1 Lonely Ln','Bumblefuck','MA','12345',0),
('Yusiff','Hamis','38484848848','imfromghana@centre.com','FutbolScoccer94','234534533','4533',NULL,'18 Main St','Danville','KY','28282',7.77);
