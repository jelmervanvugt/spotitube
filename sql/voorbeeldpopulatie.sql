use spotitube;

select * from playlist;

insert into user(fullname, user, password, token) values
("Jelmer van Vugt", "jelmo", "jelmo01", "1234-1234-1234");

insert into performer(id, name) values
(1, "TestPerformer1"),
(2, "TestPerformer2"),
(3, "TestPerformer3"),
(4, "TestPerformer4"),
(5, "TestPerformer5");

insert into track(title, performer, duration, album, playcount, publicationDate, description, offlineAvailable) values
("TestTitle1", 1, 1, "TestAlbum1", 11, "2010-10-10", "TestDescription1", true),
("TestTitle2", 2, 2, "TestAlbum2", 22, "2010-10-10", "TestDescription2", true),
("TestTitle3", 3, 3, "TestAlbum3", 33, "2010-10-10", "TestDescription3", true),
("TestTitle4", 4, 4, "TestAlbum4", 44, "2010-10-10", "TestDescription4", true),
("TestTitle5", 5, 5, "TestAlbum4", 55, "2010-10-10", "TestDescription5", true);

insert into playlist(name, trackid) values
("Playlist1", 1),
("Playlist1", 1),
("Playlist1", 1),
