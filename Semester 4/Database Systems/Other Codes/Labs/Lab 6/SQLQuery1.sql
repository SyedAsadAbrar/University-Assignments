--Q1
select Users.UserName, count(Tweets.TweetID)
from Users left join Tweets on Users.UserName=Tweets.UserName
group by Users.UserName

--Q2
select max(Age) as 'Maximum Age',min(Age) as 'Minimum Age',avg(Age) as 'Average Age',stdev(Age) as 'Standard Deviation of Age'
from Users

--Q3
create view Followers as select FollowedUserName ,count(FollowedUserName) as Number_of_Followers
from Following
group by FollowedUserName;

select FollowedUserName
from Followers
where Number_of_Followers=(select max(Number_of_Followers)
                           from Followers)
                           
--Q4
create view Followers as select FollowedUserName ,count(FollowedUserName) as Number_of_Followers
from Following
group by FollowedUserName;

select FollowedUserName
from Followers
where Number_of_Followers=(select max(Number_of_Followers)
                           from Followers
                           where Number_of_Followers<(select max(Number_of_Followers)
                           from Followers))
                           
--Q5
select UserName, count(TweetID)
from Tweets
group by UserName
having count(TweetID)>1

--Q6
select Users.Country,Tweets.TweetID,count(Text)
from Tweets,Users
where Tweets.UserName=Users.UserName
group by Users.Country,Tweets.TweetID

--Q8
select FollowerUserName
from Following

except

select FollowedUserName
from Following

--Q9
select FollowerUserName
from Following
where not exists(
select FollowedUserName
from Following)