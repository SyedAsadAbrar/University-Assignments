create database HR
use HR

create table COUNTRIES(
C_ID int not null primary key,
C_NAME varchar(40),
R_ID int 
)

create table DEPARTMENTS(
D_ID int not null primary key,
D_NAME varchar(40),
M_ID int,
L_ID int 
)

create table EMPLOYEES(
E_ID int not null primary key,
E_FIRST_NAME varchar(40) not null ,
E_LAST_NAME varchar(40) not null,
E_EMAIL varchar(40) not null ,
E_PHONE_NUMBER varchar(40),
E_HIRE_DATE date not null ,
J_ID int not null ,
E_SALARY int not null ,
E_COMMISSION_PCT float,
M_ID int,
D_ID int,
)


create table JOBS(
J_ID int not null primary key,
J_TITLE varchar(40) not null ,
J_MIN_SALARY int not null ,
J_MAX_SALARY int not null ,
)

create table JOB_HISTORY(
E_ID int not null primary key,
JH_START_DATE date not null ,
JH_END_DATE date not null ,
J_ID int not null,
D_ID int,
)

create table LOCATIONS(
L_ID int not null primary key,
L_ADDRESS varchar(40),
L_POSTAL_CODE varchar(40) ,
L_CITY varchar(40) not null ,
L_STATE_PROVINCE varchar(40),
C_ID int,
)

create table REGIONS(
R_ID int not null primary key,
R_NAME varchar(40),
)

ALTER TABLE COUNTRIES
ADD CONSTRAINT FK_COUNTRIES_REGIONS
FOREIGN KEY (R_ID) REFERENCES REGIONS(R_ID);

ALTER TABLE DEPARTMENTS
ADD CONSTRAINT FK_DEPARTMENTS_LOCATIONS
FOREIGN KEY (L_ID) REFERENCES LOCATIONS(L_ID);

ALTER TABLE EMPLOYEES
ADD CONSTRAINT FK_EMPLOYEES_JOBS
FOREIGN KEY (M_ID) REFERENCES JOBS(J_ID);

ALTER TABLE EMPLOYEES
ADD CONSTRAINT FK_EMPLOYEES_DEPARTMENTS
FOREIGN KEY (D_ID) REFERENCES DEPARTMENTS(D_ID);

ALTER TABLE EMPLOYEES
ADD CONSTRAINT FK_EMPLOYEES_JOBS2
FOREIGN KEY (J_ID) REFERENCES JOBS(J_ID);


ALTER TABLE JOB_HISTORY
ADD CONSTRAINT FK_JOB_HISTORY_JOBS
FOREIGN KEY (J_ID) REFERENCES JOBS(J_ID);

ALTER TABLE JOB_HISTORY
ADD CONSTRAINT FK_JOB_HISTORY_DEPARTMENTS
FOREIGN KEY (D_ID) REFERENCES DEPARTMENTS(D_ID);

ALTER TABLE LOCATIONS
ADD CONSTRAINT FK_LOCATIONS_COUNTRIES
FOREIGN KEY (C_ID) REFERENCES COUNTRIES(C_ID);


INSERT INTO JOBS(J_ID, J_TITLE, J_MIN_SALARY,J_MAX_SALARY)
VALUES (1, 'manager', 65000, 150000);
INSERT INTO JOBS(J_ID, J_TITLE, J_MIN_SALARY,J_MAX_SALARY)
VALUES (2, 'Peon', 10000, 25000);
INSERT INTO JOBS(J_ID, J_TITLE, J_MIN_SALARY,J_MAX_SALARY)
VALUES (3, 'Supervisor', 35000, 55000);
INSERT INTO JOBS(J_ID, J_TITLE, J_MIN_SALARY,J_MAX_SALARY)
VALUES (4, 'developer', 65000, 120000);
INSERT INTO JOBS(J_ID, J_TITLE, J_MIN_SALARY,J_MAX_SALARY)
VALUES (5, 'accountant', 55000, 89000);

INSERT INTO REGIONS(R_ID, R_NAME)
VALUES (1, 'london');
INSERT INTO REGIONS(R_ID, R_NAME)
VALUES (2, 'east midlands');
INSERT INTO REGIONS(R_ID, R_NAME)
VALUES (3, 'north west');
INSERT INTO REGIONS(R_ID, R_NAME)
VALUES (4, 'south west');
INSERT INTO REGIONS(R_ID, R_NAME)
VALUES (5, 'north east');
INSERT INTO REGIONS(R_ID, R_NAME)
VALUES (6, 'south east');
INSERT INTO REGIONS(R_ID, R_NAME)
VALUES (7, 'balochistan');
INSERT INTO REGIONS(R_ID, R_NAME)
VALUES (8, 'hazara, pakistan');
INSERT INTO REGIONS(R_ID, R_NAME)
VALUES (9, 'neeli bar');
INSERT INTO REGIONS(R_ID, R_NAME)
VALUES (10, 'waziristan');

INSERT INTO COUNTRIES(C_ID,C_NAME,R_ID)
VALUES(1,'UK',1);
INSERT INTO COUNTRIES(C_ID,C_NAME,R_ID)
VALUES(2,'UK',2);
INSERT INTO COUNTRIES(C_ID,C_NAME,R_ID)
VALUES(3,'UK',3);
INSERT INTO COUNTRIES(C_ID,C_NAME,R_ID)
VALUES(4,'UK',4);
INSERT INTO COUNTRIES(C_ID,C_NAME,R_ID)
VALUES(5,'UK',5);
INSERT INTO COUNTRIES(C_ID,C_NAME,R_ID)
VALUES(6,'UK',6);
INSERT INTO COUNTRIES(C_ID,C_NAME,R_ID)
VALUES(7,'PAKISTAN',7);
INSERT INTO COUNTRIES(C_ID,C_NAME,R_ID)
VALUES(8,'PAKISTAN',8);
INSERT INTO COUNTRIES(C_ID,C_NAME,R_ID)
VALUES(9,'PAKISTAN',9);
INSERT INTO COUNTRIES(C_ID,C_NAME,R_ID)
VALUES(10,'PAKISTAN',10);

INSERT INTO LOCATIONS(L_ID,L_ADDRESS,L_POSTAL_CODE,L_CITY,L_STATE_PROVINCE,C_ID)
VALUES(1,'street 102','1590','london','london',1)
INSERT INTO LOCATIONS(L_ID,L_ADDRESS,L_POSTAL_CODE,L_CITY,L_STATE_PROVINCE,C_ID)
VALUES(2,'street 130','3597','east midlands','east midlands',2)
INSERT INTO LOCATIONS(L_ID,L_ADDRESS,L_POSTAL_CODE,L_CITY,L_STATE_PROVINCE,C_ID)
VALUES(3,'street 154','3625','north west','north west',3)
INSERT INTO LOCATIONS(L_ID,L_ADDRESS,L_POSTAL_CODE,L_CITY,L_STATE_PROVINCE,C_ID)
VALUES(4,'street 47','1257','hazara, pakistan','hazara, pakistan',8)
INSERT INTO LOCATIONS(L_ID,L_ADDRESS,L_POSTAL_CODE,L_CITY,L_STATE_PROVINCE,C_ID)
VALUES(5,'street 229','1212','neeli bar','neeli bar',9)

INSERT INTO DEPARTMENTS(D_ID,D_NAME,L_ID)
VALUES(1,'accounts',1)
INSERT INTO DEPARTMENTS(D_ID,D_NAME,L_ID)
VALUES(2,'HR',3)
INSERT INTO DEPARTMENTS(D_ID,D_NAME,L_ID)
VALUES(3,'management',4)
INSERT INTO DEPARTMENTS(D_ID,D_NAME,L_ID)
VALUES(4,'staff',5)
INSERT INTO DEPARTMENTS(D_ID,D_NAME,L_ID)
VALUES(5,'IT',2)

INSERT INTO EMPLOYEES(E_ID,E_FIRST_NAME,E_LAST_NAME,E_EMAIL,E_PHONE_NUMBER,E_HIRE_DATE,J_ID,E_SALARY,E_COMMISSION_PCT,M_ID,D_ID)
VALUES(1,'ali','akbar','ali@gmail.com','0315478458','2012-11-11',1,'105000',2.5,1,1)
INSERT INTO EMPLOYEES(E_ID,E_FIRST_NAME,E_LAST_NAME,E_EMAIL,E_PHONE_NUMBER,E_HIRE_DATE,J_ID,E_SALARY,E_COMMISSION_PCT,M_ID,D_ID)
VALUES(2,'jhon','josh','jhon@gmail.com','0315254147','2011-10-10',1,'95000',2.7,2,2)
INSERT INTO EMPLOYEES(E_ID,E_FIRST_NAME,E_LAST_NAME,E_EMAIL,E_PHONE_NUMBER,E_HIRE_DATE,J_ID,E_SALARY,E_COMMISSION_PCT,M_ID,D_ID)
VALUES(3,'yasmeen','bajwa','yasmeen@yahoo.com','0325478415','2010-09-05',1,'85000',1.5,2,3)
INSERT INTO EMPLOYEES(E_ID,E_FIRST_NAME,E_LAST_NAME,E_EMAIL,E_PHONE_NUMBER,E_HIRE_DATE,J_ID,E_SALARY,E_COMMISSION_PCT,M_ID,D_ID)
VALUES(4,'seena','jalwa','seena@gmail.com','0317853694','2009-05-07',3,'45000',3.7,2,3)
INSERT INTO EMPLOYEES(E_ID,E_FIRST_NAME,E_LAST_NAME,E_EMAIL,E_PHONE_NUMBER,E_HIRE_DATE,J_ID,E_SALARY,E_COMMISSION_PCT,M_ID,D_ID)
VALUES(5,'iqra','riaz','iqra@yahoo.com','032555555','2016-06-06',5,'85000',1.5,1,5)

update DEPARTMENTS SET M_ID='1' where D_ID=1
update DEPARTMENTS SET M_ID='2' where D_ID=2
update DEPARTMENTS SET M_ID='2' where D_ID=3
update DEPARTMENTS SET M_ID='1' where D_ID=4
update DEPARTMENTS SET M_ID='2' where D_ID=5
update DEPARTMENTS SET M_ID='1' where D_ID=6


INSERT INTO JOB_HISTORY(E_ID,JH_START_DATE,JH_END_DATE,J_ID,D_ID)
VALUES (1,'2012-11-11','2017-11-11',1,1)
INSERT INTO JOB_HISTORY(E_ID,JH_START_DATE,JH_END_DATE,J_ID,D_ID)
VALUES (2,'2016-06-06','2018-01-02',5,5)

--Q1
create trigger deleteloc on LOCATIONS
instead of delete
as begin
print 'You are not allowed to delete location'
end
go

delete from LOCATIONS
where L_ID=5
go

--Q2
create trigger deleteemp on EMPLOYEES
instead of delete
as begin
	declare @startdate date
	select @startdate=E_HIRE_DATE from deleted
	declare @id int
	select @id=E_ID from deleted
	if DATEDIFF(year,@startdate,GETDATE())>5
	begin
		print 'You can not delete experienced employees.'
	end
	else
	begin
		delete from EMPLOYEES where E_ID=@id
	end
end
go

delete from EMPLOYEES
where E_ID=4

select * from EMPLOYEES

--Q3
create trigger insertemp on EMPLOYEES
instead of insert
as begin
	declare @salmin int
	declare @salmax int
	declare @jid int
	declare @ins_sal int
	declare @id int
	select @jid=J_ID from inserted
	select @salmin=J_Min_salary from Jobs where @jid=JOBS.J_ID
	select @salmax=J_Max_salary from Jobs where @jid=JOBS.J_ID
	select @ins_sal=E_salary from inserted
	select @id=E_ID from inserted
	if @ins_sal >@salmax OR @ins_sal <@salmin
	begin
		print 'Invalid Salary specified'
	end
	else
	begin
		insert into Employees
		select * from inserted
	end
end
go

INSERT INTO EMPLOYEES(E_ID,E_FIRST_NAME,E_LAST_NAME,E_EMAIL,E_PHONE_NUMBER,E_HIRE_DATE,J_ID,E_SALARY,E_COMMISSION_PCT,M_ID,D_ID)
VALUES(8,'iqra','riaz','iqra@yahoo.com','032555555','2016-06-06',5,'60000',1.5,1,5)

select * from employees

--Q4
create trigger delprocedure on database
for drop_procedure
as begin
	print 'Procedures cannot be deleted.'
	rollback
end
go

create procedure p1
as begin
	print 'hello'
end

drop procedure p1

drop trigger delprocedure