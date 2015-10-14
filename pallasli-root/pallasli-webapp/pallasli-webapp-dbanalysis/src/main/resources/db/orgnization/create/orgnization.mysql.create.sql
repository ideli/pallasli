#说明，id 作为自增标识，无任何含义，仅用于，系统中查找表数据使用，表间外键一律使用有意义的唯一标识
drop table if exists SYS_USER;
drop table if exists SYS_DEPT;
drop table if exists SYS_ROLE;
drop table if exists SYS_RESOURCE;
drop table if exists SYS_DEPT_USER;
drop table if exists SYS_ROLE_USER;
drop table if exists SYS_DEPT_ROLE;
drop table if exists SYS_RESOURCE;
drop table if exists SYS_ORG_CONFIG;

create table SYS_ORG_CONFIG(
	id int not null auto_increment,
	code varchar(3) comment '配置项代码',
	caption varchar(30) comment '配置项名称',
	cfg_value tinyint(1) comment '配置项的值'
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE utf8_bin COMMENT='配置组织结构的设置规则';

insert into SYS_ORG_CONFIG(code,caption,cfg_value)values('001','是否设置岗位',1);
insert into SYS_ORG_CONFIG(code,caption,cfg_value)values('002','新增用户是否默认拥有部门所有权限',0); 

create table SYS_DEPT(
	id int not null auto_increment,
	code varchar(30) comment '部门唯一标识',
	parent_code varchar(30) comment '上级部门唯一标识，为空时为顶级部门'
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE utf8_bin COMMENT='系统组织结构表';

create table SYS_DEPT_USER(
	id int not null auto_increment,
	user_name varchar(30) comment '用户名',
	dept_code varchar(30) comment '部门唯一标识'
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE utf8_bin COMMENT='系统组织用户关系表';

create table SYS_USER(
	id int not null auto_increment,
	name varchar(30) comment '用户名/id号',
	caption varchar(30) comment '真实姓名/昵称' ,
	create_time datetime comment '用户开户时间' ,
	first_time datetime comment '首次激活时间' ,
	last_time datetime comment '上次登录时间',
	status tinyint comment '用户状态：登录，禁用，离线'  
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE utf8_bin COMMENT='系统用户表：用户的创建、修改、登录、禁用日志在日志表中纪录';

create table SYS_ROLE_USER(
	id int not null auto_increment,
	role_name varchar(30) comment '角色名',
	user_name varchar(30) comment '用户名'
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE utf8_bin COMMENT='对于用户按部门分配角色的情况（岗位在系统中不显式存在），在数据库中扔体现为用户和角色的关系，即用户的部门变动的同时变更用户岗位';

create table SYS_ROLE(
	id int not null auto_increment,
	name varchar(30) comment '角色名',
	caption int comment '中文名称',
	primary_key varchar(30) comment '数据表主键',
	disabled tinyint comment '是否禁用',
	record_type int comment '记录方式：1全表字段，2部分字段'
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE utf8_bin COMMENT='岗位/角色表';
 
create table SYS_ROLE_USER(
	id int not null auto_increment,
	role_name varchar(30) comment '角色名',
	user_name varchar(30) comment '用户名'
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE utf8_bin COMMENT='角色/岗位用户关系表';

create table SYS_DEPT_ROLE(
	id int not null auto_increment,
	dept_code varchar(30) comment '部门唯一标识',
	role_name varchar(30) comment '角色名'
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE utf8_bin COMMENT='部门岗位关系表';

create table SYS_authority(
	id int not null auto_increment,
	name varchar(30) comment '角色名',
	caption int comment '中文名称',
	primary_key varchar(30) comment '数据表主键',
	disabled tinyint comment '是否禁用',
	record_type int comment '记录方式：1全表字段，2部分字段'
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE utf8_bin COMMENT='岗位/角色表';
 
create table SYS_ROLE_authority(
	id int not null auto_increment,
	role_name varchar(30) comment '角色名',
	authority_name varchar(30) comment '权限名'
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE utf8_bin COMMENT='角色/岗位用户关系表';

create table SYS_authority_RESOURCE(
	id int not null auto_increment,
	authority_name varchar(30) comment '权限名',
	RESOURCE_name varchar(30) comment '资源名'
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE utf8_bin COMMENT='角色/岗位用户关系表';

create table SYS_RESOURCE(
	id int not null auto_increment,
	name varchar(30) comment '资源名',
	caption varchar(30) comment '中文名称',
	resource_type tinyint comment '资源类型',
	url varchar(30) comment '资源路径'  
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE utf8_bin COMMENT='资源表';
