Create Table Customers 

(
	CustomerID int primary key,
	[Name] varchar (20), 
	ShippingAddress varchar (50) ,
	Email varchar (20), 
	ContactNumber int,
	PW varchar(50)
)

Create Table Employees

(
	EmployeeID int primary key,
	Name varchar (20) ,
	EmpAddress varchar (50) ,
	Email varchar (20) ,
	ContactNumber int,
	PW varchar(50)
)


Create Table Supplier

(
	SupplierID int primary key,
	Name varchar (20),
	Rate int,
	Email varchar (20),
	SuppAddress varchar (50)

)

Create Table Products

(
	ProductID int primary key,
	Brand varchar (10) ,
	Name varchar (20), 
	BestBefore date,
	Price int,
	SupplierID int foreign key references Supplier
	ON DELETE SET NULL ON UPDATE CASCADE,
)

Create Table Invoice

(
	InvoiceNo int primary key,
	CustomerID int foreign key references Customers
	ON DELETE SET NULL ON UPDATE CASCADE,
	ProductID int foreign key references Products
	ON DELETE SET NULL ON UPDATE CASCADE,
	SalesDate date,
	TotalAmount int,
	
)

Create Table OrdersPlaced

(
	InvoiceNo int foreign key references Invoice,
	OrderID int primary key,
	ProductID int foreign key references Products
	ON DELETE SET NULL ON UPDATE CASCADE,
	CustomerID int foreign key references Customers
	ON DELETE SET NULL ON UPDATE CASCADE,
	PlacingDate date,
	DeliveryDate date,
	
)

Select * from Customers
Select * from Employees
Select * from Products
Select * from Supplier
Select * from Invoice
Select * from OrdersPlaced

Insert into Customers values (1, 'A', 'House 123 Street ABC, XYZ, 54000', 'a@yahoo.com', 123456, 'abc'); 
Insert into Customers values (2, 'B', 'House 446 Street 56, ABC, 74000', 'b@yahoo.com', 569823, 'cde');
Insert into Customers values (3, 'C', 'House 789 Street DRT,London, 2000', 'c@gmail.com', 235452, 'fgh');
Insert into Customers values (4, 'D', 'House 562, 7 th Avenue,New York, 7000', 'd@gmail.com', 216548, 'ijk');


Insert into Employees values (1, 'E', 'House 123 Street ABC, XYZ, 54000', 'e@yahoo.com', 4351351,'lmn');
Insert into Employees values (2, 'F', 'House 446 Street 56, ABC, 74000', 'f@yahoo.com', 56512165,'opq');
Insert into Employees values (3, 'G', 'House 789 Street DRT, London, 2000', 'g@gmail.com', 654654632,'rst');
Insert into Employees values (4, 'H', 'House 562, 7th Avenue, New York, 7000', 'h@gmail.com', 54546546,'uvw');

Insert into Supplier values (1, 'Aby',5000, 'aby@gmail.com', 'House 123 Street ABC, XYZ, 54000');
Insert into Supplier values (2, 'Xyz', 15000, 'xyz@gmail.com', 'House 446 Street 56, ABC, 74000');
Insert into Supplier values (3, 'Def', 20000, 'def@gmail.com' , 'House 789 Street DRT, London, 2000');
Insert into Supplier values (4 ,'Fgh', 21000, 'fgh@gmail.com', 'House 562, 7 th Avenue, New York, 7000');

Insert into Products values (1, 'L’oreal','Eye Shades', '2018-11-1',1000,1);
Insert into Products values (2, 'Maybelline', 'Face powder','2019-12-5',2000,1);
Insert into Products values (3, 'Lauder', 'Lipstick', '2020-12-15',500,2);
Insert into Products values (4, 'Sephora', 'Perfume', '2019-8-25',700,3);
Insert into Products values (5, 'Mac', 'Nail Polish', '2019-8-25',700,3);



Insert into Invoice values (101, 1, 4, '2017-05-19', 700);
Insert into Invoice values (109, 2, 3, '2017-09-12',500);
Insert into Invoice values (111, 3, 2, '2017-06-25',2000 );
Insert into Invoice values (187, 4, 1, '2017-09-09',1000 );

Insert into OrdersPlaced values (109,1,1, 2, '2017-08-25', '2017-09-25');
Insert into OrdersPlaced values (111,2,4, 3, '2017-07-30', '2017-08-15');
Insert into OrdersPlaced values (101,3,3, 4, '2017-05-17', '2017-06-26');
Insert into OrdersPlaced values (187,4,3, 3, '2017-09-17', '2017-12-25');

		--SIMPLE--

		--1--

Create Procedure DisplaySupplierbyName
	
	@Name varchar (20)
	as
	begin
	Select * from Supplier
	where Name=@Name
	end



		--2--

Create Procedure DisplayOrdersPlaced
as
	
	begin
	Select * from OrdersPlaced
	end


		--3--	

				
Create Procedure DisplayAllSuppliers
as
	begin
	Select * from Products
	end
		

	--4--

Create Procedure DisplayCustomers
as
	begin
	Select * from Customers
	end
	

	Execute DisplayCustomers

		--5--
Create procedure DisplayAllEmployees
as
	begin
	Select * from Employees
	end

	Execute DisplayAllEmployees
		--6--

Create Procedure OrdersDelivered
as
	begin
	Select * from OrdersPlaced
	end

	Execute OrdersDelivered

		--7--

Create procedure DisplayAllCustomers
as
	begin
	Select * from Customers
	end

	Execute DisplayAllCustomers

		--8--

	
Create Procedure DisplayAllProducts
as
	begin
	Select * from Products
	end



	---COMPLEX---

	--1--
CREATE procedure [dbo].[Login]
@email varchar(20),
@password varchar(50),
@status int=-1 output
as 
begin

  
        SELECT @status = [CustomerID] from [Customers] where Email like @email and PW like @password;
   return @status

end


DECLARE @ReturnValue INT
EXEC @ReturnValue = Login @email='b@yahoo.com',@password='cde'
SELECT @ReturnValue



			--2--

CREATE procedure [dbo].[InsertOrder]
@custID int,
@productID int,
@status int=-1 output
as 
begin
declare @invoiceno int, @price int, @orderID int
set @status=1

select @invoiceno=count(*) from [Invoice] 
set @invoiceno=@invoiceno+1

select @orderID=count(*) from [OrdersPlaced] 
set @orderID=@orderID+1

select @price= price from Products where ProductID=@productID

insert into Invoice values (@invoiceno,@custID,@productID,'2017-4-12',@price)
insert into OrdersPlaced values (@invoiceno,@orderID,@productID,@custID,'2017-4-12','')

return @status
end


--3--


CREATE procedure [dbo].[signup]
@name varchar(20),
@address varchar(50),
@phone int,
@pw varchar(50),
@email varchar(20),
@status int=-1 output
as 
begin

if exists(select * from [Customers] where Email = @email)
begin
     set @status=0;
	 print 'sorry, this email already exists. Try another'
end
else
begin
declare @custID int

select @custID=count(*) from [Customers] 
set @custID=@custID+1

insert into Customers values (@custID,@name,@address,@email,@phone,@pw)
 SELECT @status = [CustomerID] from [Customers] where CustomerID=@custID
end
return @status
end


--4--

CREATE procedure [dbo].[AddProduct]
@name varchar(20),
@brand varchar(10),
@expirydate date,
@price int,
@supplierID int
as 
begin
declare @productID int
select @productID=count(*) from [Products] 
set @productID=@productID+1

insert into Products values (@productID,@brand,@name,@expirydate,@price,@supplierID)
end



--5--
Create Procedure DeletebyProductID
	
	@ID int
	as
	begin
	Delete from Products
	where ProductID=@ID
	end

Execute
	 DeletebyProductID @ID=1



	 --6--

	 Create Procedure DisplayProductsbyBrandname
	
	@Brand varchar (50)
	as
	begin
	Select * from Products
	where Brand=@Brand
	end

Execute
	DisplayProductsbyDisplayProductsbyBrandname @Brand='Maybelline'

