DROP DATABASE IF EXISTS stockxsteals;
CREATE DATABASE stockxsteals;

USE stockxsteals;

CREATE TABLE Customer (
  discord VARCHAR(5) NOT NULL PRIMARY KEY,
  customerAction VARCHAR(4) NOT NULL,
  size DOUBLE,
  sizeType VARCHAR(4)
);