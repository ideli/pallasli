
drop database if exists edu;
create database edu;
use edu;

--编码表;
--用户类型编码;
create table code_user_type(
	code varchar(64) not null primary key,
	caption varchar(64) not null
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE utf8_bin;
--资源类型编码;

create table code_resource_type(
	code varchar(64) not null primary key,
	caption varchar(64) not null,
	description varchar(256) null
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE utf8_bin;




create table user_register(
	id varchar(64) not null primary key,
	user_name varchar(64) not null,
	password varchar(64) not null,
	register_date timestamp(3) not null,
	delete_date timestamp(3) null,
	is_freeze TINYINT default 0
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE utf8_bin;

create unique index idx_user_register_user_name on user_register(user_name);

create table user_login_log(
	id varchar(64) not null primary key,
	login_id varchar(64) not null,
	login_name varchar(64) not null,
	login_ip varchar(32) not null,
	proxy_ip varchar(256) not null,
	login_date timestamp(3) not null,
	logout_date timestamp(3) null
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE utf8_bin;

create index idx_user_login_log_login_id on user_login_log(login_id);

create table user_operation_log(
	id varchar(64) not null primary key,
	login_id varchar(64) not null,
	login_name varchar(64) not null,
	module integer not null,
	function integer not null,
	params varchar(4000) not null
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE utf8_bin;

create index idx_user_operation_log_login_id on user_operation_log(login_id);

create table user_info(
	id varchar(64) not null primary key,
	user_id varchar(64) not null,
	user_caption varchar(64) not null,
	idcards varchar(18) not null,
	sex TINYINT not null,
	birth_place varchar(128) not null,
	qq varchar(20)  null,
	telphone varchar(20)  null,
	mobile varchar(20)  null,
	registered_place varchar(128) not null,
	present_place varchar(128) not null,
	birthday timestamp(3) null
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE utf8_bin;

create unique index idx_user_info_user_id on user_info(user_id);



create table user_type_relation(
	user_id varchar(64) not null,
	user_type_code varchar(64) not null
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE utf8_bin;



--;
create table orgnization_info(
	id varchar(64) not null primary key,
	full_name varchar(256) not null,
	short_name varchar(32) not null,
	address varchar(256) null
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE utf8_bin;

create table page(
	id varchar(64) not null primary key,
	name varchar(64) not null,
	caption varchar(64) not null,
	description varchar(256) null
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE utf8_bin;

create table function(
	id varchar(64) not null primary key,
	name varchar(64) not null,
	caption varchar(64) not null,
	description varchar(256) null
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE utf8_bin;

create table page_function(
	page_id varchar(64) not null,
	function_id varchar(64) not null
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE utf8_bin;


create table resource(
	id varchar(64) not null primary key,
	type_code varchar(64) not null,
	name varchar(64) not null,
	caption varchar(64) not null,
	description varchar(256) null
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE utf8_bin;

create table authority(
	id varchar(64) not null primary key,
	name varchar(64) not null,
	caption varchar(64) not null,
	description varchar(256) null
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE utf8_bin;

create table role(
	id varchar(64) not null primary key,
	name varchar(64) not null,
	caption varchar(64) not null,
	description varchar(256) null
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE utf8_bin;

create table user_role(
	user_id varchar(64) not null,
	role_id varchar(64) not null
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE utf8_bin;

create table role_authority(
	role_id varchar(64) not null,
	authority_id varchar(64) not null
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE utf8_bin;

create table authority_resource(
	authority_id varchar(64) not null,
	resource_id varchar(64) not null
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE utf8_bin;







insert into user_register(id,user_name,password,register_date)
values('000000000000000000000000000000','admin','admin','2016-08-15');
insert into code_user_type(code,caption)
values('admin','管理员');
insert into code_user_type(code,caption)
values('orgnizationManager','机构管理员');
insert into code_user_type(code,caption)
values('teacher','老师');
insert into code_user_type(code,caption)
values('student','学生');
insert into user_type_relation(user_id,user_type_code)
values('000000000000000000000000000000','admin');



insert into code_resource_type(code,caption,description)
values('page','页面','程序代码中存在的较高层次的html');
insert into code_resource_type(code,caption,description)
values('module','模块','程序代码中存在的html');
insert into code_resource_type(code,caption,description)
values('function','功能','html中的element');

commit;


