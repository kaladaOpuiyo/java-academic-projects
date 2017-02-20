-- Drop User if necessary 
DROP USER homework_2 CASCADE; 

-- Grand User Permissions
CREATE USER homework_2 IDENTIFIED BY kalada;
GRANT CREATE SESSION to homework_2;
GRANT CREATE TABLE to homework_2;
GRANT UNLIMITED TABLESPACE to homework_2;
GRANT SELECT ANY TABLE to homework_2;
GRANT UPDATE ANY TABLE to homework_2;
GRANT INSERT ANY TABLE to homework_2;
GRANT DROP ANY TABLE to homework_2;
GRANT CREATE SEQUENCE TO homework_2;
GRANT CREATE VIEW TO homework_2;
GRANT CREATE TRIGGER TO homework_2;

-- Connect to User 
connect homework_2/kalada

set sqlblanklines on



-- Create ADDRESSES Tables  
CREATE TABLE TRANSACTION_LOG
(
    "ID"        		VARCHAR2 (10 BYTE)Not Null,
    "USERNAME" 			VARCHAR2(30 BYTE),
    "TRANSACTION_TYPE"  VARCHAR2(50 BYTE)	Not Null,
    "DATE_CREATED" 		TIMESTAMP NOT NULL,
   
    
   CONSTRAINT TRANSACTION_LOG PRIMARY KEY (ID)
  
   
);

  CREATE SEQUENCE TRANSACTIONS_LOG_ID_SEQ START WITH 1 INCREMENT BY 1 NOCACHE NOCYCLE;


