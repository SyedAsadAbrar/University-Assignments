create database Lab11

use Lab11

/****** Object:  Table [dbo].[Items]    Script Date: 02/17/2017 13:04:03 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[Items](
	[ItemNo] [int] NOT NULL,
	[Name] [varchar](10) NULL,
	[Price] [int] NULL,
	[Quantity in Store] [int] NULL,
PRIMARY KEY CLUSTERED 
(
	[ItemNo] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
SET ANSI_PADDING OFF
GO
INSERT [dbo].[Items] ([ItemNo], [Name], [Price], [Quantity in Store]) VALUES (100, N'A', 1000, 100)
INSERT [dbo].[Items] ([ItemNo], [Name], [Price], [Quantity in Store]) VALUES (200, N'B', 2000, 50)
INSERT [dbo].[Items] ([ItemNo], [Name], [Price], [Quantity in Store]) VALUES (300, N'C', 3000, 60)
INSERT [dbo].[Items] ([ItemNo], [Name], [Price], [Quantity in Store]) VALUES (400, N'D', 6000, 400)
/****** Object:  Table [dbo].[Courses]    Script Date: 02/17/2017 13:04:02 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[Customers](
	[CustomerNo] [varchar](2) NOT NULL,
	[Name] [varchar](30) NULL,
	[City] [varchar](3) NULL,
	[Phone] [varchar](11) NULL,
PRIMARY KEY CLUSTERED 
(
	[CustomerNo] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
SET ANSI_PADDING OFF
GO
INSERT [dbo].[Customers] ([CustomerNo], [Name], [City], [Phone]) VALUES (N'C1', N'AHMED ALI', N'LHR', N'111111')
INSERT [dbo].[Customers] ([CustomerNo], [Name], [City], [Phone]) VALUES (N'C2', N'ALI', N'LHR', N'222222')
INSERT [dbo].[Customers] ([CustomerNo], [Name], [City], [Phone]) VALUES (N'C3', N'AYESHA', N'LHR', N'333333')
INSERT [dbo].[Customers] ([CustomerNo], [Name], [City], [Phone]) VALUES (N'C4', N'BILAL', N'KHI', N'444444')
INSERT [dbo].[Customers] ([CustomerNo], [Name], [City], [Phone]) VALUES (N'C5', N'SADAF', N'KHI', N'555555')
INSERT [dbo].[Customers] ([CustomerNo], [Name], [City], [Phone]) VALUES (N'C6', N'FARAH', N'ISL', NULL)
/****** Object:  Table [dbo].[Order]    Script Date: 02/17/2017 13:04:03 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[Order](
	[OrderNo] [int] NOT NULL,
	[CustomerNo] [varchar](2) NULL,
	[Date] [date] NULL,
	[Total_Items_Ordered] [int] NULL,
PRIMARY KEY CLUSTERED 
(
	[OrderNo] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
SET ANSI_PADDING OFF
GO
INSERT [dbo].[Order] ([OrderNo], [CustomerNo], [Date], [Total_Items_Ordered]) VALUES (1, N'C1', CAST(0x7F360B00 AS Date), 30)
INSERT [dbo].[Order] ([OrderNo], [CustomerNo], [Date], [Total_Items_Ordered]) VALUES (2, N'C3', CAST(0x2A3C0B00 AS Date), 5)
INSERT [dbo].[Order] ([OrderNo], [CustomerNo], [Date], [Total_Items_Ordered]) VALUES (3, N'C3', CAST(0x493C0B00 AS Date), 20)
INSERT [dbo].[Order] ([OrderNo], [CustomerNo], [Date], [Total_Items_Ordered]) VALUES (4, N'C4', CAST(0x4A3C0B00 AS Date), 15)
INSERT [dbo].[Order] ([OrderNo], [CustomerNo], [Date], [Total_Items_Ordered]) VALUES (5, N'C4', GETDATE(), 15)
INSERT [dbo].[Order] ([OrderNo], [CustomerNo], [Date], [Total_Items_Ordered]) VALUES (6, N'C5', GETDATE(), 45)
INSERT [dbo].[Order] ([OrderNo], [CustomerNo], [Date], [Total_Items_Ordered]) VALUES (7, N'C5', '2017-4-12', 45)
/****** Object:  Table [dbo].[OrderDetails]    Script Date: 02/17/2017 13:04:03 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[OrderDetails](
	[OrderNo] [int] NOT NULL,
	[ItemNo] [int] NOT NULL,
	[Quantity] [int] NULL,
PRIMARY KEY CLUSTERED 
(
	[OrderNo] ASC,
	[ItemNo] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
INSERT [dbo].[OrderDetails] ([OrderNo], [ItemNo], [Quantity]) VALUES (1, 200, 20)
INSERT [dbo].[OrderDetails] ([OrderNo], [ItemNo], [Quantity]) VALUES (1, 400, 10)
INSERT [dbo].[OrderDetails] ([OrderNo], [ItemNo], [Quantity]) VALUES (2, 200, 5)
INSERT [dbo].[OrderDetails] ([OrderNo], [ItemNo], [Quantity]) VALUES (3, 200, 60)
INSERT [dbo].[OrderDetails] ([OrderNo], [ItemNo], [Quantity]) VALUES (3, 200, 60)
INSERT [dbo].[OrderDetails] ([OrderNo], [ItemNo], [Quantity]) VALUES (5, 200, 15)
INSERT [dbo].[OrderDetails] ([OrderNo], [ItemNo], [Quantity]) VALUES (6, 300, 45)

GO
/****** Object:  ForeignKey [FK__OrderDeta__ItemN__4316F928]    Script Date: 02/03/2017 13:55:38 ******/
ALTER TABLE [dbo].[OrderDetails]  WITH CHECK ADD FOREIGN KEY([ItemNo])
REFERENCES [dbo].[Items] ([ItemNo])
on update cascade on delete cascade
GO
/****** Object:  ForeignKey [FK__OrderDeta__Order__4222D4EF]    Script Date: 02/03/2017 13:55:38 ******/
ALTER TABLE [dbo].[OrderDetails]  WITH CHECK ADD FOREIGN KEY([OrderNo])
REFERENCES [dbo].[Order] ([OrderNo])
on update cascade on delete cascade
GO

--Q1
create trigger delitem on Items
instead of delete
as
	declare @temp int
	select @temp=ItemNo from deleted
begin
	if exists(select count(OrderNo) from OrderDetails, deleted where OrderDetails.ItemNo=deleted.ItemNo having count(OrderNo)<1)
	begin
		delete from Items where ItemNo=@temp
	end
	else
	begin
		print 'Item cannot be deleted.'
	end
end

--drop trigger delitem

delete from Items
where ItemNo=100

select * 
from OrderDetails, Items, [Order]
where OrderDetails.OrderNo=[Order].OrderNo AND Items.ItemNo=OrderDetails.ItemNo

select * from OrderDetails

--Q2
create trigger increasePrice on Items
instead of update
as
	declare @id int
	select @id=ItemNo from inserted
	declare @new int
	select @new=Price from inserted
	declare @old int
	select @old=Price from deleted
begin
	if (@new>@old)
	begin
		Print 'Error, price cannot be increased.'
	end
	else
	begin
		update Items
		set Price=@new
		where ItemNo=@id
	end
end

create procedure updatePrice
	@id int,
	@price int
as
begin
	update Items
	set Price=@price
	where ItemNo=@id	
end

--drop trigger increasePrice

exec updatePrice 100, 2000

--Q3
--assuming that quantity of items ordered does not matter, only number of orders does

create procedure CustomerOfTheMonth
	@date date,
	@retval varchar(30)
as
	declare @month int
	select @month=month(@date)
	declare @year int
	select @year=year(@date)
begin
	set @retval=(select top 1 Name as 'Customer of the Month'
	from Customers C, [Order]
	where C.CustomerNo=[Order].CustomerNo AND @month=month([Order].Date) AND @year=year([Order].Date)
	group by Name
	order by count(Total_Items_Ordered) desc)
	print 'Customer of the month: '
	print @retval
end

--drop procedure CustomerOfTheMonth

declare @helo date
declare @name varchar(30)
set @helo=GETDATE()
exec CustomerOfTheMonth @helo, @name

select *
from [Order], Customers
where Customers.CustomerNo=[Order].CustomerNo

--Q4
--only 1 item is eligible for a discount at a time

create procedure discount
as
begin
	update Items
	set price=price/2
	where ItemNo=(select top 1 Items.ItemNo
	from Items left outer join OrderDetails on Items.ItemNo=OrderDetails.ItemNo
	group by Items.ItemNo
	order by sum(Quantity) asc)
end

--drop procedure discount

select *from items
exec discount

select * 
from OrderDetails, Items, [Order]
where OrderDetails.OrderNo=[Order].OrderNo AND Items.ItemNo=OrderDetails.ItemNo

--Q5
create procedure totalsaleofmonth
	@date date,
	@retval int
as
begin
	set @retval=(select sum(Quantity*Price)
	from [Order], OrderDetails, Items
	where [order].OrderNo=OrderDetails.OrderNo AND OrderDetails.ItemNo=Items.ItemNo AND month(Date)=month(@date) AND year(Date)=year(@date))

	print 'Total sale in month'
	print @retval
end

--drop procedure totalsaleofmonth

declare @ret int
declare @helo date
set @helo=GETDATE()
exec totalsaleofmonth @helo, @ret

--only including those items which have sold at least one time

create procedure minsale
	@date date,
	@name varchar(10)
as
begin
	set @name=(select top 1 Name
	from Items, [Order], OrderDetails
	where Items.ItemNo=OrderDetails.ItemNo AND [Order].OrderNo=OrderDetails.OrderNo AND month(@date)=month(Date)
	group by Items.Name
	order by sum(Quantity) asc)
	print 'Item with the minimum sale:'
	print @name
end

--drop procedure minsale

declare @ret varchar(10)
declare @helo date
set @helo=GETDATE()
exec minsale @helo,@ret 

select * 
from OrderDetails, Items, [Order]
where OrderDetails.OrderNo=[Order].OrderNo AND Items.ItemNo=OrderDetails.ItemNo

create procedure maxsale
	@date date,
	@name varchar(10)
as
begin
	set @name=(select top 1 Name
	from Items, [Order], OrderDetails
	where Items.ItemNo=OrderDetails.ItemNo AND [Order].OrderNo=OrderDetails.OrderNo AND month(@date)=month(Date)
	group by Items.Name
	order by sum(Quantity) desc)
	print 'Item with the maximum sale:'
	print @name
end

--drop procedure maxsale

create procedure final
	@date date,
	@totalsale int,
	@minname varchar(10),
	@maxname varchar(10),
	@custname varchar(30)
as
begin
	exec totalsaleofmonth @date, @totalsale;
	exec minsale @date, @minname;
	exec maxsale @date, @maxname;
	exec CustomerOfTheMonth @date, @custname;
end

--drop procedure final

declare @ret1 int
declare @ret2 varchar(10)
declare @ret3 varchar(10)
declare @ret4 varchar(30)
declare @helo date
set @helo=GETDATE()
exec final @helo, @ret1, @ret2, @ret3, @ret4