create table schema_version (
    NAME_ varchar(64) not null,
    VALUE_ varchar(300),
    REV_ integer,
    primary key (NAME_)
);

insert into schema_version
values ('schema.version', '1.0.0.0', 1);
