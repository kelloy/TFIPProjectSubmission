drop database if exists makan app;

create database makanapp;

create table user(
    userId int not null auto_increment,
    username char(64),
    email char(64),
    contactNumber char(16),
    primary key(userId)
);

create table feedbacks(
    feedbackId int not null auto_increment,
    comment text,
    userId int,
    primary key(feedbackId),
    constraint fk_userId
        foreign key(userId)
        references user(userId)
);
