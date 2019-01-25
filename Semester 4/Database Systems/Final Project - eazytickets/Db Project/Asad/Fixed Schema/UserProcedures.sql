use Project

--=============================================================================================================
--										USER Procedures
--=============================================================================================================

--===========
--	RATING
--===========

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

go
create procedure AddRating
@user varchar(30),
@ticket_id int,
@rating int,
@review varchar(100),
@out int output
as
begin
	declare @userId int;
	select @userId=[User].UserID From [User] Where [User].Email=@user;
	if exists (select * from RatingReview where ticket_id = @ticket_id and userID = @userId)
	begin
		update RatingReview set rating = @rating where ticket_id = @ticket_id and userID = @userId
		update RatingReview set review = @review where ticket_id = @ticket_id and userID = @userId
		set @out = 2
	end
	else 
	begin
		insert into RatingReview values (@userId,@ticket_id,@rating,@review)
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

--===========
--	USER
--===========

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
	else if exists(select Email from [User] where Email=@email)
	begin
		print 'User already exists'
		set @output=2
	end
	
	--checking if valid mobile number is given
	else if @phone<923001111111 OR @phone>923499999999
	begin
		print 'Invalid mobile number specified.'
		set @output=3
	end

	--checking if invalid gender is entered
	else if @gender='0'
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
--go
--declare @o int;
--exec signup 'Danial','Mirza','DANIAL@yahoo.com',09007860111,'Lahore','male','nhiprhamenepurasaal',@o OUTPUT
--print @o
--select * from [User]
--delete from [User]
--where UserID=5
--go

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

--===========
--	CART
--===========

Go
create procedure [addToCart]
@user varchar(30),
@ticket_id int,
@amount int,
@ex int output,
@done int output
as
begin
	set @ex=1;
	set @done=1;
	declare @user_id int;
	select @user_id=U.UserID from [User] U where U.Email=@user; 
	
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
			begin transaction t1
			
			begin try
				select @id=ID from Tickets where ticketId=@ticket_id;
				select @available=availableSeats from Showtime where showId=@id
				print 'Movie'
				print @available
				if (@available-@amount) >= 0
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
					set @done=-1;
					print 'Not enough seats available!'
				end
				commit transaction t1 
			end try
			begin catch
				set @ex=-1;
				rollback transaction t1
			end catch
		end
		else if @cname='Events'
		begin
			begin transaction t2
			begin try	
				select @id=ID from Tickets where ticketId=@ticket_id;
				print @id
				select @available=E.ticketsAvailable from [Event] E where eventId=@id
				print 'Event'
				print @available
				if (@available-@amount) >= 0
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
					set @done=-1;
					print 'Not enough tickets available!'
				end
				commit transaction t2
			end try
			begin catch
				set @ex=-1;
				rollback transaction t2;
			end catch
		end
		else if @cname='Concerts'
		begin
			begin transaction t3
			
			begin try
				select @id=ID from Tickets where ticketId=@ticket_id;
				print @id
				select @available=C.availableTickets from Concert C where concertId=@id
				print 'Concert'
				print @available
				if (@available-@amount) >= 0
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
					set @done=-1;
					print 'Not enough tickets available!'
				end
				commit transaction t3
			end try
			begin catch
				set @ex=-1;
				rollback transaction t3
			end catch
		end
	
end
go

declare @ex int
declare @a int
exec addToCart 'mrk@gmail.com',11,100,@ex Output,@a Output
print @ex
print @a

--delete from Cart

--testrun

select * from Cart
select * from Cart_Details
select * from [Event]
select * from Tickets

--exec ConfirmOrder 3
go

Go
create procedure [dropCart]
@user varchar(30)
as
begin
	declare @orderId int;
	select @orderId=C.orderId From Cart C join [User] U on U.UserID=C.user_id  where U.Email=@user ;
	
	declare @no_of_enteries int
	declare @ticket_id int
	select @no_of_enteries=Count(*) from Cart_Details where order_Id=@orderId
	while @no_of_enteries > 0
	begin
		select @ticket_id = ticketId from Cart_Details where order_Id=@orderId
		
		EXEC dropItem @user,@ticket_id

		set @no_of_enteries=@no_of_enteries-1;
	end 
	delete from Cart Where orderId=@orderId
end
go

Select * from Cart
Select * from Cart_Details
Select * from [Event]
exec dropCart 'mrk@gmail.com'
Select * from Cart
Select * from Cart_Details
Select * from [Event]
--test run
go
--exec dropCart 1
go

Go
alter procedure [dropItem]
@user varchar(30),
@ticketId int
as
begin
	declare @orderId int;
	select @orderId=C.orderId From Cart C join [User] U on U.UserID=C.user_id  where U.Email=@user ;
	declare @no_of_items int;
	select @no_of_items=COUNT(CD.ticketId) from Cart_Details CD where CD.order_Id=@orderId
	if @no_of_items>1
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
			select @id=T.ID from Tickets T where T.ticketId=@ticketId 
			update Showtime 
			set availableSeats=availableSeats+@amount
			where showId=@id

			delete from Cart_Details
			where order_Id=@orderId AND ticketId=@ticketId

		end
		else if @catname='Events'
		begin
			select @id=T.ID from Tickets T where T.ticketId=@ticketId 
			update [Event]
			set ticketsAvailable=ticketsAvailable+@amount
			where eventId=@id

			delete from Cart_Details
			where order_Id=@orderId AND ticketId=@ticketId
	
		end
		else if @catname='Concerts'
		begin
			select @id=T.ID from Tickets T where T.ticketId=@ticketId 
			update Concert
			set availableTickets=availableTickets+@amount
			where concertId=@id

			delete from Cart_Details
			where order_Id=@orderId AND ticketId=@ticketId
		end
	end
	else if @no_of_items=1
	begin
		exec dropCart @user;
	end
end
go
select * from Cart
exec dropItem 'mrk@gmail.com',24
select * from Cart_Details


Go
create procedure [ConfirmOrder]
@user varchar(30)
as
begin
	declare @orderId int
	select @orderId=C.orderId from [User] U join Cart C on U.UserID=C.user_id where U.Email=@user

	insert into Orders(orderId,[user_id]) 
	select C.orderId,C.user_id from Cart C where C.orderId=@orderId

	insert into Order_Details(order_Id,ticketId,amount)
	select CD.order_Id, CD.ticketId,CD.amount from Cart_Details CD where CD.order_Id=@orderId

	delete from Cart_Details where order_Id=@orderId  
	delete from Cart where orderId=@orderId 
	
	print 'Your order has been placed!'
end
go

--===========
--	MISC
--===========

GO
create procedure ContactMessage
@email varchar(30),@message varchar(200), @output int output
as
begin
	set @output=0
	if @email not like '%@%'
	begin
		print 'Invalid email specified.'
		set @output=1
	end

	else 
	begin
		insert into Contact values
		(@email,@message)
	end
end

--drop procedure ContactMessage

--select* from Contact
--exec ContactMessage 'abcd@gmail.com','Awesom'

select* from Contact

--=============================================================================================================

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
--===========================
--procedures added afterwards
--===========================
--=============================================================================================================
go
create proc viewInfo
@email varchar(30),@fname varchar(20) OUTPUT,@lname varchar(20) OUTPUT,@phone bigint OUTPUT,@city varchar(20) OUTPUT
as
begin
	select @fname=U.FirstName from  [User] U where Email=@email;
	select @lname=U.LastName from  [User] U where Email=@email;
	select @phone=U.MobileNo from  [User] U where Email=@email;
	select @city=U.City from  [User] U where Email=@email;	
end

select* from [User]
--=============================================================================================================
go
create proc changePassword
@email varchar(30),@opass varchar(32),@npass varchar(32),@output int OUTPUT
as 
begin
	set @output=-1;
	if not exists (select* from [User] U where U.Email=@email)
	begin
		print 'no such user found '
		return
	end
	else
	begin
		if exists (select* from [User] U where U.Email=@email AND U.Password=@opass)
		begin
			update [User]
			set [Password]=@npass
			where Email=@email

			set @output=1;
			return;
		end
		else 
		begin
			print 'password does not match'
			set @output=-2;
			return;
		end
	end
end
declare @out int;
exec changePassword 'mirza_danial70@yahoo.com','123456','654321',@out OUTPUT
print 

--=============================================================================================================

GO
alter procedure eventCount
@email varchar(30),@output int OUTPUT
as
begin
	declare @location varchar(20);
	select @location=City from [User] where Email=@email;
	select @output=COUNT(*) FROM [Event] E where E.venue like '%'+@location+'%' and datediff(month,getdate(),E.Day_Time)<=1 and E.Day_Time>getdate(); 
	select @output=@output+COUNT(*) FROM [Concert] C where C.venue like '%'+@location+'%' and datediff(month,getdate(),C.DayTime)<=1 and C.DayTime>getdate(); 

end

--=============================================================================================================
GO
create procedure nearbyEvents
@email varchar(30)
as
begin
	declare @city varchar(20);
	select @city=U.City from [User] U where U.Email=@email;
	
	select E.Day_Time as DayTime, E.venue as Venue ,E.eventName as Name   from [Event] E where E.venue like ('%'+@city+'%') and datediff(month,getdate(),E.Day_Time)<=1 and E.Day_Time>getdate()
	UNION
	select C.DayTime as DayTime, C.venue as Venue ,C.performer as Name   from Concert C where  C.venue like ('%'+@city+'%') and datediff(month,getdate(),C.DayTime)<=1 and C.DayTime>getdate()
	 
end
exec nearbyEvents 'mrk@gmail.com'
select * from Concert
update Concert  set DayTime='2018-05-20 10:30:00' where concertId=2
--=============================================================================================================
Go
create proc selectEvents 
as
begin
	select * from Tickets T join [Event] E on T.ID=E.eventId AND T.categoryId=2  ;
end
GO

exec selectEvents


--=============================================================================================================
go
create Proc displayCart
@user varchar(30)
as
begin
	declare @id int;
	select @id=UserId from [User] where Email=@user;

	select E.eventName [Name],E.cost UnitCost,CD.amount Quantity ,CD.ticketId ItemId  from( (Cart C join Cart_Details CD On C.orderId=CD.order_Id) join Tickets T on T.ticketId=CD.ticketId ) join [Event] E on categoryId=2 and E.eventId=T.ID   where C.user_id=@id
	Union
	select M.movieName [Name],S.cost UnitCost,CD.amount Quantity ,CD.ticketId ItemId  from( (Cart C join Cart_Details CD On C.orderId=CD.order_Id) join Tickets T on T.ticketId=CD.ticketId ) join [Showtime] S on categoryId=1 and S.showId=T.ID join Movie M on M.movieId=S.movieId   where C.user_id=@id
	Union
	select Co.performer [Name],Co.cost UnitCost,CD.amount Quantity ,CD.ticketId ItemId  from( (Cart C join Cart_Details CD On C.orderId=CD.order_Id) join Tickets T on T.ticketId=CD.ticketId ) join [Concert] Co on categoryId=3 and Co.concertId=T.ID   where C.user_id=@id
	

end
--=============================================================================================================
Go
create proc selectConcerts 
as
begin
	select * from Tickets T join [Concert] C on T.ID=C.concertId AND T.categoryId=3  ;
end
GO

exec selectConcerts
--=============================================================================================================
go
create proc upcoming
as
begin
	select M.movieName [name],M.ReleaseDate comingdate,ImageLink from Movie M where M.ReleaseDate > GETDATE()
	Union
	select E.eventName [name],E.Day_Time comingdate,ImageLink from [Event] E where E.Day_Time  > GETDATE()
	union
	select C.performer [name],C.DayTime comingdate,ImageLink from Concert C where C.DayTime > GetDate() 
end

exec upcoming
--=============================================================================================================
Go 
create proc selectCinemas
as
begin
	select * from Cinema C where 0<(select Count(*) from Showtime S where S.cinemaId=C.CinemaId );
end
--=============================================================================================================

Go
create proc selectMovies 
@cinemaId int
as
begin
	select * from Tickets T join Showtime S on T.ID=S.showId AND T.categoryId=1 join Movie M on S.movieId=M.movieId where S.cinemaId=@cinemaId;
end
GO

--exec selectMovies 1

--select * from Showtime
select* from Cart
select*from Cart_Details
select*from Tickets
--=============================================================================================================
GO
create proc getOrderDetails
@user varchar(30)
as
begin
	declare @id int;
	select @id=UserId from [User] where Email=@user;

	select E.eventName [Name],E.cost cost,OD.amount amount ,OD.order_Id orderId,T.ticketId from( ([Orders] O join Order_Details OD On O.orderId=OD.order_Id) join Tickets T on T.ticketId=OD.ticketId ) join [Event] E on categoryId=2 and E.eventId=T.ID   where O.user_id=@id
	Union
	select M.movieName [Name],S.cost cost,OD.amount amount ,OD.order_Id orderId,T.ticketId from( (Orders O join Order_Details OD On O.orderId=OD.order_Id) join Tickets T on T.ticketId=OD.ticketId ) join [Showtime] S on categoryId=1 and S.showId=T.ID join Movie M on M.movieId=S.movieId   where O.user_id=@id
	Union
	select Co.performer [Name],Co.cost cost,OD.amount amount ,OD.order_Id orderId,T.ticketId  from( (Orders O join Order_Details OD On O.orderId=OD.order_Id) join Tickets T on T.ticketId=OD.ticketId ) join [Concert] Co on categoryId=3 and Co.concertId=T.ID   where O.user_id=@id
	 
end
 