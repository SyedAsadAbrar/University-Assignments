create procedure SearchStudentByName
@name varchar(40),
@value int OUTPUT
/*
GOOD PRACTICE: write the execution statment of procedure as comment in procedure
Execute  SearchStudentByName  'Ali'
*/
as
begin    
	set @value=0
	select * From Students 
	where StudentName like '%'+@name+'%' 
	set @value=(select count(*)
	from Students where StudentName like '%'+@name+'%')
end

--drop procedure SearchStudentByName