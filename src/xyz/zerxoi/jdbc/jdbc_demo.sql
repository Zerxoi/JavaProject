drop database if exists jdbc_demo;

create database jdbc_demo;

use jdbc_demo;

create table t_user (
	`id` int primary key auto_increment,
    `username` varchar(20) not null unique,
    `password` varchar(32) not null,
    `email` varchar(50)
);

insert into t_user(`username`, `password`, `email`) values ('admin', 'password', 'admin@gmail.com'), ('zerxoi', 'password', 'zerxoi@163.com'), ('未来', 'password', 'miraicyan@qq.com');

select * from t_user;

create table account(
	`id` int primary key auto_increment,
    `name` varchar(40),
    `money` float
);

insert into account (`name`, `money`) values('A', 1000), ('B', 1000), ('C', 1000);

select * from account;