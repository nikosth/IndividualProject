

CREATE DATABASE `indproj`;

CREATE TABLE `indproj`.`users`(
`id` INT NOT NULL AUTO_INCREMENT,
`username` VARCHAR(25) NOT NULL,
`password` VARCHAR(25) NOT NULL,
`fname` VARCHAR(25) NOT NULL,
`lname` VARCHAR(25) NOT NULL,
`type`    VARCHAR(25) NOT NULL,
PRIMARY KEY (`id`)

);


CREATE TABLE `indproj`.`messages`(
`id` INT NOT NULL AUTO_INCREMENT,
`sender` VARCHAR(25) NOT NULL,
`receiver` VARCHAR(25) NOT NULL,
`data`  VARCHAR(250) NOT NULL,
`date`   date,
PRIMARY KEY (`id`));

