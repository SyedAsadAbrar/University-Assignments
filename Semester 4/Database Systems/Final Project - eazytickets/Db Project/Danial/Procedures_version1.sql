
--=============================================================================================================
GO
create procedure logincheck
@email varchar(30), @password varchar(32),
@output  int output
as 
begin
	set @output=-1

	if exists (select * from [User] where Email=@email AND [Password]=@password)
	begin	set @output=0   end

	if exists (select * from [Admin] join [User] U on adminID=UserID where U.Email=@email and U.[Password]=@password)
	BEGIN 
		set @output=3
		print 'admins view'
	END	

	if @output=-1
	begin print 'user not found' end
	else if @output=0
	begin print 'incorrect password'   end
end


--=============================================================================================================
GO
create procedure signup
@email varchar(30),@password varchar(32), @fname varchar(20),@lname varchar(20), @city varchar (20),@phone varchar(32),@gender varchar(10)
as 
begin 
	
 insert into [User](Email,Password,FirstName,LastName,MobileNo,City,Gender)
 values(@email,@password,@fname,@lname,@phone,@gender);	

end 


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

--=============================================================================================================
GO
create procedure AddAdmin
@email varchar(30),@password varchar(32), @fname varchar(20),@lname varchar(20), @city varchar (20),@phone varchar(32),@gender varchar(10)
as 
begin 
	
 insert into [User](Email,Password,FirstName,LastName,MobileNo,City,Gender)
 values(@email,@password,@fname,@lname,@phone,@gender);	
 declare @var int;
 select @var=UserID from [User] where Email=@email;
 insert into [Admin] values(@var);
end 
--=============================================================================================================
GO
create procedure AddMovie
@movieName varchar(30),
@director varchar(20),
@writer varchar(20),
@runningTime time,
@rDate date,
@genere varchar(20),
@mainCast varchar(100)
as
begin
	insert into Movie(movieName,director,writer,runnigTime,ReleaseDate,genere,mainCast)
	values (@movieName,@director,@writer,@runningTime,@rDate,@genere,@mainCast)
end
--=============================================================================================================

--=============================================================================================================
GO
create procedure MovieDelete
@id int
as
begin
	delete from Movie where movieId = @id
end

--=============================================================================================================

GO
create procedure RatingDelete
@userID int, @ticketID int
as
begin
	delete from RatingReview where userID = @userID and ticket_id = @ticketID
end

--=============================================================================================================

GO
create PROCEDURE [UpdateAdminInfo]
@id int,
@NewFName varchar(20),
@NewLName varchar(20),
@NewCity varchar(20),
@NewPhone int,
@NewPassword varchar(30)
as
begin
	Update [User]
	Set [FirstName]=@NewFName,City=@NewCity,[Password]=@NewPassword,MobileNo=@NewPhone
	where UserID=@id
end

--=============================================================================================================
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
		update RatingReview set reveiw = @review where ticket_id = @ticket_id and userID = @user_id
		set @out = 2
	end
	else 
	begin
		insert into RatingReview values (@user_id,@ticket_id,@rating,@review)
		set @out = 1
	end
end
--=============================================================================================================

--Updated Order Placement Procedures
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
	if @cname='Movies'
	begin
		select @id=movieId from Tickets where ticketId=@ticket_id;
		select @available=availableSeats from Showtime where showId=@id
		if (@available-@amount) > 0
		begin
			update Showtime
			set availableSeats=availableSeats-@amount
			where showId=@id

			insert into dbo.[Cart]([user_id]) values(@user_id) 
			select @orderId=orderId from Cart where [user_id]=@user_id
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
		select @available=E.ticketsAvailable from [Event] E where eventId=@id
		if (@available-@amount) > 0
		begin
			update [Event]
			set ticketsAvailable=ticketsAvailable-@amount
			where eventId=@id

			insert into dbo.[Cart]([user_id]) values(@user_id) 
			select @orderId=orderId from Cart where [user_id]=@user_id
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
		select @available=C.availableTickets from Concert C where consertId=@id
		if (@available-@amount) > 0
		begin
			update [Concert]
			set availableTickets=availableTickets-@amount
			where consertId=@id

			insert into dbo.[Cart]([user_id]) values(@user_id) 
			select @orderId=orderId from Cart where [user_id]=@user_id
			insert into dbo.[Cart_Details](order_Id,ticketId,amount) values (@orderId,@ticket_id,@amount) 
		end	
		else 
		begin
			print 'Not enough tickets available!'
		end
	end


end
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

--=============================================================================================================
Go
create procedure [dropItem]
@orderId int,
@ticketId int
as
begin
	declare @amount int
	declare @catId int
	declare @catname VARCHAR(20)
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
		where consertId=@id

		delete from Cart_Details
		where order_Id=@orderId AND ticketId=@ticketId

	end
end
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
--=============================================================================================================

