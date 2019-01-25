/*-------------------Q1 (A)-------------------*/

create table Users
(
    user_id int not null primary key,
    user_name varchar(30) not null UNIQUE,
    country varchar(30) not null
)

create table Developer
(
    developer_id int not null primary key,
    developer_name varchar(30) not null,
    age int not null check(age>=13 AND age<=99), 
    country varchar(30) not null
)

create table App
(
    app_id int not null primary key,
	app_name varchar(30) not null,
	developer_id int not null FOREIGN KEY references Developer(developer_id),
	cost decimal not null,
    category varchar(30) not null
)

create table Downloads
(
    app_id int not null references App(app_id),
    user_id int not null references Users(user_id),
    rating int not null check(rating>=1 AND rating <=5), 
    review varchar(100),
    PRIMARY KEY(app_id,user_id)
)

/*-------------------Q1 (B)-------------------*/

--Q1
select developer_name as 'Pakistani Developers under the age of 18'
from Developer
where country='Pakistan' AND age<18

--Q2
select app_name
from App, Developer
where App.developer_id=Developer.developer_id AND developer_name='Ali-Shah'

--Q3
select Users.user_id
from Users join Downloads on Users.user_id=Downloads.user_id join App on Downloads.app_id=App.app_id
where category='kids'

--Q4
(select Users.user_name, Users.user_id
from Users join Downloads on Downloads.user_id=Users.user_id join App on App.app_id=Downloads.app_id
where category='Productivity')

except

(select Users.user_name, Users.user_id
from Users join Downloads on Downloads.user_id=Users.user_id join App on App.app_id=Downloads.app_id
where category='Entertainment')

--Q5
(select Downloads.user_id
 from Downloads join App on Downloads.app_id=App.app_id
 where App.app_name='The Zone')

intersect

(select Downloads.user_id
 from Downloads join App on Downloads.app_id=App.app_id
 where App.app_name='Fighters')
 
--Q6
select Downloads.user_id
from Downloads join App on Downloads.app_id=App.app_id join Developer on App.developer_id=Developer.developer_id
where Developer.developer_name='Ahmed'

--Q7
Select Downloads.user_id
from Downloads,App,Developer
where Downloads.app_id=App.app_id AND App.developer_id=Developer.developer_id AND Developer.developer_name='Ali-Shah'
group by Downloads.user_id
having count(Downloads.app_id)=(Select count(App.app_id)
from App, Developer
where App.developer_id=Developer.developer_id AND Developer.developer_name='Ali-Shah')

--Q8
Select Downloads.app_id as 'App ID',App.app_name as 'App Name'
from Downloads,App
where App.app_id=Downloads.app_id
Group by Downloads.app_id,App.app_name
Having count(Downloads.user_id)>=2

--Q9
select App.app_id, App.app_name
from App, Downloads
where Downloads.rating=(
select max(Downloads.rating)
from App, Downloads
where App.app_id=Downloads.app_id) AND App.app_id=Downloads.app_id

--Q10
select App.app_id, App.app_name
from App
where App.cost=(
select max(App.cost)
from App)

--Q11
select App.app_id, App.app_name
from App, Developer
where App.cost=(
  select max(App.cost)
  from App, Developer
  where Developer.developer_name='Reeta') AND App.developer_id=Developer.developer_id

--Q12
select App.app_id, App.app_name, Developer.developer_name
from App, Developer
where App.cost=0 AND App.developer_id=Developer.developer_id

--Q13
select App.app_id,avg(Downloads.rating) as avg_rating
from App, Downloads
where App.cost=0 AND App.app_id=Downloads.app_id
group by App.app_id
having avg(Downloads.rating)>3

--Q14
select Developer.developer_id,Developer.developer_name, count(App.app_id)
from Developer left join App on Developer.developer_id=App.developer_id
group by Developer.developer_id,Developer.developer_name

--Q15
Select Developer.developer_name, Developer.developer_id,sum(App.cost) as total_income
from Developer join App on Developer.developer_id=App.developer_id join Downloads on Downloads.app_id=App.app_id
Group by Developer.developer_name, Developer.developer_id

--Q16
Select Developer.country,sum(App.cost) as total_income
from Developer join App on Developer.developer_id=App.developer_id join Downloads on Downloads.app_id=App.app_id
Group by Developer.country

--Q17  INCOMPLETE
select A.app_name,A.app_id
from App A
where A.cost= ANY(select max(App.cost)
from App
group by App.category
having A.category=App.category)

--Q18
select Developer.developer_name,Developer.developer_id,count(App.app_id) as num_of_apps
from Developer left join App on Developer.developer_id=App.developer_id
group by Developer.developer_name,Developer.developer_id

--Q19
select Developer.developer_name,Developer.developer_id
from Developer, App
where Developer.developer_id=App.developer_id
group by Developer.developer_name,Developer.developer_id
having count(App.app_id)=3

--Q20
select Developer.developer_name, App.app_name
from Developer left join App on Developer.developer_id=App.developer_id