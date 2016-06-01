

--组件初始配置
create table t_page( 
	page_key varchar not null,
	page_caption varchar(50) null,
	page_layout varchar(50) null,
	page_url varchar(200) null
);

--嵌套关系
create table t_relation( 
	page_key int not null,
	page_comp_parent_id int not null,
	page_comp int not null,
	page_comp_order varchar(50) null
);

--组件初始配置
create table t_comp_type( 
	id int primary key,
	comp_key int not null,
	comp_type varchar(50) null,
	comp_config_key varchar(500) null,
	comp_config_default varchar(4000) null
);
--组件初始配置
create table t_comp( 
	id int primary key,
	comp_key int not null,
	comp_type varchar(50) null,
	comp_config_key varchar(500) null,
	comp_config_default varchar(4000) null,
	allow_children BOOLEAN null
);

--组件
create table t_page_component( 
	id int primary key,
	comp_id int not null,
	page_comp_layout varchar(50) null,
	page_comp_children varchar(50) null,
	comp_config varchar(4000) null
);
 





















--组件
create table t_component(
	id int primary key,
	name varchar(50) null,
	caption varchar(50) null,
	class_name varchar(50) null,
	comments  varchar(100) null,
	html varchar(1000) null
);

insert into t_component(id,name,caption,class_name,comments)
values (1,'','面板','','');
insert into t_component(id,name,caption,class_name,comments)
values (2,'','表单','','');
insert into t_component(id,name,caption,class_name,comments)
values (3,'','文本框','','');
insert into t_component(id,name,caption,class_name,comments)
values (4,'','数值框','','');
insert into t_component(id,name,caption,class_name,comments)
values (5,'','下拉框','','');
insert into t_component(id,name,caption,class_name,comments)
values (6,'','搜索框','','');
insert into t_component(id,name,caption,class_name,comments)
values (7,'','表格','',''); 

--布局
create table t_layout(
	id int primary key,
	name varchar(50) null,
	caption varchar(50) null,
	class_name varchar(50) null,
	comments  varchar(100) null,
	c_id int
);

insert into t_layout(id,name,caption,class_name,comments,c_id)
values (1,'','铺满','','',1); 
insert into t_layout(id,name,caption,class_name,comments,c_id)
values (2,'','边框','','',1); 
