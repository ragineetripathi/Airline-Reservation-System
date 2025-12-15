create database airlinemanagementsystem;

use airlinemanagementsystem;

create table login(username varchar(20), password varchar(20));

-- insert into login values("admin", "admin");

ALTER TABLE login ADD role VARCHAR(10) DEFAULT 'user';

UPDATE login SET role = 'admin' WHERE username = 'admin';
INSERT INTO login (username, password, role) VALUES ('newuser', 'newpass', 'user');


delete from login;
select * from login;
select * from passenger;
show tables;
create table passenger ( name varchar(20), nationality varchar(20), phone varchar(15), address varchar(50), aadhar varchar(20), gender varchar(20));
create table flight(f_code varchar(20), f_name varchar(20), source varchar(40), destination varchar(40));

desc flight;

insert into flight values("1001", "AI-1212", "Delhi", "Mumbai");
insert into flight values("1002", "AI-1453", "Delhi", "Goa");
insert into flight values("1003", "AI-1112", "Mumbai", "Chennai");
insert into flight values("1004", "AI-3222", "Delhi", "Amritsar");
insert into flight values("1005", "AI-1212", "Delhi", "Ayodhya");
insert into flight values("1006", "AI-1106", "Mumbai", "Delhi");
insert into flight values("1007", "AI-1107", "Delhi", "Chennai");
insert into flight values("1008", "AI-1108", "Chennai", "Delhi");
insert into flight values("1009", "AI-1109", "Hyderabad", "Mumbai");
insert into flight values("1010", "AI-1110", "Delhi", "Hyderabad");
insert into flight values("1011", "AI-1111", "Kolkata", "Delhi");
insert into flight values("1012", "AI-1112", "Mumbai", "Kolkata");
insert into flight values("1013", "AI-1113", "Bangalore", "Delhi");
insert into flight values("1014", "AI-1114", "Delhi", "Bangalore");
insert into flight values("1015", "AI-1115", "Delhi", "Lucknow");
insert into flight values("1016", "AI-1116", "Lucknow", "Mumbai");
insert into flight values("1017", "AI-1117", "Goa", "Delhi");
insert into flight values("1018", "AI-1118", "Mumbai", "Goa");
insert into flight values("1019", "AI-1119", "Delhi", "Pune");
insert into flight values("1020", "AI-1120", "Pune", "Delhi");

delete from flight where f_code = "1008";

select * from flight;

create table reservation(PNR varchar(15), TICKET varchar(20), aadhar varchar(20), name varchar(20), nationality varchar(30), flightname varchar(15), flightcode varchar(20), src varchar(30), des varchar(30), ddate varchar(30));

desc reservation;

select * from reservation;

create table cancel(pnr varchar(20), name varchar(40), cancelno varchar(20), fcode varchar(20), ddate varchar(30));


