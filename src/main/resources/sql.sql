create table sys_role
(
    id        int auto_increment comment '主键'
        primary key,
    role_name varchar(200)  not null comment '角色名称',
    role_code varchar(200)  not null comment '角色编码',
    `desc`    varchar(2000) not null comment '中文描述',
    constraint sys_role_pk
        unique (role_code)
);

