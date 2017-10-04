create table compact_discs (
	id int primary key identity,
	title varchar (50),
	artist varchar(30),
	tracks int,
	price money);

CREATE TABLE tracks (
	id int primary key identity,
	cd_id int not null,
    title varchar(50),
    FOREIGN KEY (cd_id) REFERENCES compact_discs(id)
                    
);


insert into compact_discs values('Is This It','The Strokes',11,13.99);
insert into compact_discs values('Just Enough Education to Perform','Stereophonics',11,10.99);
insert into compact_discs values('Parachutes','Coldplay',10,11.99);
insert into compact_discs values('White Ladder','David Gray',10,9.99);
insert into compact_discs values('Greatest Hits','Penelope',14,14.99);
insert into compact_discs values('Echo Park','Feeder',12,13.99);
insert into compact_discs values('Mezzanine','Massive Attack',11,12.99);
insert into compact_discs values('Spice World','Spice Girls',11,4.99);



insert into tracks values (SCOPE_IDENTITY(), 'Mama');
insert into tracks values (SCOPE_IDENTITY(), 'Wannabe');
insert into tracks values (SCOPE_IDENTITY(), 'Spice up your life');

