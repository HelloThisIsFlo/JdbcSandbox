create table books
(
    id     int auto_increment,
    title  varchar(255) not null,
    author varchar(255) not null,
    constraint books_pk
        primary key (id)
);

create table reviews
(
    id       int auto_increment,
    book_id  int          not null,
    username varchar(255) not null,
    rating   int          not null,
    constraint reviews_pk
        primary key (id),
    constraint reviews_books_id_fk
        foreign key (book_id) references books (id) on delete CASCADE
);

create unique index reviews_book_id_username_uindex
    on reviews (book_id, username);



insert into books (title, author) VALUE ('My Biography', 'Frank');
insert into books (title, author) VALUE ('Moby Dick', 'Sarah');
insert into books (title, author) VALUE ('Java in 2 minutes', 'Patrick');
insert into books (title, author) VALUE ('TDD by example', 'Kent Beck');

insert into reviews (book_id, username, rating) VALUE (
                                                           (select id from books where title like 'Moby%'),
                                                           'internetuser111',
                                                           4
    );
insert into reviews (book_id, username, rating) VALUE (
                                                           (select id from books where author like '%Fra%'),
                                                           'asdfasdf',
                                                           2
    );

SET @mobyDickId = (select id
                   from books
                   where title like '%Moby%');
SET @tddByExampleId = (select id
                       from books
                       where title like '%TDD%');

insert into reviews (book_id, username, rating) VALUE (@mobyDickId, 'superswag', 1);
insert into reviews (book_id, username, rating) VALUE (@mobyDickId, 'lkjwef', 3);
insert into reviews (book_id, username, rating) VALUE (@mobyDickId, 'asdf', 1);
insert into reviews (book_id, username, rating) VALUE (@mobyDickId, 'weroic', 2);
insert into reviews (book_id, username, rating) VALUE (@mobyDickId, 'wscxv', 5);
insert into reviews (book_id, username, rating) VALUE (@mobyDickId, 'asdfasd', 4);

insert into reviews (book_id, username, rating) VALUE (@tddByExampleId, 'superswag', 2);
insert into reviews (book_id, username, rating) VALUE (@tddByExampleId, 'lkjwef', 1);
insert into reviews (book_id, username, rating) VALUE (@tddByExampleId, 'asdf', 4);
insert into reviews (book_id, username, rating) VALUE (@tddByExampleId, 'weroic', 2);
insert into reviews (book_id, username, rating) VALUE (@tddByExampleId, 'otheruser', 2);
insert into reviews (book_id, username, rating) VALUE (@tddByExampleId, 'asdfasd', 5);
