Create Database Project

use Project

Create Table Category
(
categoryId int primary key,
categoryName varchar(10)
)

CREATE Table[User]
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



Create Table [Cart]
(
orderId int identity(1,1) primary key,
user_id int foreign key references  [User]([UserID]) on delete no action on update no action
)

Create Table Cart_Details
(
order_Id int foreign key references [Cart](orderId) on delete cascade on update cascade,
ticketId int foreign key references Tickets on delete no action on update cascade,
amount int,
Primary Key(order_Id,ticketId)
)
