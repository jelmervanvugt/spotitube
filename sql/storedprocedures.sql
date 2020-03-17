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

/* returned playlist id, name & owner */
delimiter //
drop procedure if exists getAllPlaylists //
create procedure getAllPlaylists (
in 		token		 		varchar(14)
)
begin

			select 		p.id, p.name, up.owner
            from			playlist p, userplaylist up, user u
            where		user.token = token
            and				user.id = up.userid
            and				p.id = up.playlistid;
		
end //
delimiter ;

/* returned alles van een track */
delimiter //
drop procedure if exists getTrackFromPlaylist //
create procedure getTrackFromPlaylist (
in 		playlistid		 		int
)
begin

			select  *
            from track t, playlist p
            where  t.id = p.trackid;
		
end //
delimiter ;








