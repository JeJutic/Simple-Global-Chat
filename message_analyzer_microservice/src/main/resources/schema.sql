create table if not exists CustomPattern (
  id identity not null unique,
  regexp varchar(100) not null
);
