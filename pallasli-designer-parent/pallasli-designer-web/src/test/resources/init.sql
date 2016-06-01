--drop table t_models;
--drop table t_field_types;
--drop table t_field_type_configs;
--drop table t_fields;
--drop table t_field_configs;
--drop table t_user;
 
create table t_user(
	id int primary key,
	name varchar(30) null,
	password varchar(100) null
);

--模块菜单
create table t_models(
	id int primary key,
	caption varchar(30) null,
	path varchar(100) null,
	order_num int null,
	source_name varchar(30) null,
	parent_id int null,
	version int null
);
--页面组件类型
create table t_field_types(
	id int primary key,
	caption varchar(30) null,
	name varchar(100) null,
	order_num int null
);
insert into t_field_types(id,name,caption,order_num)
values('1','text','文本框',1);
insert into t_field_types(id,name,caption,order_num)
values('2','number','数值框',1);
insert into t_field_types(id,name,caption,order_num)
values('3','textarea','文本域',1);
insert into t_field_types(id,name,caption,order_num)
values('4','checkbox','复选框',1);
insert into t_field_types(id,name,caption,order_num)
values('5','radio','单选框',1);
insert into t_field_types(id,name,caption,order_num)
values('6','combo','下拉框',1);
insert into t_field_types(id,name,caption,order_num)
values('7','panel','面板',1);
insert into t_field_types(id,name,caption,order_num)
values('8','fieldset','字段区域',1);
insert into t_field_types(id,name,caption,order_num)
values('9','form','表单',1);
insert into t_field_types(id,name,caption,order_num)
values('10','table','表格',1);
insert into t_field_types(id,name,caption,order_num)
values('11','column','列',1);
insert into t_field_types(id,name,caption,order_num)
values('12','tab','标签页',1); 
-- 组件包含的配置项
create table t_field_type_configs(
	id int primary key,
	field_type_id int,
	name varchar(100) null,
	caption varchar(30) null,
	data_type varchar(30) null,
	default_value varchar(30) null
);
insert into t_field_type_configs(id,field_type_id,name,caption,data_type,default_value)
values(1,1,'name','name','string','');
insert into t_field_type_configs(id,field_type_id,name,caption,data_type,default_value)
values(2,1,'width','宽度','int','');
insert into t_field_type_configs(id,field_type_id,name,caption,data_type,default_value)
values(3,1,'height','高度','int','');
insert into t_field_type_configs(id,field_type_id,name,caption,data_type,default_value)
values(4,7,'width','宽度','string','');
insert into t_field_type_configs(id,field_type_id,name,caption,data_type,default_value)
values(5,7,'height','高度','string','');
insert into t_field_type_configs(id,field_type_id,name,caption,data_type,default_value)
values(6,7,'lsyout','布局','string','');
--页面组件
create table t_fields(
	id int primary key,
	caption varchar(30) null,
	path varchar(100) null,
	order_num int null,
	source_name varchar(30) null,
	parent_id int null,
	version int null
);
--页面组件配置
create table t_field_configs(
	id int primary key,
	caption varchar(30) null,
	path varchar(100) null,
	order_num int null,
	source_name varchar(30) null,
	parent_id int null,
	version int null
);