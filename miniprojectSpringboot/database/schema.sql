--- drop database if exists
drop schema if exists csfminiproject;

create schema csfminiproject;

use csfminiproject;

create table contacts (
    contact_id int not null AUTO_INCREMENT,
    name varchar(32) not null,
    email varchar(256) not null,
    mobile int not null,
    primary key(contact_id)
);