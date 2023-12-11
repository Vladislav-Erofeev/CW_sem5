-- liquibase formatted sql

-- changeSet Vladislav:1
alter table item add column category varchar;
-- rollback drop table item;