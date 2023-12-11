-- liquibase formatted sql

-- changeSet Vladislav:2
create table ordered_item (
    order_id int references order_item(id) on delete cascade,
    item_id int references item(id) on delete cascade,
    primary key(order_id, item_id)
)
-- rollback drop table cart;