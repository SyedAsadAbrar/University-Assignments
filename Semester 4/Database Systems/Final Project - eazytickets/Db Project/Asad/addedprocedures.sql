GO
create procedure AddAdmin
@fname varchar(20),@lname varchar(20), @email varchar(30),@phone varchar(32),@city varchar (20),@gender varchar(10),@password varchar(32),
@output int OUTPUT
as 
begin 
	
 exec signup @fname, @lname, @email, @phone, @city, @gender, @password, @output
 declare @var int;
 select @var=UserID from [User] where Email=@email;
 insert into [Admin] values(@var);
end