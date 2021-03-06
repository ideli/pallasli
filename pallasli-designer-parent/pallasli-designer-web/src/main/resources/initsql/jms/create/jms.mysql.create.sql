create table schema_version (
    NAME_ varchar(64) not null,
    VALUE_ varchar(300),
    REV_ integer,
    primary key (NAME_)
);

insert into schema_version
values ('schema.version', '1.0.0.0', 1);

create table LOG_OPERATION(
	id int,
	table_name varchar(30) comment '操作的表',
	primary_key_value varchar(30) comment '数据表主键值',
	operation_type int COMMENT '操作类型：1增加2修改3删除4查询',
	content text comment '操作内容',
	operation_user int comment '操作员',
	operation_time datetime comment '操作时间'
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE utf8_bin COMMENT='系统日志表';

create table LOG_SETTING(
	table_name varchar(30) comment '数据表名称',
	business_name int comment '业务名称',
	primary_key varchar(30) comment '数据表主键',
	disabled tinyint comment '是否禁用',
	record_type int comment '记录方式：1全表字段，2部分字段'
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE utf8_bin COMMENT='配置要进行日志记录的表';

create table LOG_SETTING_DETAIL(
	table_name varchar(30) comment '数据表名称',
	column_name varchar(30) comment '要记录日志的列'
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE utf8_bin COMMENT='配置要进行日志记录的表字段';
