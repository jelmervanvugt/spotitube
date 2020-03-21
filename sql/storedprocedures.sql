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

/* delete een playlist */
delimiter //
drop procedure if exists deletePlaylist //
create procedure deletePlaylist (
in		playlistid			int
)
begin
		delete 
        from playlist p
        where id = playlistid;
end //
delimiter ;

/* kijkt op basis van token en playistid of de user owner van de playlist is*/
delimiter //
drop procedure if exists doesUserOwnPlaylist //
create procedure doesUserOwnPlaylist (
in 		token		 		varchar(14),
in		playlistid			int
)
begin
		 select up.isOwner
		from user u, userplaylist up
         where u.token = token
         and u.id = up.userid
         and up.playlistid = playlistid;
end //
delimiter ;

/* voegt een playlist toe */
delimiter //
drop procedure if exists addPlaylist //
create procedure addPlaylist (
in 		token		 						varchar(14),
in		playlistname					varchar(50)
)
begin
			set @userid = (select id from user u where u.token = token);
            insert into playlist(name) values
            (playlistname);
            set @playlistid = (select id from playlist order by id desc limit 1);
			insert into userplaylist(userid, playlistid, isowner)
            values(@userid, @playlistid, true);
end //
delimiter ;

/* wijzigt de naam van een playlist */
delimiter //
drop procedure if exists editPlaylistName //
create procedure editPlaylistName (
in 		playlistid		 					int,
in		playlistname					varchar(50)
)
begin
			update playlist
            set name = playlistname
            where id = playlistid;
end //
delimiter ;

/* returned alle tracks van de opgegeven playlist */
delimiter //
drop procedure if exists getTracksFromPlaylist //
create procedure getTracksFromPlaylist (
in 		playlistid		 					int
)
begin
			select t.id, t.title, p.name as performer, t.duration, t.album, t.playcount, t.publicationDate, t.description, t.offlineAvailable
            from track t, playlistsong ps, performer p
            where ps.playlistid = playlistid
            and ps.trackid = t.id
            and t.performer = p.id;
end //
delimiter ;

/* delete track van playlist */
delimiter //
drop procedure if exists deleteTrackFromPlaylist //
create procedure deleteTrackFromPlaylist (
in 		playlistid		 					int,
in 		trackid		 					int
)
begin
			delete from playlistsong ps
            where ps.playlistid = playlistid
            and ps.trackid = trackid;
end //
delimiter ;













