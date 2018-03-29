drop table if exists users;
create table if not exists users (
  id int not null primary key auto_increment,
  username varchar(100),
  password varchar(100),
  status int
);

insert into users (username, password, status) values
('michale', '123321', 1),
('marria', '123321', 1),
('jack', '123321', 0),
('anne', '123321', 1);