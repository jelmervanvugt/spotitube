drop database if exists spotitube;
create database spotitube;
use spotitube;

create table user (
id									int							not null auto_increment,
fullname						varchar(250)		not null,
user							varchar(20)			not null,
password					varchar(50)			not null,
token							varchar(15)			null,

primary key(id)
);
create table album (
id									int							not null auto_increment,
name							varchar(250)		not null,

primary key(id)
);

create table track (
id									int							not null auto_increment,
title								varchar(250)		not null,
performer					varchar(100)		not null,
duration						int							not null,
album							int							null,
playcount					int							null,
publicationDate			date 						null,
description				varchar(500)		null,
offlineAvailable			boolean				not null,

primary key(id),
foreign key(album) references album(id)
);

create table playlist (
id									int 							not null auto_increment,
name							varchar(50)			not null,
owner						int 							not null,
trackid						int							null,

primary key(id),
foreign key(owner) references user(id),
foreign key(trackid) references track(id)
);



