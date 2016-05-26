# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table login_form (
  id                        varchar(255) not null,
  username                  varchar(255),
  password                  varchar(255),
  constraint pk_login_form primary key (id))
;

create table message (
  id                        varchar(255) not null,
  show                      boolean,
  text                      varchar(255),
  classes                   varchar(255),
  constraint pk_message primary key (id))
;

create table task (
  id                        varchar(255) not null,
  contents                  varchar(255),
  constraint pk_task primary key (id))
;

create table user (
  id                        varchar(255) not null,
  username                  varchar(255),
  password                  varchar(255),
  alias                     varchar(255),
  specialties               varchar(255),
  weaknesses                varchar(255),
  constraint pk_user primary key (id))
;

create sequence login_form_seq;

create sequence message_seq;

create sequence task_seq;

create sequence user_seq;




# --- !Downs

SET REFERENTIAL_INTEGRITY FALSE;

drop table if exists login_form;

drop table if exists message;

drop table if exists task;

drop table if exists user;

SET REFERENTIAL_INTEGRITY TRUE;

drop sequence if exists login_form_seq;

drop sequence if exists message_seq;

drop sequence if exists task_seq;

drop sequence if exists user_seq;

