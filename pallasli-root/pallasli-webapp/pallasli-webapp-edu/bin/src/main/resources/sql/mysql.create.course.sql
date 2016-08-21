
create table homework(
	id varchar(64) not null primary key,
	caption varchar(64) not null,
	description varchar(256) null,
	release_date timestamp(3) not null
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE utf8_bin;

create table homework_answer(
	id varchar(64) not null primary key,
	caption varchar(64) not null,
	description varchar(256) null,
	release_date timestamp(3) not null,
	answer_date timestamp(3) not null
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE utf8_bin;

create table cloud_class(
	id varchar(64) not null primary key,
	caption varchar(64) not null,
	class_type int not null,
	grade varchar(64) not null,
	subject varchar(64) not null,
	description varchar(256) null,
	create_date timestamp(3) not null,
	create_user varchar(64) not null,
	owner_orgnization varchar(64) not null,
	sale_begin_date timestamp(3) not null,
	sale_end_date timestamp(3) not null,
	study_mode int not null default 1,
	study_begin_date timestamp(3) not null,
	study_end_date timestamp(3) not null,
	class_state int not null,
	course_state int not null,
	price int default 0,
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE utf8_bin;



create table student_cloud_class(
	id varchar(64) not null primary key,
	cloud_class_id varchar(64) null,
	student_id varchar(64) not null,
	study_mode int not null,
	begin_date timestamp(3) not null,
	end_date timestamp(3) not null
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE utf8_bin;


create table homework_history(
	id varchar(64) not null primary key,
	caption varchar(64) not null,
	description varchar(256) null,
	release_date timestamp(3) not null,
	audit_date timestamp(3) not null
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE utf8_bin;

create table homework_answer_history(
	id varchar(64) not null primary key,
	caption varchar(64) not null,
	description varchar(256) null,
	release_date timestamp(3) not null,
	answer_date timestamp(3) not null,
	audit_date timestamp(3) not null
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE utf8_bin;

create table student_cloud_class_history(
	id varchar(64) not null primary key,
	cloud_class_id varchar(64) null,
	student_id varchar(64) not null,
	study_mode int not null,
	begin_date timestamp(3) not null,
	end_date timestamp(3) not null
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE utf8_bin;




