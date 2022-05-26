	CREATE SCHEMA univer AUTHORIZATION postgres;
	
	CREATE TABLE univer.groups
	(
		id bigserial NOT NULL,
		name text NOT NULL,
		PRIMARY KEY (id)
	);
	
	CREATE TABLE univer.students
	(
    id bigserial NOT NULL,
    name text NOT NULL,
    age bigint NOT NULL,
    score double precision,
    olimpic_gamer boolean,
    PRIMARY KEY (id)
	);
	
	CREATE TABLE univer.groups_and_students
	(
    group_name text NOT NULL,
    id_student bigint NOT NULL
	);