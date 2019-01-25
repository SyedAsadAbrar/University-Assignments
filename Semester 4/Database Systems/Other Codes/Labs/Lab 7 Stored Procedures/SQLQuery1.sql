--Q1
Create view view1 as
  select OrderDetails.OrderNo as Order_No,sum(OrderDetails.Quantity*Items.Price) as Total_Price
  from OrderDetails, Items
  where OrderDetails.ItemNo=Items.ItemNo
  group by OrderDetails.OrderNo;

select * from view1

--Q2
Create view view2 as
   select Items.ItemNo, Items.Name
   from Items, OrderDetails
   where OrderDetails.ItemNo=Items.ItemNo
   group by Items.ItemNo, Items.Name
   having sum(OrderDetails.Quantity)>20;
   
select * from view2

--Q3
CREATE PROCEDURE [procedure1]
@OrderNo int,
@ItemNo int,
@Quantity int
AS 
  Select @OrderNo=OrderNo from OrderDetails where OrderNo=@OrderNo
  Select @ItemNo=ItemNo from Items where ItemNo=@ItemNo
  Select @Quantity=Quantity from OrderDetails where Quantity=@Quantity
  declare @temp int
  select @temp=[Quantity in Store] from Items where ItemNo=@ItemNo
if(@Quantity<@temp)
  BEGIN 
      Insert into OrderDetails values(@OrderNo, @ItemNo, @Quantity)
      update Items 
      set [Quantity in Store]= [Quantity in Store] - @Quantity 
      where ItemNo = @ItemNo 
END
  else
  BEGIN 
      print 'Only is present which is less than your required quantity'
  END
go

Exec procedure1
5,200,1000

--Q4
CREATE PROCEDURE [CustomerSignup]
@CustNo int,
@CustName varchar(30),
@CustCity varchar(3),
@CustPhone varchar(11),
@Flag int OUTPUT
AS
BEGIN
  IF EXISTS (SELECT* FROM Customers WHERE CustomerNo=@CustNo)
    BEGIN
        SET @Flag=1	
    END

  IF(@CustCity IS NULL)
    BEGIN
        SET @Flag=2
    END

  IF(len(@CustPhone)>6 OR len(@CustPhone)<6)
    BEGIN
        SET @Flag=3
    END

  ELSE
    BEGIN
        SET @Flag=0
        INSERT INTO Customers
        VALUES (@CustNo,@CustName,@CustCity,@CustPhone)
    END	
END
GO


declare @var int
exec CustomerSignup
'05','Asad','Lahore','202000',@var OUTPUT

PRINT @var
SELECT *FROM Customers