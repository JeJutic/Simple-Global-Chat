create table if not exists GlobalMessage (
  id identity,
  text varchar(50) not null,
  author varchar(50) not null
);
