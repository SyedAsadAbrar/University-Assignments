Create Database AsadProject

use AsadProject

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
concertId int identity(1,1) Primary Key,
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
review varchar(100),
primary key(userID,ticket_id)
)

--Triggers--

--Cinema

create trigger insertCinema on Cinema
instead of insert
as begin
	declare @CName varchar(50)
	declare @CLoc varchar(100)
	declare @CContact int 
	select @CName=Name from inserted
	select @CLoc=Location from inserted
	select @CContact=contactNo from inserted

	--checking for duplication
	if @CName in (select Name from Cinema)
	begin
		if @CLoc in (select Location from Cinema where Name=@CName)
		begin
			print 'Cinema already exists.'
		end
	end

	--checking if number is out of bounds
	if @CContact < 1111111111 OR @CContact > 9999999999
	begin
		print 'Invalid Contact number specified.'
	end

	else
	begin
		insert into Cinema (Name,Location,contactNo) values (@CName,@CLoc,@CContact)
		--select * from inserted
		print 'Cinema has been added into the list.'
	end
end
go

--No update trigger or procedure for cinema cause it doesn't make any sense, simple queries can be used
;

-- Doesn't let you delete cinema
create trigger deleteCinema on Cinema
instead of delete
as begin
	print 'Cinema cannot be deleted.'
end
go

--Showtime

create trigger insertShowtime on Showtime
instead of insert
as begin
	declare @SmovieId int 
	declare @ScinemaId int
	declare @Sdate_time datetime 
	declare @SScreeningType char(2)
	declare @Savailableseats int
	declare @Scost int

	select @SmovieId=movieId  from inserted
	select @ScinemaId=cinemaId from inserted
	select @Sdate_time=date_time from inserted
	select @SScreeningType=ScreeningType from inserted
	select @Savailableseats=availableseats from inserted

	--checking for duplication
	if @SmovieId in (select movieId from Showtime)
	begin
		if @ScinemaId in (select cinemaId from Showtime where movieId=@SmovieId)
		begin
			if @Sdate_time in (select date_time from Showtime where movieId=@SmovieId AND cinemaId=@ScinemaId)
			begin
				if @SScreeningType in (select ScreeningType from Showtime where movieId=@SmovieId AND cinemaId=@ScinemaId AND date_time=@Sdate_time)
				begin
					print 'Showtime already exists.'
				end
			end
		end
	end

	--checking if number of available seats is out of bounds
	if @Savailableseats < 0
	begin
		print 'Number of available seats cannot be less than 0.'
	end

	--checking if cost is out of bounds
	if @Scost < 0 OR @Scost>1000
	begin
		print 'Invalid cost specified.'
	end

	--checking if screeningtype is 2d or 3d
	if @SScreeningType!='2d' AND @SScreeningType!='3d'
	begin
		print 'Invalid Screening type specified.'
	end

	else
	begin
		insert into Showtime (movieId, cinemaId, date_time, availableSeats, cost, ScreeningType) values (@SmovieId, @ScinemaId, @Sdate_time, @Savailableseats, @Scost, @SScreeningType)
		--select * from inserted
		print 'Showtime has been added into the list.'
	end
end
go

create procedure updateShowtime
@SmovieId int, 
@ScinemaId int,
@Sdate_time datetime,
@SScreeningType char(2),
@Savailableseats int,
@Scost int
as
begin
	--checking in movie id is out of bounds
	if @SmovieId < 0
	begin
		print 'Invalid Movie id enterded.'
	end

	--checking in Cinema id is out of bounds
	if @ScinemaId < 0
	begin
		print 'Invalid Cinema id enterded.'
	end

	--checking if screeningtype is 2d or 3d
	if @SScreeningType!='2d' AND @SScreeningType!='3d'
	begin
		print 'Invalid Screening type specified.'
	end

	--checking if number of available seats is out of bounds
	if @Savailableseats < 0
	begin
		print 'Number of available seats cannot be less than 0.'
	end

	--checking if cost is out of bounds
	if @Scost < 0 OR @Scost>1000
	begin
		print 'Invalid cost specified.'
	end

	else
	begin
		update 
	end
end
go

--Cannot create delete and update triggers because of foreign keys
--Delete trigger is unnecessary
;

--[RatingReview]
create trigger insertRatingReview on [RatingReview]
instead of insert
as begin
	declare @Rrating int
	select @Rrating=rating from inserted

	declare @Rreview varchar(100)
	select @Rreview=review from inserted

	if @Rrating < 0 OR @Rrating > 5
	begin
		print 'Invalid rating entered.'
	end
end
go

--Asad Yaar kaam thora tezz krlo

--Asad Yaar kaam thora tezz krlo

--Asad Yaar kaam thora tezz krlo

--Asad Yaar kaam thora tezz krlo


--Asad Yaar kaam thora tezz krlo

--Asad Yaar kaam thora tezz krlo

--Asad Yaar kaam thora tezz krlo

--Asad Yaar kaam thora tezz krlo

--Asad Yaar kaam thora tezz krlo