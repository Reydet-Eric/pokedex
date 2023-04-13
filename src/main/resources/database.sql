CREATE DATABASE IF NOT EXISTS pokemon;

USE pokemon;

DROP TABLE IF EXISTS pokemon;

CREATE TABLE pokemon(
    id int PRIMARY KEY NOT NULL AUTO_INCREMENT,
    name varchar(64) not null,
    type varchar(32) not null
);