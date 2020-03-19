drop database if exists spotitube;
create database spotitube;
use spotitube;

create table user (
id									int							not null auto_increment,
fullname						varchar(250)		not null,
user							varchar(50)			not null,
password					varchar(50)			not null,
token							varchar(14)			null,

unique(user),
primary key(id)
);

create table performer (
id									int							not null auto_increment,
name 						varchar(100)		not null,

primary key(id),
unique(name)
);

create table track (
id									int							not null auto_increment,
title								varchar(250)		not null,
performer					int							not null,
duration						int							not null,
album							varchar(100)		null,
playcount					int							null,
publicationDate			date 						null,
description				varchar(500)		null,
offlineAvailable			boolean				not null,

primary key(id),
foreign key(id) references performer(id)
);

create table playlist (
id									int 							not null auto_increment,
name							varchar(50)			not null,

primary key(id)
);

create table playlistsong (
playlistid						int							not null,
trackid						int							not null,

primary key(playlistid, trackid),
foreign key(trackid) references track(id) on delete cascade,
foreign key(playlistid) references playlist(id) on delete cascade
);

create table userplaylist (
userid							int							not null,
playlistid						int							not null,
isowner						boolean				not null,

primary key(userid, playlistid),
foreign key(userid) references user(id) on delete cascade,
foreign key (playlistid) references playlist(id) on delete cascade
);



