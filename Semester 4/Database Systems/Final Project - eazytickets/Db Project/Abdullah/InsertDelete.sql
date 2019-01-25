use Project
--=============================================================================================================
GO
create procedure addCinema
@Name varchar(50),
@location varchar(100),
@ContactNo int 
as
begin
insert into Cinema(Name,Location,contactNo) values
(@Name,@location,@ContactNo)
end
exec addCinema 'CineStar','Township, Lahore',1456789
--=============================================================================================================
GO
create procedure addShowtime
@movieId int,@CinemaId int,@date_time datetime,@seats int,@cost int,@screentype char(2)
as
begin
insert into Showtime(movieId,cinemaId,date_time,availableSeats,cost,ScreeningType) values
(@movieId,@CinemaId,@date_time,@seats,@cost,@screentype)
end
exec addShowtime 1,1,'2018-04-30 17:00:00',500,800,'3D'
--=============================================================================================================
GO
create procedure removeCinema
@CinemaId int,
as
begin
delete from Cinema
where Cinema.CinemaId=@CinemaId
end
--=============================================================================================================
GO
create procedure removeShowtime
@Showid int
as
begin
delete from Showtime
where Showtime.showId=@Showid
end
--=============================================================================================================
GO
create procedure addConcert
@venue varchar(100),@DayTime datetime,@performer varchar(30),@organizer varchar(20),@seats int,@cost int
as
begin
insert into Concert(venue,DayTime,performer,organizers,availableTickets,cost) values
(@venue,@DayTime,@performer,@organizer,@seats,@cost)
end
--=============================================================================================================
GO
create procedure removeConcert
@ConcertId int
as
begin
delete from Concert
where consertId=@ConcertId
end
--=============================================================================================================
GO
create procedure addEvent
@Name varchar(30),@type varchar(20),@organizer varchar(20),@venue varchar(100),@day date,@time time,@tickets int,@cost int
as
begin
insert into [Event](eventName,eventType,organizers,venue,[Day],[Time],ticketsAvailable,cost) values
(@Name,@type,@organizer,@venue,@day,@time,@tickets,@cost)
end
exec addEvent 'Karachi Eat','Food','Pepsico','khi','2018-05-16','17:00:00',1000,400
select* from [Event]
--=============================================================================================================
GO
create procedure removeEvent
@Eventid int
as
begin
delete from [Event]
where eventId=@Eventid
end
exec removeEvent 1
--=============================================================================================================
GO
create procedure ContactMessage
@email varchar(30),@message varchar(200)
as
begin
insert into Contact values
(@email,@message)
end
exec ContactMessage 'abd@gmail.com','Good website but plz reply to Email on time'
