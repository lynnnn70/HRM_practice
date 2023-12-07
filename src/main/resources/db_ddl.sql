DROP DATABASE IF EXISTS db2;
CREATE DATABASE IF NOT EXISTS db2;
USE db2;

DROP TABLE IF EXISTS `users`;
DROP TABLE IF EXISTS `roles`;
DROP TABLE IF EXISTS `user_roles`;
DROP TABLE IF EXISTS `employee`;
DROP TABLE IF EXISTS `department`;


CREATE TABLE `users` (
                         `user_id` BIGINT NOT NULL AUTO_INCREMENT,
                         `user_name` VARCHAR(50) NOT NULL,
                         `password` VARCHAR(100) NOT NULL,
                         PRIMARY KEY (`user_id`),
                         UNIQUE KEY `user_name` (`password`)
);

CREATE TABLE `roles` (
                         `role_id` BIGINT NOT NULL AUTO_INCREMENT,
                         `role_name` VARCHAR(50) NOT NULL,
                         PRIMARY KEY (`role_id`)
);

CREATE TABLE `user_roles` (
                              `user_id` BIGINT NOT NULL,
                              `role_id` BIGINT NOT NULL,
                              PRIMARY KEY (`user_id`, `role_id`),
                              FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`),
                              FOREIGN KEY (`role_id`) REFERENCES `roles` (`role_id`)
);


CREATE TABLE `department`(
                             `dept_id`BIGINT NOT NULL AUTO_INCREMENT,
                             `dept_name` VARCHAR(10) NOT NULL,
                             `loc` VARCHAR(20) NOT NULL,
                             PRIMARY KEY (`dept_id`)
);

CREATE TABLE `employee`(
                            `emp_id` BIGINT NOT NULL AUTO_INCREMENT,
                            `dept_id`BIGINT NOT NULL,
                            `e_name` VARCHAR(30) NOT NULL,
                            `e_job` VARCHAR(30) NOT NULL,
                            `hire_date` DATE NOT NULL,
                            `sal` DECIMAL NOT NULL,
                            `comm` DECIMAL NOT NULL,
                            PRIMARY KEY (`emp_id`),
                            FOREIGN KEY (`dept_id`) REFERENCES `department`(`dept_id`)
);


