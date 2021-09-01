--------------------------------------------------------
--  File created - Wednesday-September-01-2021   
--------------------------------------------------------
--------------------------------------------------------
--  DDL for Table SNACKS
--------------------------------------------------------

  CREATE TABLE "SYSTEM"."SNACKS" 
   (	"SNO" VARCHAR2(20 BYTE), 
	"SNACKSNAME" VARCHAR2(20 BYTE), 
	"QUANTITY" VARCHAR2(20 BYTE), 
	"PRICE" VARCHAR2(20 BYTE)
   ) PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 NOCOMPRESS LOGGING
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "SYSTEM" ;
REM INSERTING into SYSTEM.SNACKS
SET DEFINE OFF;
Insert into SYSTEM.SNACKS (SNO,SNACKSNAME,QUANTITY,PRICE) values ('1','chips','2','60.0');
Insert into SYSTEM.SNACKS (SNO,SNACKSNAME,QUANTITY,PRICE) values ('2','biscuit','5','120.0');
Insert into SYSTEM.SNACKS (SNO,SNACKSNAME,QUANTITY,PRICE) values ('3','chocolate','10','45.0');
Insert into SYSTEM.SNACKS (SNO,SNACKSNAME,QUANTITY,PRICE) values ('4','Ladoo','30','546.0');
Insert into SYSTEM.SNACKS (SNO,SNACKSNAME,QUANTITY,PRICE) values ('10','chips','2','60.0');
Insert into SYSTEM.SNACKS (SNO,SNACKSNAME,QUANTITY,PRICE) values ('5','kulfi','12','340.0');
Insert into SYSTEM.SNACKS (SNO,SNACKSNAME,QUANTITY,PRICE) values ('9','kil','2','60.0');
Insert into SYSTEM.SNACKS (SNO,SNACKSNAME,QUANTITY,PRICE) values ('11','chips','2','60.0');
Insert into SYSTEM.SNACKS (SNO,SNACKSNAME,QUANTITY,PRICE) values ('11','chips','2','60.0');
Insert into SYSTEM.SNACKS (SNO,SNACKSNAME,QUANTITY,PRICE) values ('12','kkkk','2','60.0');
Insert into SYSTEM.SNACKS (SNO,SNACKSNAME,QUANTITY,PRICE) values (null,'aaaa','2','60');
Insert into SYSTEM.SNACKS (SNO,SNACKSNAME,QUANTITY,PRICE) values ('14','uuuu','2','60');
