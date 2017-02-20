-- Add User 
DROP USER homework_4 CASCADE; 
CREATE USER homework_4 IDENTIFIED BY kalada;
GRANT CREATE SESSION to homework_4;
GRANT CREATE TABLE to homework_4;
GRANT UNLIMITED TABLESPACE to homework_4;
GRANT SELECT ANY TABLE to homework_4;
GRANT UPDATE ANY TABLE to homework_4;
GRANT INSERT ANY TABLE to homework_4;
GRANT DROP ANY TABLE to homework_4;
GRANT CREATE SEQUENCE TO homework_4;
GRANT CREATE VIEW TO homework_4;
GRANT CREATE TRIGGER TO homework_4;

connect homework_4/kalada

set sqlblanklines on

-- Create Users table
CREATE TABLE sdev_users (
  user_id INTEGER PRIMARY KEY,
  email  VARCHAR(75) NOT NULL UNIQUE,
  firstname VARCHAR(50) NOT NULL,
  lastname VARCHAR(75) NOT NULL,
  city VARCHAR(75),
  State CHAR(2),
  zip VARCHAR(10) 
);

-- Roles table
CREATE TABLE roles (
  role_id INTEGER PRIMARY KEY,
  role varchar(20) NOT NULL UNIQUE
);

-- user-info
CREATE TABLE user_info (
  user_id INTEGER Primary Key, 
  password  VARCHAR(40)  NOT NULL,
  CONSTRAINT fk_wu2 Foreign Key (user_id) 
  references  sdev_users(user_id) on delete cascade   
);


-- User roles
CREATE TABLE user_roles (
  user_id INTEGER NOT NULL,
  role_id INTEGER NOT NULL,
  Constraint PKUR primary key (user_id, role_id),
  Constraint fk_ur1 Foreign Key (user_id) references  
   sdev_users(user_id) on delete cascade,   
  Constraint fk_ur2 Foreign Key (role_id) references  
   roles(role_id) on delete cascade    
);

-- Account data
CREATE TABLE CustomerAccount (
  account_id INTEGER Primary Key,
  user_id INTEGER NOT NULL references sdev_users (user_id),
  Cardholdername VARCHAR(75) NOT NULL,
  CardType VARCHAR(20) NOT NULL,
  ServiceCode VARCHAR(20) NOT NULL,
  CardNumber VARCHAR(30) NOT NULL,
  CAV_CCV2 INTEGER NOT NULL,  
  expiredate date NOT NULL,
  FullTrackData varchar (75) Not NULL,
  PIN varchar(10) NOT Null
);

-- Insert records
insert into sdev_users (user_id, email, firstname, lastname,
city, state, zip)
values (1,'james.robertson@umuc.edu','Jim', 'Robertson','Adelphi',
'MD','20706');

-- Create a Fake Admin account for testing
insert into sdev_users (user_id, email, firstname, lastname,
city, state, zip)
values (2,'test.admin@umuc.edu','Test', 'Admin','Adelphi',
'MD','20706');

-- Create a Fake Customer account for testing
insert into sdev_users (user_id, email, firstname, lastname,
city, state, zip)
values (3,'test.customer@umuc.edu','Test', 'Custtomer','Adelphi',
'MD','20706');

--Insert user_info
insert into user_info (user_id, password) 
values (1,'mypassword');

insert into user_info (user_id, password) 
values (2,'adminpasstest');

insert into user_info (user_id, password) 
values (3,'customerpasstest');


-- Insert roles
insert into roles (role_id, role)
values (1,'Customer');

insert into roles (role_id, role)
values (2,'Admin');


-- Inseer user_roles
insert into user_roles (user_id, role_id)
values (1,1);

insert into user_roles (user_id, role_id)
values (1,2);

insert into user_roles (user_id, role_id)
values (2,2);

insert into user_roles (user_id, role_id)
values (3,1);




-- Insert CustomerAccount
insert into CustomerAccount (account_id, user_id,
CardType, ServiceCode, CardNumber, CAV_CCV2, Cardholdername, expiredate,
FullTrackData,PIN) values (1,1,'MasterCard','27aD','1111111111111',321,'James Robertson','02/23/2016','3323344ASDfc23442','3Ds2q');

insert into CustomerAccount (account_id, user_id,
CardType, ServiceCode, CardNumber, CAV_CCV2, Cardholdername, expiredate,
FullTrackData,PIN) values (2,2,'Visa','34q4','222222222222',365,'Test Administrator','09/16/2018','9852QDFXu43678','9w21Q');

insert into CustomerAccount (account_id, user_id,
CardType, ServiceCode, CardNumber, CAV_CCV2, Cardholdername, expiredate,
FullTrackData,PIN) values (3,3,'AMEX','48w5','333333333333',439,'Test Customer','05/30/2019','65234qwpH39302','92ERS2');
