create table if not exists tb_order
(
    id   bigint unsigned auto_increment,
    name varchar(20) not null,
    constraint tb_order_id_uindex
        unique (id)
)
    comment '订单信息表';

alter table tb_order
    add primary key (id);
