Create Database Phase2

use Phase2

Create Table[User]
(
userId varchar(20),
FirstName varchar(20),
LastName varchar(20),
Email varchar(30),
MobileNo int ,
City varchar(20),
Gender varchar (10),
[Password] varchar (32),
Constraint pk_user Primary Key (userId)
)
Create Table Concert
(
consertId int Primary Key,
venue varchar(100),
[Day] date,
[time] time,
singer varchar(30),
organizers varchar(20),
availableTickets int,
cost int
)
 
Create Table Movie
(
movieId int,
movieName varchar(30),
director varchar(20),
writer varchar(20),
runnigTime Time,
ReleaseDate Date,
genre varchar(20),
mainCast varchar(100),
Constraint pk_Movie Primary Key (movieID)
)
Create Table [Event]
(
eventId int Primary Key,
eventName varchar(30),
eventType varchar(20),
organizers varchar(20),
venue varchar(100),
[Day] date,
[Time] time,
ticketsAvailable int,
cost int
)
Create Table Cinema
(
CinemaId int Primary Key,
Name varchar(50),
[Location] varchar(100),
contactNo int
)

Create Table Showtime
(
showId int Primary Key,
movieId int foreign key references Movie,
cinemaId int foreign key references Cinema,
date_time datetime ,
ScreeningType char(2),
)
Create Table Tickets(
ticketId int,
category char(1),
movieId int,
eventId int,
concertId int,
Constraint pk_Ticket Primary Key(ticketId),
Constraint fk_TM Foreign Key(movieId) References ShowTime(showId),
Constraint fk_TE Foreign Key(eventId) References [Event],
Constraint fk_TC Foreign Key(concertId) References Concert
)



Create Table Showtime_Details
(
showId  int primary key foreign key references Showtime,
TotalSeats int,
cost int ,
)

Create Table [Orders]
(
orderId int primary key,
user_id varchar(20) foreign key references  [User],
totalItems int,
totalCost int
)
Create Table Order_Details
(
order_Id int foreign key references [Orders],
ticketId int foreign key references Tickets,
amount int,
Primary Key(order_Id,ticketId)
)

