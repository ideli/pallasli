--drop table t_models;
--drop table t_component_type;
--drop table t_component_type_config;
--drop table t_fields;
--drop table t_field_configs;
--drop table t_user;
 
create table t_user(
	id int primary key,
	name varchar(30) null,
	password varchar(100) null
);
insert into t_user (id,name,password)values(1,'lyt','111111');
--模块分类
create table t_module(
	id int primary key,
	caption varchar(30) null,
	order_num int null,
	parent_id int null,
	version int null
);
insert into t_module(id,caption,order_num,parent_id,version)
values(1,'归集',1,0,1);
insert into t_module(id,caption,order_num,parent_id,version)
values(2,'贷款',1,0,1);
--页面
create table t_page(
	id int primary key,
	caption varchar(30) null,
	path varchar(100) null,
	order_num int null,
	source_name varchar(30) null,
	module_id int null,
	version int null
);
insert into t_page(id,caption,path,order_num,source_name,module_id,version)
values(1,'提取','/designer/bftq',1,'bftq',1,1);


--页面组件类型
create table t_component_type(
	id int primary key,
	caption varchar(30) null,
	name varchar(100) null,
	order_num int null
);
insert into t_component_type(id,name,caption,order_num)
values(1,'text','文本框',1);
insert into t_component_type(id,name,caption,order_num)
values(2,'number','数值框',1);
insert into t_component_type(id,name,caption,order_num)
values(3,'textarea','文本域',1);
insert into t_component_type(id,name,caption,order_num)
values(4,'checkbox','复选框',1);
insert into t_component_type(id,name,caption,order_num)
values(5,'radio','单选框',1);
insert into t_component_type(id,name,caption,order_num)
values(6,'combo','下拉框',1);
insert into t_component_type(id,name,caption,order_num)
values(7,'panel','面板',1);
insert into t_component_type(id,name,caption,order_num)
values(8,'fieldset','字段区域',1);
insert into t_component_type(id,name,caption,order_num)
values(9,'form','表单',1);
insert into t_component_type(id,name,caption,order_num)
values(10,'table','表格',1);
insert into t_component_type(id,name,caption,order_num)
values(11,'column','列',1);
insert into t_component_type(id,name,caption,order_num)
values(12,'tab','标签页',1); 
-- 组件包含的配置项
create table t_component_type_config(
	id int primary key,
	field_type_id int,
	name varchar(100) null,
	caption varchar(30) null,
	data_type varchar(30) null,
	default_value varchar(30) null
);
insert into t_component_type_config(id,field_type_id,name,caption,data_type,default_value)
values(1,1,'name','name','string','');
insert into t_component_type_config(id,field_type_id,name,caption,data_type,default_value)
values(2,1,'width','宽度','int','');
insert into t_component_type_config(id,field_type_id,name,caption,data_type,default_value)
values(3,1,'height','高度','int','');
insert into t_component_type_config(id,field_type_id,name,caption,data_type,default_value)
values(4,7,'width','宽度','string','');
insert into t_component_type_config(id,field_type_id,name,caption,data_type,default_value)
values(5,7,'height','高度','string','');
insert into t_component_type_config(id,field_type_id,name,caption,data_type,default_value)
values(6,7,'lsyout','布局','string','');
--页面组件
create table t_page_component(
	id int primary key,
	name varchar(30) null,
	caption varchar(30) null,
	order_num int null, 
	component_type_Id  int null,
	parent_id int null,
	page_id int null 
);
--页面组件配置
create table t_page_component_config(
	id int primary key,
	config_key varchar(30) null,
	config_value varchar(100) null,
	page_component_id int null
);