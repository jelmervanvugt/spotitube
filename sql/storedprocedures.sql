use spotitube;

/* bestaat de user */
delimiter //
drop procedure if exists doesUserExist //
create procedure doesUserExist (
in 		user		 		varchar(50), 
in 		password 	varchar(50)
)
begin

		select 			count(*) as nUsers
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
            
            select *
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

		select p.id, p.name, up.isowner
        from playlist p, userplaylist up, user u
        where u.token = token
        and u.id = up.userid
        and p.id = up.playlistid;
		
end //
delimiter ;

/* returned alles van een track */
delimiter //
drop procedure if exists getTrackFromPlaylist //
create procedure getTrackFromPlaylist (
in 		playlistid		 		int
)
begin

			select songid
            from playlistsong ps
            where ps.playlistid = playlistid;
		
end //
delimiter ;

/* genereert een token */
delimiter //
drop procedure if exists generateToken //
create procedure generateToken (
in 		user		 		varchar(50), 
in 		password 	varchar(50)
)
begin
                    update user u set token = concat(
					cast(floor(rand()*(999-100+1)+100) as char(3)),"-", 
					cast(floor(rand()*(999-100+1)+100) as char(3)), "-", 
					cast(floor(rand()*(999-100+1)+100) as char(3)))
            where u.user = user;
end //
delimiter ;

/* returned de lengte van een playlist */
delimiter //
drop procedure if exists getPlaylistLength //
create procedure getPlaylistLength (
in 		playlistid		 	int
)
begin
       select sum(duration) as playlistlength
       from track t, playlist p, playlistsong ps
       where p.id = playlistid
       and ps.playlistid = playlistid
       and ps.trackid = t.id;
end //
delimiter ;














