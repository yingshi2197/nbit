drop table if exists message;

/*==============================================================*/
/* Table: message                                               */
/*==============================================================*/
create table message
(
   id                   varchar(40) not null,
   send_name            varchar(200) comment '发送者名称',
   sender               varchar(200) comment '发送者',
   receiver             varchar(200) comment '接收者，只允许一个',
   copyer               varchar(2000) comment '抄送者，允许多个',
   title                varchar(400) comment '标题',
   content              text comment '内容',
   send_time            datetime comment '发送时间',
   reason               varchar(2000) comment '失败原因',
   status               varchar(1) comment '0表示失败，1表示成功，2表示发送中，3表示待发送',
   type                 varchar(1) comment '0表示邮件，1表示消息,2表示邮件和消息',
   template             varchar(400) comment '模板路径',
   create_user_id       varchar(40) comment '创建人',
   create_time          datetime comment '创建时间',
   last_modify_user_id  varchar(40) comment '最后修改人',
   last_modify_time     datetime comment '最后修改时间',
   delete_flag          varchar(1) comment '删除标志,0表示正常，1表示删除',
   primary key (id)
)
ENGINE=InnoDB DEFAULT CHARSET=utf8;

alter table message comment '消息表';
