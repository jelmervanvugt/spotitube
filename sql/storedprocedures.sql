use spotitube;

/* bestaat de user */
delimiter //
drop procedure if exists doesUserExist //
create procedure doesUserExist (
in 		user		 		varchar(50), 
in 		password 	varchar(50)
)
begin

		select 			count(*) 
		from 				user u 
		where 			u.user = user
		and 				u.password = password;
        
end //
delimiter ;

/* return de fullname van user inc. token */
delimiter //
drop procedure if exists getUser //
create procedure getUser (
in 		user		 		varchar(50), 
in 		password 	varchar(50)
)
begin

			update user u set token = concat(
            cast(floor(rand()*(999-100+1)+100) as char(3)),"-", 
            cast(floor(rand()*(999-100+1)+100) as char(3)), "-", 
            cast(floor(rand()*(999-100+1)+100) as char(3)))
            where u.user = user;
            
            select token, fullname 
            from user u 
            where u.user = user
            and u.password = password;
		
end //
delimiter ;






