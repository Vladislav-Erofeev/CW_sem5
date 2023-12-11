-- liquibase formatted sql

-- changeSet Vladislav:1
alter table order_item add column price float;
-- rollback alter table order_item delete column price;