DROP database IF EXISTS vote;
create database vote DEFAULT CHARACTER SET = UTF8;
use vote;

create table t_vote_user(user_id varchar(48) primary key, password varchar(120),name varchar(48), role varchar(10), created_dt datetime);
create table t_option(id int auto_increment primary key, select_id int, full_name varchar(128), short_name varchar(48));
create table t_vote(id int auto_increment primary key, user_id varchar(48), select_id int, option_id int, created_dt datetime);


insert into t_option(select_id, full_name, short_name) values(1, '方案一（三清山+婺源两日两晚）','三清山+婺源');
insert into t_option(select_id, full_name, short_name) values(1, '方案二（黄山+宏村两日两晚）','黄山+宏村');
insert into t_option(select_id, full_name, short_name) values(1, '方案三（千岛湖）','千岛湖');
insert into t_option(select_id, full_name, short_name) values(1, '方案四（嵊泗）','嵊泗');

create table t_outing_register(user_id varchar(48) primary key, name varchar(4), gender char(1), inditify_no varchar(18), phone_no varchar(11), remark varchar(128), created_dt datetime, update_dt datetime);

