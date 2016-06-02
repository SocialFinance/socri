# Create user and database
# Command: mysql -u root -p < mysql_init.sql

DROP DATABASE IF EXISTS socri;
CREATE DATABASE socri;
CREATE USER IF NOT EXISTS 'socri' IDENTIFIED BY 'socri';
GRANT ALL ON socri.* TO 'socri'@'%' IDENTIFIED BY 'socri';
GRANT ALL ON socri.* TO 'socri'@'localhost' IDENTIFIED BY 'socri';
FLUSH PRIVILEGES;
