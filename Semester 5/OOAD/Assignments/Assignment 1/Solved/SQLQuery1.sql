create database LMS

use LMS

-----------------
--Users
-----------------

create Table Users
(
	username varchar(30) primary key,
	password varchar(30) not null,
	name varchar(30) not null,
    address varchar(100) not null,
    phone bigint not null,
	type char not null,
	fine int not null
)

--drop table Users

--Insert into Users values ('sdf2','123','Beebop Boo Dee','8273 Hillcrest St. Thomasville, NC 27360', 923358674922, 'L', 0),
					('Hehe','123','Heehhee Huhu','8273 Hillcrest St. Thomasville, NC 27360', 923358674832, 'B', 0),
					('Asad','123','Asad Abrar','8273 Hillcrest St. Jacksonville, NC 27360', 923335169190, 'B', 0),
					('Abrar','123','Abrar Hussain','8273 Towncrest St. Thomasville, NC 27360', 923334837291, 'L', 0),
					('Siddiq','1', 'Siddiqui Abdullah','06-Gulnar Street Tajbagh Housing Scheme', 923124690971, 'C', 0);

--select * from Users

--delete from Users

--select * from Users where username = 'Asad'

-----------------
--Books
-----------------

create Table Books
(
	isbn char(13) not null,
	name varchar(30) not null,
	author varchar(30) not null,
	subject varchar(30) not null,
	primary key(isbn)
)

--drop Table Books

--select * from Books

--insert into Books values('1234567890123','Object Oriented Design','Amir Raheem','Programming Paradigms'),
('0987654321234','Object Oriented','Amir Iqbal','Programming'),
('9089786756453','Object','Farooq Ahmed','Programming'),
('1213141516171','Harry Potter 1','JK Rowling','Novel');

-----------------
--Copies
-----------------

create Table Copies
(
	id int not null,
	isbn char(13) not null references Books(isbn),
	primary key(id, isbn),
)

insert into Copies values (1, '1234567890123'),
(1, '0987654321234'),
(2, '0987654321234'),
(1, '9089786756453'),
(1, '1213141516171');

select * from Copies

drop table Copies

-----------------
--Loans
-----------------

create Table Loans
(
	bookId int not null,
	isbn char(13) not null,
    username varchar(30) references Users(username),
	issueDate Date not null,
	returnDate Date not null,
	status bit not null,
	foreign key(bookId,isbn) references Copies(id, isbn),
	primary key(isbn, bookId, username, issueDate)
)

--drop Table Loans

insert into Loans values(2, '0987654321234', 'Asad', GETDATE(), DATEADD(day, 7, GETDATE()), 1),
(1,'9089786756453','Asad', GETDATE(), DATEADD(day, 7, GETDATE()), 1),
(1,'1213141516171', 'Siddiq', DATEADD(day, -8, GETDATE()), DATEADD(day, -1, GETDATE()), 1);

select * from Loans

delete from Loans

-----------------
--Requests
-----------------

create Table Requests
(
	username varchar(30) references Users(username),
	isbn char(13) not null,
	status varchar(30),
	foreign key(isbn) references Books(isbn),
	primary key(isbn, username, status)
)

select * from Requests

--drop Table Requests

-----------------
--LoggedIn
-----------------

--create Table LoggedIn
--(
--	username varchar(30) references Users(username),
--)

--select * from LoggedIn

--delete from LoggedIn

--drop table LoggedIn

--select Books.id, Books.isbn, username, issueDate, returnDate from Loans, Books where Books.id = Loans.bookId AND Books.isbn = Loans.isbn AND Books.id = 1 AND Books.isbn = '1213141516171'

SELECT * FROM Books
SELECT * FROM Loans
SELECT * FROM Requests
SELECT * FROM Users
select * from Copies