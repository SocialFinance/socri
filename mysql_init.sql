# Create user, database, tables and shit.
# Command: mysql -u root -p < mysql_init.sql

DROP DATABASE IF EXISTS socri;
CREATE DATABASE socri;
CREATE USER IF NOT EXISTS 'socri' IDENTIFIED BY 'socri';
GRANT ALL ON socri.* TO 'socri'@'%' IDENTIFIED BY 'socri';
GRANT ALL ON socri.* TO 'socri'@'localhost' IDENTIFIED BY 'socri';
FLUSH PRIVILEGES;

USE socri;

CREATE TABLE `hibernate_sequence` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `loans` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `amount` double DEFAULT NULL,
  `backer_id` int(11) DEFAULT NULL,
  `end` bigint(20) DEFAULT NULL,
  `original_amount` double DEFAULT NULL,
  `rate` double DEFAULT NULL,
  `start` bigint(20) DEFAULT NULL,
  `type` varchar(255) DEFAULT NULL,
  `user_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `users` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `alias` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `specialties` varchar(255) DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL,
  `weaknesses` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
