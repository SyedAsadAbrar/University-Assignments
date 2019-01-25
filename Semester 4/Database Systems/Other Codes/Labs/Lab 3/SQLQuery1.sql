create table Departments
(
	SrNo int not null,
	Department_ID varchar(4) not null primary key,
	Department_Name varchar(30) not null UNIQUE,
	Department_Code varchar(2) not null UNIQUE
)

create table students
(
	SrNo int not null,
	Reg_no varchar(7) not null primary key,
	Name varchar(30) not null,
	Department varchar(2) not null FOREIGN KEY references Departments(Department_Code) ON DELETE CASCADE ON UPDATE CASCADE
)

create table courses
(
	SrNo int not null,
	Course_Id varchar(4) not null primary key,
	Course_Name varchar(30) not null UNIQUE,
	Credit_Hours int not null
)

create table instructor
(
	SrNo int not null,
	Instructor_ID int not null primary key,
	Instructor_Name varchar(30) not null,
	Department varchar(2) not null FOREIGN KEY references Departments(Department_Code) ON DELETE CASCADE ON UPDATE CASCADE
)

Insert into departments
values (1,'1C','Computer Science','CS'),
		(2,'1E','Electrical','EE'),
		(3,'1CV','Civil','CV')

select * from departments

INSERT INTO students 
values (1,'L164214','Ahmad Khalid','CS'),
(2,'L164112','Faisal Khan','EE'),
(3,'L164001','Shahid Abbas','CV')

select* from students

Insert into courses
values (1,'C11','Programming',3),
(2,'E4','Circuit Analysis',3),
(3,'CV8','Material Engineering',3)

select* from courses

Insert into instructor
values (1,12,'Ishaq Raza','CV'),
(2,22,'Zareen Alamgir','Ee'),
(3,32,'Habib Ullah','CS')

select* from instructor

alter table students drop column SrNo
alter table students add Warning_Count int

INSERT INTO students 
values ('l162334', 'Fozan Shahid', 'CS', 3.2)

Insert into instructor
values (1,12, 'Ramin Rana', 'CS') --invalid query, change instructor id

Update departments
set Department_Code='CE'
where Department_ID='1CV'

Update departments
set Department_ID='1CVE'
where Department_Name='Civil'

delete from courses
where Course_Name='Programming'

delete from students
where Name='Faisal Khan'