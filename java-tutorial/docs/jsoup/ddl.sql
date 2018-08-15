create table tb_province_city_county
(
   id                   bigint unsigned not null auto_increment comment '主键id',
   name                 varchar(16) not null default '' comment '城市名称',
   code                 varchar(6) not null default '' comment '城市代码',
   full_spell           varchar(128) not null default '' comment '全拼，北京全拼为beijing',
   easy_spell           varchar(16) not null default '' comment '简拼，北京简拼为bj',
   initial              char(1) not null default '' comment '首字母，北京首字母为b',
   parent_code          varchar(6) not null default '' comment '父级城市代码',
   depth                tinyint unsigned not null default 0 comment '等级：省=1，市=2，县区=3',
   is_del               tinyint unsigned not null default 0 comment '是否删除：0=未删除；1=删除',
   editor               varchar(32) not null default '' comment '编辑人',
   editor_id            bigint unsigned not null default 0 comment '编辑人id',
   edit_time            datetime not null default CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP comment '编辑时间',
   creator              varchar(32) not null default '' comment '创建人',
   creator_id           bigint unsigned not null default 0 comment '创建人id',
   create_time          datetime not null default CURRENT_TIMESTAMP comment '创建时间',
   primary key (id)
);

alter table tb_province_city_county comment '省市县(区)表';