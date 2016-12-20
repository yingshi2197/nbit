drop table if exists contact;

drop table if exists customer;

drop table if exists customer_label;

drop table if exists deliver;

drop table if exists dict;

drop table if exists dict_type;

drop table if exists entrant;

drop table if exists evaluate;

drop table if exists experience;

drop table if exists interview;

drop table if exists label;

drop table if exists position;

drop table if exists requirement;

drop table if exists resume;

drop table if exists resume_intention;

drop table if exists resume_label;

drop table if exists resume_position;

drop table if exists user_info;

/*==============================================================*/
/* Table: contact                                               */
/*==============================================================*/
create table contact
(
   id                   varchar(40) not null,
   name                 varchar(200) comment '姓名',
   phone                varchar(200) comment '电话',
   sex                  varchar(40) comment '性别，0表示男，1表示女',
   position_id          varchar(40) comment '职位',
   customer_id          varchar(40) comment '客户',
   description          text comment '描述',
   create_user_id       varchar(40),
   create_time          datetime,
   last_modify_user_id  varchar(40),
   last_modify_time     datetime,
    delete_flag          varchar(1) comment '删除标志,0表示正常，1表示删除',
   primary key (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

alter table contact comment '客户联系人';

/*==============================================================*/
/* Table: customer                                              */
/*==============================================================*/
create table customer
(
   id                   varchar(40) not null,
   name                 varchar(200) comment '名称',
   status               varchar(200) comment '状态，0表示有效，1表示无效',
   fund                 varchar(40) comment '注册资金',
   found_time           datetime comment '成立时间',
   industry             varchar(40) comment '行业，来源数据字典',
   nature               varchar(40) comment '性质，来源数据字典',
   scale                varchar(40) comment '规模，来源数据字典',
   address              varchar(400) comment '地址',
   description          text comment '描述',
   create_user_id       varchar(40),
   create_time          datetime,
   last_modify_user_id  varchar(40),
   last_modify_time     datetime,
    delete_flag          varchar(1) comment '删除标志,0表示正常，1表示删除',
   primary key (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

alter table customer comment '客户';

/*==============================================================*/
/* Table: customer_label                                        */
/*==============================================================*/
create table customer_label
(
   id                   varchar(40) not null,
   customer_id          varchar(200) comment '客户id',
   label_id             varchar(200) comment '标签id',
   create_user_id       varchar(40),
   create_time          datetime,
   last_modify_user_id  varchar(40),
   last_modify_time     datetime,
    delete_flag          varchar(1) comment '删除标志,0表示正常，1表示删除',
   primary key (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

alter table customer_label comment '客户标签关联表';

/*==============================================================*/
/* Table: deliver                                               */
/*==============================================================*/
create table deliver
(
   id                   varchar(40) not null,
   resume_id            varchar(200) comment '简历id',
   requirement_id       varchar(40) comment '需求id',
   deliver_time         datetime comment '投递时间',
   description          text comment '描述',
   create_user_id       varchar(40),
   create_time          datetime,
   last_modify_user_id  varchar(40),
   last_modify_time     datetime,
    delete_flag          varchar(1) comment '删除标志,0表示正常，1表示删除',
   primary key (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

alter table deliver comment '投递（匹配）表';

/*==============================================================*/
/* Table: dict                                                  */
/*==============================================================*/
create table dict
(
   id                   varchar(40) not null,
   name                 varchar(200) comment '名称',
   ename                varchar(400) comment '英文名',
   type_id              varchar(40) comment '字典类型id',
   code                 varchar(40) comment '编码',
   description          text comment '项目描述',
   create_user_id       varchar(40),
   create_time          datetime,
   last_modify_user_id  varchar(40),
   last_modify_time     datetime,
   delete_flag          varchar(1) comment '删除标志,0表示正常，1表示删除',
   primary key (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

alter table dict comment '字典表';

/*==============================================================*/
/* Table: dict_type                                             */
/*==============================================================*/
create table dict_type
(
   id                   varchar(40) not null,
   name                 varchar(200) comment '名称',
   ename                varchar(400) comment '英文名',
   code                 varchar(40) comment '编码',
   description          text comment '项目描述',
   create_user_id       varchar(40),
   create_time          datetime,
   last_modify_user_id  varchar(40),
   last_modify_time     datetime,
   delete_flag          varchar(1) comment '删除标志,0表示正常，1表示删除',
   primary key (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

alter table dict_type comment '字典类型表';

/*==============================================================*/
/* Table: entrant                                               */
/*==============================================================*/
create table entrant
(
   id                   varchar(40) not null,
   resume_id            varchar(40) comment '简历id',
   enter_time           varchar(40) comment '入职时间',
   leave_time           varchar(40) comment '离职时间',
   customer_id         varchar(40) comment '客户id',
   position_id          varchar(40) comment '职位id',
   interview_id         varchar(40) comment '面试id',
   pay                  varchar(40) comment '薪资，加密',
   status               date comment '状态，0表示离职，1表示在职',
   description          text comment '描述',
   create_user_id       varchar(40),
   create_time          datetime,
   last_modify_user_id  varchar(40),
   last_modify_time     datetime,
   delete_flag          varchar(1) comment '删除标志,0表示正常，1表示删除',
   primary key (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

alter table entrant comment '入职记录表';

/*==============================================================*/
/* Table: evaluate                                              */
/*==============================================================*/
create table evaluate
(
   id                   varchar(40) not null,
   resume_id            varchar(200) comment '简历',
   customer_id          varchar(200) comment '客户',
   r_evaluate           text comment '简历的评价',
   c_evaluate           text comment '客户的评价',
   create_user_id       varchar(40),
   create_time          datetime,
   last_modify_user_id  varchar(40),
   last_modify_time     datetime,
    delete_flag          varchar(1) comment '删除标志,0表示正常，1表示删除',
   primary key (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

alter table evaluate comment '评价表';

/*==============================================================*/
/* Table: experience                                            */
/*==============================================================*/
create table experience
(
   id                   varchar(40) not null,
   name                 varchar(200) comment '项目名',
   resume_id            varchar(40) comment '简历id',
   position_id          varchar(40) comment '职位',
   start_time           datetime comment '开始时间',
   end_time             datetime comment '结束时间',
   description          text comment '项目描述',
   duty                 text comment '职责',
   create_user_id       varchar(40),
   create_time          datetime,
   last_modify_user_id  varchar(40),
   last_modify_time     datetime,
    delete_flag          varchar(1) comment '删除标志,0表示正常，1表示删除',
   primary key (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

alter table experience comment '工作经历';

/*==============================================================*/
/* Table: interview                                             */
/*==============================================================*/
create table interview
(
   id                   varchar(40) not null,
   deliver_id           varchar(40) comment '投递id',
   user_id              varchar(40) comment '面试人',
   customer_id          varchar(40) comment '客户id',
   name                 varchar(200) comment '名称',
   time                 datetime comment '面试时间',
   address              varchar(200) comment '面试地址',
   interviewer          varchar(100) comment '面试官',
   result               varchar(40) comment '面试结果，来源于数据字典',
   reason               text comment '面试原因',
   type                 varchar(40) comment '面试类型，来源于数据字典',
   next_time            datetime comment '下次面试时间',
   enter_time           datetime comment '入职日期',
   description          text comment '描述',
   create_user_id       varchar(40),
   create_time          datetime,
   last_modify_user_id  varchar(40),
   last_modify_time     datetime,
    delete_flag          varchar(1) comment '删除标志,0表示正常，1表示删除',
   primary key (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

alter table interview comment '面试表';

/*==============================================================*/
/* Table: "label"                                               */
/*==============================================================*/
create table label
(
   id                   varchar(40) not null,
   name                 varchar(200) comment '名称',
   ename                varchar(200) comment '英文名',
   code                 varchar(10) comment '编码',
   description          text comment '描述',
   create_user_id       varchar(40),
   create_time          datetime,
   last_modify_user_id  varchar(40),
   last_modify_time     datetime,
    delete_flag          varchar(1) comment '删除标志,0表示正常，1表示删除',
   primary key (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

alter table label comment '标签表';

/*==============================================================*/
/* Table: position                                              */
/*==============================================================*/
create table position
(
   id                   varchar(40) not null,
   type                 varchar(40) comment '职位类型，来源数据字典',
   name                 varchar(200) comment '职位名称',
   ename                char(10) comment '职位英文名',
   code                 varchar(40) comment '职位编码',
   description          text comment '描述',
   create_user_id       varchar(40),
   create_time          datetime,
   last_modify_user_id  varchar(40),
   last_modify_time     datetime,
    delete_flag          varchar(1) comment '删除标志,0表示正常，1表示删除',
   primary key (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

alter table position comment '职位表';

/*==============================================================*/
/* Table: requirement                                           */
/*==============================================================*/
create table requirement
(
   id                   varchar(40) not null,
   name                 varchar(200) comment '需求名',
   customer_id          varchar(40) comment '客户id',
   code                 varchar(40) comment '需求编码',
   level                varchar(40) comment '级别，来源数据字典',
   num                  int comment '需求人数',
   period               varchar(40) comment '需求周期，来源数据字典',
   position_id          varchar(40) comment '需求职位',
   duty                 text comment '岗位职责',
   demand               text comment '岗位要求',
   address              varchar(40) comment '地点，来源数据字典',
   description          text comment '岗位描述',
   status          		varchar(40) comment '需求状态，0草稿，1已发布',
   create_user_id       varchar(40),
   create_time          datetime,
   last_modify_user_id  varchar(40),
   last_modify_time     datetime,
    delete_flag          varchar(1) comment '删除标志,0表示正常，1表示删除',
   primary key (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

alter table requirement comment '需求表';

/*==============================================================*/
/* Table: resume                                                */
/*==============================================================*/
create table resume
(
   id                   varchar(40) not null,
   name                 varchar(200) comment '名称',
   position_id          varchar(40) comment '求职岗位',
   years                varchar(40) comment '工作年限，来源数据字典',
   join_time            datetime comment '参加工作时间',
   school               varchar(40) comment '毕业院校',
   finish_time          datetime comment '毕业时间',
   user_id              varchar(40) comment '用户',
   degree               varchar(40) comment '学历，来源数据字典',
   mobile               varchar(40) comment '电话，加密',
   birthday             date comment '出生日期',
   contact              varchar(100) comment '联系人',
   contact_mobile       varchar(40) comment '联系人电话',
   address              varchar(200) comment '住址',
   intention            char(10) comment '意向地区，来源数据字典',
   pay                  varchar(40) comment '期望薪资，来源数据字典',
   native               varchar(100) comment '籍贯，来自数据字典',
   sex                  varchar(40) comment '性别，来源于数据字典',
   description          text comment '描述',
   create_user_id       varchar(40),
   create_time          datetime,
   last_modify_user_id  varchar(40),
   last_modify_time     datetime,
    delete_flag          varchar(1) comment '删除标志,0表示正常，1表示删除',
   primary key (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

alter table resume comment '简历表';

/*==============================================================*/
/* Table: resume_intention                                      */
/*==============================================================*/
create table resume_intention
(
   id                   varchar(40) not null,
   resume_id            varchar(200) comment '简历id',
   intention            varchar(40) comment '意向地区，来源数据字典',
   create_user_id       varchar(40),
   create_time          datetime,
   last_modify_user_id  varchar(40),
   last_modify_time     datetime,
    delete_flag          varchar(1) comment '删除标志,0表示正常，1表示删除',
   primary key (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

alter table resume_intention comment '简历意向地区';

/*==============================================================*/
/* Table: resume_label                                          */
/*==============================================================*/
create table resume_label
(
   id                   varchar(40) not null,
   resume_id            varchar(200) comment '简历id',
   label_id             varchar(200) comment '标签id',
   create_user_id       varchar(40),
   create_time          datetime,
   last_modify_user_id  varchar(40),
   last_modify_time     datetime,
    delete_flag          varchar(1) comment '删除标志,0表示正常，1表示删除',
   primary key (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

alter table resume_label comment '简历标签关联表';

/*==============================================================*/
/* Table: resume_position                                       */
/*==============================================================*/
create table resume_position
(
   id                   varchar(40) not null,
   resume_id            varchar(200) comment '简历id',
   position_id          varchar(40) comment '意向地区，来源数据字典',
   create_user_id       varchar(40),
   create_time          datetime,
   last_modify_user_id  varchar(40),
   last_modify_time     datetime,
    delete_flag          varchar(1) comment '删除标志,0表示正常，1表示删除',
   primary key (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

alter table resume_position comment '简历意向职位';

/*==============================================================*/
/* Table: user_info                                             */
/*==============================================================*/
create table user_info
(
   id                   varchar(40) not null,
   name                 varchar(200) comment '姓名',
   role                 varchar(40) comment '角色，暂时放这',
   phone                varchar(200) comment '电话',
   photo                varchar(400) comment '头像',
   sex                  varchar(40) comment '性别，0表示男，1表示女',
   email                varchar(400) comment '邮箱',
   customer_id          varchar(40) comment '客户',
   login_id             varchar(40) comment '账号',
   password             varchar(512) comment '密码，加密',
   description          text comment '描述',
   create_user_id       varchar(40),
   create_time          datetime,
   last_modify_user_id  varchar(40),
   last_modify_time     datetime,
    delete_flag          varchar(1) comment '删除标志,0表示正常，1表示删除',
   primary key (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

alter table user_info comment '用户信息';
