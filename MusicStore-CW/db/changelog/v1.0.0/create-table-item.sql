-- liquibase formatted sql

-- changeSet Vladislav:1
create table item(
    id int generated by default as identity primary key,
    title varchar not null,
    description varchar not null,
    body varchar not null,
    count int check ( count > 0 ),
    category varchar not null
);
-- rollback drop table item;