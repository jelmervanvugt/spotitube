use spotitube;

drop procedure if exists login;
create procedure login(in username varchar(50), in password varchar(50))
begin

select * from user where username = username and password = password;

end;