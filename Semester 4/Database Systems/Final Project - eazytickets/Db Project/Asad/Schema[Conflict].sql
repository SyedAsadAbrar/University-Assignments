Create Database Project1

use Project1

Create Table Category
(
categoryId int primary key,
categoryName varchar(10) NOT NULL
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
runningTime Time NOT NULL,
ReleaseDate Date NOT NULL,
genre varchar(20) NOT NULL,
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

--============ Cinema

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

--//////////////////////;

--============ Showtime

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
		insert into Tickets (categoryId,movieId,eventId,concertId) values (1, @SshowId, NULL, NULL)
		print 'Showtime has been added into the list.'
	end
end
go

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

--Cannot create delete and update triggers because of foreign keys
--Delete trigger is unnecessary
;

--//////////////////////;

--============ RatingReview

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

--//////////////////////

--============User

create trigger insertUser on [User]
instead of insert
as begin
	declare @UUserID int
	select @UUserID=UserID from inserted

	declare @UFirstName varchar(20)
	select @UFirstName=FirstName from inserted

	declare @ULastName varchar(20)
	select @ULastName=LastName from inserted

	declare @UEmail varchar(30)
	select @UEmail=Email from inserted

	declare @UMobileNo int
	select @UMobileNo=MobileNo from inserted

	declare @UCity varchar(20)
	select @UCity=City from inserted

	declare @UGender varchar(10)
	select @UGender=Gender from inserted
	
	declare @UPassword varchar(32)
	select @UPassword=[Password] from inserted 

	--checking if valid email is given
	if @UEmail not like '%@%'
	begin
		print 'Invalid email specified.'
	end

	--checking if valid mobile number is given
	if @UMobileNo<923001111111 OR @UMobileNo>923499999999
	begin
		print 'Invalid mobile number specified.'
	end

	--checking if valid gender is given
	if @UGender!='Male' OR @UGender!='Female' OR @UGender!='Unspecified'
	begin
		print 'Invalid gender specified.'
	end

	else
	begin
		insert into [User] (UserID,FirstName,LastName,Email,MobileNo,City,Gender,[Password]) values (@UUserID,@UFirstName,@ULastName,@UEmail,@UMobileNo,@UCity,@UGender,@UPassword)
		--select * from inserted
		print 'User has been added into the list.'
	end

end
go

--//////////////////////

--============Concert

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

--//////////////////////;

--============ Movie

create trigger insertMovie on Movie
instead of insert
as begin
	declare @MmovieId int
	declare @MmovieName varchar(30)
	declare @Mdirector varchar(20)
	declare @Mwriter varchar(20)
	declare @MrunningTime Time
	declare @MReleaseDate Date
	declare @Mgenre varchar(20)
	declare @MmainCast varchar(100)

	select @MmovieId=movieId from inserted
	select @MmovieName=movieName from inserted
	select @Mdirector=director from inserted
	select @Mwriter=writer from inserted
	select @MrunningTime=runningTime from inserted
	select @MReleaseDate=ReleaseDate from inserted
	select @Mgenre=genre from inserted
	select @MmainCast=mainCast from inserted

	--checking for duplication
	if @MmovieName in (select movieName from Movie)
	begin
		if @Mdirector in (select director from Movie where movieName=@MmovieName)
		begin
			if @Mwriter in (select writer from Movie where director=@Mdirector AND  movieName=@MmovieName)
			begin
				if @MrunningTime in (select runningTime from Movie where writer=@Mwriter AND director=@Mdirector AND  movieName=@MmovieName)
				begin
					if @MReleaseDate in (select ReleaseDate from Movie where runningTime=@MrunningTime AND writer=@Mwriter AND director=@Mdirector AND  movieName=@MmovieName)
					begin
						if @Mgenre in (select genre from Movie where ReleaseDate=@MReleaseDate AND runningTime=@MrunningTime AND writer=@Mwriter AND director=@Mdirector AND  movieName=@MmovieName)
						begin
							if @MmainCast in (select mainCast from Movie where genre=@Mgenre AND ReleaseDate=@MReleaseDate AND runningTime=@MrunningTime AND writer=@Mwriter AND director=@Mdirector AND  movieName=@MmovieName)
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
	if @MrunningTime < 0
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
		insert into Movie (movieName, director, writer, runningTime, ReleaseDate, genre, mainCast) values (@MmovieName, @Mdirector, @Mwriter, @MrunningTime, @MReleaseDate, @Mgenre, @MmainCast)
		print 'Movie has been added into the list.'
	end

end
go

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
	if @MrunningTime < 0
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
		set movieId=@MmovieId, movieName=@MmovieName, director=@Mdirector, writer=@Mwriter, runningTime=@MrunningTime, ReleaseDate=@MReleaseDate, genre=@Mgenre, mainCast=@MmainCast
		where movieId=@MmovieId
	end
end
go

--//////////////////////;

--============ Event

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