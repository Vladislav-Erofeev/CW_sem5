-- liquibase formatted sql

-- changeSet Vladislav:1
create table order_item(
    id int generated by default as identity primary key,
    person_id int not null,
    address varchar,
    order_date date,
    status varchar
)
-- rollback drop table order;