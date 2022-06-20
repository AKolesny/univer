CREATE DATABASE json;

\c json

CREATE SCHEMA univer;

CREATE TABLE IF NOT EXISTS univer.groups
(
    id bigint NOT NULL DEFAULT nextval('univer.groups_id_seq'::regclass),
    name text COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT groups_pkey PRIMARY KEY (id)
)

CREATE TABLE IF NOT EXISTS univer.students
(
    id bigint NOT NULL DEFAULT nextval('univer.students_id_seq'::regclass),
    name text COLLATE pg_catalog."default" NOT NULL,
    age bigint NOT NULL,
    score double precision NOT NULL,
    olympic_gamer boolean NOT NULL,
    CONSTRAINT students_pkey PRIMARY KEY (id)
)

CREATE TABLE IF NOT EXISTS univer.groups_students
(
    group_id bigint NOT NULL,
    student_id bigint NOT NULL,
    CONSTRAINT groups_students_student_id_key UNIQUE (student_id),
    CONSTRAINT groups_students_group_id_fkey FOREIGN KEY (group_id)
        REFERENCES univer.groups (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT groups_students_student_id_fkey FOREIGN KEY (student_id)
        REFERENCES univer.students (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)
