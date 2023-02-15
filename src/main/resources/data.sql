insert into Author (name, age) values ('First author from data.sql', 22)
insert into Author (name, age) values ('Second author from data.sql', 26)
insert into Author (name, age) values ('Third author from data.sql', 21)
insert into Author (name, age) values ('Fourth author from data.sql', 29)
insert into Author (name, age) values ('Fifth author from data.sql', 20)

insert into PublishingHouse (address,name) values ('Gorj','Cartea Veche')
insert into PublishingHouse (address,name) values ('Constanta','Editura Veche')

insert into Title (title_name) values ('title 1')
insert into Title (title_name) values ('title 2')

insert into Book (price,publishingHouse_id,publishingYear,title_id) values (23.99, 1, 2022,1)
insert into Book (price,publishingHouse_id,publishingYear,title_id) values (29.99, 2, 2022,2)


insert into Book_Author (books_id,authors_id) values (1,1)
insert into Book_Author (books_id,authors_id) values (1,2)
insert into Book_Author (books_id,authors_id) values (1,3)

insert into Book_Author (books_id,authors_id) values (2,3)
insert into Book_Author (books_id,authors_id) values (2,4)
insert into Book_Author (books_id,authors_id) values (2,5)