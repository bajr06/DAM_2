create database hibernate_db;
use hibernate_db;

create table personas(
	id bigint auto_increment primary key,
    nombre varchar(255) not null,
    edad int not null
);
