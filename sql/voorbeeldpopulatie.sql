use spotitube;

insert into user(fullname, user, password, token) values
("Jelmer van Vugt", "jelmo", "jelmo01", "token1"),
("Jelmer van Vugt2", "jelmo2", "jelmo02", "token2");

insert into performer(id, name) values
(1, "TestPerformer1"),
(2, "TestPerformer2"),
(3, "TestPerformer3"),
(4, "TestPerformer4"),
(5, "TestPerformer5"),
(6, "TestPerformer6");

insert into track(title, performer, duration, album, playcount, publicationDate, description, offlineAvailable) values
("TestTitle1", 1, 1, "TestAlbum1", 11, "2010-10-10", "TestDescription1", true),
("TestTitle2", 2, 2, "TestAlbum2", 22, "2010-10-10", "TestDescription2", true),
("TestTitle3", 3, 3, "TestAlbum3", 33, "2010-10-10", "TestDescription3", true),
("TestTitle4", 4, 4, "TestAlbum4", 44, "2010-10-10", "TestDescription4", true),
("TestTitle5", 5, 5, "TestAlbum5", 55, "2010-10-10", "TestDescription5", true),
("TestTitle6", 6, 6, "TestAlbum6", 66, "2010-10-10", "TestDescription6", true);

insert into playlist(name) values
("Playlist1"),
("Playlist2");

insert into userplaylist(userid, playlistid, isowner) values
(1, 1, true),
(2, 2, true),
(1, 2, false);

insert into playlistsong(playlistid, trackid) values
(1, 1),
(1, 2),
(1, 3),
(2, 4),
(2, 5),
(2, 6);







