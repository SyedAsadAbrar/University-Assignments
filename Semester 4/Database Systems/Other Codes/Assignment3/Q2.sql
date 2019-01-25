/*-------------------Q2 (A)-------------------*/

create table Team
(
    TeamNum int not null primary key,
    TeamName varchar(30) not null UNIQUE,
    City varchar(30) not null,
    Manager varchar(30) not null
)

/*Since we assume that "Coach name is unique only within a team (and we assume that a team cannot have two coaches of the 
same name)." so there is no need for CoachName to be unique but it can be a part of primary key as a team can have multiple coaches
but this way, a team wouldn't have two coaches with the same name hence fulfilling the condition of Coach name to be unique
within a team.*/
create table Coach
(
    TeamNum int not null references Team(TeamNum),
    CoachName varchar(30) not null UNIQUE,
    Address varchar(30) not null,
    primary key(TeamNum,CoachName)
)

/*Same logic for Primary key as above, there can be multiple coaches of a team.*/
create table WorkExperience 
(
    TeamNum int not null references Team(TeamNum),
	CoachName varchar(30) not null references Coach(CoachName),
	ExperienceType varchar(30) not null,
	YearsExperience int not null check(YearsExperience>=0),
	primary key(TeamNum,CoachName)
)

create table Player
(
    PlayerNum int not null primary key,
    PlayerName varchar(30) not null,
    Age int not null check(Age>16 AND Age <99)
)

/*All the teams a player has played for and all players a team has are considered so alone Player or Team cannot be primary key.*/
create table Affiliation
(
    PlayerNum int not null references Player(PlayerNum),
    TeamNum int not null references Team(TeamNum),
    Years int not null check(Years>0 AND Years <99),
    BattingAvg decimal not null,
    primary key(PlayerNum,TeamNum)
)

/*-------------------Q2 (B)-------------------*/

--Q1
Select Player.PlayerName
from Player
where Age<18

--Q2
Select CoachName
from WorkExperience
where ExperienceType='College Coach' AND YearsExperience>=5 AND YearsExperience=<10

--Q3
Select CoachName, sum(YearsExperience)
from WorkExperience
where TeamNum=23
group by CoachName

--Q4
Select Player.PlayerName
from Player join Affiliation on Player.PlayerNum=Affiliation.PlayerNum join Team on Team.TeamNum=Affiliation.TeamNum
where Team.TeamName='Dodgers' AND Affiliation.Years>=5

--Q5
Select WorkExperience.CoachName,sum(WorkExperience.YearsExperience)
from WorkExperience,Team
where WorkExperience.TeamNum=Team.TeamNum AND TeamName='Dodgers'
group by WorkExperience.CoachName
having sum(WorkExperience.YearsExperience)>8

--Q6
Select Player.PlayerName
from Player
where Player.Age=(select min(Player.Age)
                  from Player)
                  
--Q7
Select Player.PlayerNum,Player.PlayerName
from Player, Affiliation
where Player.PlayerNum=Affiliation.Playernum
group by Player.PlayerNum,Player.PlayerName
having count(Affiliation.TeamNum)>2

--Q8
Select Player.PlayerNum,Player.PlayerName
from Player left join Affiliation on Player.PlayerNum=Affiliation.Playernum
where Affiliation.TeamNum is NULL

--Q9
Select Player.PlayerNum,Player.PlayerName,count(Affiliation.TeamNum)
from Player, Affiliation
where Player.PlayerNum=Affiliation.Playernum
group by Player.PlayerNum,Player.PlayerName

--Q10
Select Affiliation.TeamNum, Team.TeamName, avg(Affiliation.BattingAvg)
from Affiliation, Team
where Affiliation.TeamNum=Team.TeamNum
group by Affiliation.TeamNum, Team.TeamName