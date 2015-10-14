drop table T_SYS_MENU;
drop table T_APP_MENU;
drop table T_SYS_USER;

create table T_SYS_MENU(
    ID bigint not null auto_increment,
    PROJECT_NAME varchar(255),
    APP_KEY varchar(255),
    MENU_KEY varchar(255),
    PARENT_KEY varchar(255),
    MENU_NAME varchar(255),
    MENU_CAPTION varchar(255),
    URL_PATH varchar(255),
    CHILD_URL_PATH varchar(255),
    MENU_TABLE_NAME varchar(255),
    MENU_WHERE_SQL varchar(255),
    MENU_TYPE_CODE varchar(255),
    PAGE_TYPE_CODE varchar(255),
    MENU_CONFIGS varchar(255),
    VERSION integer,
    primary key (ID)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE utf8_bin;


create table T_APP_MENU(
    ID bigint not null auto_increment,
    PROJECT_NAME varchar(255),
    APP_KEY varchar(255),
    MENU_KEY varchar(255),
    PARENT_KEY varchar(255),
    MENU_NAME varchar(255),
    MENU_CAPTION varchar(255),
    URL_PATH varchar(255),
    CHILD_URL_PATH varchar(255),
    MENU_TABLE_NAME varchar(255),
    MENU_WHERE_SQL varchar(255),
    MENU_TYPE_CODE varchar(255),
    PAGE_TYPE_CODE varchar(255),
    MENU_CONFIGS varchar(255),
    VERSION integer,
    primary key (ID)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE utf8_bin;

create table T_SYS_USER(
    ID bigint not null auto_increment,
    USER_NAME varchar(255),
    USER_CAPTION varchar(255),
    PARENT_KEY varchar(255),
    MENU_NAME varchar(255),
    MENU_CAPTION varchar(255),
    URL_PATH varchar(255),
    CHILD_URL_PATH varchar(255),
    MENU_TABLE_NAME varchar(255),
    MENU_WHERE_SQL varchar(255),
    MENU_TYPE_CODE varchar(255),
    PAGE_TYPE_CODE varchar(255),
    MENU_CONFIGS varchar(255),
    VERSION integer,
    primary key (ID)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE utf8_bin;


set names GBK;
insert into T_SYS_MENU(APP_KEY,MENU_KEY,PARENT_KEY,MENU_NAME,MENU_CAPTION,MENU_TYPE_CODE,VERSION)
values ('pallas_activiti','bpm','','bpm','流程管理','40',1);
insert into T_SYS_MENU(APP_KEY,MENU_KEY,PARENT_KEY,MENU_NAME,MENU_CAPTION,MENU_TYPE_CODE,VERSION)
values ('pallas_activiti','workbanch','','workbanch','我的工作台','40',1);



insert into T_APP_MENU(APP_KEY,MENU_KEY,PARENT_KEY,MENU_NAME,MENU_CAPTION,URL_PATH,MENU_TYPE_CODE,VERSION)
values ('pallas_activiti','progress_user_group','bpm','progress_user_group','用户岗位管理','bpm/progress_user_group','02',1);
insert into T_APP_MENU(APP_KEY,MENU_KEY,PARENT_KEY,MENU_NAME,MENU_CAPTION,URL_PATH,MENU_TYPE_CODE,VERSION)
values ('pallas_activiti','progress_group_user','bpm','progress_group_user','岗位用户管理','bpm/progress_group_user','02',1);
insert into T_APP_MENU(APP_KEY,MENU_KEY,PARENT_KEY,MENU_NAME,MENU_CAPTION,URL_PATH,MENU_TYPE_CODE,VERSION)
values ('pallas_activiti','progress_definition','bpm','progress_definition','流程定义管理','bpm/progress_definition','02',1);
insert into T_APP_MENU(APP_KEY,MENU_KEY,PARENT_KEY,MENU_NAME,MENU_CAPTION,URL_PATH,MENU_TYPE_CODE,VERSION)
values ('pallas_activiti','progress_warning','bpm','progress_warning','流程预警管理','bpm/progress_warning','02',1);
insert into T_APP_MENU(APP_KEY,MENU_KEY,PARENT_KEY,MENU_NAME,MENU_CAPTION,URL_PATH,MENU_TYPE_CODE,VERSION)
values ('pallas_activiti','progress_instance','bpm','progress_instance','流程实例管理','bpm/progress_instance','02',1);
insert into T_APP_MENU(APP_KEY,MENU_KEY,PARENT_KEY,MENU_NAME,MENU_CAPTION,URL_PATH,MENU_TYPE_CODE,VERSION)
values ('pallas_activiti','progress_report','bpm','progress_report','流程报表管理','bpm/progress_report','02',1);
insert into T_APP_MENU(APP_KEY,MENU_KEY,PARENT_KEY,MENU_NAME,MENU_CAPTION,URL_PATH,MENU_TYPE_CODE,VERSION)
values ('pallas_activiti','progress_database','bpm','progress_database','流程数据库管理','bpm/progress_database','02',1);


insert into T_APP_MENU(APP_KEY,MENU_KEY,PARENT_KEY,MENU_NAME,MENU_CAPTION,URL_PATH,MENU_TYPE_CODE,VERSION)
values ('pallas_activiti','task_doing','workbanch','task_doing','我的待办','workbanch/task_doing','02',1);
insert into T_APP_MENU(APP_KEY,MENU_KEY,PARENT_KEY,MENU_NAME,MENU_CAPTION,URL_PATH,MENU_TYPE_CODE,VERSION)
values ('pallas_activiti','task_done','workbanch','task_done','我的已办','workbanch/task_done','02',1);
 
