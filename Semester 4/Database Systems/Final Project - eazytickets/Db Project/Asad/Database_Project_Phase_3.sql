create database Project

use Project

--=============================================================================================================
--											SCHEMA
--=============================================================================================================
Create Table Category
(
categoryId int primary key,
categoryName varchar(10)
)

insert into Category values
(1,'Movies'),
(2,'Events'),
(3,'Concerts')
--=============================================================================================================
Create Table[User]
(
UserID int identity(1,1) NOT NULL,
FirstName varchar(20) NOT NULL,
LastName varchar(20) NOT NULL,
Email varchar(30)  Unique NOT NULL,
MobileNo Char(11) NOT NULL,
City varchar(20),
Gender varchar (10),
[Password] varchar (32) NOT NULL,
Constraint pk_user Primary Key (UserID)
)
alter table [User]
alter column MobileNo bigint;
go

--test run
go
--declare @output int

--exec Signup 'Abdullah','Siddiqui','abdullahsiddiqui9823@gmail.com',923004567890,'Lahore','Male','abcdefgh', @output
--exec Signup 'Asad','Abrar','asad.abrar97@gmail.com',923337654321,'Lahore','Male','ijklmno', @output
--exec Signup 'Ayesha','Siddiqah','ayesha@gmail.com',923993141516,'Lahore','Female','helloworld', @output

--delete from [User]

--select * from [User]
go
--=============================================================================================================
create table [Admin]
(
adminID int primary key,

)
Alter table [Admin] add constraint fk_admin foreign key (adminID) references [User](UserID) on delete no action on update cascade

--test run
go
--insert into [Admin] values
--(5)
go
--=============================================================================================================
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
alter table Concert add ImageLink varchar(100) NOT NULL

--test run
go
--insert into Concert(venue,DayTime,performer,organizers,availableTickets,cost) values
--('Lake City, Lahore','2018-8-16 10:34:09','Ali Zafar','OB(Gr-3)',47,2300),
--('National Stadium, Karachi','2018-5-20  23:34:09','Asrar','Fun Boyz',250,500),
--('FAST-NU, Lahore','2018-11-3 21:34:09','Qurat-ul-Ain Baloch','Fast Music Society',200,1200)
go
--=============================================================================================================

Create Table Movie
(
movieId int identity(1,1),
movieName varchar(30) NOT NULL,
director varchar(20),
writer varchar(20),
runningTime Time(7) NOT NULL,
ReleaseDate Date NOT NULL,
genre varchar(20) NOT NULL,
mainCast varchar(100),
Constraint pk_Movie Primary Key (movieID)
)
alter table Movie add ImageLink varchar(100) NOT NULL

--test run
go
--insert into Movie(movieName,director,writer,runnigTime,ReleaseDate,genere,mainCast) values
--('Avengers:Infinity War','Russo Brothers','Stan Lee','02:40:00','2018-4-27','Science fiction','Josh Brolin'),
--('Deadpool','David','Stan Lee','02:00:00','2018-05-16','Fantasy/Action','Ryan Reynolds'),
--('Ant-Man And The Wasp','Peyton Reed','Stan Lee','02:00:00','2018-07-05','Science fiction','Paul Rudd')
go
--=============================================================================================================
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
alter table [Event] add ImageLink varchar(100) NOT NULL

--test run
go
--insert into [Event](eventName,eventType,organizers,venue,[Day],[Time],ticketsAvailable,cost) values
--('Coke Fest','Concert/Food','Coca Cola, Pakistan', 'Royal Palm, Lahore','2018-07-31','16:00:00',1000,400),
--('Lahore Eat','Food','Pepsico','Fortress Stadium, Lahore','2018-08-20','17:00:00',1000,400),
--('Lahore Literary Fest','Literary','Faiz Club','Al-Hamra, Lahore','2018-05-10','10:00:00',1000,200)
go
--=============================================================================================================
Create Table Contact
(
email varchar(30) NOT NULL,
message varchar(200),
primary key(email,message)

)
--test run
go
--insert into Contact values
--('abdullahsiddiqui9823@gmail.com','Bekaar website. Mazay ka interface nahi hai'),
--('asad.abrar97@gmail.com','Website iz gud, but u need 2 make it beautiful'),
--('danial.amir.mirza@gmail.com','Best Website Ever')
go
--=============================================================================================================
Create Table Cinema
(
CinemaId int identity(1,1) Primary Key,
Name varchar(50) NOT NULL,
[Location] varchar(100) NOT NULL,
contactNo int NOT NULL
)
--test run
go
--insert into Cinema(Name,Location,contactNo) values
--('Angoori Cinema','Angoori Bagh, Lahore',123456789),
--('Universal Cinema','Emporium Mall,Lahore',1234),
--('Imperial Cinema','Barki Road, Lahore',987654321),
--('PAF Cinema','Sarfaraz Rafiqui Road, Lahore',9876523)
go
--=============================================================================================================
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

--test run
go
--insert into Showtime(movieId,cinemaId,date_time,availableSeats,cost,ScreeningType) values
--(1,1,'2018-04-27 14:00:00',500,200,'3D'),
--(1,2,'2018-04-28 14:00:00',500,400,'2D'),
--(1,3,'2018-04-29 17:00:00',500,400,'2D'),
--(2,1,'2018-05-16 17:00:00',500,800,'3D'),
--(3,2,'2018-07-20 14:00:00',500,200,'2D')
go
--=============================================================================================================
Create Table Tickets(
ticketId int identity(1,1) ,
categoryId int NOT NULL,
ID int NOT NULL,
Primary Key(ticketId)
)
Alter table Tickets add ID int NOT NULL
Alter table Tickets add Constraint fk_C Foreign Key(categoryId) References Category(categoryId) on delete cascade on update cascade;

--Alter table Tickets add Constraint fk_TM Foreign Key(movieId) References ShowTime(showId) on delete cascade on update cascade;
--Alter table Tickets add Constraint fk_TE Foreign Key(eventId) References [Event](eventID) on delete cascade on update cascade;
--Alter table Tickets add Constraint fk_TC Foreign Key(concertId) References Concert on delete cascade on update cascade;

--test run
go
--insert into Tickets(categoryId,movieId,eventId,concertId) values
--(1,1,NULL,NULL),
--(1,1,null,null),
--(2,null,3,null),
--(3,null,null,1)
go
--=============================================================================================================
Create Table [Orders]
(
orderId int primary key,
user_id int foreign key references  [User]([UserID]) on delete no action on update no action
)
--=============================================================================================================
Create Table Order_Details
(
order_Id int foreign key references [Orders] on delete cascade on update cascade,
ticketId int foreign key references Tickets on delete no action on update cascade,
amount int,
Primary Key(order_Id,ticketId)
)
--=============================================================================================================
Create Table [RatingReview]
(
userID int  foreign key references [User]([UserID]) on delete no action on update cascade,
ticket_id int foreign key references [Tickets] on delete cascade on update cascade,
rating int Not Null,
review varchar(100),
primary key(userID,ticket_id)
)

--test run
go
--insert into RatingReview values
--(1,1,5,'Best movie ever. Thanos is awsome.'),
--(2,2,5,Null),
--(3,3,3,'Bht mazay ka event tha.'),
--(1,4,2,'Bekaar Managment singer late aya.')
go
--=============================================================================================================
Create Table [Cart]
(
orderId int identity(1,1) primary key,
user_id int foreign key references  [User]([UserID]) on delete no action on update no action
)
--=============================================================================================================
Create Table Cart_Details
(
order_Id int foreign key references [Cart](orderId) on delete cascade on update cascade,
ticketId int foreign key references Tickets on delete no action on update cascade,
amount int,
Primary Key(order_Id,ticketId)
)
--=============================================================================================================
--										ADMIN PROCEDURES												
--=============================================================================================================
GO
create procedure AddAdmin
@email varchar(30),@password varchar(32), @fname varchar(20),@lname varchar(20), @city varchar (20),@phone varchar(32),@gender varchar(10)
as 
begin 
	
 insert into [User](Email,Password,FirstName,LastName,MobileNo,City,Gender)
 values(@email,@password,@fname,@lname,@phone,@city,@gender);	
 declare @var int;
 select @var=UserID from [User] where Email=@email;
 insert into [Admin] values(@var);
end 

--test run
go
--exec AddAdmin 'waqarzaqa@lhr.nu.edu.pk','iamkewl','waqar','zaqa','sargodha','1231231','male'
go
--=============================================================================================================
GO
create procedure AddMovie
@movieName varchar(30),
@director varchar(20),
@writer varchar(20),
@runningTime time,
@rDate date,
@genre varchar(20),
@mainCast varchar(100)
as
begin
	--checking for duplication
	if @movieName in (select movieName from Movie)
	begin
		if @director in (select director from Movie where movieName=@movieName)
		begin
			if @writer in (select writer from Movie where director=@director AND  movieName=@movieName)
			begin
				if @runningTime in (select runningTime from Movie where writer=@writer AND director=@director AND  movieName=@movieName)
				begin
					if @rDate in (select ReleaseDate from Movie where runningTime=@runningTime AND writer=@writer AND director=@director AND  movieName=@movieName)
					begin
						if @genre in (select genre from Movie where ReleaseDate=@rDate AND runningTime=@runningTime AND writer=@writer AND director=@director AND  movieName=@movieName)
						begin
							if @mainCast in (select mainCast from Movie where genre=@genre AND ReleaseDate=@rDate AND runningTime=@runningTime AND writer=@writer AND director=@director AND  movieName=@movieName)
							begin
								print 'Movie already exists.'
							end
						end
					end
				end
			end
		end
	end

	--checking if running time is out of bounds
	if convert(time(7),@runningTime,8) < '00:00:00.000'
	begin
		print 'Running time cannot be less than 0.'
	end

	--checking if release date has passed
	if @rDate < (SELECT CAST(GETDATE() AS DATE))
	begin
		print 'Release date has already passed.'
	end

	insert into Movie(movieName,director,writer,runningTime,ReleaseDate,genre,mainCast)
	values (@movieName,@director,@writer,@runningTime,@rDate,@genre,@mainCast)
end
--exec AddMovie 'Muala Jutt 2','Bilal Lashari','Bilal Lashari','02:00:00','2018-11-29','Action/Drama','Fawad Khan,Hamza Ali Abbassi, Mahira Khan';
--=============================================================================================================

--=============================================================================================================
GO

create procedure MovieDelete
@id int
as
begin
	delete from Movie where movieId = @id
end

--test run
go
--exec MovieDelete 4
go
--=============================================================================================================

GO
create procedure RatingDelete
@userID int, @ticketID int
as
begin
	delete from RatingReview where userID = @userID and ticket_id = @ticketID
end
go

--test run
go
--exec RatingDelete 2,2
go
--=============================================================================================================

GO
CREATE PROCEDURE [UpdateAdminInfo]
@id int,
@NewFName varchar(20),
@NewLName varchar(20),
@NewCity varchar(20),
@NewPhone int,
@NewPassword varchar(30)
as
begin
	Update [User]
	Set [FirstName]=@NewFName,[LastName]=@NewLName,City=@NewCity,[Password]=@NewPassword,MobileNo=@NewPhone
	where UserID=@id
end
go

--test run
go
--exec UpdateAdminInfo 5,'Ahman','Bajwa','Kakul','3221122','pakistan'
go

--=============================================================================================================

GO
create procedure addCinema
@Name varchar(50),
@location varchar(100),
@ContactNo int
as
begin
	--checking for duplication
	if @Name in (select Name from Cinema)
	begin
		if @location in (select Location from Cinema where Name=@Name)
		begin
			print 'Cinema already exists.'
		end
	end

	--checking if number is out of bounds
	if @ContactNo < 1111111111 OR @ContactNo > 9999999999
	begin
		print 'Invalid Contact number specified.'
	end

	else
	begin
		insert into Cinema(Name,Location,contactNo) values
		(@Name,@location,@ContactNo)
	end
end
go

create procedure deleteCinema
@Id int
as
begin
	delete from Cinema where CinemaId=@Id
end
go

--test run
go
--exec addCinema 'CineStar','Township, Lahore',1456789
go
--=============================================================================================================
GO
create procedure addShowtime
@movieId int,@CinemaId int,@date_time datetime,@seats int,@cost int,@screentype char(2)
as
begin
	--checking for duplication
	if @movieId in (select movieId from Showtime)
	begin
		if @CinemaId in (select cinemaId from Showtime where movieId=@movieId)
		begin
			if @date_time in (select date_time from Showtime where movieId=@movieId AND cinemaId=@CinemaId)
			begin
				if @screentype in (select ScreeningType from Showtime where movieId=@movieId AND cinemaId=@CinemaId AND date_time=@date_time)
				begin
					if @seats in (select availableSeats from Showtime where movieId=@movieId AND cinemaId=@CinemaId AND date_time=@date_time AND ScreeningType=@screentype)
					begin
						if @cost in (select cost from Showtime where movieId=@movieId AND cinemaId=@CinemaId AND date_time=@date_time AND ScreeningType=@screentype AND availableSeats=@seats)
						begin
							print 'Showtime already exists.'
						end
					end
				end
			end
		end
	end

	--checking if number of available seats is out of bounds
	if @seats < 0
	begin
		print 'Number of available seats cannot be less than 0.'
	end

	--checking if cost is out of bounds
	if @cost < 0 OR @cost>1000
	begin
		print 'Invalid cost specified.'
	end

	--checking if screeningtype is 2d or 3d
	if @screentype!='2d' AND @screentype!='3d'
	begin
		print 'Invalid Screening type specified.'
	end

	insert into Showtime(movieId,cinemaId,date_time,availableSeats,cost,ScreeningType) values
	(@movieId,@CinemaId,@date_time,@seats,@cost,@screentype)

end
go

create trigger insertShowtime on Showtime
instead of insert
as begin
	declare @SshowId int
	declare @SmovieId int 
	declare @ScinemaId int
	declare @Sdate_time datetime 
	declare @SScreeningType char(2)
	declare @Savailableseats int
	declare @Scost int

	select @SshowId=showId  from inserted
	select @SmovieId=movieId  from inserted
	select @ScinemaId=cinemaId from inserted
	select @Sdate_time=date_time from inserted
	select @SScreeningType=ScreeningType from inserted
	select @Savailableseats=availableseats from inserted
	select @Scost=cost from inserted

	insert into Showtime(movieId,cinemaId,date_time,availableSeats,cost,ScreeningType) values
	(@SmovieId,@ScinemaId,@Sdate_time,@Savailableseats,@Scost,@SScreeningType)
	insert into Tickets (categoryId,ID) values (1, @SshowId)

	print 'Showtime has been added to the list.'
end
go

--=============================================================================================================
GO
create procedure removeCinema
@CinemaId int
as
begin
	delete from Cinema
	where Cinema.CinemaId=@CinemaId
end
--exec removeCinema 5
--=============================================================================================================
GO
create procedure removeShowtime
@Showid int
as
begin
delete from Showtime
where Showtime.showId=@Showid
end
--exec removeShowtime 6
--=============================================================================================================
GO
create procedure addConcert
@venue varchar(100),@DayTime datetime,@performer varchar(30),@organizer varchar(20),@seats int,@cost int
as
begin
	insert into Concert(venue,DayTime,performer,organizers,availableTickets,cost) values
	(@venue,@DayTime,@performer,@organizer,@seats,@cost)
end
--exec addConcert 'Royal Palm,Lahore','2018-6-10 22:00:00','Ed Sheeran','Mad Decent Party',800,3000
--=============================================================================================================
GO
create procedure removeConcert
@ConcertId int
as
begin
delete from Concert
where concertId=@ConcertId
end

--exec removeConcert 4
--=============================================================================================================
GO
CREATE procedure addEvent
@Name varchar(30),@type varchar(20),@organizer varchar(20),@venue varchar(100),@day date,@time time,@tickets int,@cost int
as
begin
	insert into [Event](eventName,eventType,organizers,venue,[Day],[Time],ticketsAvailable,cost) values
	(@Name,@type,@organizer,@venue,@day,@time,@tickets,@cost)
	declare @eId int;
	select @eId=E.eventId from [Event] E where E.eventName=@Name AND E.eventType=@type AND E.Day=@day AND E.Time=@time
	insert into Tickets(categoryId,eventId,movieId,concertId)
	values(2,@eId,NULL,NULL)

end
--select* from [Event]
--exec addEvent 'Karachi Eat','Food','Pepsico','khi','2018-05-16','17:00:00',1000,400
--select* from [Event]
--=============================================================================================================
GO
create procedure removeEvent
@Eventid int
as
begin
delete from [Event]
where eventId=@Eventid
end
--select* from [Event]
--exec removeEvent 4
--select* from [Event]

--=============================================================================================================
--											USER PROCEDURES
--=============================================================================================================

GO
create procedure logincheck
@email varchar(30), @password varchar(32),
@output  int output
as 
begin
	set @output=-1

	--checking if User exists or not
	if exists (select * from [User] where Email=@email AND [Password]=@password)
	begin	
		set @output=0   
	end

	if exists (select * from [Admin] join [User] U on adminID=UserID where U.Email=@email and U.[Password]=@password)
	BEGIN 
		set @output=3
		print 'admins view'
	END	

	if @output=-1
	begin
		print 'user not found' 
	end
	
	else if @output=0
	begin 
		print 'logged in :)'   
	end
end

--test run
go
--declare @OUT int
--exec logincheck 'danialmirza@yahoo.com','nhiprhamenepurasaal',@OUT output
--print @OUT
go

--=============================================================================================================
GO
create procedure signup
@fname varchar(20),@lname varchar(20),@email varchar(30),@phone bigint,@city varchar (20),@gender char(1),@password varchar(32),
@output int OUTPUT
as 
begin 
	set @output=0;

	--checking if valid email is given
	if @email not like '%@%'
	begin
		print 'Invalid email specified.'
	    set @output=1
	end

	 --checking if account already exists
	if exists(select Email from [User] where Email=@email)
	begin
		print 'User already exists'
		set @output=2
	end
	
	--checking if valid mobile number is given
	if @phone<923001111111 OR @phone>923499999999
	begin
		print 'Invalid mobile number specified.'
		set @output=3
	end

	--checking if invalid gender is entered
	if @gender='0'
	begin
		print 'Invalid gender specified.'
		set @output=4
	end

	else
	begin
		insert into [User] (FirstName,LastName,Email,MobileNo,City,Gender,[Password]) values (@fname,@lname,@email,@phone,@city,@gender,@password)
		--select * from inserted
		print 'User has been added into the list.'
	end

end 
go

--test run
go
--declare @o int;
--exec signup 'DANIAL@yahoo.com','nhiprhamenepurasaal','Danial','Mirza','Lahore','09007860111','male',@o OUTPUT
--print @o
--select * from [User]
--delete from [User]
go
--=============================================================================================================
GO
create procedure searchbycategory
@category int
as
begin
	declare @cname varchar(10);
	select @cname=categoryName from Category where categoryId=@category;
	if @cname='Movies'
	begin
		select* from Movie;
	end
	else if @cname='Events'
	begin
		select * from [Event]
	end
	else if @cname='Concerts'
	begin
		select * from[Concert]
	end
end

--test run
go
--exec searchbycategory 3
go
--=============================================================================================================
GO
create PROCEDURE [UpdateUserInfo]
@email varchar(30),
@NewFName varchar(20),
@NewLName varchar(20),
@NewCity varchar(20),
@NewPhone int,
@NewPassword varchar(30)
as
begin
	Update [User]
	Set [FirstName]=@NewFName,City=@NewCity,[Password]=@NewPassword,MobileNo=@NewPhone
	where Email=@email

end
--test run
go
--exec UpdateUserInfo 'danialmirza@yahoo.com','Daniyal','Mirza','Islamabad','34343434','123456'
go

--=============================================================================================================

go
create procedure AddRating
@user_id int,
@ticket_id int,
@rating int,
@review varchar(100),
@out int output
as
begin
	if @rating > 5 or @rating < 1
		set @out = -1
	else if exists (select * from RatingReview where ticket_id = @ticket_id and userID = @user_id)
	begin
		update RatingReview set rating = @rating where ticket_id = @ticket_id and userID = @user_id
		update RatingReview set review = @review where ticket_id = @ticket_id and userID = @user_id
		set @out = 2
	end
	else 
	begin
		insert into RatingReview values (@user_id,@ticket_id,@rating,@review)
		set @out = 1
	end
end
go

--test run
go
--declare @outp int
--exec AddRating 1,2,5,'GOOD :)',@outp output
--print @outp
go
--=============================================================================================================
Go
create procedure [addToCart]
@user_id int,
@ticket_id int,
@amount int
as
begin
	
	declare @categoryId int;
	declare @id int;
	select @categoryId=T.categoryId from Tickets T where ticketId=@ticket_id;
	declare @cname varchar(10);
	select @cname=categoryName from Category where categoryId=@categoryId;
    declare @available int;
	declare @orderId int;
	declare @check int;
	set @check=0;
	if exists( select * from Cart C where C.user_id=@user_id)
	begin
		select @orderId=C.orderId from Cart C where C.user_id=@user_id;
		set @check=1;
	end 		

	if @cname='Movies'
	begin
		select @id=movieId from Tickets where ticketId=@ticket_id;
		select @available=availableSeats from Showtime where showId=@id
		print 'Movie'
		print @available
		if (@available-@amount) > 0
		begin
			update Showtime
			set availableSeats=availableSeats-@amount
			where showId=@id
			if @check = 0
			begin
				insert into dbo.[Cart]([user_id]) values(@user_id) 
				select @orderId=orderId from Cart where [user_id]=@user_id
			end
			insert into dbo.[Cart_Details](order_Id,ticketId,amount) values (@orderId,@ticket_id,@amount) 
		end	
		else 
		begin
			print 'Not enough seats available!'
		end
	end
	else if @cname='Events'
	begin		
		select @id=eventId from Tickets where ticketId=@ticket_id;
		print @id
		select @available=E.ticketsAvailable from [Event] E where eventId=@id
		print 'Event'
		print @available
		if (@available-@amount) > 0
		begin
			update [Event]
			set ticketsAvailable=ticketsAvailable-@amount
			where eventId=@id

			if @check = 0
			begin
				insert into dbo.[Cart]([user_id]) values(@user_id) 
				select @orderId=orderId from Cart where [user_id]=@user_id
			end
			insert into dbo.[Cart_Details](order_Id,ticketId,amount) values (@orderId,@ticket_id,@amount) 
		end	
		else 
		begin
			print 'Not enough tickets available!'
		end
	end
	else if @cname='Concerts'
	begin
		select @id=concertId from Tickets where ticketId=@ticket_id;
		print @id
		select @available=C.availableTickets from Concert C where concertId=@id
	    print 'Concert'
		print @available
		if (@available-@amount) > 0
		begin
			update [Concert]
			set availableTickets=availableTickets-@amount
			where concertId=@id

			if @check = 0
			begin
				insert into dbo.[Cart]([user_id]) values(@user_id) 
				select @orderId=orderId from Cart where [user_id]=@user_id
			end
			insert into dbo.[Cart_Details](order_Id,ticketId,amount) values (@orderId,@ticket_id,@amount) 
		end	
		else 
		begin
			print 'Not enough tickets available!'
		end
	end
end
go

--testrun
go
--exec addToCart 1,1,100
--exec ConfirmOrder 3
go
--=============================================================================================================
Go
create procedure [dropCart]
@orderId int
as
begin
	declare @no_of_enteries int
	declare @ticket_id int
	select @no_of_enteries=Count(*) from Cart_Details where order_Id=@orderId
	while @no_of_enteries > 0
	begin
		select @ticket_id = ticketId from Cart_Details where order_Id=@orderId
		
		EXEC dropItem @orderId,@ticket_id

		set @no_of_enteries=@no_of_enteries-1;
	end 
	delete from Cart Where orderId=@orderId
end
go

--test run
go
--exec dropCart 1
go
--=============================================================================================================
Go
create procedure [dropItem]
@orderId int,
@ticketId int
as
begin
	declare @amount int
	declare @catId int
	declare @catname varchar(20)
	declare @id int
	select @amount=amount from Cart_Details where order_Id=@orderId AND ticketId=@ticketId
	select @catId=T.categoryId from Tickets T where T.ticketId=@ticketId
	select @catname=C.categoryName from Category C where C.categoryId=@catId
	if @catname='Movies'
	begin
		select @id=T.movieId from Tickets T where T.ticketId=@ticketId 
		update Showtime 
		set availableSeats=availableSeats+@amount
		where showId=@id

		delete from Cart_Details
		where order_Id=@orderId AND ticketId=@ticketId

	end
	else if @catname='Events'
	begin
		select @id=T.eventId from Tickets T where T.ticketId=@ticketId 
		update [Event]
		set ticketsAvailable=ticketsAvailable+@amount
		where eventId=@id

		delete from Cart_Details
		where order_Id=@orderId AND ticketId=@ticketId
	
	end
	else if @catname='Concerts'
	begin
		select @id=T.concertId from Tickets T where T.ticketId=@ticketId 
		update Concert
		set availableTickets=availableTickets+@amount
		where concertId=@id

		delete from Cart_Details
		where order_Id=@orderId AND ticketId=@ticketId

	end
end
go
--=============================================================================================================
Go
create procedure [ConfirmOrder]
@orderId int

as
begin
	
	insert into Orders(orderId,[user_id]) 
	select C.orderId,C.user_id from Cart C where C.orderId=@orderId

	insert into Order_Details(order_Id,ticketId,amount)
	select CD.order_Id, CD.ticketId,CD.amount from Cart_Details CD where CD.order_Id=@orderId

	delete from Cart_Details where order_Id=@orderId  
	delete from Cart where orderId=@orderId 
	
	print 'Your order has been placed!'
end
go
--=============================================================================================================


GO
create procedure ContactMessage
@email varchar(30),@message varchar(200)
as
begin
insert into Contact values
(@email,@message)
end
--select* from Contact
--exec ContactMessage 'abcd@gmail.com','Awesom'

select* from Contact

--=============================================================================================================
Go 
create procedure NearbyEvents
@userId int
as
begin
	declare @city varchar(20)
	select @city=U.City From [User] U where U.UserID=@userId

	print 'Events:'
	select E.eventName,E.eventType,E.organizers,E.Day,E.Time,E.venue,E.cost from [Event] E where E.venue like ('%'+@city+'%')

	print 'Concert:'
	select C.performer, C.organizers,C.venue,C.DayTime,C.cost from Concert C where C.venue like('%' +@city + '%')

	print 'Showtimes:'
	select C.Name,C.Location,M.movieName,S.date_time,S.ScreeningType,S.cost from (Showtime S join Cinema C on S.cinemaId=C.CinemaId) join Movie M on S.movieId=M.movieId  where C.Location like('%'+@city+'%')

end
--exec NearbyEvents 2
--=============================================================================================================

--=============================================================================================================
create procedure updateShowtime
@SshowId int,
@SmovieId int, 
@ScinemaId int,
@Sdate_time datetime,
@SScreeningType char(2),
@Savailableseats int,
@Scost int
as
begin
	--checking in show id is out of bounds
	if @SshowId < 0
	begin
		print 'Invalid Showtime id enterded.'
	end

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
		update Showtime
		set movieId=@SmovieId, cinemaId=@ScinemaId, date_time=@Sdate_time, availableSeats=@Savailableseats, cost=@Scost, ScreeningType=@SScreeningType
		where showId=@SshowId
	end
end
go
--===========================================================================================================
--============ RatingReview
--=============================================================================================================
create trigger insertRatingReview on [RatingReview]
instead of insert
as begin
	declare @Rrating int
	select @Rrating=rating from inserted

	declare @Rreview varchar(100)
	select @Rreview=review from inserted

	declare @RuserID int  
	select @RuserID=userID  from inserted

	declare @Rticket_id  int
	select @Rticket_id=ticket_id from inserted

	if @Rrating < 0 OR @Rrating > 5
	begin
		print 'Invalid rating entered.'
	end

	else
	begin
		insert into [RatingReview] (userID , ticket_id , rating , review) values (@Rrating, @Rticket_id, @Rrating, @Rreview)
		--select * from inserted
		print 'Rating has been added into the list.'
	end
end
go

--=============================================================================================================
--============User
--=============================================================================================================

--=============================================================================================================
--============Concert
--=============================================================================================================

create trigger insertConcert on Concert
instead of insert
as begin
	declare @CconcertId int
	declare @Cvenue varchar(100)
	declare @CDayTime datetime
	declare @Cperformer varchar(30)
	declare @Corganizers varchar(20)
	declare @CavailableTickets int
	declare @Ccost int

	select @CconcertId=concertId from inserted
	select @Cvenue=venue from inserted
	select @CDayTime=[DayTime] from inserted
	select @Cperformer=performer from inserted
	select @Corganizers=organizers from inserted
	select @CavailableTickets=availableTickets from inserted
	select @Ccost=cost from inserted

	--checking for duplication
	if @Cvenue in (select venue from Concert)
	begin
		if @CDayTime in (select [DayTime] from Concert where venue=@Cvenue)
		begin
			if @Cperformer in (select performer from Concert where venue=@Cvenue AND [DayTime]=@CDayTime)
			begin
				if @Corganizers in (select organizers from Concert where performer=@Cperformer AND venue=@Cvenue AND [DayTime]=@CDayTime)
				begin
					print 'Concert already exists.'
				end
			end
		end
	end

	--checking if number of available tickets is out of bounds
	if @CavailableTickets < 0
	begin
		print 'Number of available tickets cannot be less than 0.'
	end

	--checking if cost is out of bounds
	if @Ccost < 0 OR @Ccost>1000
	begin
		print 'Invalid cost specified.'
	end

	else
	begin
		insert into Concert (venue , [DayTime] , performer , organizers , availableTickets , cost ) values (@Cvenue, @CDayTime, @Cperformer, @Corganizers, @CavailableTickets, @Ccost)
		insert into Tickets (categoryId,movieId,eventId,concertId) values (3, NULL,NULL,@CconcertId)
		print 'Concert has been added into the list.'
	end

end
go
--=============================================================================================================
create procedure updateConcert
@CconcertId int,
@Cvenue varchar(100),
@CDayTime datetime,
@Cperformer varchar(30),
@Corganizers varchar(20),
@CavailableTickets int,
@Ccost int
as
begin
	--checking in Concert id is out of bounds
	if @CconcertId < 0
	begin
		print 'Invalid Concert id enterded.'
	end

	--checking if number of available tickets is out of bounds
	if @CavailableTickets < 0
	begin
		print 'Number of available tickets cannot be less than 0.'
	end

	--checking if cost is out of bounds
	if @Ccost < 0 OR @Ccost>1000
	begin
		print 'Invalid cost specified.'
	end

	else
	begin
		update Concert
		set venue=@Cvenue, [DayTime]=@CDayTime, performer=@Cperformer, organizers=@Corganizers, availableTickets=@CavailableTickets, cost=@Ccost
		where concertId=@CconcertId
	end
end
go


--=============================================================================================================
--============ Movie
--=============================================================================================================

--=============================================================================================================
create procedure updateMovie
@MmovieId int,
@MmovieName varchar(30),
@Mdirector varchar(20),
@Mwriter varchar(20),
@MrunningTime Time,
@MReleaseDate Date,
@Mgenre varchar(20),
@MmainCast varchar(100)
as
begin
	--checking in Movie id is out of bounds
	if @MmovieId < 0
	begin
		print 'Invalid Movie id enterded.'
	end

	--checking if running time is out of bounds
	if convert(time(7),@MrunningTime,8) < '00:00:00.000'
	begin
		print 'Running time cannot be less than 0.'
	end

	--checking if release date has passed
	if @MReleaseDate < (SELECT CAST(GETDATE() AS DATE))
	begin
		print 'Release date has already passed.'
	end

	else
	begin
		update Movie
		set movieName=@MmovieName, director=@Mdirector, writer=@Mwriter, runningTime=@MrunningTime, ReleaseDate=@MReleaseDate, genre=@Mgenre, mainCast=@MmainCast
		where movieId=@MmovieId
	end
end
go

--=============================================================================================================

--============ Event
--=============================================================================================================

create trigger insertEvent on Event
instead of insert
as begin
	declare @EeventId int
	declare @EeventName varchar(30)
	declare @EeventType varchar(20)
	declare @Eorganizers varchar(20)
	declare @Evenue varchar(100)
	declare @EDay date
	declare @ETime time
	declare @EticketsAvailable int
	declare @Ecost int

	select @EeventId=eventId from inserted
	select @EeventName=eventName from inserted
	select @EeventType=eventType from inserted
	select @Eorganizers=organizers from inserted
	select @Evenue=venue from inserted
	select @EDay=[Day] from inserted
	select @ETime=[Time] from inserted
	select @EticketsAvailable=ticketsAvailable from inserted
	select @Ecost=cost from inserted

	--checking for duplication
	if @EeventId in (select eventId from [Event])
	begin
		if @EeventName in (select eventName from [Event] where eventId=@EeventId)
		begin
			if @EeventType in (select eventType from [Event] where eventName=@EeventName AND  eventId=@EeventId)
			begin
				if @Eorganizers in (select organizers from [Event] where eventType=@EeventType AND eventName=@EeventName AND  eventId=@EeventId)
				begin
					if @Evenue in (select venue from [Event] where organizers=@Eorganizers AND eventType=@EeventType AND eventName=@EeventName AND  eventId=@EeventId)
					begin
						if @EDay in (select [Day] from [Event] where venue=@Evenue AND organizers=@Eorganizers AND eventType=@EeventType AND eventName=@EeventName AND  eventId=@EeventId)
						begin
							if @ETime in (select [Time] from [Event] where [Day]=@EDay AND venue=@Evenue AND organizers=@Eorganizers AND eventType=@EeventType AND eventName=@EeventName AND  eventId=@EeventId)
							begin
								if @EticketsAvailable in (select ticketsAvailable from [Event] where [Time]=@ETime AND [Day]=@EDay AND venue=@Evenue AND organizers=@Eorganizers AND eventType=@EeventType AND eventName=@EeventName AND  eventId=@EeventId)
								begin
									if @Ecost in (select cost from [Event] where ticketsAvailable=@EticketsAvailable AND [Time]=@ETime AND [Day]=@EDay AND venue=@Evenue AND organizers=@Eorganizers AND eventType=@EeventType AND eventName=@EeventName AND  eventId=@EeventId)
									begin
										print 'Event already exists.'
									end
								end
							end
						end
					end
				end
			end
		end
	end

	--checking if day has already passed
	if @EDay < (SELECT CAST(GETDATE() AS DATE))
	begin
		print 'Event day has already passed.'
	end

	--checking if available tickets are less than zero
	if @EticketsAvailable < 0
	begin
		print 'Tickets available cannot be less than 0.'
	end

	--checking if cost is less than zero
	if @Ecost < 0
	begin
		print 'Cost cannot be less than 0.'
	end

	else
	begin
		insert into [Event] (eventName, eventType, organizers, venue, [Day], [Time], ticketsAvailable, cost) values (@EeventId, @EeventType, @Eorganizers, @Evenue, @EDay, @ETime, @EticketsAvailable, @Ecost)
		insert into Tickets (categoryId,movieId,eventId,concertId) values (2, NULL,@EeventId,NULL)
		print 'Event has been added into the list.'
	end

end
go
--=============================================================================================================
create procedure updateEvent
@EeventId int,
@EeventName varchar(30),
@EeventType varchar(20),
@Eorganizers varchar(20),
@Evenue varchar(100),
@EDay date,
@ETime time,
@EticketsAvailable int,
@Ecost int
as
begin
	--checking if day has already passed
	if @EDay < (SELECT CAST(GETDATE() AS DATE))
	begin
		print 'Event day has already passed.'
	end

	--checking if available tickets are less than zero
	if @EticketsAvailable < 0
	begin
		print 'Tickets available cannot be less than 0.'
	end

	--checking if cost is less than zero
	if @Ecost < 0
	begin
		print 'Cost cannot be less than 0.'
	end

	else
	begin
		update Event
		set eventName=@EeventName, eventType=@EeventType,organizers=@Eorganizers, venue=@Evenue,[Day]=@EDay,[Time]=@ETime,ticketsAvailable=@EticketsAvailable,cost=@Ecost
		where eventId=@EeventId
	end
end
go
--=============================================================================================================
--										THE END
--=============================================================================================================