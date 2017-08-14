DROP DATABASE IF EXISTS BlbApp;
CREATE DATABASE BlbApp;

\c BlbApp postgres;

DROP TABLE IF EXISTS "bond" CASCADE;
CREATE TABLE "bond" (
  "bondid" BIGSERIAL PRIMARY KEY,
  "cusip" VARCHAR UNIQUE,
  "issuer" VARCHAR,
  "issuedate" VARCHAR,
  "type" VARCHAR,
  "interestrate" DOUBLE PRECISION,
  "maturitydate" VARCHAR,
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
	"userid" BIGSERIAL,
  	FOREIGN KEY ("userid") REFERENCES useraccount("userid")
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
  "ordertimestamp" TIMESTAMPTZ,
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

DROP TABLE IF EXISTS "address" CASCADE;
CREATE TABLE "address" (
  "id" BIGSERIAL PRIMARY KEY,
  "streetaddress" VARCHAR,
  "city" VARCHAR,
  "state" VARCHAR,
  "postalcode" VARCHAR,
  "userid" BIGSERIAL,
  FOREIGN KEY ("userid") REFERENCES useraccount("userid")
);
