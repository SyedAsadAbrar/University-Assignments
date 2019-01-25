--Create Database Project

--use Project

Create Table Category
(
categoryId int primary key,
categoryName varchar(10)
)

Create Table[User]
(
UserID int identity(1,1) NOT NULL,
FirstName varchar(20) NOT NULL,
LastName varchar(20) NOT NULL,
Email varchar(30)  Unique NOT NULL,
MobileNo int NOT NULL,
City varchar(20),
Gender varchar (10),
[Password] varchar (32) NOT NULL,
Constraint pk_user Primary Key (UserID)
)

create table [Admin]
(
adminID int primary key,

)
Alter table [Admin] add constraint fk_admin foreign key (adminID) references [User](UserID) on delete no action on update cascade

Create Table Concert
(
consertId int identity(1,1) Primary Key,
venue varchar(100) NOT NULL,
[DayTime] datetime NOT NULL,
performer varchar(30) NOT NULL,
organizers varchar(20) NOT NULL,
availableTickets int NOT NULL,
cost int NOT NULL
)

 
Create Table Movie
(
movieId int identity(1,1),
movieName varchar(30) NOT NULL,
director varchar(20),
writer varchar(20),
runnigTime Time NOT NULL,
ReleaseDate Date NOT NULL,
genere varchar(20) NOT NULL,
mainCast varchar(100),
Constraint pk_Movie Primary Key (movieID)
)
Create Table [Event]
(
eventId int identity(1,1) Primary Key,
eventName varchar(30) NOT NULL,
eventType varchar(20) NOT NULL,
organizers varchar(20) NOT NULL,
venue varchar(100) NOT NULL,
[Day] date NOT NULL,
[Time] time NOT NULL,
ticketsAvailable int NOT NULL ,
cost int NOT NULL
)
Create Table Contact
(
email varchar(30) NOT NULL,
message varchar(200),
primary key(email,message)

)
Create Table Cinema
(
CinemaId int identity(1,1) Primary Key,
Name varchar(50) NOT NULL,
[Location] varchar(100) NOT NULL,
contactNo int NOT NULL
)
Create Table Showtime
(
showId int identity(1,1) Primary Key,
movieId int foreign key references Movie on delete cascade on update cascade NOT NULL,
cinemaId int foreign key references Cinema on delete cascade on update cascade NOT NULL,
date_time datetime NOT NULL,
availableSeats int not null,
cost int not null,
ScreeningType char(2) NOT NULL,
)

Create Table Tickets(
ticketId int identity(1,1) ,
categoryId int NOT NULL,
movieId int,
eventId int,
concertId int,
Primary Key(ticketId)
)
Alter table Tickets add Constraint fk_C Foreign Key(categoryId) References Category(categoryId) on delete cascade on update cascade;
Alter table Tickets add Constraint fk_TM Foreign Key(movieId) References ShowTime(showId) on delete cascade on update cascade;
Alter table Tickets add Constraint fk_TE Foreign Key(eventId) References [Event](eventID) on delete cascade on update cascade;
Alter table Tickets add Constraint fk_TC Foreign Key(concertId) References Concert on delete cascade on update cascade;

Create Table [Orders]
(
orderId int identity(1,1) primary key,
user_id int foreign key references  [User]([UserID]) on delete no action on update no action
)

Create Table Order_Details
(
order_Id int foreign key references [Orders] on delete cascade on update cascade,
ticketId int foreign key references Tickets on delete no action on update cascade,
amount int,
Primary Key(order_Id,ticketId)
)

Create Table [RatingReview]
(
userID int  foreign key references [User]([UserID]) on delete no action on update cascade,
ticket_id int foreign key references [Tickets] on delete cascade on update cascade,
rating int Not Null,
reveiw varchar(100),
primary key(userID,ticket_id)
)


insert into Category values
(1,'Movies'),
(2,'Concerts'),
(3,'Events')

insert into Cinema(Name,Location,contactNo) values
('Angoori Cinema','Angoori Bagh, Lahore',123456789),
('Universal Cinema','Emporium Mall,Lahore',1234),
('Imperial Cinema','Barki Road, Lahore',987654321),
('PAF Cinema','Sarfaraz Rafiqui Road, Lahore',9876523)

insert into Contact values
('abdullahsiddiqui9823@gmail.com','Bekaar website. Mazay ka interface nahi hai'),
('asad.abrar97@gmail.com','Website iz gud, but u need 2 make it beautiful'),
('danial.amir.mirza@gmail.com','Best Website Ever')

insert into Concert(venue,DayTime,performer,organizers,availableTickets,cost) values
('Lake City, Lahore','2018-8-16 10:34:09','Ali Zafar','OB(Gr-3)',47,2300),
('National Stadium, Karachi','2018-5-20  23:34:09','Asrar','Fun Boyz',250,500),
('FAST-NU, Lahore','2018-11-3 21:34:09','Qurat-ul-Ain Baloch','Fast Music Society',200,1200)

insert into Movie(movieName,director,writer,runnigTime,ReleaseDate,genere,mainCast) values
('Avengers:Infinity War','Russo Brothers','Stan Lee','02:40:00','2018-4-27','Science fiction','Josh Brolin'),
('Deadpool','David','Stan Lee','02:00:00','2018-05-16','Fantasy/Action','Ryan Reynolds'),
('Ant-Man And The Wasp','Peyton Reed','Stan Lee','02:00:00','2018-07-05','Science fiction','Paul Rudd')

insert into [Event](eventName,eventType,organizers,venue,[Day],[Time],ticketsAvailable,cost) values
('Coke Fest','Concert/Food','Coca Cola, Pakistan', 'Royal Palm, Lahore','2018-07-31','16:00:00',1000,400),
('Lahore Eat','Food','Pepsico','Fortress Stadium, Lahore','2018-08-20','17:00:00',1000,400),
('Lahore Literary Fest','Literary','Faiz Club','Al-Hamra, Lahore','2018-05-10','10:00:00',1000,200)

insert into [User](FirstName,LastName,Email,MobileNo,City,Gender,[Password]) values
('Abdullah','Siddiqui','abdullahsiddiqui9823@gmail.com',1234567890,'Lahore','Male','abcdefgh'),
('Asad','Abrar','asad.abrar97@gmail.com',0987654321,'Lahore','Male','ijklmno'),
('Ayesha','Siddiqah','ayesha@gmail.com',1213141516,'Lahore','Female','helloworld')

insert into Showtime(movieId,cinemaId,date_time,availableSeats,cost,ScreeningType) values
(1,1,'2018-04-27 14:00:00',500,200,'3D'),
(1,2,'2018-04-28 14:00:00',500,400,'2D'),
(1,3,'2018-04-29 17:00:00',500,400,'2D'),
(2,1,'2018-05-16 17:00:00',500,800,'3D'),
(3,2,'2018-07-20 14:00:00',500,200,'2D')

insert into [Admin] values
(1),
(2),
(3)

insert into Tickets(categoryId,movieId,eventId,concertId) values
(1,1,NULL,NULL),
(1,1,null,null),
(2,null,3,null),
(3,null,null,1)

insert into Orders([user_id]) values
(1),
(1),
(2),
(2),
(3)

insert into RatingReview values
(1,1,5,'Best movie ever. Thanos is awsome.'),
(2,2,5,Null),
(3,3,3,'Bht mazay ka event tha.'),
(1,4,2,'Bekaar Managment singer late aya.')

select * from [Admin]
select * from Category
select * from Cinema
select * from Concert
select * from Contact
select * from [Event]
select * from Movie
--select * from Order_Details
select * from Orders
select * from RatingReview
select * from Showtime
select * from Tickets
select * from [User]

